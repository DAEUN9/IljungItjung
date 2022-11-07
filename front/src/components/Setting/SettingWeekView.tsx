import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";

import { getDay } from "@components/Calendar/common/util";
import styles from "@styles/Calendar/Calendar.module.scss";

export default function SettingWeekView() {
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
        <WeekView.TimeTableCell
          onDoubleClick={() => {
            console.log("cell_double_click", props.startDate);
          }}
          {...props}
          className={styles.timeTableCell}
        />
      )}
    />
  );
}
