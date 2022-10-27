import { useEffect, useState } from 'react';
import { ViewState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  Resources,
} from '@devexpress/dx-react-scheduler-material-ui';
import Paper from '@mui/material/Paper';

import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomWeekView from './common/CustomWeekView';
import CustomToolbar from './common/CustomToolbar';
import CustomTodayButton from './common/CustomTodayButton';
import CustomDateNavigator from './common/CustomDateNavigator';
import CustomAppointments from './common/CustomAppointments';
import CustomAppointmentTooltip from './common/CustomAppointmentTooltip';
import { SchedulerDate, Resource } from './common/util';

const MyCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [schedulerData, setSchedulerData] =
    useState<SchedulerDate[] | undefined>(undefined);
  const [resource, setResource] = useState<Resource[]>([
    { fieldName: '', title: '', instances: [] },
  ]);

  useEffect(() => {
    setSchedulerData([
      {
        id: 1,
        startDate: '2022-10-25T09:45',
        endDate: '2022-10-25T11:00',
        title: '목욕',
        nickname: 1,
        desc: 1,
        color: '#F4F38A',
      },
      {
        id: 2,
        startDate: '2022-10-26T12:00',
        endDate: '2022-10-26T13:30',
        title: '손발톱관리',
        nickname: 2,
        desc: 2,
        color: '#C3DBE3'
      },
      {
        id: 3,
        startDate: '2022-10-30T12:00',
        endDate: '2022-10-30T13:30',
        title: '커트',
        nickname: 3,
        desc: 3,
        color: '#D7CBF4'
      },
    ]);

    setResource([
      {
        fieldName: 'nickname',
        title: 'Nickname',
        instances: [
          { id: 1, text: '곰고구마' },
          { id: 2, text: '봉미선' },
          { id: 3, text: '퍼플독' },
        ],
      },
      {
        fieldName: 'desc',
        title: 'Description',
        instances: [
          { id: 1, text: '잘 부탁드립니다.' },
          { id: 2, text: '잘 부탁드릴게요' },
          { id: 3, text: '예쁘게 해주세요' },
        ],
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
        <CustomAppointmentTooltip />
        <Resources data={resource} />
      </Scheduler>
    </Paper>
  );
};

export default MyCalendar;
