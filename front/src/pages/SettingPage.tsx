import React, { useState } from "react";
import { IoHelpCircleOutline } from "react-icons/io5";
import { Fab, IconButton, Tab, Tabs } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { BsQuestionLg } from "react-icons/bs";
import { ThemeProvider } from "@emotion/react";

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

const SettingPage = () => {
  const [tab, setTab] = useState(0);
  const navigate = useNavigate();

  const [saveOpen, setSaveOpen] = useState(false);

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
    setSaveOpen(false);
  };

  const handleTabChange = (e: React.SyntheticEvent, newValue: number) => {
    setTab(newValue);
  };

  return (
    <div className={styles["setting-page"]}>
      <Sidebar />
      <div className={styles["content"]}>
        <div className={styles["calendar-container"]}>
          <SetCalendar />
          <ThemeProvider theme={theme}>
            <CalendarTooltip title={TooltipContent()}>
              <div className={styles["fab-wrapper"]}>
                <Fab size="small" color="primary">
                  <BsQuestionLg />
                </Fab>
              </div>
            </CalendarTooltip>
          </ThemeProvider>
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
