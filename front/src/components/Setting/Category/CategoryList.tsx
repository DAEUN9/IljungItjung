import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Snackbar } from "@mui/material";

import CustomChip from "@components/common/CustomChip";
import styles from "@styles/Setting/CategoryList.module.scss";
import { delCategory, selectCategory } from "@modules/setting";
import { SettingCategoryState } from "@components/types/types";
import { RootState } from "@modules/index";

const data: SettingCategoryState[] = [
  // { name: "커트", color: "#FF0000", hour: "2", min: "30" },
  // { name: "손발톱관리", color: "#000000", hour: "1", min: "00" },
  // { name: "목욕", color: "#00FF00", hour: "3", min: "00" },
];

const CategoryList = () => {
  const dispatch = useDispatch();

  const { categories, selectedCategory } = useSelector(
    (state: RootState) => state.setting
  );
  const [delSnackbar, setDelSnackbar] = useState(false);

  const handleDeleteCategory = (category: SettingCategoryState) => {
    if (categories.length < 2) {
      setDelSnackbar(true);
    } else {
      if (category.categoryName === selectedCategory.categoryName) {
        dispatch(selectCategory({ categoryName: "", time: "", color: "" }));
      }
      dispatch(delCategory(category));
    }
  };

  return (
    <div className={styles["category-list"]}>
      <div className={styles["title"]}>
        <h3>카테고리 목록</h3>
        <span>* 최대 15개까지 등록 가능</span>
      </div>
      <div className={styles["content"]}>
        {categories.map((category, index) => (
          <div
            className={`${styles["chip"]} ${category.color} chip${category.categoryName}`}
            key={index}
          >
            <CustomChip
              active={
                selectedCategory.categoryName === category.categoryName
                  ? true
                  : false
              }
              label={category.categoryName}
              onClick={() => dispatch(selectCategory(category))}
              onDelete={() => handleDeleteCategory(category)}
            />
          </div>
        ))}
        <Snackbar
          open={delSnackbar}
          autoHideDuration={6000}
          anchorOrigin={{ vertical: "top", horizontal: "right" }}
          onClose={() => setDelSnackbar(false)}
          message="카테고리는 1개 이상 있어야 합니다."
        />
      </div>
    </div>
  );
};

export default CategoryList;
