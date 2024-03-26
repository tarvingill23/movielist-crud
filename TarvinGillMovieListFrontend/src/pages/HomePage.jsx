import { useState, useEffect } from "react";
import axios from "axios";
import PropTypes from "prop-types";

import MovieListCardComponent from "../components/MovieListCardComponent";
import { Button, Grid, TextField, Typography } from "@mui/material";

const HomePage = ({ usernameProp, userMovielistsProp }) => {
  const [movielists, setMovielists] = useState([]);
  const [searchValue, setSearchValue] = useState("");
  const [defaultMovielist, setDefaultMovielist] = useState();

  // eslint-disable-next-line no-unused-vars
  const [userMovielists, setUserMovielists] = userMovielistsProp;

  const gridStyle = {
    marginTop: "100px",
  };
  const searchStyle = {
    width: "400px",
  };
  const buttonStyle = {
    margin: "6px",
  };

  const handleSearchValueChange = (event) => {
    setSearchValue(event.target.value);
  };

  useEffect(() => {
    const api = "api/movielists";
    axios.get(api).then((response) => {
      setMovielists(response.data);
      setDefaultMovielist(response.data);
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
  useEffect(() => {
    if (searchValue?.length === 0) {
      setMovielists(defaultMovielist);
    }
  }, [searchValue, defaultMovielist]);

  const keyPress = (e) => {
    if (e.keyCode == 13) {
      searchByTitle();
    }
  };

  return (
    <Grid rowSpacing={4} sx={gridStyle} container>
      <Grid md={12} item xs={12}>
        <TextField
          onKeyDown={() => keyPress(event)}
          onChange={() => handleSearchValueChange(event)}
          value={searchValue}
          sx={searchStyle}
          placeholder="Enter title"
        ></TextField>
        <Button sx={buttonStyle} size="large" onClick={searchByTitle}>
          Search
        </Button>
      </Grid>
      <Grid item xs={12}>
        {movielists && movielists.length > 0 && (
          <MovieListCardComponent
            movieLists={movielists}
            sectionTitle={"Check out these lists"}
          />
        )}
        {movielists && movielists.length < 1 && (
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
