import { DeleteOutline } from "@mui/icons-material";
import "../assets/styles/components/MovieComponent.css";
import Director from "./DirectorComponent";
import Actor from "./ActorComponent";
import { Button, Grid, TextField, Typography } from "@mui/material";
import RenderStarsComponent from "./RenderStarsComponent";

const Movie = ({ movies, editMode, removeMovie, parseDate, changeRank }) => {
  return movies.map((movie, index) => (
    <div key={movie.movieId} className="movie-div">
      {!editMode && (
        <div>
          <Typography variant="h4">{index + 1}</Typography>
        </div>
      )}
      {editMode && (
        <div>
          <TextField
            id="rankingTextField"
            onChange={(event) =>
              changeRank(event.target.value, index + 1, movie)
            }
            placeholder={(index + 1).toString()}
            sx={{ width: "50px" }}
          ></TextField>
        </div>
      )}
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
    </div>
  ));
};

export default Movie;
