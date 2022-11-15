import styled from "@emotion/styled";
import { Tooltip, tooltipClasses, TooltipProps } from "@mui/material";
import React from "react";

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
    <React.Fragment>
      <h3>블락 기능</h3>
      <div>
        각 셀을 클릭하여 해당 시간대를 블락할 수 있습니다. <br />
        블락된 시간대에는 타인이 예약을 요청할 수 없습니다.
      </div>
      <h3>일정 삭제 기능</h3>
      <div>
        휴지통 버튼을 클릭하여 해당 일정을 삭제할 수 있습니다. <br />
        삭제 시, 사유를 입력하게 되며 해당 사유는 고객에게 알림톡으로
        발송됩니다.
      </div>
    </React.Fragment>
  );
};
