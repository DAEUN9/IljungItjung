import { useEffect } from "react";
import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";

import styles from "@styles/Calendar/Calendar.module.scss";
import { getDay } from "./util";

export default function CustomWeekView() {
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
      timeTableCellComponent={(props) => (
        <WeekView.TimeTableCell {...props} className={styles.timeTableCell} />
      )}
    />
  );
}
