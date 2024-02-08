import { Link } from "react-router-dom";
import PropTypes from "prop-types";

import "../assets/styles/components/MovieListComponent.css";
const MovieListCardComponent = ({ movieLists, sectionTitle }) => {
  return (
    <div className="movie-list-container">
      <h1>{sectionTitle}</h1>
      {movieLists.map((movieList) => {
        return (
          <Link key={movieList.listId} to={`movielists/${movieList.listId}`}>
            {movieList.title}
          </Link>
        );
      })}
    </div>
  );
};
MovieListCardComponent.propTypes = {
  movieLists: PropTypes.array,
  sectionTitle: PropTypes.string,
  bearerProp: PropTypes.string,
};

export default MovieListCardComponent;
