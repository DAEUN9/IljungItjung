import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import { getDay } from './util';
import styles from '@styles/Calendar/Calendar.module.scss';

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
