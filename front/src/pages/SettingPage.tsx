import { ViewState } from "@devexpress/dx-react-scheduler";
import { Scheduler } from "@devexpress/dx-react-scheduler-material-ui";
import React, { useState } from "react";
import { IoHelpCircleOutline } from "react-icons/io5";
import { IconButton, Tab, Tabs } from "@mui/material";
import { useNavigate } from "react-router-dom";

import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Setting/Setting.module.scss";
import CustomWeekView from "@components/Calendar/common/CustomWeekView";
import CustomToolbar from "@components/Calendar/common/CustomToolbar";
import CustomDateNavigator from "@components/Calendar/common/CustomDateNavigator";
import CustomAppointments from "@components/Calendar/common/CustomAppointments";
import CustomAppointmentTooltip from "@components/Calendar/common/CustomAppointmentTooltip";
import CustomTodayButton from "@components/Calendar/common/CustomTodayButton";
import CustomButton from "@components/common/CustomButton";
import CategoryList from "@components/Setting/Category/CategoryList";
import CustomTooltip from "@components/common/CustomTooltip";
import ChangeTab from "@components/Setting/Category/ChangeTab";
import AddTab from "@components/Setting/Category/AddTab";
import "@styles/Setting/TabButton.scss";
import CustomModal from "@components/common/CustomModal";
import iljung from "@assets/iljung.png";

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

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} {...other}>
      {value === index && <div>{children}</div>}
    </div>
  );
}

const SettingPage = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [tab, setTab] = useState(0);
  const navigate = useNavigate();

  const [saveOpen, setSaveOpen] = useState(false);
  const [cancelOpen, setCancelOpen] = useState(false);

  const handleClickSave = () => {
    // 모달 띄우기
    setSaveOpen(true);
  };

  const handleClickCancel = () => {
    navigate("/calendar/my");
  };

  const handleSubmit = () => {
    console.log("handleSubmit()");
    // 변경 내용 api 요청
  };

  const handleTabChange = (e: React.SyntheticEvent, newValue: number) => {
    setTab(newValue);
  };

  return (
    <div className={styles["setting-page"]}>
      <Sidebar />
      <div className={styles["content"]}>
        <div className={styles["calendar-container"]}>
          <Scheduler data={data} locale="ko-KR" firstDayOfWeek={1}>
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
        </div>
        <div className={styles["right"]}>
          <div className={styles["button-group"]}>
            <CustomButton
              variant="contained"
              children="저장"
              onClick={handleClickSave}
            />
            <CustomButton
              color="secondary"
              children="취소"
              onClick={handleClickCancel}
            />
          </div>
          <CustomModal
            open={saveOpen}
            setOpen={setSaveOpen}
            cancelLabel="취소"
            confirmLabel="저장"
            handleConfirm={handleSubmit}
            children={
              <div className={styles["modal-content"]}>
                <div className={styles["img"]}>
                  <img src={iljung} />
                </div>
                <div className={styles["text"]}>
                  변경사항이 저장됩니다.
                  <br />
                  계속 하시겠습니까?
                </div>
              </div>
            }
          />
          <div className={styles["category-container"]}>
            <div className={styles["title"]}>
              <h2>카테고리 관리</h2>
              <CustomTooltip
                title="카테고리 관리"
                children={
                  <IconButton>
                    <IoHelpCircleOutline />
                  </IconButton>
                }
              />
            </div>
            <div className={styles["content"]}>
              <CategoryList />
              <div className={styles["tabs"]}>
                <Tabs value={tab} onChange={handleTabChange}>
                  <Tab label="추가" />
                  <Tab label="변경" />
                </Tabs>
                <TabPanel value={tab} index={0}>
                  <AddTab />
                </TabPanel>
                <TabPanel value={tab} index={1}>
                  <ChangeTab />
                </TabPanel>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SettingPage;
