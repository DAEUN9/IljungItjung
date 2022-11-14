import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";
import { useDispatch, useSelector } from "react-redux";
<<<<<<< HEAD
=======
import { useEffect } from "react";
>>>>>>> front-develop

import {
  getDay,
  getFullStringFromDate,
  makeFormat,
} from "@components/Calendar/common/util";
import styles from "@styles/Setting/Setting.module.scss";
import { RootState } from "@modules/index";
import { deleteLockShade, lockShade, toggleShade } from "@modules/setting";

export default function SettingWeekView() {
  const { lock, set, lockMap } = useSelector(
    (state: RootState) => state.setting
  );
  const dispatch = useDispatch();
  const onToggleShade = (date: string) => dispatch(toggleShade(date));
  const onLockShade = (day: number, time: string) =>
    dispatch(lockShade(day, time));
  const onDeleteLockShade = (day: number, time: string, all?: boolean) =>
    dispatch(deleteLockShade(day, time, all));

<<<<<<< HEAD
=======
  useEffect(() => {}, [lockMap]);

>>>>>>> front-develop
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

          const day = props.startDate.getDay();
          if (lock[(day + 6) % 7]) {
            const list = lockMap.get(day);
            if (list) {
              for (const item of list) {
                const propsTime =
                  makeFormat(props.startDate.getHours().toString()) +
                  makeFormat(props.startDate.getMinutes().toString());
                if (item === propsTime) {
                  isDisabled = true;
                  break;
                }
              }
            }
          }
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

                  if (props.startDate) {
                    const day = props.startDate.getDay();

                    // 해당 요일이 잠금된 상태라면, lockMap에도 추가한다.
                    if (lock[(day + 6) % 7]) {
                      const propsTime =
                        makeFormat(props.startDate.getHours().toString()) +
                        makeFormat(props.startDate.getMinutes().toString());

                      // 이미 lockMap에 존재한다면 지우고, 그렇지 않다면 추가한다.
                      if (lockMap.get(day)?.includes(propsTime))
                        onDeleteLockShade(day, propsTime);
                      else onLockShade(day, propsTime);
                    }
                  }
                }}
              />
            }
          />
        );
      }}
    />
  );
}
