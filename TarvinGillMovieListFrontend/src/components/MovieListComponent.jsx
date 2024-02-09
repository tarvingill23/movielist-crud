import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { EditOutlined } from "@mui/icons-material";
import { Button } from "@mui/material";
import PropTypes from "prop-types";
import axios from "axios";

import "../assets/styles/components/MovieListComponent.css";
import Movie from "../components/MovieComponent";
import MovieOptionsModal from "./MovieOptionsModal";

const MovieListComponent = ({ usernameProp }) => {
  const [movielist, setMovielist] = useState([]);
  const [movies, setMovies] = useState(movielist.movies);
  const [title, setTitle] = useState("");
  const [user, setUser] = useState({});

  const [movieOptions, setMovieOptions] = useState([]);
  const [loading, setLoading] = useState(true);
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
      axios
        .delete(apiDelete)
        .then(() => {
          // console.log(response);
        })
        .then(() => {
          navigate("/");
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  // Update a movielist
  const updateList = () => {
    const apiUpdate = "/api/movielists";
    console.log(listId);
    const updatedMovielist = {
      listId,
      title,
      movies,
      user, // never changes
    };
    console.log(updatedMovielist);
    axios
      .put(apiUpdate, updatedMovielist)
      .then((response) => {
        console.log(response);
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
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
    console.log(updatedMovies);
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

  // useEffect(() => {
  //   movieOptions.filter((movie) =>
  //     movies.some((movie2) => movie.id != movie2.id)
  //   );
  // }, [movies, movieOptions]);

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
            <div className="editing-buttons">
              <button onClick={deleteList} type="submit">
                Delete List
              </button>
              <button onClick={updateList} type="submit">
                Update list
              </button>
              <button onClick={() => navigate(0)} type="submit">
                Cancel
              </button>
            </div>
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
