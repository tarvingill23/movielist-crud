import PropTypes from "prop-types";
const MovieOptionsModal = ({ movies }) => {
  return (
    <div>
      <label htmlFor="movies">Choose a movie:</label>
      <select name="movies" id="movies">
        {movies.map((movie, index) => {
          return (
            <option key={index} value={movie.movieId}>
              {movie.title}
            </option>
          );
        })}
      </select>
      ;
    </div>
  );
};
MovieOptionsModal.propTypes = {
  movies: PropTypes.array.isRequired,
};

export default MovieOptionsModal;
