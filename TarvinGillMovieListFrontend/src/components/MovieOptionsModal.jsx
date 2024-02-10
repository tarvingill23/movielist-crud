import PropTypes from "prop-types";
import { AddOutlined, CloseOutlined } from "@mui/icons-material";
import RenderStarsComponent from "./RenderStarsComponent";
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
} from "@mui/material";

const cardStyle = {
  cursor: "pointer",
};
const dialogStyle = {
  padding: "40px",
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
  const handleOpen = (param) => param(true);
  const handleClose = (param) => param(false);

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
          </Toolbar>
          <Grid sx={dialogStyle} direction={"row"} container spacing={6}>
            {movies.map((movie) => {
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
                      component="img"
                      image={movie.posterImage}
                      alt={movie.title}
                    />
                  </Card>
                </Grid>
              );
            })}
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
