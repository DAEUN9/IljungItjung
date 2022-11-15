import { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { ViewState } from '@devexpress/dx-react-scheduler';
import { Scheduler } from '@devexpress/dx-react-scheduler-material-ui';
import Paper from '@mui/material/Paper';

import { RootState } from '@modules/index';
import { setRequestList, setScheduleList } from '@modules/mycalendar';
import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomWeekView from '@components/Calendar/common/CustomWeekView';
import CustomToolbar from '@components/Calendar/common/CustomToolbar';
import CustomTodayButton from '@components/Calendar/common/CustomTodayButton';
import CustomDateNavigator from '@components/Calendar/common/CustomDateNavigator';
import CustomAppointments from '@components/Calendar/common/CustomAppointments';
import CustomAppointmentTooltip from '@components/Calendar/common/CustomAppointmentTooltip';
import Profile from '@components/Calendar/common/Profile';
import InfoTabs from './My/InfoTabs';
import { getSchedule } from '@api/calendar';

interface CommonState {
  id: number;
  categoryName: string;
}

interface DateState {
  startDate: string;
  endDate: string;
}

interface CategoryState extends CommonState {
  color: string;
  time: string;
}

interface RequestState extends CommonState, DateState {
  color: string;
  contents: string;
  phonenum: string;
}

interface AcceptState extends RequestState {}

interface BlockState extends CategoryState, DateState {
  contents: string;
  block: boolean;
}

interface CancelState extends CategoryState, DateState {
  reason: string;
  cancelFrom: string;
  contents: string;
  phonenum: string;
}

interface ScheduleApiData {
  status: string;
  data: {
    categoryList: CategoryState[];
    requestList: RequestState[];
    acceptList: AcceptState[];
    blockList: BlockState[];
    cancelList: CancelState[];
  };
}

const next = [
  {
    id: 1,
    startDate: '2022-11-07T09:30',
    endDate: '2022-11-07T11:00',
    title: '목욕',
    nickname: '곰고구마',
    desc: '요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요 요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요',
    phone: '010-1111-1111',
    color: '#F4F38A',
  },
  {
    id: 2,
    startDate: '2022-11-08T12:00',
    endDate: '2022-11-08T13:30',
    title: '손발톱관리',
    nickname: '신봉선',
    desc: '예쁘게 해주세용',
    phone: '010-2222-2222',
    color: '#C3DBE3',
  },
  {
    id: 3,
    startDate: '2022-11-11T12:00',
    endDate: '2022-11-11T13:30',
    title: '커트',
    nickname: '퍼플독',
    desc: '멋지게 해주십쇼',
    phone: '010-3333-3333',
    color: '#D7CBF4',
  },
];

const request = [
  {
    id: 1,
    startDate: '2022-11-03T09:45',
    endDate: '2022-11-03T11:00',
    title: '목욕',
    nickname: '김주영',
    desc: '주영이 잘 부탁드립니당',
    phone: '010-1111-1111',
    color: '#F4F38A',
  },
  {
    id: 2,
    startDate: '2022-11-05T12:00',
    endDate: '2022-11-05T13:30',
    title: '손발톱관리',
    nickname: '바보',
    desc: '바보 잘 부탁드립니당',
    phone: '010-2222-2222',
    color: '#C3DBE3',
  },
];

const MyCalendar = () => {
  const profile = useSelector((state: RootState) => state.profile.profile);
  const list = useSelector((state: RootState) => state.mycalendar.list);
  const dispatch = useDispatch();
  const [currentDate, setCurrentDate] = useState(new Date());

  useEffect(() => {
    if (profile.nickname !== '') {
      getSchedule(profile.nickname, true, (res: ScheduleApiData) => {
        const requestList = res.data.requestList;
        const acceptList = res.data.acceptList;
        console.log(res.data);
      });
      dispatch(setScheduleList(next));
      dispatch(setRequestList(request));
    }
  }, [profile]);

  return (
    <>
      <Paper className={styles['calendar-container']}>
        <Scheduler data={list} locale="ko-KR" firstDayOfWeek={1}>
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
        </Scheduler>
      </Paper>
      <div className={styles.info}>
        <Profile profile={profile} />
        <InfoTabs />
      </div>
    </>
  );
};

export default MyCalendar;
