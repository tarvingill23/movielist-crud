import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { EditOutlined, SaveOutlined } from "@mui/icons-material";
import { Button, ButtonGroup, Grid } from "@mui/material";
import PropTypes from "prop-types";
import axios from "axios";

import "../assets/styles/components/MovieListComponent.css";
import Movie from "../components/MovieComponent";
import MovieOptionsModal from "./MovieOptionsModal";
import { LoadingButton } from "@mui/lab";

const MovieListComponent = ({ usernameProp }) => {
  const [movielist, setMovielist] = useState([]);
  const [movies, setMovies] = useState(movielist.movies);
  const [title, setTitle] = useState("");
  const [user, setUser] = useState({});

  const [movieOptions, setMovieOptions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [buttonLoading, setButtonLoading] = useState(false);
  const [showEditIcon, setShowEditIcon] = useState(false);
  const [editingMode, setEditMode] = useState();

  const { listId } = useParams();
  const navigate = useNavigate();

  // Loading individual movie list
  useEffect(() => {
    const apiGet = `/api/movielists/${listId}`;
    axios
      .get(apiGet)
      .then((response) => {
        setMovielist(response.data);
        setMovies(response.data.movies.sort((a, b) => b.rating - a.rating)); // sorts descending by rating by default
        setTitle(response.data.title);
        setUser(response.data.user);
      })
      .then(() => {
        setLoading(false);
      })
      .catch((error) => {
        console.log("Unable to load data", error);
      });
  }, [listId]);

  // Deleting a movielist
  const deleteList = () => {
    const apiDelete = `/api/movielists/${listId}`;
    let text = "Are you sure you want to delete this list";
    if (confirm(text) === true) {
      setButtonLoading(true);
      axios
        .delete(apiDelete)
        .then(() => {
          setButtonLoading(false);
          navigate("/");
        })
        .catch((error) => {
          console.log("Unable to delete list", error);
        });
    }
  };

  // Update a movielist
  const updateList = () => {
    const apiUpdate = "/api/movielists";
    const updatedMovielist = {
      listId,
      title,
      movies,
      user, // never changes
    };
    setButtonLoading(true);
    axios
      .put(apiUpdate, updatedMovielist)
      .then(() => {
        setTimeout(() => {
          setButtonLoading(false);
        }, 1000);
        navigate("/");
        navigate("/mylists");
      })
      .catch((error) => {
        console.log("Unable to update list", error);
      });
  };

  // Remove movie based on movieData object passed by child to parent
  const removeMovie = (childMovie) => {
    let updatedMovies = movies.filter(
      (movie) => movie.movieId != childMovie.movieId
    );
    setMovies(updatedMovies);
  };
  // Add movie based on movieData object passed by child to parent
  const addMovie = (childMovie) => {
    let updatedMovies = movies;
    updatedMovies.push(childMovie);
    setMovies(updatedMovies);
  };

  // Load all movies
  useEffect(() => {
    const apiGetAll = "/api/movies";
    axios
      .get(apiGetAll)
      .then((response) => {
        setMovieOptions(response.data);
      })
      .catch((error) => {
        console.log("Unable to load data", error);
      });
  }, []);

  useEffect(() => {
    // optional chaining to handle undefined and null values
    if (movielist.user?.username === usernameProp) {
      setShowEditIcon(true);
    }
  }, [usernameProp, movielist]);

  // toggle editing mode
  const toggleEditMode = () => {
    setEditMode((editingMode) => !editingMode);
  };

  const parseDate = (date) => {
    const currentDate = new Date(date);
    const options = { year: "numeric", month: "long", day: "numeric" };
    const formattedDate = currentDate.toLocaleDateString("en-AU", options);
    return formattedDate;
  };

  const editingButtons = [
    { id: 1, name: "Delete List", action: deleteList },
    { id: 2, name: "Update List", action: updateList },
    { id: 3, name: "Cancel", action: () => navigate("/") },
  ];

  if (loading) {
    return <div className="list-container">...Loading</div>;
  } else {
    return (
      <div key={movielist.listId} className="list-container">
        {showEditIcon && (
          <Button onClick={toggleEditMode}>
            <EditOutlined />
          </Button>
        )}
        {editingMode && (
          <>
            <div>
              <input
                className="editing-input"
                value={title}
                placeholder={movielist.title}
                onChange={(event) => setTitle(event.target.value)}
              />
            </div>

            <Grid justifyContent={"space-around"} container>
              <ButtonGroup>
                {editingButtons.map((button) => {
                  return (
                    <LoadingButton
                      loading={buttonLoading}
                      loadingPosition="start"
                      startIcon={<SaveOutlined />}
                      key={button.id}
                      onClick={button.action}
                    >
                      {button.name}
                    </LoadingButton>
                  );
                })}
              </ButtonGroup>
            </Grid>
          </>
        )}

        <div>
          <h1>{title}</h1>
          <p>{`created by: ${movielist.user.username}`}</p>
          <p>{`created on: ${parseDate(movielist.dateCreated)}`}</p>
          <p>{`last modified on: ${parseDate(movielist.dateModified)}`}</p>
        </div>

        <div>
          <Movie
            removeMovie={removeMovie}
            editMode={editingMode}
            movies={movies}
            parseDate={parseDate}
          />
        </div>

        <div>
          <MovieOptionsModal movies={movieOptions} addMovie={addMovie} />
        </div>
      </div>
    );
  }
};

MovieListComponent.propTypes = {
  usernameProp: PropTypes.string,
};

export default MovieListComponent;
