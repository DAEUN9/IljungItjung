import { useSelector } from 'react-redux';

import styles from '@styles/Calendar/Calendar.module.scss';
import { TabPanelProps } from '@components/Calendar/common/util';
import { RootState } from '@modules/index';
import UpcomingItem from './UpcomingItem';

const UpcomingTab = (props: TabPanelProps) => {
  const { value, index, ...other } = props;
  const list = useSelector((state: RootState) => state.mycalendar.list);

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
          <UpcomingItem />
          <UpcomingItem />
          <UpcomingItem />
        </div>
      )}
    </div>
  );
};

export default UpcomingTab;
