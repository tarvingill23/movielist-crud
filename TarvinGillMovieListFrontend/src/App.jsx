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

const theme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#FFFFFF",
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
                bearerProp={bearer}
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
          <Route
            path="/mylists"
            element={
              <ViewUserListsPage
                userMovielistsProp={userMovielists}
                usernameProp={username}
              />
            }
          />
        </Routes>
      </ThemeProvider>
    </div>
  );
}
export default App;
