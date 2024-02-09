import { Grid, Typography } from "@mui/material";
import "../assets/styles/pages/ViewUserLists.css";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
const ViewUserLists = ({ userMovielistsProp, usernameProp }) => {
  return (
    <Grid>
      <Typography variant="h3">{`${usernameProp} lists`}</Typography>
      <MovieListCardComponent movieLists={userMovielistsProp} />
    </Grid>
  );
};
ViewUserLists.propTypes = {
  usernameProp: PropTypes.string,
  userMovielistsProp: PropTypes.array,
};
export default ViewUserLists;
