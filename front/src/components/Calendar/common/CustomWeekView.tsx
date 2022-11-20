import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";
import { useSelector } from "react-redux";

import styles from "@styles/Calendar/Calendar.module.scss";
import { RootState } from "@modules/index";
import { getDay, getStringFromDate } from "./util";

export default function CustomWeekView() {
  const { blockList, fixedBlockList, lock } = useSelector(
    (state: RootState) => state.mycalendar
  );

  return (
    <WeekView
      startDayHour={9}
      endDayHour={22}
      dayScaleCellComponent={(props) => (
        <WeekView.DayScaleCell
          {...props}
          className="day-scale"
          formatDate={getDay}
        />
      )}
      timeTableCellComponent={(props) => {
        const startDate = props.startDate;
        let isDisabled = false;

        if (startDate) {
          const day = (startDate.getDay() + 6) % 7;
          const time =
            startDate.getHours().toString() + startDate.getMinutes().toString();
          // 블락 고정일 때
          if (lock[day]) {
            isDisabled = fixedBlockList.get(day)?.includes(time) ?? false;
          }
          // 고정이 아닐 때
          else {
            const date = getStringFromDate(startDate) + time;
            isDisabled = blockList?.has(date);
          }
        }

        return (
          <WeekView.TimeTableCell
            {...props}
            className={
              isDisabled
                ? styles["timeTableCell-disabled"]
                : styles.timeTableCell
            }
          />
        );
      }}
    />
  );
}
