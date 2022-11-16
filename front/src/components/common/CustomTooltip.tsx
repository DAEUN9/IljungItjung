import styled from "@emotion/styled";
import Tooltip, { tooltipClasses, TooltipProps } from "@mui/material/Tooltip";

interface customTooltipProps extends TooltipProps {}

const StyledTooltip = styled(({ className, ...props }: TooltipProps) => (
  <Tooltip {...props} classes={{ popper: className }} />
))`
  & .${tooltipClasses.tooltip} {
    font-size: 12px;
    border: 1px solid #dadada;
    background-color: #eaeaea;
    color: black;
    padding: 1rem;
    maxwidth: none;
  }

  & .${tooltipClasses.arrow} {
    color: #eaeaea;

    &:before {
      border: 1px solid #dadada;
    }
  }
`;

const CustomTooltip = ({
  title,
  children,
  placement,
  ...rest
}: customTooltipProps) => {
  return (
    <StyledTooltip title={title} placement={placement} arrow {...rest}>
      {children}
    </StyledTooltip>
  );
};

export default CustomTooltip;
