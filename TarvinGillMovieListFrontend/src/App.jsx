import "./assets/styles/App.css";
import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import HeaderComponent from "./components/HeaderComponent";
import LoginPage from "./pages/LoginPage";
import { useState } from "react";
import MovieListComponent from "./components/MovieListComponent";
import ViewUserListsPage from "./pages/ViewUserListsPage";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import SignUpPage from "./pages/SignUpPage";

const theme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#FFFFFF",
    },
    secondary: {
      main: "#A89932",
    },
  },
  typography: {
    fontFamily: `"Protest Strike", sans-serif`,
    fontWeightMedium: "400",
    button: {
      textTransform: "none",
    },
  },
  components: {
    MuiButton: {
      defaultProps: {
        variant: "outlined",
      },
    },
    MuiBadge: {
      styleOverrides: {
        badge: {
          color: "white",
        },
      },
    },
  },
});

function App() {
  const [bearer, setBearer] = useState("");
  const [username, setUsername] = useState("");
  const [userMovielists, setUserMovielists] = useState([]);

  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <HeaderComponent usernameProp={username} bearerProp={bearer} />
        <Routes>
          <Route
            path="/"
            element={
              <HomePage
                userMovielistsProp={[userMovielists, setUserMovielists]}
                usernameProp={username}
              />
            }
          />
          <Route
            path="/movielists/:listId"
            element={<MovieListComponent usernameProp={username} />}
          />
          <Route
            path="/login"
            element={
              <LoginPage
                bearerProp={[bearer, setBearer]}
                usernameProp={[username, setUsername]}
              />
            }
          />
          <Route path="/signup" element={<SignUpPage />} />
          {bearer && (
            <Route
              path={"/mylists"}
              element={
                <ViewUserListsPage
                  bearerProp={bearer}
                  userMovielistsProp={userMovielists}
                  usernameProp={username}
                />
              }
            />
          )}
          {!bearer && (
            <Route
              path={"/mylists"}
              element={
                <LoginPage
                  bearerProp={[bearer, setBearer]}
                  usernameProp={[username, setUsername]}
                />
              }
            />
          )}
        </Routes>
      </ThemeProvider>
    </div>
  );
}
export default App;
