import { Remove } from "@mui/icons-material";

import "../assets/styles/components/MovieComponent.css";
import Director from "./DirectorComponent";
import Actor from "./ActorComponent";
import { Button } from "@mui/material";
import RenderStarsComponent from "./RenderStarsComponent";

const Movie = ({ movies, editMode, removeMovie, parseDate }) => {
  return movies.map((movie) => (
    <div key={movie.movieId} className="movie-div">
      <div>
        {/* Passes the movie object back to parent */}
        {editMode && (
          <Button onClick={() => removeMovie(movie)}>
            <Remove />
          </Button>
        )}

        <div className="star-rating">
          <RenderStarsComponent movie={movie} />
        </div>
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
