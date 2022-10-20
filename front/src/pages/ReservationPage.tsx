import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Reservation/Reservation.module.scss";

const ReservationPage = () => {
  return (
    <div className={styles["reservation-page"]}>
      <Sidebar />
      <div className={styles["content"]}></div>
    </div>
  );
};

export default ReservationPage;
