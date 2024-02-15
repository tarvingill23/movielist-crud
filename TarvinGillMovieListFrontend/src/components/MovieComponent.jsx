import {
  ArrowDownwardOutlined,
  ArrowDownwardTwoTone,
  ArrowUpwardOutlined,
  DeleteOutline,
} from "@mui/icons-material";
import "../assets/styles/components/MovieComponent.css";
import Director from "./DirectorComponent";
import Actor from "./ActorComponent";
import { Button, Grid, IconButton, Typography } from "@mui/material";
import RenderStarsComponent from "./RenderStarsComponent";

const Movie = ({ movies, editMode, removeMovie, parseDate, changeRank }) => {
  return movies.map((movie, index) => (
    <div key={movie.movieId} className="movie-div">
      <div>
        <Typography variant="h4">{index + 1}</Typography>
      </div>
      <div>
        {/* Passes the movie object back to parent */}
        {editMode && (
          <Grid justifyContent={"end"} container>
            <Grid item xs={12}>
              <Button onClick={() => removeMovie(movie)}>
                <DeleteOutline />
              </Button>
            </Grid>
          </Grid>
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
      {editMode && (
        <Grid>
          <IconButton onClick={() => changeRank(index, movie, "down")}>
            {index === 0 && movies.length > 1 && <ArrowDownwardTwoTone />}
          </IconButton>
          {index != 0 && index != movies.length - 1 && (
            <Grid>
              <IconButton onClick={() => changeRank(index, movie, "up")}>
                <ArrowUpwardOutlined />
              </IconButton>
              <IconButton onClick={() => changeRank(index, movie, "down")}>
                <ArrowDownwardOutlined />
              </IconButton>
            </Grid>
          )}
          <IconButton onClick={() => changeRank(index, movie, "up")}>
            {index === movies.length - 1 && movies.length > 1 && (
              <ArrowUpwardOutlined />
            )}
          </IconButton>
        </Grid>
      )}
    </div>
  ));
};

export default Movie;
