import { Button, Collapse } from "@mui/material";
import { useState } from "react";
import { TbTrashX } from "react-icons/tb";
import { RiArrowDownSLine } from "react-icons/ri";

import styles from "@styles/Reservation/Reservation.module.scss";
import CustomModal from "@components/common/CustomModal";
import iljung from "@assets/iljung.png";

interface CancelReasonProps {
  reason: string;
  detail: string;
}

interface CancelButtonProps {
  detail: string;
}

const CancelButton = ({ detail }: CancelButtonProps) => {
  const [open, setOpen] = useState(false);

  const handleConfirm = () => {
    // 예약 취소 api 통신 코드
  };

  return (
    <div className={styles["cancel-button"]}>
      <div className={styles["detail"]}>{detail}</div>
      <div className={styles["button"]}>
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
              <div className={styles["img"]}>
                <img src={iljung} />
              </div>
              <div className={styles["text"]}>
                예약이 취소됩니다.
                <br />
                계속 하시겠습니까?
              </div>
            </div>
          }
        />
      </div>
    </div>
  );
};

const CancelReason = ({ reason, detail }: CancelReasonProps) => {
  const [checked, setChecked] = useState(false);

  return (
    <div className={styles["cancel-reason"]}>
      <div className={`${styles["detail"]} ${styles["canceled"]}`}>
        {detail}
      </div>
      <div className={styles["wraaper"]}>
        <div className={styles["button"]}>
          <Button
            startIcon={<RiArrowDownSLine />}
            onClick={() => setChecked(!checked)}
          >
            취소 사유
          </Button>
        </div>
        <Collapse in={checked}>
          <div className={styles["reason"]}>{reason}</div>
        </Collapse>
      </div>
    </div>
  );
};

export { CancelButton, CancelReason };
