import TabContent from "@components/Setting/Category/TabContent";
import styles from "@styles/Setting/Setting.module.scss";

const ChangeTab = () => {
  return (
    <div className={styles["tab"]}>
      <TabContent flag={true} />
    </div>
  );
};

export default ChangeTab;
