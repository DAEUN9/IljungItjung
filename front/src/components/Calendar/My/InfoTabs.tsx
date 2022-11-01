import { useState } from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import styled from '@emotion/styled';
import { Badge } from '@mui/material';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `tab-${index}`,
    'aria-controls': `tabpanel-${index}`,
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

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    if(newValue === 1 && !check) {
      setCheck(true);
    }
    setValue(newValue);
  };

  const getLabel = () => {
    if (check) {
      return '요청';
    } else {
      return (
        <Badge color="warning" variant="dot" sx={{ padding: '2px' }}>
          요청
        </Badge>
      );
    }
  };

  return (
    <Box sx={{ margin: '15px' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <CustomTabs
          value={value}
          onChange={handleChange}
          aria-label="info tabs"
        >
          <CustomTab label="다음 일정" {...a11yProps(0)} />
          <CustomTab label={getLabel()} {...a11yProps(1)} />
        </CustomTabs>
      </Box>
      <TabPanel value={value} index={0}>
        다음 일정
      </TabPanel>
      <TabPanel value={value} index={1}>
        요청
      </TabPanel>
    </Box>
  );
};

export default InfoTabs;
