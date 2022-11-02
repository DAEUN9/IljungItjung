import { useSelector } from 'react-redux';

import styles from '@styles/Calendar/Calendar.module.scss';
import { RootState } from '@modules/index';

const Reservation = () => {
  const current = useSelector((state: RootState) => state.othercalendar.time);

  return (
    <div className={styles.reservation}>
      {!current && <div className={styles.center}>시간대를 선택해주세요</div>}
      {current && <div>예약 요청 어쩌구</div>}
    </div>
  );
};

export default Reservation;
