import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler } from "@devexpress/dx-react-scheduler-material-ui";
import Paper from "@mui/material/Paper";

import { RootState } from "@modules/index";
import { setRequestList, setScheduleList } from "@modules/mycalendar";
import "@styles/Calendar/CustomCalendar.css";
import styles from "@styles/Calendar/Calendar.module.scss";
import CustomWeekView from "@components/Calendar/common/CustomWeekView";
import CustomToolbar from "@components/Calendar/common/CustomToolbar";
import CustomTodayButton from "@components/Calendar/common/CustomTodayButton";
import CustomDateNavigator from "@components/Calendar/common/CustomDateNavigator";
import CustomAppointments from "@components/Calendar/common/CustomAppointments";
import CustomAppointmentTooltip from "@components/Calendar/common/CustomAppointmentTooltip";
import Profile from "@components/Calendar/common/Profile";
import InfoTabs from "./My/InfoTabs";
import { getSchedule } from "@api/calendar";
import { ScheduleApiData } from "@components/types/types";

const next = [
  {
    id: 1,
    startDate: "2022-11-14T09:30",
    endDate: "2022-11-14T11:00",
    categoryName: "목욕",
    nickname: "곰고구마",
    contents:
      "요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요 요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요",
    phonenum: "010-1111-1111",
    color: "#F4F38A",
  },
  {
    id: 2,
    startDate: "2022-11-16T12:00",
    endDate: "2022-11-16T13:30",
    categoryName: "손발톱관리",
    nickname: "신봉선",
    contents: "예쁘게 해주세용",
    phonenum: "010-2222-2222",
    color: "#C3DBE3",
  },
  {
    id: 3,
    startDate: "2022-11-18T12:00",
    endDate: "2022-11-18T13:30",
    categoryName: "커트",
    nickname: "퍼플독",
    contents: "멋지게 해주십쇼",
    phonenum: "010-3333-3333",
    color: "#D7CBF4",
  },
];

const request = [
  {
    id: 1,
    categoryName: "목욕",
    color: "#F4F38A",
    startDate: "2022-11-03T09:45",
    endDate: "2022-11-03T11:00",
    nickname: "김주영",
    contents: "주영이 잘 부탁드립니당",
    phonenum: "010-1111-1111",
  },
];

const MyCalendar = () => {
  const profile = useSelector((state: RootState) => state.profile.profile);
  const list = useSelector((state: RootState) => state.mycalendar.list);
  const dispatch = useDispatch();
  const [currentDate, setCurrentDate] = useState(new Date());

  useEffect(() => {
    if (profile.nickname !== "") {
      getSchedule(profile.nickname, (res: ScheduleApiData) => {
        const { acceptList, requestList } = res.data;
        console.log(res.data);
        dispatch(setScheduleList(acceptList));
        dispatch(setRequestList(request));
      });
    }
  }, [profile]);

  return (
    <>
      <Paper className={styles["calendar-container"]}>
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
