import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";
import { RiCalendarCheckLine } from "react-icons/ri";
import { IconButton, Tooltip, Zoom } from "@mui/material";

import styles from "@styles/Reservation/Reservation.module.scss";
import "@styles/Reservation/DatePicker.scss";

const Period = () => {
  // default 기간은 오늘로부터 -15일~15일
  const date = new Date();
  const [startDate, setStartDate] = useState<Date>(
    new Date(date.getFullYear(), date.getMonth(), date.getDate() - 15)
  );
  const [endDate, setEndDate] = useState<Date>(
    new Date(date.getFullYear(), date.getMonth(), date.getDate() + 15)
  );

  const tooltip = "기간을 설정해 보세요.";

  return (
    <div className={styles["period"]}>
      <div className={styles["picker"]}>
        <Tooltip title={tooltip} arrow TransitionComponent={Zoom}>
          <IconButton disableTouchRipple>
            <RiCalendarCheckLine />
          </IconButton>
        </Tooltip>
        <DatePicker
          dateFormat="yyyy/MM/dd"
          selected={startDate}
          onChange={(date: Date) => setStartDate(date)}
          selectsStart
          startDate={startDate}
          endDate={endDate}
          locale={ko}
        ></DatePicker>
        <div className={styles["divider"]}>-</div>
        <DatePicker
          dateFormat="yyyy/MM/dd"
          selected={endDate > startDate ? endDate : startDate}
          onChange={(date: Date) => setEndDate(date)}
          selectsEnd
          startDate={startDate}
          endDate={endDate}
          minDate={startDate}
          locale={ko}
        />
      </div>
    </div>
  );
};

export default Period;
