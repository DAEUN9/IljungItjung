import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import CustomButton from "@components/common/CustomButton";
import CustomChip from "@components/common/CustomChip";
import styles from "@styles/Search/SearchResult.module.scss";
import { RootState } from "@modules/index";

const SearchResult = () => {
  const { nickname, email, imagePath, /*desc, detail,*/ categories } =
    useSelector((state: RootState) => state.search);
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/calendar/${nickname}`);
  };

  return (
    <div className={styles["search-result"]}>
      <div className={styles.profile}>
        <div className={styles.img}>
          <img src={imagePath} />
        </div>
        <div className={styles.right}>
          <div className={styles.name}>{nickname}</div>
          <div className={styles.desc}>한줄소개</div>
        </div>
      </div>
      <div className={styles.detail}>설명</div>
      <div className={styles.categories}>
        {categories &&
          categories.length > 0 &&
          categories.map((category, index) => (
            <div className={styles.chip} key={index}>
              <CustomChip label={category.categoryName} onClick={() => {}} />
            </div>
          ))}
      </div>
      <div className={styles.button}>
        <CustomButton
          variant="contained"
          children="바로가기"
          onClick={handleClick}
        />
      </div>
    </div>
  );
};

export default SearchResult;
