import { useDispatch, useSelector } from 'react-redux';
import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import styles from '@styles/Calendar/Calendar.module.scss';
import { getDay } from '@components/Calendar/common/util';
import { setSelectedTime } from '@modules/othercalendar';
import { SchedulerDateTime } from '@components/types/types';
import { RootState } from '@modules/index';

export default function OtherWeekView() {
  const time = useSelector((state: RootState) => state.othercalendar.time);
  const dispatch = useDispatch();

  const handleClick = (startDate: SchedulerDateTime) => {
    dispatch(setSelectedTime({ startDate }));
  };

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
        return (
          <WeekView.TimeTableCell
            {...props}
            className={styles.timeTableCell}
            children={
              <div
                className={
                  time?.startDate === props.startDate
                    ? styles['timeTableCell-selected']
                    : styles['timeTableCell-inner']
                }
                onClick={(e) => {
                  if (props.startDate) {
                    handleClick(props.startDate);
                  }
                }}
              ></div>
            }
          />
        );
      }}
    />
  );
}
