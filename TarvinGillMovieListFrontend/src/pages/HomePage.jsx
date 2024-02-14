import { useState, useEffect } from "react";
import axios from "axios";
import PropTypes from "prop-types";

import MovieListCardComponent from "../components/MovieListCardComponent";
import { Button, Grid, TextField, Typography } from "@mui/material";

const HomePage = ({ usernameProp, userMovielistsProp }) => {
  const [movielists, setMovielists] = useState([]);
  const [searchValue, setSearchValue] = useState([]);

  // eslint-disable-next-line no-unused-vars
  const [userMovielists, setUserMovielists] = userMovielistsProp;

  const gridStyle = {
    marginTop: "120px",
  };
  const searchStyle = {
    width: "600px",
  };
  const buttonStyle = {
    margin: "6px",
  };

  const handeSearchValueChange = (event) => {
    setSearchValue(event.target.value);
  };

  useEffect(() => {
    const api = "api/movielists";
    axios.get(api).then((response) => {
      setMovielists(response.data);
      setUserMovielists(
        response.data.filter(
          (movielist) => movielist.user.username === usernameProp
        )
      );
    });
  }, [setUserMovielists, usernameProp]);

  const searchByTitle = () => {
    const apiSearch = `api/movielists/search?q=${searchValue}`;
    axios.get(apiSearch).then((response) => {
      setMovielists(response.data);
    });
  };

  return (
    <Grid rowSpacing={4} style={gridStyle} container>
      <Grid item xs={12}>
        <TextField
          onChange={() => handeSearchValueChange(event)}
          value={searchValue}
          sx={searchStyle}
          placeholder="Enter title"
        ></TextField>
        <Button sx={buttonStyle} size="large" onClick={searchByTitle}>
          Search
        </Button>
      </Grid>
      <Grid item xs={12}>
        {movielists.length > 0 && (
          <MovieListCardComponent
            movieLists={movielists}
            sectionTitle={"Check out these lists"}
          />
        )}
        {movielists.length < 1 && (
          <Typography variant="h2">No lists found, try again</Typography>
        )}
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
