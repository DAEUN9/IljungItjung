import { useMemo } from "react";
import { useSelector } from "react-redux";
import { FaRegCalendar } from "react-icons/fa";

import styles from "@styles/Calendar/Calendar.module.scss";
import { SchedulerDateTime } from "@components/types/types";
import { RootState } from "@modules/index";

const getFullDate = (date: SchedulerDateTime | undefined) => {
  switch (typeof date) {
    case "undefined":
      return "-";
    case "string":
      return date;
    case "object":
      return (
        date.getFullYear() +
        "년 " +
        (date.getMonth() + 1) +
        "월 " +
        date.getDate() +
        "일"
      );
  }
};

const ReservationDate = () => {
  const { selected } = useSelector((state: RootState) => state.othercalendar);
  const fullDate = useMemo(() => getFullDate(selected?.startDate), [selected]);

  return (
    <div className={styles["reservation-item"]}>
      <div className={styles["icon-short"]}>
        <FaRegCalendar />
      </div>
      {fullDate}
    </div>
  );
};

export default ReservationDate;
