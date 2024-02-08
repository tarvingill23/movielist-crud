import axios from "axios";
const Fetch = (entity) => {
  const api = `/api/${entity}`;
  axios
    .get(api)
    .then((response) => {
      console.log(response.data, "Hello");
      return response.data;
    })
    .catch((error) => {
      console.log("Unable to load data", error);
    });
};

export default Fetch;
