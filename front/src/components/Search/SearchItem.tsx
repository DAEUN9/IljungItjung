import { useDispatch } from "react-redux";
import { Divider } from "@mui/material";

import styles from "@styles/Search/SearchItem.module.scss";
import { selectUser } from "@modules/search";
import { SearchState } from "@components/types/types";

const SearchItem = (props: SearchState) => {
  const dispatch = useDispatch();
  const onSelectUser = (user: SearchState) => dispatch(selectUser(user));

  const handleClick = () => {
    onSelectUser({ ...props });
  };

  return (
    <div className={styles["search-item"]} onClick={handleClick}>
      <div className={styles.content}>
        <div className={styles.img}>
          <img src={props.imagePath} />
        </div>
        <div className={styles.right}>
          <div className={styles.name}>{props.nickname}</div>
          {/* <div className={styles.desc}>{props.desc}</div> */}
        </div>
      </div>
      <Divider className={styles.divider} />
    </div>
  );
};

export default SearchItem;
