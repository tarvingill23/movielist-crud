import { Button, Grid, Modal, TextField } from "@mui/material";
import PropTypes from "prop-types";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const CreateListModal = ({ createListProp, usernameProp, bearerProp }) => {
  const [openCreateList, setOpenCreateList] = createListProp;
  const handleOpen = (param) => param(true);
  const handleClose = (param) => param(false);
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
      navigate("/mylists", { state: { key: false } });
      handleClose(setOpenCreateList);
    });
  };

  const style = {
    display: "flex",
    marginTop: "200px",
    backgroundColor: "black",
    zIndex: 9999,
  };
  return (
    <>
      <Button
        onClick={() => handleOpen(setOpenCreateList)}
        sx={{ margin: "6px" }}
      >
        Create new list
      </Button>
      <Modal sx={style} open={openCreateList}>
        <Grid textAlign={"center"} justifyContent={"center"} container>
          <Grid xs={12} item>
            <TextField
              value={title}
              onChange={(event) => setTitle(event.target.value)}
              placeholder="List title"
            ></TextField>
          </Grid>
          <Grid xs={6} item>
            <Button onClick={createList}>Create List</Button>
          </Grid>
          <Grid xs={6} item>
            <Button onClick={() => handleClose(setOpenCreateList)}>
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
