import { Button, Grid, Modal, TextField } from "@mui/material";
import PropTypes from "prop-types";

const CreateListModal = ({ createListProp }) => {
  const [openCreateList, setOpenCreateList] = createListProp;
  const handleOpen = (param) => param(true);
  const handleClose = (param) => param(false);

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
            <TextField placeholder="List title"></TextField>
          </Grid>
          <Grid xs={6} item>
            <Button onClick={() => handleClose(setOpenCreateList)}>
              Create List
            </Button>
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
};

export default CreateListModal;
