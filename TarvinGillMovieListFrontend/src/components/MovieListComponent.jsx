import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  ArrowDownwardOutlined,
  ArrowUpwardOutlined,
  EditOutlined,
  SaveOutlined,
} from "@mui/icons-material";
import {
  Button,
  ButtonGroup,
  FormControl,
  Grid,
  MenuItem,
  IconButton,
  Modal,
  Select,
  TextField,
  Typography,
  InputLabel,
  Stack,
  Divider,
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
  const [arrowUp, setArrowUp] = useState(true);

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

  const [sort, setSortValue] = useState("");

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
        setMovies(response.data.movies); // sorts movies based on order of entry in database
        setTitle(response.data.title);
        setUser(response.data.user);
      })
      .then(() => {
        setLoading(false);
      });
  }, [listId]);

  useEffect(() => {
    if (movies) {
      setSelectedIds([...movies].map((movie) => movie.movieId));
    }
  }, [movies]);

  // Deleting a movielist
  const deleteList = () => {
    const apiDelete = `/api/movielists/${listId}`;
    setButtonLoading(true);
    axios.delete(apiDelete).then(() => {
      setButtonLoading(false);
      navigate("/");
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
    axios.put(apiUpdate, updatedMovielist).then(() => {
      setTimeout(() => {
        setButtonLoading(false);
      }, 1000);
      navigate("/");
    });
  };

  // Remove movie based on movieData object passed by child to parent
  const removeMovieFromList = (childMovie) => {
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

  const changeRank = (index, childMovie, direction) => {
    let updatedMovies = [...movies];
    if (direction === "down") {
      updatedMovies.splice(index, 1);
      updatedMovies.splice(index + 1, 0, childMovie);
    }
    if (direction === "up") {
      updatedMovies.splice(index, 1);
      updatedMovies.splice(index - 1, 0, childMovie);
    }
    setMovies(updatedMovies);
  };

  // Load all movies
  useEffect(() => {
    const apiGetAll = "/api/movies";
    axios.get(apiGetAll).then((response) => {
      setMovieOptions(response.data);
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
  // toggle arrow up or down
  const sortDirection = () => {
    setMovies((movies) => [...movies].reverse()); // reverses the array to handle ascending and descending
    setArrowUp((arrowUp) => !arrowUp);
  };

  const parseDate = (date) => {
    const currentDate = new Date(date);
    const options = { year: "numeric", month: "long", day: "numeric" };
    const formattedDate = currentDate.toLocaleDateString("en-AU", options);
    return formattedDate;
  };
  useEffect(() => {
    if (sort === "Default") {
      setMovies(movielist.movies);
    }
    if (sort === "title") {
      setMovies((movies) =>
        [...movies].sort((a, b) => {
          return a.title.localeCompare(b.title);
        })
      );
    }
    if (sort === "runtime") {
      setMovies((movies) =>
        [...movies].sort((a, b) => {
          return a.runtime - b.runtime;
        })
      );
    }
    if (sort === "releaseDate") {
      setMovies((movies) =>
        [...movies].sort((a, b) => {
          return Date.parse(a.releaseDate) - Date.parse(b.releaseDate);
        })
      );
    }
    if (sort === "rating") {
      setMovies((movies) =>
        [...movies].sort((a, b) => {
          return a.rating - b.rating;
        })
      );
    }
    setArrowUp(true); // put arrow up when any sort is chosen as default
  }, [sort, movielist.movies]);

  const handleSort = (event) => {
    setSortValue(event.target.value);
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

  const menuOptions = [
    { name: "User Rank", value: "Default" },
    { name: "Title ", value: "title" },
    { name: "Runtime ", value: "runtime" },
    { name: "Release Date ", value: "releaseDate" },
    { name: "Rating", value: "rating" },
  ];

  if (loading) {
    return <div className="list-container">...Loading</div>;
  }
  return (
    <div key={movielist.listId} className="list-container">
      <Grid justifyContent={"end"} alignItems={"center"} container>
        {showEditIcon && (
          <Grid xs={12} item>
            <IconButton sx={{ margin: "0 20px" }} onClick={toggleEditMode}>
              <EditOutlined />
            </IconButton>
          </Grid>
        )}
      </Grid>
      {!editingMode && (
        <>
          <h1>{title}</h1>
          <Stack
            direction={"row"}
            spacing={2}
            divider={<Divider orientation="vertical" flexItem />}
          >
            <p>{`created by: ${movielist.user.username}`}</p>
            <p>{`created on: ${parseDate(movielist.dateCreated)}`}</p>
            <p>{`last modified on: ${parseDate(movielist.dateModified)}`}</p>
          </Stack>
        </>
      )}
      <Grid
        sx={{ margin: "50px 10px 10px 0px" }}
        alignItems={"center"}
        justifyContent={"center"}
        container
      >
        {!editingMode && movielist.movies.length > 0 && (
          <>
            <Grid xs={2} item>
              <FormControl fullWidth>
                <InputLabel id="sort-label">Sort</InputLabel>
                <Select
                  id="sort-label"
                  onChange={handleSort}
                  label="Sort"
                  value={sort}
                >
                  {menuOptions.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                      {option.name}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid xs="auto" item>
              <IconButton sx={{ justifySelf: "end" }} onClick={sortDirection}>
                {arrowUp ? <ArrowUpwardOutlined /> : <ArrowDownwardOutlined />}
              </IconButton>
            </Grid>
          </>
        )}
      </Grid>
      {editingMode && (
        <Grid spacing={4} justifyContent={"space-around"} container>
          <Grid xs={12} item>
            <div>
              <TextField
                sx={{ width: "600px" }}
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
          {editingMode
            ? "Add your first movie now!"
            : "No movies to see... yet"}
        </Typography>
      )}

      <div>
        <Movie
          removeMovie={removeMovieFromList}
          editMode={editingMode}
          movies={movies}
          parseDate={parseDate}
          changeRank={changeRank}
        />
      </div>
    </div>
  );
};

MovieListComponent.propTypes = {
  usernameProp: PropTypes.string,
};

export default MovieListComponent;
