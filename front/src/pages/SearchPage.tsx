import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Search/Search.module.scss";

const SearchPage = () => {
  return (
    <div className={styles["search-page"]}>
      <Sidebar />
      <div className={styles["content"]}></div>
    </div>
  );
};

export default SearchPage;
