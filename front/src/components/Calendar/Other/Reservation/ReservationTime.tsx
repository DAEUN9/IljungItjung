import { useSelector } from "react-redux";
import { FaRegClock } from "react-icons/fa";

import styles from "@styles/Calendar/Calendar.module.scss";
import { RootState } from "@modules/index";
import { formatTime } from "@components/Calendar/common/util";

const ReservationTime = () => {
  const { selected } = useSelector((state: RootState) => state.othercalendar);

  return (
    <div className={styles["reservation-item"]}>
      <div className={styles["icon-short"]}>
        <FaRegClock />
      </div>
      {selected && selected.endDate
        ? formatTime(selected.startDate.toString(), selected.endDate.toString())
        : "-"}
    </div>
  );
};

export default ReservationTime;
