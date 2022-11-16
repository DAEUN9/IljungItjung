import { useDispatch, useSelector } from "react-redux";
import { Divider } from "@mui/material";

import styles from "@styles/Search/SearchItem.module.scss";
import { selectUser } from "@modules/search";
import { SearchState } from "@components/types/types";
import { useEffect } from "react";

const SearchItem = (props: SearchState) => {
  const dispatch = useDispatch();

  useEffect(() => {
    console.log("props", props);
  });

  useEffect(() => {
    console.log("props", props);
  });

  const handleClick = () => {
    dispatch(selectUser({ ...props }));
  };

  return (
    <div className={styles["search-item"]} onClick={handleClick}>
      <div className={styles.content}>
        <div className={styles.img}>
          <img src={props.imagePath} />
        </div>
        <div className={styles.right}>
          <div className={styles.name}>{props.nickname}</div>
          <div className={styles.desc}>{props.introduction}</div>
        </div>
      </div>
      <Divider className={styles.divider} />
    </div>
  );
};

export default SearchItem;
