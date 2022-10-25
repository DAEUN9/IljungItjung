import { ComponentType, ReactNode, useState } from 'react';
import Paper from '@mui/material/Paper';
import Avatar from '@mui/material/Avatar';
import AvatarGroup from '@mui/material/AvatarGroup';
import IconButton from '@mui/material/IconButton';
import SettingsIcon from '@mui/icons-material/Settings';
import {
  ViewState,
  TodayButton,
  Toolbar,
} from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Toolbar as MuiToolbar,
  DateNavigator,
  Appointments,
  TodayButton as MuiTodayButton,
} from '@devexpress/dx-react-scheduler-material-ui';

import styles from '@styles/Calendar/Calendar.module.scss';
import '@styles/Calendar/CustomCalendar.css';

interface ButtonProps {
  /** Function that sets the Scheduler's current date. */
  setCurrentDate: (nextDate: Date) => void;
  /** Returns a localization message by the message key. */
  getMessage: (messageKey: string) => string;
  className?: string;
  style?: React.CSSProperties;
  [x: string]: any;
}

interface RootProps {
  /** A React node to be placed in the toolbar. */
  children?: React.ReactNode;
}

interface FlexibleSpaceProps {
  /** A React node that should be placed inside the empty area. */
  children?: React.ReactNode;
}

const CustomTodayButton: ComponentType<ButtonProps> = (props) => (
  <MuiTodayButton.Button {...props} className={styles.today} />
);

const CustomToolbarRoot: ComponentType<RootProps> = ({ children }) => (
  <MuiToolbar.Root>{children}</MuiToolbar.Root>
);

const CustomToolbarFlexibleSpace: ComponentType<FlexibleSpaceProps> = ({
  children,
}) => <MuiToolbar.FlexibleSpace>{children}</MuiToolbar.FlexibleSpace>;

const CustomerList = () => (
  <AvatarGroup
    max={4}
    sx={{
      '& .MuiAvatar-root': { width: 30, height: 30, fontSize: 15 },
    }}
  >
    <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
    <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg" />
    <Avatar alt="Agnes Walker" src="/static/images/avatar/4.jpg" />
    <Avatar alt="Trevor Henderson" src="/static/images/avatar/5.jpg" />
    <Avatar alt="Trevor Henderson" src="/static/images/avatar/5.jpg" />
  </AvatarGroup>
);

const schedulerData = [
  {
    startDate: '2022-10-25T09:45',
    endDate: '2022-10-25T11:00',
    title: 'Meeting',
  },
  {
    startDate: '2022-10-26T12:00',
    endDate: '2022-10-26T13:30',
    title: 'Go to a gym',
  },
  {
    startDate: '2022-10-30T12:00',
    endDate: '2022-10-30T13:30',
    title: 'Go to a gym',
  },
];

const MyCalendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());

  return (
    <Paper className={styles['calendar-container']}>
      <Scheduler data={schedulerData} locale="ko-KR" firstDayOfWeek={1}>
        <ViewState
          currentDate={currentDate}
          onCurrentDateChange={(currentDate) => setCurrentDate(currentDate)}
        />
        <WeekView startDayHour={9} endDayHour={22} />
        <Toolbar
          rootComponent={CustomToolbarRoot}
          flexibleSpaceComponent={() => (
            <CustomToolbarFlexibleSpace
              children={
                // <div className={styles['customer-list']}>
                //   <IconButton>
                //     <SettingsIcon />
                //   </IconButton>
                // </div>
                <CustomerList />
              }
            />
          )}
        />
        <TodayButton
          messages={{ today: '오늘' }}
          buttonComponent={CustomTodayButton}
        />
        <DateNavigator />
        <Appointments />
      </Scheduler>
    </Paper>
  );
};

export default MyCalendar;
