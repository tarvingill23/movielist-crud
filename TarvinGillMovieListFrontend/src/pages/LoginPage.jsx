import { useState } from "react";
import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";

import axios from "axios";
import { TextField, Grid, Button } from "@mui/material";

const LoginPage = ({ bearerProp, usernameProp }) => {
  // eslint-disable-next-line no-unused-vars
  const [bearer, setBearer] = bearerProp;
  const [username, setUsername] = usernameProp;
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const style = {
    marginTop: 200,
  };

  const textFieldStyle = {
    width: "600px",
  };

  const submitted = (event) => {
    const apiLogin = "/api/auth/login";
    event.preventDefault();
    const requestOptions = {
      auth: {
        username: username,
        password: password,
      },
    };
    axios.post(apiLogin, {}, requestOptions).then((response) => {
      setBearer("Bearer " + response.data);
      setUsername(username);
      navigate("/");
    });
  };
  return (
    <Grid style={style} container spacing={6}>
      <Grid item xs={12}>
        Login
      </Grid>
      <Grid item xs={12}>
        <TextField
          style={textFieldStyle}
          color="primary"
          label="username"
          variant="outlined"
          value={username}
          onChange={(event) => setUsername(event.target.value)}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          style={textFieldStyle}
          type="password"
          label="password"
          variant="outlined"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </Grid>
      <Grid item xs={12}>
        <Button onClick={submitted} variant="outlined">
          Submit
        </Button>
      </Grid>
    </Grid>
  );
};
LoginPage.propTypes = {
  bearerProp: PropTypes.array,
  usernameProp: PropTypes.array,
};

export default LoginPage;
