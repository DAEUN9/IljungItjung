import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import 'devextreme/dist/css/dx.light.css';
import { Scheduler, View, Scrolling } from 'devextreme-react/scheduler';

import setting from '@assets/setting.png';
import styles from '@styles/Calendar/Calendar.module.scss';
import '@styles/Calendar/CustomCalendar.css';

const dayOfWeekNames = ['일', '월', '화', '수', '목', '금', '토'];

const MyCalendar = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const settingEl = document.getElementsByClassName('setting')[0];
    if (!settingEl) {
      const wrapper = document.createElement('div');
      wrapper.className = styles.setting;
      wrapper.innerHTML = `<img src=${setting} alt='setting' />`;
      wrapper.addEventListener('click', () => {
        navigate('/setting');
      });

      const el = document.getElementsByClassName('dx-toolbar-before')[0];
      el.appendChild(wrapper);
    }
  }, []);

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
        <span className={styles.name}>
          {dayOfWeekNames[cellData.date.getDay()]}
        </span>
      </div>
    </div>
  );
}

export default MyCalendar;
