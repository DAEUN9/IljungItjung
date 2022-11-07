import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler } from "@devexpress/dx-react-scheduler-material-ui";
import { useEffect, useState } from "react";
import { faLock, faUnlock } from "@fortawesome/free-solid-svg-icons";
import { renderToStaticMarkup } from "react-dom/server";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useDispatch, useSelector } from "react-redux";

import CustomDateNavigator from "@components/Calendar/common/CustomDateNavigator";
import CustomTodayButton from "@components/Calendar/common/CustomTodayButton";
import CustomToolbar from "@components/Calendar/common/CustomToolbar";
import CustomWeekView from "@components/Calendar/common/CustomWeekView";
import SettingAppointments from "@components/Setting/SettingAppointments";
import "@styles/Setting/SetCalendar.scss";
import { RootState } from "@modules/index";
import { toggleLock } from "@modules/setting";

const data = [
  {
    id: 1,
    startDate: "2022-10-31T09:45",
    endDate: "2022-10-31T11:00",
    title: "목욕",
    nickname: "곰고구마",
    desc: "요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요 요청사항이 엄청나게 길어지면 어떻게 보일지 정말정말 궁금하네요",
    phone: "010-1111-1111",
    color: "#F4F38A",
  },
  {
    id: 2,
    startDate: "2022-11-02T12:00",
    endDate: "2022-11-02T13:30",
    title: "손발톱관리",
    nickname: "신봉선",
    desc: "예쁘게 해주세용",
    phone: "010-2222-2222",
    color: "#C3DBE3",
  },
  {
    id: 3,
    startDate: "2022-11-05T12:00",
    endDate: "2022-11-05T13:30",
    title: "커트",
    nickname: "퍼플독",
    desc: "멋지게 해주십쇼",
    phone: "010-3333-3333",
    color: "#D7CBF4",
  },
];

const SetCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const lockElement = <FontAwesomeIcon icon={faLock} />;
  const unlockElement = <FontAwesomeIcon icon={faUnlock} />;

  const isLocked = useSelector((state: RootState) => state.setting.lock);
  const dispatch = useDispatch();
  const onToggleLock = (index: number) => dispatch(toggleLock(index));

  useEffect(() => {
    console.log(isLocked);
  });

  // useEffect(() => {
  //   const dayList = document.getElementsByClassName("Cell-dayView");

  //   for (let i = 0; i < dayList.length; i++) {
  //     const lock = document.createElement("div");
  //     lock.className = "lock";

  //     lock.addEventListener("click", () => {
  //       onToggleLock(i);
  //       console.log(isLocked);
  //     });

  //     dayList[i].appendChild(lock);
  //     console.log(dayList[i]);
  //   }
  // }, []);

  // useEffect(() => {
  //   const lockElements = document.getElementsByClassName("lock");
  //   for (let i = 0; i < lockElements.length; i++) {
  //     let staticElement;

  //     // 잠금된 요일이면 잠금 아이콘 출력
  //     if (isLocked[i]) staticElement = renderToStaticMarkup(lockElement);
  //     else staticElement = renderToStaticMarkup(unlockElement);

  //     lockElements[i].innerHTML = `${staticElement}`;
  //   }
  // }, [isLocked]);

  return (
    <Scheduler data={data} locale="ko-KR" firstDayOfWeek={1}>
      <ViewState
        currentDate={currentDate}
        onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
      />
      <CustomWeekView />
      <CustomToolbar />
      <CustomDateNavigator />
      <CustomTodayButton />
      <SettingAppointments />
    </Scheduler>
  );
};

export default SetCalendar;
