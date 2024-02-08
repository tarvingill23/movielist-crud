import PropTypes from "prop-types";

import "../assets/styles/pages/MovieListPage.css";
import MovieListComponent from "../components/MovieListComponent";

const MovieListPage = () => {
  return (
    <div>
      <div className="movie-list-page"></div>
      <MovieListComponent editMode={false} />
    </div>
  );
};
MovieListPage.propTypes = {
  movielist: PropTypes.object,
};

export default MovieListPage;
