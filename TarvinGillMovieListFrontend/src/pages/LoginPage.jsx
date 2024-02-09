import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../assets/styles/pages/LoginPage.css";
import axios from "axios";
import PropTypes from "prop-types";

const LoginPage = ({ bearerProp, usernameProp }) => {
  // eslint-disable-next-line no-unused-vars
  const [bearer, setBearer] = bearerProp;
  const [username, setUsername] = usernameProp;
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const apiLogin = "/api/auth/login";
  const submitted = (e) => {
    e.preventDefault();
    const requestOptions = {
      auth: {
        username: username,
        password: password,
      },
    };
    // console.log(user);
    axios.post(apiLogin, {}, requestOptions).then((response) => {
      setBearer("Bearer " + response.data);
      setUsername(username);
      navigate("/");
    });
  };
  return (
    <div className="login-page-container">
      <div className="login-page-content">
        Login Page
        <form onSubmit={submitted}>
          <input
            value={username}
            onChange={(event) => setUsername(event.target.value)}
            placeholder="username"
          />
          <input
            value={password}
            type="password"
            onChange={(event) => setPassword(event.target.value)}
            placeholder="password"
          />
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
};
LoginPage.propTypes = {
  bearerProp: PropTypes.array,
  usernameProp: PropTypes.array,
};

export default LoginPage;
