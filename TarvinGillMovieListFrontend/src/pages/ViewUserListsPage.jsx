import { Grid, Typography } from "@mui/material";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
import { useEffect, useState } from "react";
import axios from "axios";

const ViewUserLists = ({ bearerProp, userMovielistsProp, usernameProp }) => {
  const [movielists, setMovielists] = useState(userMovielistsProp);
  const style = {
    marginTop: "100px",
  };

  useEffect(() => {
    axios.get("api/movielists").then((response) => {
      setMovielists(response.data);
    });
  }, [userMovielistsProp]);

  // useEffect(() => {
  //   navigate(`api/movielists/${}`)
  // })
  return (
    <Grid style={style} container>
      {bearerProp && movielists.length > 1 && (
        <Grid xs={12} item>
          <Typography variant="h3">{`${usernameProp} lists`}</Typography>
        </Grid>
      )}
      {bearerProp && movielists.length < 1 && (
        <Grid xs={12} item>
          <Typography variant="h3">{"No lists to see"}</Typography>
        </Grid>
      )}
      <Grid item xs={12}>
        <MovieListCardComponent movieLists={movielists} />
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
