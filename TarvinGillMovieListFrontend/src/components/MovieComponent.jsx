import { Remove, Star } from "@mui/icons-material";

import "../assets/styles/components/MovieComponent.css";
import Director from "./DirectorComponent";
import Actor from "./ActorComponent";

const Movie = ({ movies, editMode, removeMovie, parseDate }) => {
  const renderStars = (movie) => {
    const items = [];
    let isGold;
    for (let i = 0; i < 5; i++) {
      i < movie.rating ? (isGold = true) : (isGold = false);
      const dynamicClass = isGold ? "star-gold" : "star";
      items.push(
        <div key={i} className={dynamicClass}>
          <Star />
        </div>
      );
    }
    return items;
  };

  return movies.map((movie) => (
    <div key={movie.movieId} className="movie-div">
      {console.log(movies)}
      <div>
        {/* Passes the movie object back to parent */}
        {editMode && (
          <button onClick={() => removeMovie(movie)} className="delete-icon">
            <Remove />
          </button>
        )}

        <div className="star-rating">{renderStars(movie)}</div>
        <img className="poster-img" src={movie.posterImage} alt="" />
      </div>
      <div className="gap"></div>
      <div className="content">
        <h1>{movie.title}</h1>
        <p>{movie.description}</p>
        <Director directors={movie.directors} />
        <Actor actors={movie.actors} />
        <p>{parseDate(movie.releaseDate)}</p>
        <p>{movie.runtime} minutes</p>
        <p>{movie.genre}</p>
      </div>
    </div>
  ));
};

export default Movie;
