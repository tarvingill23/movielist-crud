import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import {
  Card,
  CardContent,
  CardHeader,
  Grid,
  ImageList,
  ImageListItem,
  Typography,
} from "@mui/material";
import "../assets/styles/components/MovieListComponent.css";

const cardStyle = {
  borderRadius: 20,
  cursor: "pointer",
};

const gridStyle = {
  padding: "0px 20px 0px 20px",
};

const MovieListCardComponent = ({ movieLists, sectionTitle }) => {
  return (
    <Grid style={gridStyle} container spacing={8}>
      <Grid item xs={12}>
        <Typography variant="h2">{sectionTitle}</Typography>
      </Grid>
      {movieLists.map((movieList) => {
        return (
          <Grid item xs={4} key={movieList.listId}>
            <Card variant="outlined" raised style={cardStyle}>
              <Link to={`http://localhost:5173/movielists/${movieList.listId}`}>
                <CardHeader title={movieList.title}></CardHeader>
                <ImageList variant="masonry" cols={3} gap={12}>
                  {movieList.movies.slice(0, 3).map((movie) => (
                    <ImageListItem key={movie.movieId}>
                      <img
                        src={`${movie.posterImage}?w=248&fit=crop&auto=format`}
                        loading="lazy"
                      />
                    </ImageListItem>
                  ))}
                </ImageList>
              </Link>
              <CardContent>{`created by ${movieList.user.username}`}</CardContent>
            </Card>
          </Grid>
        );
      })}
    </Grid>
  );
};
MovieListCardComponent.propTypes = {
  movieLists: PropTypes.array,
  sectionTitle: PropTypes.string,
  bearerProp: PropTypes.string,
};

export default MovieListCardComponent;
