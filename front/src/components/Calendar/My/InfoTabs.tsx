import { useState } from "react";
import { useSelector } from "react-redux";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import Badge from "@mui/material/Badge";
import styled from "@emotion/styled";

import UpcomingTab from "./UpcomingTab";
import RequestTab from "./RequestTab";
import { RootState } from "@modules/index";

function a11yProps(index: number) {
  return {
    id: `tab-${index}`,
    "aria-controls": `tabpanel-${index}`,
  };
}

const CustomTabs = styled(Tabs)`
  &.MuiTabs-root > .MuiTabs-scroller > .MuiTabs-indicator {
    opacity: 0;
  }
`;

const CustomTab = styled(Tab)`
  &.MuiButtonBase-root {
    font-size: 1.1em;

    & .MuiTouchRipple-root {
      opacity: 0;
    }
  }

  &.MuiTab-root {
    font-weight: 900;
    color: gray;
  }

  &.Mui-selected {
    color: black;
  }
`;

const InfoTabs = () => {
  const [value, setValue] = useState(0);
  const [check, setCheck] = useState(false);
  const request = useSelector((state: RootState) => state.mycalendar.request);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    if (newValue === 1 && !check) {
      setCheck(true);
    }
    setValue(newValue);
  };

  const getLabel = () => {
    if (request.length === 0 || check) {
      return "요청";
    } else {
      return (
        <Badge color="warning" variant="dot" sx={{ paddingRight: "3px" }}>
          요청
        </Badge>
      );
    }
  };

  return (
    <Box sx={{ margin: "15px" }}>
      <div>
        <CustomTabs
          value={value}
          onChange={handleChange}
          aria-label="info tabs"
        >
          <CustomTab label="다음 일정" {...a11yProps(0)} />
          <CustomTab label={getLabel()} {...a11yProps(1)} />
        </CustomTabs>
      </div>
      <UpcomingTab value={value} index={0} />
      <RequestTab value={value} index={1} />
    </Box>
  );
};

export default InfoTabs;
