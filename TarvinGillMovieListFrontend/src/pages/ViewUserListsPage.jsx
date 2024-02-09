import { Grid, Typography } from "@mui/material";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
const ViewUserLists = ({ bearerProp, userMovielistsProp, usernameProp }) => {
  const style = {
    marginTop: "200px",
  };
  return (
    <Grid style={style} container>
      {bearerProp && (
        <Grid xs={12} item>
          <Typography variant="h3">{`${usernameProp} lists`}</Typography>
        </Grid>
      )}
      {!bearerProp && (
        <Grid xs={12} item>
          <Typography variant="h3">{"No Lists To See"}</Typography>
        </Grid>
      )}
      <Grid>
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
