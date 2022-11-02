import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import { getDay } from '@components/Calendar/common/util';
import styles from '@styles/Calendar/Calendar.module.scss';


export default function OtherWeekView() {
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
        console.log(props);
        return (
          <WeekView.TimeTableCell {...props} className={styles['other-timeTableCell']}/>
        )
      }}
    />
  );
}
