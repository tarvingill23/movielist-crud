import "../assets/styles/components/HeaderComponent.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";

const HeaderComponent = ({ bearerProp }) => {
  const navigate = useNavigate();
  const logout = () => {
    bearerProp = "";
    navigate(0);
  };
  return (
    <div className="header">
      <Link className="home-icon" to={"/"}>
        <FontAwesomeIcon icon={faHome} />
      </Link>
      <h1>Movie List Maker</h1>
      <div className="login">
        {bearerProp && (
          <div>
            {/* {user.username} */}
            <button onClick={logout}>Log out</button>
          </div>
        )}
        {!bearerProp && <Link to={"/login"}>Login</Link>}
      </div>
    </div>
  );
};
HeaderComponent.propTypes = {
  bearerProp: PropTypes.string,
};

export default HeaderComponent;
