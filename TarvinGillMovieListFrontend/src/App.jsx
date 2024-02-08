import "./assets/styles/App.css";
import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import HeaderComponent from "./components/HeaderComponent";
// import MovieListPage from "./pages/MovieListPage";
import LoginPage from "./pages/LoginPage";
import { useState } from "react";
import MovieListComponent from "./components/MovieListComponent";
function App() {
  const [bearer, setBearer] = useState("");
  const [username, setUsername] = useState("");

  return (
    <div className="App">
      <HeaderComponent bearerProp={bearer} />
      <Routes>
        <Route
          path="/"
          element={<HomePage usernameProp={username} bearerProp={bearer} />}
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
      </Routes>
    </div>
  );
}
export default App;
