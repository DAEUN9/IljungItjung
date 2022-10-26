import { Button, Collapse } from "@mui/material";
import { useState } from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import DeleteForeverOutlinedIcon from "@mui/icons-material/DeleteForeverOutlined";

import styles from "@styles/Reservation/Reservation.module.scss";

interface CancelReasonProps {
  reason: string;
  detail: string;
}

interface CancelButtonProps {
  detail: string;
}

const CancelButton = ({ detail }: CancelButtonProps) => {
  return (
    <div className={styles["cancel-button"]}>
      <div className={styles["detail"]}>{detail}</div>
      <div className={styles["button"]}>
        <Button startIcon={<DeleteForeverOutlinedIcon />}>취소하기</Button>
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
            startIcon={<ExpandMoreIcon />}
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
