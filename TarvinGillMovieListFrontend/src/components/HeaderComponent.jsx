import { AccountCircle, HomeOutlined } from "@mui/icons-material";
import CreateListModal from "./CreateListModal";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";
import { useState } from "react";
import {
  Divider,
  Drawer,
  Grid,
  IconButton,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
  Typography,
} from "@mui/material";

const HeaderComponent = ({ bearerProp, usernameProp }) => {
  const navigate = useNavigate();
  const logout = () => {
    bearerProp = "";
    navigate("/");
    navigate(0); // refresh page to change log in state
  };
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const [openCreateList, setOpenCreateList] = useState(false);

  const toggleDrawer = () => {
    setIsMenuOpen(!isMenuOpen);
  };
  const headerStyle = {
    display: "flex",
    height: "100px",
    width: "100%",
    position: "absolute",
  };

  const listStyle = {
    width: "300px",
  };

  const drawerList = () => (
    <Grid onClick={toggleDrawer}>
      <List disablePadding style={listStyle}>
        <ListItem>
          <ListItemText>
            <Typography variant="p">{usernameProp}</Typography>
          </ListItemText>
        </ListItem>
        <Divider />

        <ListItem component={Link} to={"/mylists"} disablePadding>
          <ListItemButton>
            <ListItemText primary="My Lists" />
          </ListItemButton>
        </ListItem>

        <ListItem disablePadding>
          <ListItemButton onClick={logout}>
            <ListItemText primary="Log Out" />
          </ListItemButton>
        </ListItem>
      </List>
    </Grid>
  );
  return (
    <div style={headerStyle}>
      <Grid columns={12} alignItems="center" container>
        <Grid xs={2} item>
          <IconButton component={Link} to={"/"}>
            <HomeOutlined />
          </IconButton>
        </Grid>
        <Grid xs={bearerProp ? 8 : 7.7} item>
          <Typography variant="h4">Movie List Maker</Typography>
        </Grid>
        <Grid xs={bearerProp ? 2 : "auto"} item>
          <CreateListModal
            bearerProp={bearerProp}
            usernameProp={usernameProp}
            createListProp={[openCreateList, setOpenCreateList]}
          />
          {bearerProp && (
            <>
              <IconButton onClick={toggleDrawer}>
                <AccountCircle />
              </IconButton>
              <Drawer anchor="right" open={isMenuOpen} onClose={toggleDrawer}>
                {drawerList()}
              </Drawer>
            </>
          )}
        </Grid>
        {!bearerProp && (
          <Grid xs="auto" item>
            <Link style={{ margin: "8px" }} to={"/signup"}>
              <Typography variant="p">Sign Up</Typography>
            </Link>

            <Link to={"/login"}>
              <Typography variant="p">Log In</Typography>
            </Link>
          </Grid>
        )}
      </Grid>
    </div>
  );
};
HeaderComponent.propTypes = {
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
};

export default HeaderComponent;
