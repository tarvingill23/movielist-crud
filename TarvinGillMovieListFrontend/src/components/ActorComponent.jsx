import PropTypes from "prop-types";

const Actor = ({ actors }) => {
  return (
    <div>
      <p className="actor-label">
        {actors.length > 1 ? "Actors: " : "Actor: "}
      </p>
      {actors.map((actor, index) => (
        <p key={index} className="actors">
          {`${actor.personnel.firstName} 
          ${
            actor.personnel.middleName === null
              ? ""
              : actor.personnel.middleName
          } ${actor.personnel.lastName}`}
          {index === actors.length - 1 ? "" : ","}
        </p>
      ))}
    </div>
  );
};
Actor.propTypes = {
  actors: PropTypes.array.isRequired,
};
export default Actor;
