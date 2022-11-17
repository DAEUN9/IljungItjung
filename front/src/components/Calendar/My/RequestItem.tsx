import { useState, useCallback } from "react";
import MuiTextField from "@mui/material/TextField";
import styled from "@emotion/styled";

import styles from "@styles/Calendar/Calendar.module.scss";
import Schedule from "@components/common/Schedule";
import iljung from "@assets/defaultImg.png";
import DetailInfo from "./DetailInfo";
import {
  formatTime,
  getFullDate,
  InfoItemProps,
} from "@components/Calendar/common/util";
import CustomButton from "@components/common/CustomButton";
import { acceptRequest } from "@api/calendar";
import { Snackbar } from "@mui/material";

interface ButtonProps {
  id: number;
}

interface RequestApiData {
  status: string;
  data: {
    id: number;
  };
}

const TextField = styled(MuiTextField)`
  > .Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }
`;

const messages = ["수락되었습니다.", "거절되었습니다."];

const RequestButtons = ({ id }: ButtonProps) => {
  const [open, setOpen] = useState(false);
  const [text, setText] = useState("");
  const [snackbar, setSnackbar] = useState(false);
  const [idx, setIdx] = useState(0);

  // 스낵바 close 핸들러
  const handleClose = useCallback(() => {
    setSnackbar(false);
  }, []);

  // 예약 거절
  const handleDeny = useCallback(() => {
    setOpen(true);
  }, []);

  // 예약 수락
  const handleAccept = () => {
    const data = { accept: true };
    acceptRequest(id, data, (res: RequestApiData) => {
      console.log(res);
      setIdx(0);
      setSnackbar(true);
    });
  };

  // 거절 사유 입력 취소
  const handleCancle = useCallback(() => {
    setOpen(false);
    setText("");
  }, []);

  // 거절 사유 입력 확인
  const handleConfirm = () => {
    const data = { accept: false, reason: text };
    acceptRequest(id, data, (res: RequestApiData) => {
      console.log(res);
      setIdx(1);
      setSnackbar(true);
    });
  };

  return (
    <div>
      {!open && (
        <div className={styles["request-buttons"]}>
          <CustomButton variant="outlined" onClick={handleDeny}>
            거절
          </CustomButton>
          <CustomButton onClick={handleAccept}>수락</CustomButton>
        </div>
      )}
      {open && (
        <div>
          <TextField
            fullWidth
            multiline
            sx={{ marginBottom: "10px" }}
            placeholder="거절 사유를 입력해주세요"
            rows={3}
            value={text}
            onChange={(e) => {
              const current = e.currentTarget.value;
              if (current.length <= 100) {
                setText(current);
              }
            }}
          />
          <div className={styles["request-text-length"]}>{text.length}/100</div>
          <div className={styles["request-buttons"]}>
            <CustomButton variant="outlined" onClick={handleCancle}>
              취소
            </CustomButton>
            <CustomButton
              disabled={text.length > 0 ? false : true}
              onClick={handleConfirm}
            >
              확인
            </CustomButton>
          </div>
        </div>
      )}
      <Snackbar
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        autoHideDuration={6000}
        open={snackbar}
        onClose={handleClose}
        message={messages[idx]}
      />
    </div>
  );
};

const RequestItem = ({ item }: InfoItemProps) => {
  const {
    id,
    color,
    startDate,
    endDate,
    categoryName,
    nickname,
    phonenum,
    contents,
  } = item;
  const time = formatTime(startDate?.toString(), endDate?.toString());

  return (
    <div className={styles["info-item"]}>
      <Schedule
        color={color}
        date={getFullDate(new Date(startDate.toString()))}
        time={time ?? "-"}
        userId="유저아이디"
        userName={nickname}
        category={categoryName ?? "-"}
        userImg={iljung}
        render={() => (
          <>
            <DetailInfo phone={phonenum} desc={contents} />
            <RequestButtons id={id} />
          </>
        )}
      />
    </div>
  );
};

export default RequestItem;
