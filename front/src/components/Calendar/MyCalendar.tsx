
import { ComponentType, ButtonHTMLAttributes, useState } from 'react';
import Paper from '@mui/material/Paper';
import { ViewState, TodayButton } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Toolbar,
  DateNavigator,
  Appointments,
  TodayButton as MuiTodayButton,
} from '@devexpress/dx-react-scheduler-material-ui';

import styles from '@styles/Calendar/Calendar.module.scss';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  /** Function that sets the Scheduler's current date. */
  setCurrentDate: (nextDate: Date) => void;
  /** Returns a localization message by the message key. */
  getMessage: (messageKey: string) => string;
  className?: string;
  style?: React.CSSProperties;
  [x: string]: any;
}

const StyledTodayButton: ComponentType<ButtonProps> = (props: ButtonProps) => (
  <MuiTodayButton.Button {...props} className={styles.today} />
);

const schedulerData = [
  {
    startDate: '2022-10-25T09:45',
    endDate: '2022-10-25T11:00',
    title: 'Meeting',
  },
  {
    startDate: '2022-10-26T12:00',
    endDate: '2022-10-26T13:30',
    title: 'Go to a gym',
  },
  {
    startDate: '2022-10-30T12:00',
    endDate: '2022-10-30T13:30',
    title: 'Go to a gym',
  },
];

const MyCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());

  return (
    <Paper className={styles['calendar-container']}>
      <Scheduler data={schedulerData} locale="ko-KR">
        <ViewState
          currentDate={currentDate}
          onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
        />
        <WeekView startDayHour={9} endDayHour={22} />
        <Toolbar />
        <TodayButton
          messages={{ today: '오늘' }}
          buttonComponent={StyledTodayButton}
        />
        <DateNavigator />
        <Appointments />
      </Scheduler>
    </Paper>
  );
};

export default MyCalendar;
