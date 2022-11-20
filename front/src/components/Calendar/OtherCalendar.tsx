import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler, Toolbar } from "@devexpress/dx-react-scheduler-material-ui";
import Paper from "@mui/material/Paper";

import "@styles/Calendar/CustomCalendar.css";
import styles from "@styles/Calendar/Calendar.module.scss";
import CustomTodayButton from "@components/Calendar/common/CustomTodayButton";
import CustomDateNavigator from "@components/Calendar/common/CustomDateNavigator";
import Profile from "@components/Calendar/common/Profile";
import Reservation from "./Other/Reservation/Reservation";
import OtherAppointments from "./Other/OtherAppointments";
import OtherWeekView from "./Other/OtherWeekView";
import { RootState } from "@modules/index";
import { setCategory } from "@modules/othercalendar";
import { useParams } from "react-router-dom";
import { getOtherProfile, getSchedule } from "@api/calendar";
import { MyProfile, ScheduleApiData } from "@components/types/types";
import { setBlockList } from "@modules/othercalendar";

interface MyInfoApiData {
  status: string;
  data: MyProfile;
}

interface MyInfoApiData {
  status: string;
  data: MyProfile;
}

interface MyInfoApiData {
  status: string;
  data: MyProfile;
}

const OtherCalendar = () => {
  const [profile, setProfile] = useState<MyProfile>({
    nickname: "",
    email: "",
    imagePath: "",
    introduction: "",
    description: "",
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
        const {
          categoryList,
          blockList,
          blockDayList,
          acceptList,
          requestList,
        } = res.data;

        const reservations = acceptList.concat(requestList);
        const now = new Date();
        const filter = reservations.filter(
          (reservation) => new Date(reservation.startDate) >= now
        );
        dispatch(setBlockList(filter, blockList, blockDayList));
        dispatch(setCategory(categoryList));
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
