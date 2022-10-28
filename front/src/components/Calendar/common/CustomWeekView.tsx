import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import { getDay } from './util';
import styles from '@styles/Calendar/Calendar.module.scss';
import { useEffect } from 'react';

export default function CustomWeekView() {
  useEffect(() => {
    const els = document.getElementsByClassName('Cell-dayView');
    /* 맨처음 or navigator click 이벤트마다 자물쇠 렌더링 */
  }, []);

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
