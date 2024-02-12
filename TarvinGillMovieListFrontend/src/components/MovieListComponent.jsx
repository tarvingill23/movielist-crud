import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { EditOutlined, SaveOutlined } from "@mui/icons-material";
import {
  Button,
  ButtonGroup,
  Grid,
  IconButton,
  Modal,
  TextField,
  Typography,
} from "@mui/material";
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
  const [selectedIds, setSelectedIds] = useState([]);

  const [openConfirmDelete, setConfirmDelete] = useState(false);
  const handleOpen = (param) => param(true);
  const handleClose = (param) => param(false);

  const [movieOptions, setMovieOptions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [buttonLoading, setButtonLoading] = useState(false);
  const [showEditIcon, setShowEditIcon] = useState(false);
  const [editingMode, setEditMode] = useState();

  const [openDialog, setOpenDialog] = useState(false);
  const [openSnackbar, setOpenSnackbar] = useState(false);

  const { listId } = useParams();
  const navigate = useNavigate();

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };

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

  useEffect(() => {
    if (movies) {
      setSelectedIds(movies.map((movie) => movie.movieId));
    }
  }, [movies]);

  // Deleting a movielist
  const deleteList = () => {
    const apiDelete = `/api/movielists/${listId}`;
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
  };

  // Update a movielist
  const updateList = () => {
    const apiUpdate = "/api/movielists";
    const updatedMovielist = {
      listId,
      title,
      movies,
      user,
    };
    setButtonLoading(true);
    axios
      .put(apiUpdate, updatedMovielist)
      .then(() => {
        setTimeout(() => {
          setButtonLoading(false);
        }, 1000);
        navigate("/");
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
    if (selectedIds.includes(childMovie.movieId)) {
      setOpenSnackbar(true);
    } else {
      const updatedMovies = [childMovie, ...movies];
      setMovies(updatedMovies);
      setOpenDialog(false);
    }
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
  }, [selectedIds]);

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
    {
      id: 1,
      name: "Delete List",
      action: () => handleOpen(setConfirmDelete),
    },
    { id: 2, name: "Update List", action: updateList },
    { id: 3, name: "Cancel", action: () => navigate(-1) },
  ];

  if (loading) {
    return <div className="list-container">...Loading</div>;
  }
  return (
    <div key={movielist.listId} className="list-container">
      <Grid justifyContent={"end"} container>
        <Grid>
          {showEditIcon && (
            <IconButton style={{ margin: "20px" }} onClick={toggleEditMode}>
              <EditOutlined />
            </IconButton>
          )}
        </Grid>
      </Grid>
      {editingMode && (
        <Grid spacing={4} justifyContent={"space-around"} container>
          <Grid xs={12} item>
            <div>
              <TextField
                placeholder={movielist.title}
                value={title}
                onChange={(event) => setTitle(event.target.value)}
              />
            </div>
          </Grid>
          <Grid xs={12} item>
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
            <Modal open={openConfirmDelete}>
              <Grid
                rowSpacing={4}
                textAlign={"center"}
                justifyContent={"center"}
                container
                sx={style}
              >
                <Grid xs={12} item>
                  <Typography variant="p">
                    Are you sure you want to delete this list?
                  </Typography>
                </Grid>
                <Grid xs={6} item>
                  <Button onClick={deleteList}>Yes</Button>
                </Grid>
                <Grid xs={6} item>
                  <Button onClick={() => handleClose(setConfirmDelete)}>
                    No
                  </Button>
                </Grid>
              </Grid>
            </Modal>
          </Grid>
        </Grid>
      )}
      {!editingMode && (
        <Grid direction={"column"} container>
          <Grid>
            <h1>{title}</h1>
          </Grid>
          <Grid>
            <p>{`created by: ${movielist.user.username}`}</p>
          </Grid>
          <Grid>
            <p>{`created on: ${parseDate(movielist.dateCreated)}`}</p>
          </Grid>
          <Grid>
            <p>{`last modified on: ${parseDate(movielist.dateModified)}`}</p>
          </Grid>
        </Grid>
      )}
      {editingMode && (
        <MovieOptionsModal
          openDialogProp={[openDialog, setOpenDialog]}
          snackBarProp={[openSnackbar, setOpenSnackbar]}
          movies={movieOptions}
          addMovie={addMovie}
        />
      )}
      {movies.length === 0 && (
        <Typography sx={{ margin: "100px 0 100px 0" }} variant="h5">
          Add your first movie now!
        </Typography>
      )}
      <div>
        <Movie
          removeMovie={removeMovie}
          editMode={editingMode}
          movies={movies}
          parseDate={parseDate}
        />
      </div>
    </div>
  );
};

MovieListComponent.propTypes = {
  usernameProp: PropTypes.string,
};

export default MovieListComponent;
