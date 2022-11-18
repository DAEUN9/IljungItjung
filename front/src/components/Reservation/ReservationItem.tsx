import { Button, Collapse, TextField } from "@mui/material";
import { useState } from "react";
import { TbTrashX } from "react-icons/tb";
import { RiArrowDownSLine } from "react-icons/ri";

import styles from "@styles/Reservation/Reservation.module.scss";
import CustomModal from "@components/common/CustomModal";
import iljung from "@assets/iljung.png";
import { cancelReservation } from "@api/reservation";

interface CancelReasonProps {
  reason: string;
  detail: string;
  nickname: string;
  cancelFrom: string;
}

interface CancelButtonProps {
  id: number;
  detail: string;
}

const CancelButton = ({ id, detail }: CancelButtonProps) => {
  const [open, setOpen] = useState(false);
  const [reason, setReason] = useState("");

  const handleConfirm = () => {
    // 예약 취소 api 통신 코드
    cancelReservation(id, reason, (res: any) => {
      console.log(res);
    });

    setOpen(false);
  };

  return (
    <div className={styles["cancel-button"]}>
      <div className={styles.detail}>{detail}</div>
      <div className={styles.button}>
        <Button startIcon={<TbTrashX />} onClick={() => setOpen(true)}>
          취소하기
        </Button>
        <CustomModal
          open={open}
          setOpen={setOpen}
          cancelLabel="취소"
          confirmLabel="확인"
          handleConfirm={handleConfirm}
          children={
            <div className={styles["modal-content"]}>
              <div className={styles.img}>
                <img src={iljung} />
              </div>
              <div className={styles.text}>예약을 취소하시겠습니까?</div>
              <TextField
                className={styles.textfield}
                multiline
                rows={3}
                placeholder="예약을 취소하는 이유를 알려주세요."
                fullWidth
                onChange={(e) => setReason(e.target.value)}
              />
            </div>
          }
        />
      </div>
    </div>
  );
};

const CancelReason = ({
  reason,
  detail,
  nickname,
  cancelFrom,
}: CancelReasonProps) => {
  const [checked, setChecked] = useState(false);

  return (
    <div className={styles["cancel-reason"]}>
      <div className={`${styles.detail} ${styles.canceled}`}>{detail}</div>
      <div className={styles.wraaper}>
        <div className={styles.button}>
          <Button
            startIcon={<RiArrowDownSLine />}
            onClick={() => setChecked(!checked)}
          >
            취소 사유
          </Button>
        </div>
        <Collapse in={checked}>
          <div className={styles.cancelFrom}>
            {cancelFrom === "사용자" ? (
              <div>사용자가 취소한 예약입니다.</div>
            ) : (
              <div>{nickname}님에 의해 취소된 예약입니다.</div>
            )}
          </div>
          <div className={styles.reason}>{reason}</div>
        </Collapse>
      </div>
    </div>
  );
};

export { CancelButton, CancelReason };
