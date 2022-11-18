import { useState, useCallback } from "react";
import { useDispatch } from "react-redux";
import MuiTextField from '@mui/material/TextField';
import styled from '@emotion/styled';

import styles from "@styles/Calendar/Calendar.module.scss";
import CustomButton from "@components/common/CustomButton";
import { InfoItemProps } from "@components/Calendar/common/util";
import { acceptRequest } from "@api/calendar";
import { addSchedule, deleteRequest } from "@modules/mycalendar";
import { useSnackbarContext } from "./useSnackbar";

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

const RequestButtons = ({ item }: InfoItemProps) => {
  const [openText, setOpenText] = useState(false);
  const [text, setText] = useState("");
  const { setMessage, setOpen } = useSnackbarContext();

  const dispatch = useDispatch();

  // 예약 거절
  const handleDeny = useCallback(() => {
    setOpenText(true);
  }, []);

  // 예약 수락
  const handleAccept = () => {
    const data = { accept: true };

    acceptRequest(item.id, data, (res: RequestApiData) => {
      setMessage("수락되었습니다.");
      setOpen(true);
      dispatch(addSchedule(item));
    });
  };

  // 거절 사유 입력 취소
  const handleCancle = useCallback(() => {
    setOpenText(false);
    setText("");
  }, []);

  // 거절 사유 입력 확인
  const handleConfirm = () => {
    const data = { accept: false, reason: text };

    acceptRequest(item.id, data, (res: RequestApiData) => {
      setMessage("거절되었습니다.");
      setOpen(true);
      dispatch(deleteRequest(res.data.id));
    });
  };

  return (
    <div>
      {!openText && (
        <div className={styles["request-buttons"]}>
          <CustomButton variant="outlined" onClick={handleDeny}>
            거절
          </CustomButton>
          <CustomButton onClick={handleAccept} className={styles["no-shadow"]}>
            수락
          </CustomButton>
        </div>
      )}
      {openText && (
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
              className={styles["no-shadow"]}
            >
              확인
            </CustomButton>
          </div>
        </div>
      )}
    </div>
  );
};

export default RequestButtons;