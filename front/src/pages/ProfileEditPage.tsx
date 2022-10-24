import Sidebar from "@components/common/Sidebar";
import styles from "@styles/ProfileEdit/ProfileEdit.module.scss";

const ProfileEditPage = () => {
  return (
    <div className={styles["profile-edit-page"]}>
      <Sidebar />
      <div className={styles["content"]}></div>
    </div>
  );
};

export default ProfileEditPage;
