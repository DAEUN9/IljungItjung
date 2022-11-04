import { useDispatch, useSelector } from 'react-redux';
import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';
import cn from 'classnames';

import styles from '@styles/Calendar/Calendar.module.scss';
import { getDay, getStringFromDate } from '@components/Calendar/common/util';
import { SchedulerDateTime } from '@components/types/types';
import { RootState } from '@modules/index';

export default function OtherWeekView() {
  const map = useSelector((state: RootState) => state.othercalendar.map);
  const selected = useSelector(
    (state: RootState) => state.othercalendar.selected
  );
  const dispatch = useDispatch();
  const now = new Date();

  const handleClick = (startDate: SchedulerDateTime) => {};

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
          const str = getStringFromDate(props.startDate);
          const list = map.get(str);

          if (list) {
            for (let item of list) {
              if (
                props.startDate >= new Date(item.startDate) &&
                item.endDate &&
                props.endDate <= new Date(item.endDate)
              ) {
                isDisabled = true;
                break;
              }
            }
          } else if (props.startDate && props.startDate <= now) {
            isDisabled = true;
          }
        }

        return (
          <WeekView.TimeTableCell
            {...props}
            className={styles.timeTableCell}
            children={
              <div
                className={
                  isDisabled
                    ? styles['timeTableCell-disabled']
                    : selected?.startDate === props.startDate
                    ? styles['timeTableCell-selected']
                    : styles['timeTableCell-inner']
                }
                onClick={(e) => {
                  const className = e.currentTarget.className;

                  if (props.startDate && !className.includes('disabled')) {
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
