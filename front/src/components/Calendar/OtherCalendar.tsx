import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { ViewState } from '@devexpress/dx-react-scheduler';
import { Scheduler, Toolbar } from '@devexpress/dx-react-scheduler-material-ui';
import Paper from '@mui/material/Paper';

import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomTodayButton from '@components/Calendar/common/CustomTodayButton';
import CustomDateNavigator from '@components/Calendar/common/CustomDateNavigator';
import Profile from '@components/Calendar/common/Profile';
import Reservation from './Other/Reservation/Reservation';
import OtherAppointments from './Other/OtherAppointments';
import OtherWeekView from './Other/OtherWeekView';
import { RootState } from '@modules/index';
import { setCategory, setDisabledMap } from '@modules/othercalendar';
import { useParams } from 'react-router-dom';
import { getOtherProfile, getSchedule } from '@api/calendar';
import { MyProfile, ScheduleApiData } from '@components/types/types';

interface MyInfoApiData {
  status: string;
  data: MyProfile;
}

const OtherCalendar = () => {
  const [profile, setProfile] = useState<MyProfile>({
    nickname: '',
    email: '',
    imagePath: '',
    introduction: '',
    description: '',
  });
  const [currentDate, setCurrentDate] = useState(new Date());
  const current = useSelector(
    (state: RootState) => state.othercalendar.current
  );
  const dispatch = useDispatch();
  const { nickname } = useParams();

  useEffect(() => {
    if (nickname) {
      // 프로필 설정
      getOtherProfile(nickname, (res: MyInfoApiData) => {
        setProfile(res.data);
      });

      // 캘린더 조회
      getSchedule(nickname, false, (res: ScheduleApiData) => {
        const { categoryList, blockList, acceptList } = res.data;
        console.log(res.data);

        // 카테고리 설정
        if (categoryList.length !== 0) {
          dispatch(setCategory(categoryList));
        }

        // 시간대 블락 처리
        if (acceptList.length !== 0 || blockList.length !== 0) {
          const now = new Date();
          const filter = acceptList.filter(
            (item) => new Date(item.startDate) >= now
          );
          dispatch(setDisabledMap(filter));
        }
      });
    }
  }, [nickname]);

  return (
    <>
      <Paper className={styles['calendar-container']}>
        <Scheduler data={current} locale="ko-KR" firstDayOfWeek={1}>
          <ViewState
            currentDate={currentDate}
            onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
          />
          <OtherWeekView />
          <Toolbar />
          <CustomDateNavigator />
          <CustomTodayButton />
          <OtherAppointments />
        </Scheduler>
      </Paper>
      <div className={styles.info}>
        <Profile profile={profile} />
        <Reservation />
      </div>
    </>
  );
};

export default OtherCalendar;
