import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Calendar/Calendar.module.scss";

const CalendarPage = () => {
  return (
    <div className={styles["calendar-page"]}>
      <Sidebar />
      <div className={styles["content"]}></div>
    </div>
  );
};

export default CalendarPage;
