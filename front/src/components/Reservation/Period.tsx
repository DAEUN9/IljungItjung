import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";
import { RiCalendarCheckLine } from "react-icons/ri";
import { IconButton, Tooltip, Zoom } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";

import styles from "@styles/Reservation/Reservation.module.scss";
import "@styles/Reservation/DatePicker.scss";
import { RootState } from "@modules/index";
import { setEndDate, setStartDate } from "@modules/reservation";

const Period = () => {
  const dispatch = useDispatch();
  const { startDate, endDate } = useSelector(
    (state: RootState) => state.reservation
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
          onChange={(date: Date) => dispatch(setStartDate(date))}
          selectsStart
          startDate={startDate}
          endDate={endDate}
          locale={ko}
        ></DatePicker>
        <div className={styles["divider"]}>-</div>
        <DatePicker
          dateFormat="yyyy/MM/dd"
          selected={endDate > startDate ? endDate : startDate}
          selectsEnd
          locale={ko}
        />
      </div>
    </div>
  );
};

export default Period;
