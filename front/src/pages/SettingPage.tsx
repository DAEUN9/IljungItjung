import React, { useEffect, useState } from "react";
import { IoHelpCircleOutline } from "react-icons/io5";
import { Fab, IconButton, Tab, Tabs, TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { BsQuestionLg } from "react-icons/bs";
import { ThemeProvider } from "@emotion/react";
import { useDispatch, useSelector } from "react-redux";

import Sidebar from "@components/common/Sidebar";
import styles from "@styles/Setting/Setting.module.scss";
import CustomButton from "@components/common/CustomButton";
import CategoryList from "@components/Setting/Category/CategoryList";
import CustomTooltip from "@components/common/CustomTooltip";
import "@styles/Setting/TabButton.scss";
import CustomModal from "@components/common/CustomModal";
import iljung from "@assets/iljung.png";
import SetCalendar from "@components/Setting/SetCalendar";
import TabContent from "@components/Setting/Category/TabContent";
import theme from "@components/common/theme";
import {
  CalendarTooltip,
  TooltipContent,
} from "@components/Setting/CalendarTooltip";
import { CategoryTooltip } from "@components/Setting/Category/CategoryTooltip";
import { blockSchedule, registerCategory } from "@api/setting";
import { RootState } from "@modules/index";
import { AppointmentsTypes, BlockListTypes } from "@components/types/types";
import { getSchedule } from "@api/calendar";
import DeleteModal from "@components/Setting/DeleteModal";
import { setCategory } from "@modules/setting";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

const TabPanel = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} {...other}>
      {value === index && <div>{children}</div>}
    </div>
  );
};

interface SettingApiData {
  status: string;
  data: number;
}

const data: AppointmentsTypes[] = [
  {
    id: 1,
    startDate: "2022-11-16T09:30",
    endDate: "2022-11-16T11:00",
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

const SettingPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [tab, setTab] = useState(0);
  const [saveOpen, setSaveOpen] = useState(false);
  const [cancelOpen, setCancelOpen] = useState(false);
  const [appointments, setAppointments] = useState<AppointmentsTypes[]>([]);
  const { categories, set, lock } = useSelector(
    (state: RootState) => state.setting
  );
  const profile = useSelector((state: RootState) => state.profile.profile);

  useEffect(() => {
    // acceptList -> 캘린더에 표시할 예약 정보
    // categoryList -> 카테고리 목록 정보
    // blockList -> 블락된 일정 정보
    getSchedule(profile.nickname, (res: any) => {
      setAppointments(res.data.acceptList);
      dispatch(setCategory(res.data.categoryList));
      console.log(res.data);

      // res.data.blockList.map((block) => {

      // })
    });
  }, []);

  const handleTabChange = (e: React.SyntheticEvent, newValue: number) => {
    setTab(newValue);
  };

  const handleSubmit = () => {
    setSaveOpen(false);

    let blockList: BlockListTypes[] = [];
    set.forEach((item) => {
      const obj = {
        startTime: item.substring(9, 13),
        endTime: item.substring(13),
        date: item.substring(1, 9),
      };
      blockList.push(obj);
    });

    console.log(blockList);

    registerCategory(categories, (res: SettingApiData) => {
      console.log(res.data);
    });
    blockSchedule(lock, blockList, (res: SettingApiData) => {
      console.log(res.data);
    });

    navigate("/calendar/my");
  };

  return (
    <div className={styles["setting-page"]}>
      <Sidebar />
      <div className={styles.content}>
        <div className={styles["calendar-container"]}>
          <SetCalendar appointments={appointments} />
          <ThemeProvider theme={theme}>
            <CalendarTooltip title={TooltipContent()}>
              <div className={styles["fab-wrapper"]}>
                <Fab size="small" color="primary">
                  <BsQuestionLg />
                </Fab>
              </div>
            </CalendarTooltip>
          </ThemeProvider>
          <DeleteModal />
        </div>
        <div className={styles.right}>
          <div className={styles["button-group"]}>
            <CustomButton
              variant="contained"
              children="저장"
              onClick={() => setSaveOpen(true)}
            />
            <CustomButton
              color="secondary"
              children="취소"
              onClick={() => setCancelOpen(true)}
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
                <div className={styles.img}>
                  <img src={iljung} />
                </div>
                <div className={styles.text}>
                  변경사항이 저장됩니다.
                  <br />
                  계속 하시겠습니까?
                </div>
              </div>
            }
          />
          <CustomModal
            open={cancelOpen}
            setOpen={setCancelOpen}
            cancelLabel="취소"
            confirmLabel="확인"
            handleConfirm={handleSubmit}
            children={
              <div className={styles["modal-content"]}>
                <div className={styles.img}>
                  <img src={iljung} />
                </div>
                <div className={styles.text}>
                  변경사항이 사라집니다.
                  <br />
                  계속 하시겠습니까?
                </div>
              </div>
            }
          />
          <div className={styles["category-container"]}>
            <div className={styles.title}>
              <h2>카테고리 관리</h2>
              <CustomTooltip
                title={CategoryTooltip()}
                children={
                  <IconButton>
                    <IoHelpCircleOutline />
                  </IconButton>
                }
              />
            </div>
            <div className={styles.content}>
              <CategoryList />
              <div className={styles.tabs}>
                <Tabs value={tab} onChange={handleTabChange}>
                  <Tab label="추가" />
                  <Tab label="변경" />
                </Tabs>
                <TabPanel value={tab} index={0}>
                  <TabContent flag={false} />
                </TabPanel>
                <TabPanel value={tab} index={1}>
                  <TabContent flag={true} />
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
