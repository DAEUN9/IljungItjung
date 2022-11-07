import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";

import { getDay } from "@components/Calendar/common/util";
import styles from "@styles/Setting/Setting.module.scss";
import { useState } from "react";

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
      timeTableCellComponent={(props) => {
        const [shade, setShade] = useState(props.isShaded);

        return (
          <WeekView.TimeTableCell
            {...props}
            className={
              shade
                ? `${styles["time-table-cell"]} ${styles["shade"]}`
                : styles["time-table-cell"]
            }
            children={
              <div
                className={styles["cell"]}
                onClick={() => setShade(!shade)}
              />
            }
          />
        );
      }}
    />
  );
}
