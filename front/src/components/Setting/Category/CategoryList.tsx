import CustomChip from "@components/common/CustomChip";
import styles from "@styles/Setting/CategoryList.module.scss";

const categories = ["커트", "목욕", "손발톱관리"];

const CategoryList = () => {
  return (
    <div className={styles["category-list"]}>
      <div className={styles["title"]}>
        <h3>카테고리 목록</h3>
        <span>* 최대 15개까지 등록 가능</span>
      </div>
      <div className={styles["content"]}>
        {categories.map((category, index) => (
          <div className={styles["chip"]} key={index}>
            <CustomChip
              label={category}
              onClick={() => {}}
              onDelete={() => {}}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoryList;
