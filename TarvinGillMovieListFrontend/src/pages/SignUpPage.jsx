import { Button, Grid, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import validator from "validator";

const SignUpPage = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
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
    if (
      usernameError === "" &&
      passwordError === "" &&
      emailError === "" &&
      confirmPasswordError === ""
    ) {
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

  const validateConfirmPassword = (value) => {
    setConfirmPassword(value);
    if (password === value) {
      setConfirmPasswordError("");
    } else {
      setConfirmPasswordError("Passwords do not match");
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

  const keyPress = (e) => {
    if (e.keyCode == 13) {
      submitSignUp(e);
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
        <Typography variant="h3">Sign Up</Typography>
      </Grid>
      <Grid item xs={12}>
        <TextField
          onKeyDown={() => keyPress(event)}
          sx={textFieldStyle}
          error={usernameError != ""}
          color={usernameError === "" ? "success" : "error"}
          value={username}
          onChange={(event) => validateUsername(event.target.value)}
          label="username"
          helperText={usernameError}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          onKeyDown={() => keyPress(event)}
          sx={textFieldStyle}
          error={emailError != ""}
          color={emailError === "" ? "success" : "error"}
          value={email}
          onChange={(event) => validateEmail(event.target.value)}
          label="email"
          helperText={emailError}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          onKeyDown={() => keyPress(event)}
          sx={textFieldStyle}
          error={passwordError != ""}
          color={passwordError === "" ? "success" : "error"}
          value={password}
          onChange={(event) => validatePassword(event.target.value)}
          type="password"
          label="password"
          helperText={passwordError}
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          onKeyDown={() => keyPress(event)}
          sx={textFieldStyle}
          error={confirmPasswordError != ""}
          color={confirmPasswordError === "" ? "success" : "error"}
          value={confirmPassword}
          onChange={(event) => validateConfirmPassword(event.target.value)}
          type="password"
          label="confirm password"
          helperText={confirmPasswordError}
        />
      </Grid>
      <Grid item xs={12}>
        <Typography variant="p">
          Got an account? <Link to={"/login"}>Sign in</Link>
        </Typography>
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
