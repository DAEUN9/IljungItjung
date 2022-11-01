import styles from '@styles/Calendar/Calendar.module.scss';
import { TabPanelProps } from '@components/Calendar/common/util';

const UpcomingTab = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      className={styles.tab}
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      {...other}
    >
      {value === index && <div className={styles['tab-inner']}>{children}</div>}
    </div>
  );
};

export default UpcomingTab;
