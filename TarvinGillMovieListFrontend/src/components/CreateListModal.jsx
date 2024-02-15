import { Button, Grid, Modal, TextField } from "@mui/material";
import PropTypes from "prop-types";
import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const CreateListModal = ({ createListProp, usernameProp, bearerProp }) => {
  const [openCreateList, setOpenCreateList] = createListProp;
  const handleOpen = () => {
    if (bearerProp) {
      setOpenCreateList(true);
    } else {
      navigate("/login");
    }
  };
  const handleClose = () => setOpenCreateList(false);
  const [title, setTitle] = useState("");
  let navigate = useNavigate();

  const createList = () => {
    const requestOptions = {
      headers: {
        Authorization: bearerProp,
      },
    };
    const apiCreate = "/api/movielists-post";
    const movielist = {
      title,
      user: {
        username: usernameProp,
      },
    };
    axios.post(apiCreate, movielist, requestOptions).then(() => {
      navigate("/mylists", { state: { key: "hello" } });
      handleClose();
    });
  };

  const style = {
    display: "flex",
    padding: "100px",
    backgroundColor: "black",
    opacity: "0.95",
    zIndex: 9999,
  };
  return (
    <>
      <Button
        component={Link}
        to="/"
        onClick={handleOpen}
        sx={{ margin: "6px" }}
      >
        Create new list
      </Button>
      <Modal sx={style} open={openCreateList}>
        <Grid textAlign={"center"} justifyContent={"center"} container>
          <Grid xs={12} item>
            <TextField
              sx={{ width: "600px" }}
              value={title}
              onChange={(event) => setTitle(event.target.value)}
              placeholder="List title"
            ></TextField>
          </Grid>
          <Grid xs={6} item>
            <Button onClick={createList}>Create List</Button>
          </Grid>
          <Grid xs={6} item>
            <Button component={Link} to={-1} onClick={() => handleClose()}>
              Cancel
            </Button>
          </Grid>
        </Grid>
      </Modal>
    </>
  );
};
CreateListModal.propTypes = {
  createListProp: PropTypes.array,
  bearerProp: PropTypes.string,
  usernameProp: PropTypes.string,
};

export default CreateListModal;
