import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import {
  Badge,
  Card,
  CardContent,
  CardHeader,
  Grid,
  ImageList,
  ImageListItem,
  Typography,
} from "@mui/material";
import "../assets/styles/components/MovieListComponent.css";

const MovieListCardComponent = ({ movieLists, sectionTitle }) => {
  const cardStyle = {
    borderRadius: 20,
    cursor: "pointer",
  };

  const gridStyle = {
    padding: "0px 60px 0px 60px",
  };

  const showBadge = (date) => {
    const modifiedTime = Date.parse(date);
    const millis = Date.now() - modifiedTime;
    const minutesElapsed = Math.floor(millis / 60000);
    if (minutesElapsed > 10) {
      return true;
    }
  };

  return (
    <Grid style={gridStyle} container spacing={8}>
      <Grid item xs={12}>
        <Typography variant="h2">{sectionTitle}</Typography>
      </Grid>
      {movieLists.map((movieList) => {
        return (
          <Grid item xs={4} key={movieList.listId}>
            <Badge
              invisible={showBadge(movieList.dateModified)}
              badgeContent={
                movieList.dateModified === movieList.dateCreated
                  ? "Recently Created"
                  : "Recently Updated"
              }
              color="secondary"
            >
              <Card
                to={`http://localhost:5173/movielists/${movieList.listId}`}
                component={Link}
                variant="outlined"
                style={cardStyle}
              >
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
                <CardContent>{`created by ${movieList.user.username}`}</CardContent>
              </Card>
            </Badge>
          </Grid>
        );
      })}
    </Grid>
  );
};
MovieListCardComponent.propTypes = {
  movieLists: PropTypes.array,
  sectionTitle: PropTypes.string,
};

export default MovieListCardComponent;
