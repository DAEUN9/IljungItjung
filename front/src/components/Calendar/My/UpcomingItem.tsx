import styles from '@styles/Calendar/Calendar.module.scss';
import Schedule from '@components/common/Schedule';
import iljung from '@assets/defaultImg.png';
import DetailInfo from './DetailInfo';
import { formatTime, getFullDate, InfoItemProps } from '@components/Calendar/common/util';

const UpcomingItem = ({ item }: InfoItemProps) => {
  const { color, startDate, endDate, title, nickname, phone, desc } = item;
  const time = formatTime(startDate?.toString(), endDate?.toString());

  return (
    <div className={styles['info-item']}>
      <Schedule
        color={color}
        date={getFullDate(new Date(startDate.toString()))}
        time={time ?? '-'}
        userId="유저아이디"
        userName={nickname}
        category={title ?? '-'}
        userImg={iljung}
        render={() => <DetailInfo phone={phone} desc={desc} />}
      />
    </div>
  );
};

export default UpcomingItem;
