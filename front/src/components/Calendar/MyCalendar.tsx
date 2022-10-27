import { useEffect, useState } from 'react';
import { ViewState } from '@devexpress/dx-react-scheduler';
import { Scheduler } from '@devexpress/dx-react-scheduler-material-ui';
import Paper from '@mui/material/Paper';

import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomWeekView from './common/CustomWeekView';
import CustomToolbar from './common/CustomToolbar';
import CustomTodayButton from './common/CustomTodayButton';
import CustomDateNavigator from './common/CustomDateNavigator';
import CustomAppointments from './common/CustomAppointments';
import { SchedulerDate } from './common/util';

const MyCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [schedulerData, setSchedulerData] =
    useState<SchedulerDate[] | undefined>(undefined);

  useEffect(() => {
    setSchedulerData([
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
    ]);
  }, []);

  return (
    <Paper className={styles['calendar-container']}>
      <Scheduler data={schedulerData} locale="ko-KR" firstDayOfWeek={1}>
        <ViewState
          currentDate={currentDate}
          onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
        />
        <CustomWeekView />
        <CustomToolbar />
        <CustomDateNavigator />
        <CustomTodayButton />
        <CustomAppointments />
      </Scheduler>
    </Paper>
  );
};

export default MyCalendar;
