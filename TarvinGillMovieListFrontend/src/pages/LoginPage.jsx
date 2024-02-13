import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import PropTypes from "prop-types";

import axios from "axios";
import { TextField, Grid, Button, FormHelperText } from "@mui/material";

const LoginPage = ({ bearerProp, usernameProp }) => {
  // eslint-disable-next-line no-unused-vars
  const [bearer, setBearer] = bearerProp;
  const [username, setUsername] = usernameProp;

  const [errorMessage, setErrorMessage] = useState();

  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  const style = {
    marginTop: 200,
  };
  const textFieldStyle = {
    width: "600px",
  };

  useEffect(() => {
    if (location.state) {
      setUsername(location.state.key);
    }
  });

  const submitted = (event) => {
    const apiLogin = "/api/auth/login";
    event.preventDefault();
    const requestOptions = {
      auth: {
        username: username,
        password: password,
      },
    };
    axios
      .post(apiLogin, {}, requestOptions)
      .then((response) => {
        setBearer("Bearer " + response.data);
        setUsername(username);
        navigate("/");
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setErrorMessage(
            "Incorrect credentials. Please check username or password again"
          );
        } else {
          setErrorMessage("");
        }
      });
  };
  return (
    <Grid justifyContent="center" style={style} container spacing={6}>
      <Grid item xs={12}>
        Login
      </Grid>
      <Grid item>
        <FormHelperText error={errorMessage != ""}>
          {errorMessage}
        </FormHelperText>
      </Grid>
      <Grid item xs={12}>
        <TextField
          sx={textFieldStyle}
          label="username"
          value={username}
          onChange={(event) => setUsername(event.target.value)}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          sx={textFieldStyle}
          type="password"
          label="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </Grid>
      <Grid item xs={12}>
        <Button onClick={submitted}>Login</Button>
      </Grid>
    </Grid>
  );
};
LoginPage.propTypes = {
  bearerProp: PropTypes.array,
  usernameProp: PropTypes.array,
};

export default LoginPage;
