import { WeekView } from '@devexpress/dx-react-scheduler-material-ui';

import { SchedulerDateTime } from './util';

const days = ['일', '월', '화', '수', '목', '금', '토'];

const formatDate = (nextDate: SchedulerDateTime | undefined, nextOptions: any) => {
  const date =
    typeof nextDate === 'object'
      ? nextDate
      : typeof nextDate === 'string'
      ? new Date(nextDate)
      : new Date();
  const { day } = nextOptions;

  let value = '';

  if (day) {
    value = date.getDate().toString();
  } else {
    value = days[date.getDay()];
  }

  return value;
};

export default function CustomWeekView() {
  return (
    <WeekView
      startDayHour={9}
      endDayHour={22}
      dayScaleCellComponent={(props) => (
        <WeekView.DayScaleCell
          {...props}
          className="day-scale"
          formatDate={formatDate}
        />
      )}
    />
  );
}
