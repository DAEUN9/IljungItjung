import TabContent from "@components/Setting/Category/TabContent";
import styles from "@styles/Setting/Setting.module.scss";

const AddTab = () => {
  return (
    <div className={styles["add-tab"]}>
      <TabContent flag={false} />
    </div>
  );
};

export default AddTab;
