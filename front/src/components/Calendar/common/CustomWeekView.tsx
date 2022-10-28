import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import { getDay } from './util';

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
    />
  );
}
