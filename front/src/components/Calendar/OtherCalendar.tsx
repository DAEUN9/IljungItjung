import { useState } from 'react';
import { ViewState } from '@devexpress/dx-react-scheduler';
import { Scheduler, Toolbar } from '@devexpress/dx-react-scheduler-material-ui';
import Paper from '@mui/material/Paper';

import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomWeekView from '@components/Calendar/common/CustomWeekView';
import CustomToolbar from '@components/Calendar/common/CustomToolbar';
import CustomTodayButton from '@components/Calendar/common/CustomTodayButton';
import CustomDateNavigator from '@components/Calendar/common/CustomDateNavigator';
import CustomAppointments from '@components/Calendar/common/CustomAppointments';
import CustomAppointmentTooltip from '@components/Calendar/common/CustomAppointmentTooltip';
import OtherAppointments from './Other/OtherAppointments';
import OtherWeekView from './Other/OtherWeekView';

const next = [
  {
    id: 1,
    startDate: '2022-10-31T09:45',
    endDate: '2022-10-31T11:00',
    title: '목욕',
    nickname: '곰고구마',
    desc: '요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요 요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요',
    phone: '010-1111-1111',
    color: '#F4F38A',
  },
  {
    id: 2,
    startDate: '2022-11-02T12:00',
    endDate: '2022-11-02T13:30',
    title: '손발톱관리',
    nickname: '신봉선',
    desc: '예쁘게 해주세용',
    phone: '010-2222-2222',
    color: '#C3DBE3',
  },
  {
    id: 3,
    startDate: '2022-11-05T12:00',
    endDate: '2022-11-05T13:30',
    title: '커트',
    nickname: '퍼플독',
    desc: '멋지게 해주십쇼',
    phone: '010-3333-3333',
    color: '#D7CBF4',
  },
];

const OtherCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());

  return (
    <>
      <Paper className={styles['calendar-container']}>
        <Scheduler data={next} locale="ko-KR" firstDayOfWeek={1}>
          <ViewState
            currentDate={currentDate}
            onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
          />
          <OtherWeekView  />
          <Toolbar />
          <CustomDateNavigator />
          <CustomTodayButton />
          <OtherAppointments />
        </Scheduler>
      </Paper>
      <div className={styles.info}>

      </div>
    </>
  );
}

export default OtherCalendar;