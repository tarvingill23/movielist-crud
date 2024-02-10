import PropTypes from "prop-types";
import { useState } from "react";
import { AddOutlined, CloseOutlined } from "@mui/icons-material";
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
} from "@mui/material";

const cardStyle = {
  cursor: "pointer",
  maxWidth: 300,
  height: 300,
};
const MovieOptionsModal = ({ movies, addMovie }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Button
            color="primary"
            variant="outlined"
            fullWidth
            onClick={handleOpen}
          >
            <AddOutlined />
          </Button>
        </Grid>
        <Dialog fullScreen open={open} onClose={handleClose}>
          <Toolbar>
            <IconButton onClick={handleClose} aria-label="close">
              <CloseOutlined />
            </IconButton>
            <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
              Movies
            </Typography>
          </Toolbar>
          <Grid container spacing={4}>
            {movies.map((movie) => {
              return (
                <Grid key={movie.movieId} item xs={12}>
                  <Card sx={cardStyle} raised onClick={() => addMovie(movie)}>
                    <CardHeader title={movie.title}></CardHeader>
                    <CardMedia
                      component="img"
                      height="400"
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
};

export default MovieOptionsModal;
