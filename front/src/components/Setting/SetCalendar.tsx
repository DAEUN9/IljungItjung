import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler } from "@devexpress/dx-react-scheduler-material-ui";
import { useEffect, useState } from "react";

import CustomDateNavigator from "@components/Calendar/common/CustomDateNavigator";
import CustomTodayButton from "@components/Calendar/common/CustomTodayButton";
import CustomToolbar from "@components/Calendar/common/CustomToolbar";
import SettingAppointments from "@components/Setting/SettingAppointments";
import SettingWeekView from "@components/Setting/SettingWeekView";
import "@styles/Setting/SetCalendar.scss";
import { AppointmentsTypes } from "@components/types/types";

interface SetCalendarProps {
  appointments: AppointmentsTypes[];
}

const SetCalendar = ({ appointments }: SetCalendarProps) => {
  const [currentDate, setCurrentDate] = useState(new Date());

  return (
    <Scheduler data={appointments} locale="ko-KR" firstDayOfWeek={1}>
      <ViewState
        currentDate={currentDate}
        onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
      />
      <SettingWeekView />
      <CustomToolbar />
      <CustomDateNavigator />
      <CustomTodayButton />
      <SettingAppointments />
    </Scheduler>
  );
};

export default SetCalendar;
