import { useState, useEffect } from "react";
import axios from "axios";
import PropTypes from "prop-types";

import "../assets/styles/pages/HomePage.css";
import MovieListCardComponent from "../components/MovieListCardComponent";

const HomePage = ({ bearerProp, usernameProp }) => {
  const [movielists, setMovielists] = useState([]);
  const [userMovielists, setUserMovielists] = useState([]);
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
  }, [usernameProp]);
  return (
    <div className="home">
      <div>
        <MovieListCardComponent
          movieLists={movielists}
          sectionTitle={"Check out these lists"}
        />
      </div>

      {bearerProp && (
        <div>
          <MovieListCardComponent
            movieLists={userMovielists}
            sectionTitle={`${usernameProp} lists`}
          />
        </div>
      )}
    </div>
  );
};
HomePage.propTypes = {
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
};
export default HomePage;
