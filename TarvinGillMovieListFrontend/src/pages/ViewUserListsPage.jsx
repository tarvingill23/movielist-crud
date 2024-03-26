import { Grid, Typography } from "@mui/material";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
import { useEffect, useState } from "react";
import axios from "axios";
import CreateListModal from "../components/CreateListModal";

const ViewUserLists = ({ bearerProp, userMovielistsProp, usernameProp }) => {
  const [movielists, setMovielists] = useState(userMovielistsProp);
  const [openCreateList, setOpenCreateList] = useState(false);
  const style = {
    marginTop: "100px",
  };

  useEffect(() => {
    axios.get("api/movielists").then((response) => {
      const filteredMovielists = [];
      response.data.filter((movielist) => {
        if (movielist.user.username === usernameProp) {
          filteredMovielists.push(movielist);
        }
      });
      setMovielists(filteredMovielists);
    });
  }, [userMovielistsProp, usernameProp]);

  return (
    <Grid rowSpacing={6} style={style} container>
      {bearerProp && (
        <Grid xs={12} item>
          <Typography variant="h3">{`${usernameProp} lists`}</Typography>
        </Grid>
      )}
      {bearerProp && movielists.length < 1 && (
        <Grid xs={12} item>
          <CreateListModal
            bearerProp={bearerProp}
            usernameProp={usernameProp}
            createListProp={[openCreateList, setOpenCreateList]}
            buttonTitle={"Create Your First List"}
          />
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
