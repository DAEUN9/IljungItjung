import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler } from "@devexpress/dx-react-scheduler-material-ui";
import Paper from "@mui/material/Paper";

import { RootState } from "@modules/index";
import {
  setBlockList,
  setRequestList,
  setScheduleList,
} from "@modules/mycalendar";
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

const MyCalendar = () => {
  const profile = useSelector((state: RootState) => state.profile.profile);
  const list = useSelector((state: RootState) => state.mycalendar.list);
  const dispatch = useDispatch();
  const [currentDate, setCurrentDate] = useState(new Date());

  useEffect(() => {
    if (profile.nickname !== "") {
      getSchedule(profile.nickname, (res: ScheduleApiData) => {
        const { acceptList, requestList, blockList, blockDayList } = res.data;

        // 일정 목록 설정
        dispatch(setScheduleList(acceptList));
        // 요청 목록 설정
        dispatch(setRequestList(requestList));
        // 블락 설정
        dispatch(setBlockList(blockList, blockDayList));
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
