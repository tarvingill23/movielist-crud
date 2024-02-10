import { Star } from "@mui/icons-material";
import { PropTypes } from "prop-types";

const RenderStarsComponent = ({ movie }) => {
  const items = [];
  let isGold;
  for (let i = 0; i < 5; i++) {
    i < movie.rating ? (isGold = true) : (isGold = false);
    const dynamicClass = isGold ? "star-gold" : "star";
    items.push(<Star key={i} className={dynamicClass} />);
  }
  return items;
};
RenderStarsComponent.propTypes = {
  movie: PropTypes.object.isRequired,
};

export default RenderStarsComponent;
