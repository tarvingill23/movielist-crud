import "../assets/styles/pages/ViewUserLists.css";
import MovieListCardComponent from "../components/MovieListCardComponent";
import PropTypes from "prop-types";
const ViewUserLists = ({ userMovielistsProp, usernameProp }) => {
  return (
    <div className="user-list-container">
      {`${usernameProp} lists`}
      <div>
        <MovieListCardComponent movieLists={userMovielistsProp} />
      </div>
    </div>
  );
};
ViewUserLists.propTypes = {
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
  userMovielistsProp: PropTypes.array,
};
export default ViewUserLists;
