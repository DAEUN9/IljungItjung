import styled from "@emotion/styled";
import { Tooltip, tooltipClasses, TooltipProps } from "@mui/material";
import { AiFillDelete } from "react-icons/ai";
import { ImBlocked } from "react-icons/im";

import styles from "@styles/Setting/CalendarTooltip.module.scss";

export const CalendarTooltip = styled(
  ({ className, ...props }: TooltipProps) => (
    <Tooltip {...props} arrow classes={{ popper: className }} />
  )
)(({ theme }) => ({
  [`& .${tooltipClasses.arrow}`]: {
    color: "#000000",
  },
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: "#000000",
    padding: "1rem",
  },
}));

export const TooltipContent = () => {
  return (
    <div className={styles.tooltip}>
      <h1>&#x1F447; 아래 기능을 사용해 보세요.</h1>
      <div className={styles.block}>
        <div className={styles.title}>
          <ImBlocked size="16px" />
          <h2>블락 기능</h2>
        </div>
        <div className={styles.content}>
          각 셀을 클릭하여 해당 시간대를 블락할 수 있습니다. <br />
          블락된 시간대에는 타인이 예약을 요청할 수 없습니다. <br />
          상단의 자물쇠 버튼을 눌러 블락할 시간을 매주 고정할 수 있습니다.
        </div>
      </div>
      <div className={styles.delete}>
        <div className={styles.title}>
          <AiFillDelete size="18px" />
          <h2>일정 삭제 기능</h2>
        </div>
        <div className={styles.content}>
          휴지통 버튼을 클릭하여 해당 일정을 삭제할 수 있습니다. <br />
          삭제 시, 사유를 입력하게 되며 해당 사유는 고객에게 알림톡으로
          발송됩니다.
        </div>
      </div>
    </div>
  );
};
