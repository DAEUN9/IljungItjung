import { useSelector } from 'react-redux';

import styles from '@styles/Calendar/Calendar.module.scss';
import UpcomingItem from './UpcomingItem';
import { SchedulerDate, TabPanelProps } from '@components/Calendar/common/util';
import { RootState } from '@modules/index';

const parseList = (list: SchedulerDate[] | undefined) => {
  if(!list) return;

  const today = new Date();
  let parsed: SchedulerDate[] = [];

  for(let item of list) {
    const {startDate} = item;
    if(!startDate) continue;

    const start = new Date(startDate);
    
    if(start.getMonth() >= today.getMonth() && start.getDate() > today.getDate()) {
      parsed.push(item);
    }
    else if(start.getDate() === today.getDate()) {
      if(start.getHours() >= today.getHours() && start.getMinutes() >= today.getHours()) {
        parsed.push(item);
      }
    }
  }

  return parsed;
}

const UpcomingTab = (props: TabPanelProps) => {
  const { value, index, ...other } = props;
  const list = useSelector((state: RootState) => state.mycalendar.list);
  const parsed = parseList(list);

  return (
    <div
      className={styles.tab}
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      {...other}
    >
      {value === index && (
        <div className={styles['tab-inner']}>
          {parsed?.map((item) => <UpcomingItem key={item.id} item={item} />)}
        </div>
      )}
    </div>
  );
};

export default UpcomingTab;
