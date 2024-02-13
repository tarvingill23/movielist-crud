import PropTypes from "prop-types";
import { AddOutlined, CloseOutlined } from "@mui/icons-material";
import RenderStarsComponent from "./RenderStarsComponent";
import { useState } from "react";
import {
  IconButton,
  Grid,
  Button,
  Card,
  CardHeader,
  CardMedia,
  Dialog,
  Toolbar,
  Typography,
  Snackbar,
  TextField,
  InputAdornment,
} from "@mui/material";
import axios from "axios";

const cardStyle = {
  cursor: "pointer",
  width: "400px",
};
const dialogStyle = {
  padding: "20px",
};
const imageStyle = {
  padding: "20px",
};
const addButtonStyle = {
  width: "20%",
  margin: "50px 0 50px 0",
};
const MovieOptionsModal = ({
  movies,
  addMovie,
  openDialogProp,
  snackBarProp,
}) => {
  const [openDialog, setOpenDialog] = openDialogProp;
  const [openSnackbar, setOpenSnackbar] = snackBarProp;
  const [retrievedMovies, setRetrievedMovies] = useState(movies);
  const handleOpen = (param) => param(true);
  const handleClose = (param) => param(false);
  const [searchValue, setSearchValue] = useState("");

  const searchForMovies = () => {
    const apiGetPartialMatch = `/api/movies/search?q=${searchValue}`;
    axios.get(apiGetPartialMatch).then((response) => {
      console.log("ran");
      setRetrievedMovies(response.data);
    });
  };

  const handleInputChange = (event) => {
    setSearchValue(event.target.value);
  };

  const clearSearch = () => {
    setSearchValue("");
    setRetrievedMovies(movies);
  };

  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Button
            color="primary"
            variant="outlined"
            sx={addButtonStyle}
            onClick={() => handleOpen(setOpenDialog)}
          >
            <AddOutlined />
          </Button>
        </Grid>
        <Dialog
          fullScreen
          open={openDialog}
          onClose={() => handleClose(setOpenDialog)}
        >
          <Toolbar>
            <IconButton
              onClick={() => handleClose(setOpenDialog)}
              aria-label="close"
            >
              <CloseOutlined />
            </IconButton>
            <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
              Movies
            </Typography>

            <TextField
              InputProps={{
                endAdornment: (
                  <InputAdornment
                    component={IconButton}
                    sx={{ height: "10px", padding: "10px" }}
                    onClick={clearSearch}
                    position="end"
                  >
                    <CloseOutlined />
                  </InputAdornment>
                ),
              }}
              size="small"
              sx={{ margin: "10px" }}
              placeholder="Search"
              onChange={() => handleInputChange(event)}
              value={searchValue}
            />
            <Button onClick={searchForMovies}>Search</Button>
          </Toolbar>
          <Grid sx={dialogStyle} direction={"row"} container spacing={6}>
            {console.log(retrievedMovies, "Retrieved Movies")}
            {retrievedMovies.length > 0 &&
              retrievedMovies.map((movie) => {
                return (
                  <Grid key={movie.movieId} item xs={4}>
                    <Snackbar
                      open={openSnackbar}
                      autoHideDuration={5000}
                      onClose={() => handleClose(setOpenSnackbar)}
                      message="Movie already exists in the list"
                    />
                    <Card sx={cardStyle} raised onClick={() => addMovie(movie)}>
                      <RenderStarsComponent movie={movie} />
                      <CardHeader subheader={movie.title}></CardHeader>

                      <CardMedia
                        sx={imageStyle}
                        height="520"
                        component="img"
                        image={movie.posterImage}
                        alt={movie.title}
                      />
                    </Card>
                  </Grid>
                );
              })}
            {retrievedMovies.length < 1 && (
              <Grid item xs={4}>
                <Typography variant="h4">No results found</Typography>
              </Grid>
            )}
          </Grid>
        </Dialog>
      </Grid>
    </>
  );
};
MovieOptionsModal.propTypes = {
  movies: PropTypes.array.isRequired,
  addMovie: PropTypes.func,
  openDialogProp: PropTypes.array,
  snackBarProp: PropTypes.array,
};

export default MovieOptionsModal;
