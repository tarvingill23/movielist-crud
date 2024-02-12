import { useState, useEffect } from "react";
import axios from "axios";
import PropTypes from "prop-types";

import MovieListCardComponent from "../components/MovieListCardComponent";
import { Grid } from "@mui/material";

const HomePage = ({ usernameProp, userMovielistsProp }) => {
  const [movielists, setMovielists] = useState([]);

  // eslint-disable-next-line no-unused-vars
  const [userMovielists, setUserMovielists] = userMovielistsProp;

  const gridStyle = {
    marginTop: "120px",
  };

  useEffect(() => {
    const api = "api/movielists";
    axios
      .get(api)
      .then((response) => {
        setMovielists(response.data);
        setUserMovielists(
          response.data.filter(
            (movielist) => movielist.user.username === usernameProp
          )
        );
      })
      .catch((error) => {
        console.log("Unable to load data", error);
      });
  }, [setUserMovielists, usernameProp, userMovielistsProp]);
  return (
    <Grid style={gridStyle} container>
      <Grid item xs={12}>
        <MovieListCardComponent
          movieLists={movielists}
          sectionTitle={"Check out these lists"}
        />
      </Grid>
    </Grid>
  );
};
HomePage.propTypes = {
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
  userMovielistsProp: PropTypes.array,
};
export default HomePage;
