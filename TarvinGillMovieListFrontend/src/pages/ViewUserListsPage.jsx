import { Grid, Typography } from "@mui/material";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
const ViewUserLists = ({ bearerProp, userMovielistsProp, usernameProp }) => {
  const style = {
    marginTop: "100px",
  };
  return (
    <Grid style={style} container>
      {bearerProp && userMovielistsProp.length > 1 && (
        <Grid xs={12} item>
          <Typography variant="h3">{`${usernameProp} lists`}</Typography>
        </Grid>
      )}
      {bearerProp && userMovielistsProp.length < 1 && (
        <Grid xs={12} item>
          <Typography variant="h3">{"No lists to see"}</Typography>
        </Grid>
      )}
      <Grid item xs={12}>
        <MovieListCardComponent movieLists={userMovielistsProp} />
      </Grid>
    </Grid>
  );
};
ViewUserLists.propTypes = {
  usernameProp: PropTypes.string,
  bearerProp: PropTypes.string,
  userMovielistsProp: PropTypes.array,
};
export default ViewUserLists;
