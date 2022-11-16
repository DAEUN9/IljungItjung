import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler, Toolbar } from "@devexpress/dx-react-scheduler-material-ui";
import Paper from "@mui/material/Paper";

import '@styles/Calendar/CustomCalendar.css';
import styles from '@styles/Calendar/Calendar.module.scss';
import CustomTodayButton from '@components/Calendar/common/CustomTodayButton';
import CustomDateNavigator from '@components/Calendar/common/CustomDateNavigator';
import Profile from '@components/Calendar/common/Profile';
import Reservation from './Other/Reservation/Reservation';
import OtherAppointments from './Other/OtherAppointments';
import OtherWeekView from './Other/OtherWeekView';
import { RootState } from '@modules/index';
import { setDisabledMap } from '@modules/othercalendar';
import { useParams } from 'react-router-dom';
import { getOtherProfile, getSchedule } from '@api/calendar';
import { MyProfile, ScheduleApiData } from '@components/types/types';

const next = [
  {
    id: 1,
    startDate: "2022-11-07T09:30",
    endDate: "2022-11-07T11:00",
    title: "목욕",
    nickname: "곰고구마",
    desc: "요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요 요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요",
    phone: "010-1111-1111",
    color: "#F4F38A",
  },
  {
    id: 2,
    startDate: "2022-11-09T12:00",
    endDate: "2022-11-09T13:30",
    title: "손발톱관리",
    nickname: "신봉선",
    desc: "예쁘게 해주세용",
    phone: "010-2222-2222",
    color: "#C3DBE3",
  },
  {
    id: 3,
    startDate: "2022-11-10T12:00",
    endDate: "2022-11-10T13:30",
    title: "커트",
    nickname: "퍼플독",
    desc: "멋지게 해주십쇼",
    phone: "010-3333-3333",
    color: "#D7CBF4",
  },
  {
    id: 4,
    startDate: "2022-11-11T13:00",
    endDate: "2022-11-11T14:30",
    title: "카테고리",
    nickname: "닉네임",
    desc: "요청사항",
    phone: "010-3333-3333",
    color: "#D7CBF4",
  },
];

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
      getSchedule(nickname, (res: ScheduleApiData) => {
        const { categoryList, blockList, acceptList } = res.data;
        console.log(res.data);

        const now = new Date();
        const filter = next.filter((item) => new Date(item.startDate) >= now);
        dispatch(setDisabledMap(filter));
      });
    }
  }, [nickname]);

  return (
    <>
      <Paper className={styles["calendar-container"]}>
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
