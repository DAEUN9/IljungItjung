import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";
import { useDispatch, useSelector } from "react-redux";

import {
  getDay,
  getFullStringFromDate,
} from "@components/Calendar/common/util";
import styles from "@styles/Setting/Setting.module.scss";
import { RootState } from "@modules/index";
import { toggleShade } from "@modules/setting";

export default function SettingWeekView() {
  const { set } = useSelector((state: RootState) => state.setting);
  const dispatch = useDispatch();
  const onToggleShade = (date: string) => dispatch(toggleShade(date));

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
        let isDisabled = false;

        if (props.startDate && props.endDate) {
          isDisabled = set.has(getFullStringFromDate(props.startDate));
        }

        return (
          <WeekView.TimeTableCell
            {...props}
            className={styles["time-table-cell"]}
            children={
              <div
                className={
                  isDisabled
                    ? `${styles["cell"]} ${styles["shade"]}`
                    : styles["cell"]
                }
                onClick={() => {
                  const date = getFullStringFromDate(props.startDate as Date);
                  onToggleShade(date);
                }}
              />
            }
          />
        );
      }}
    />
  );
}
