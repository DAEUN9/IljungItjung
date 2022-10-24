import 'devextreme/dist/css/dx.light.css';
import { Scheduler, View, Scrolling } from 'devextreme-react/scheduler';

import styles from '@styles/Calendar/Calendar.module.scss';

const dayOfWeekNames = ['일', '월', '화', '수', '목', '금', '토'];

const MyCalendar = () => {
  return (
    <Scheduler
      className={styles['my-calendar']}
      defaultCurrentView="week"
      timeZone="Asia/Seoul"
      showAllDayPanel={false}
      views={['week']}
      startDayHour={9}
      endDayHour={22}
    >
      <View type="week" dateCellRender={renderDateCell} />
      <Scrolling mode="virtual" />
    </Scheduler>
  );
};

function renderDateCell(cellData: any) {
  console.log(cellData);
  return (
    <>
      <div className="name">{dayOfWeekNames[cellData.date.getDay()]}</div>
      <div className="number">{cellData.date.getDate()}</div>
    </>
  );
}

export default MyCalendar;
