import PropTypes from "prop-types";

const Director = ({ directors }) => {
  return (
    <div>
      <p className="director-label">
        {directors.length > 1 ? "Directors: " : "Director: "}
      </p>
      {directors.map((director, index) => (
        <p key={index} className="directors">
          {`${director.personnel.firstName} 
          ${
            director.personnel.middleName === null
              ? ""
              : director.personnel.middleName
          } ${director.personnel.lastName}`}
          {index === directors.length - 1 ? "" : ", "}
        </p>
      ))}
    </div>
  );
};
Director.propTypes = {
  directors: PropTypes.array.isRequired,
};
export default Director;
