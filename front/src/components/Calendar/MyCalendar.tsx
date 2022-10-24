import React, { useEffect } from 'react';
import ReactDOM from 'react-dom/client'
import 'devextreme/dist/css/dx.light.css';
import { Scheduler, View, Scrolling } from 'devextreme-react/scheduler';
import IconButton from '@mui/material/IconButton';
import SettingsIcon from '@mui/icons-material/Settings';

import styles from '@styles/Calendar/Calendar.module.scss';
import '@styles/Calendar/CustomCalendar.css';

const dayOfWeekNames = ['일', '월', '화', '수', '목', '금', '토'];

const MyCalendar = () => {
  useEffect(() => {
    const setting = document.createElement('div');
    setting.className = 'setting';
    setting.innerText = 'setting';
    const el = document.getElementsByClassName('dx-toolbar-before')[0];
    el.appendChild(setting);
    // const settingBtn = React.createElement(IconButton);
    // ReactDOM.createRoot(el as HTMLElement).render(<IconButton><SettingsIcon /></IconButton>)
  }, [])

  return (
    <Scheduler
      className={styles.calendar}
      defaultCurrentView="week"
      timeZone="Asia/Seoul"
      showAllDayPanel={false}
      startDayHour={9}
      endDayHour={22}
      height={'100%'}
      width={'63%'}
    >
      <View type="week" dateCellRender={renderDateCell} firstDayOfWeek={1} />
      <Scrolling mode="virtual" />
    </Scheduler>
  );
};

function renderDateCell(cellData: any) {  
  return (
    <div className={styles['date-cell']}>
      <div>
        <span className={styles.number}>{cellData.date.getDate()}</span>
        <span className={styles.name}>{dayOfWeekNames[cellData.date.getDay()]}</span>
      </div>
    </div>
  );
}

export default MyCalendar;
