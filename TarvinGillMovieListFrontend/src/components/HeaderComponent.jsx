import "../assets/styles/components/HeaderComponent.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";

const HeaderComponent = ({ bearerProp, usernameProp }) => {
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
            <div className="dropdown">
              <span>{usernameProp}</span>
              <div className="dropdown-content">
                <Link to={"/mylists"}>
                  <button>View lists</button>
                </Link>
                <button>Create a list</button>
                <button onClick={logout}>Log Out</button>
              </div>
            </div>
          </div>
        )}
        {!bearerProp && <Link to={"/login"}>Log In</Link>}
      </div>
    </div>
  );
};
HeaderComponent.propTypes = {
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
};

export default HeaderComponent;
