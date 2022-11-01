import Box from '@mui/material/Box';

import { TabPanelProps } from '@components/Calendar/common/util';

const RequestTab = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      className="request-tab"
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
};

export default RequestTab;