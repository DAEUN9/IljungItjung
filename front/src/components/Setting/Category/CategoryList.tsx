import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Snackbar } from "@mui/material";

import CustomChip from "@components/common/CustomChip";
import styles from "@styles/Setting/CategoryList.module.scss";
import { addCategory, delCategory, setCategory } from "@modules/setting";
import { CategoryState } from "@components/types/types";
import { RootState } from "@modules/index";

const data: CategoryState[] = [
  // { name: "커트", color: "#FF0000", hour: "2", min: "30" },
  // { name: "손발톱관리", color: "#000000", hour: "1", min: "00" },
  // { name: "목욕", color: "#00FF00", hour: "3", min: "00" },
];

const CategoryList = () => {
  const categories = useSelector(
    (state: RootState) => state.setting.categories
  );

  const dispatch = useDispatch();
  const onSetCategory = (categories: CategoryState[]) =>
    dispatch(setCategory(categories));
  const onAddCategory = (category: CategoryState) =>
    dispatch(addCategory(category));
  const onDelCategory = (category: CategoryState) =>
    dispatch(delCategory(category));

  const [delSnackbar, setDelSnackbar] = useState(false);

  // const colorRef = useRef<HTMLDivElement[]>([]);

  useEffect(() => {
    if (data.length > 0) onSetCategory(data);
    // colorRef.current.map((curr) => {
    //   const index = curr.className.lastIndexOf("#");
    //   const color = curr.className.substring(index);
    //   const el = curr.childNodes[0] as HTMLDivElement;
    //   el.style.backgroundColor = color;
    // });
  }, []);

  const handleDeleteCategory = (category: CategoryState) => {
    if (categories.length < 2) {
      setDelSnackbar(true);
    } else {
      onDelCategory(category);
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
            className={`${styles["chip"]} ${category.color}`}
            key={index}
            // ref={(el) => (colorRef.current[index] = el as HTMLDivElement)}
          >
            <CustomChip
              label={category.name}
              onClick={() => {}}
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
