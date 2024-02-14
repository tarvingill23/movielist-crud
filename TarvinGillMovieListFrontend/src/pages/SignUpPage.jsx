import { Button, Grid, TextField } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import validator from "validator";

const SignUpPage = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [passwordError, setPasswordError] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");

  let navigate = useNavigate();

  const style = {
    marginTop: "200px",
  };

  const textFieldStyle = {
    width: "600px",
  };

  const enableButton = () => {
    if (usernameError === "" && passwordError === "" && emailError === "") {
      if (username != "" && password != "" && email != "") {
        return false;
      }
    }
    return true;
  };

  const validatePassword = (value) => {
    setPassword(value);
    if (
      validator.isStrongPassword(value, {
        minLength: 8,
        minUppercase: 1,
        minNumbers: 1,
        minSymbols: 1,
      })
    ) {
      setPasswordError("");
    } else {
      setPasswordError(
        "Password must beat least 8 characters and contain 1 uppercase letter, 1 symbol and 1 number"
      );
    }
  };

  const validateUsername = (value) => {
    setUsername(value);
    if (!validator.isAlphanumeric(value)) {
      setUsernameError("Username can only contain letters and numbers");
    } else if (value.length < 3 || value.length > 16) {
      setUsernameError("Username must be between 3-16 characters");
    } else setUsernameError("");
  };

  const validateEmail = (value) => {
    setEmail(value);
    if (!validator.isEmail(value)) {
      setEmailError("Not a valid email");
    } else {
      setEmailError("");
    }
  };
  const submitSignUp = (event) => {
    event.preventDefault();
    const apiPostUser = "/api/users";
    const user = {
      username,
      email,
      password,
    };
    setUsernameError("");
    axios
      .post(apiPostUser, user)
      .then(() => {
        navigate("/login", { state: { key: username } });
      })
      .catch((error) => {
        if (
          error.response.data.message ===
          `User with email ${email} already exists, please login with your associated username`
        ) {
          setEmailError(error.response.data.message);
        }
        if (
          error.response.data.message ===
          `${username} has been taken, please type in another username`
        ) {
          setUsernameError(error.response.data.message);
        }
      });
  };
  return (
    <Grid style={style} container spacing={6}>
      <Grid item xs={12}>
        Sign Up
      </Grid>
      <Grid item xs={12}>
        <TextField
          sx={textFieldStyle}
          error={usernameError != ""}
          value={username}
          onChange={(event) => validateUsername(event.target.value)}
          label="username"
          helperText={usernameError}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          sx={textFieldStyle}
          error={emailError != ""}
          value={email}
          onChange={(event) => validateEmail(event.target.value)}
          label="email"
          helperText={emailError}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          sx={textFieldStyle}
          error={passwordError != ""}
          value={password}
          onChange={(event) => validatePassword(event.target.value)}
          type="password"
          label="password"
          helperText={passwordError}
        />
      </Grid>
      <Grid item xs={12}>
        <Button onClick={() => submitSignUp(event)} disabled={enableButton()}>
          Submit
        </Button>
      </Grid>
    </Grid>
  );
};

export default SignUpPage;
