import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Setting/Setting.module.scss";

const SettingPage = () => {
  return (
    <div className={styles["setting-page"]}>
      <Sidebar />
      <div className={styles["content"]}></div>
    </div>
  );
};

export default SettingPage;
