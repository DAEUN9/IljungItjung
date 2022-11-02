import { useSelector } from 'react-redux';
import {
  FaThList,
  FaRegCalendar,
  FaRegClock,
  FaPhoneAlt,
} from 'react-icons/fa';

import styles from '@styles/Calendar/Calendar.module.scss';
import { RootState } from '@modules/index';
import { TextField } from '@mui/material';
import CustomButton from '@components/common/CustomButton';

const Reservation = () => {
  const current = useSelector((state: RootState) => state.othercalendar.time);

  return (
    <div className={styles.reservation}>
      {/* {!current && <div className={styles.center}>시간대를 선택해주세요</div>}
      {current && <div>예약 요청 어쩌구</div>} */}
      <div className={styles['reservation-inner']}>
        <div className={styles['reservation-item']}>
          <FaThList /> 카테고리 선택
        </div>
        <div className={styles['reservation-item']}>
          <FaRegCalendar /> 날짜
        </div>
        <div className={styles['reservation-item']}>
          <FaRegClock /> 시간
        </div>
        <div className={styles['reservation-item']}>
          <FaPhoneAlt /> 폰번호
        </div>
        <div className={styles['reservation-request']}>
          <div>요청사항</div>
          <TextField fullWidth multiline rows={2}/>
        </div>
        <CustomButton style={{width:'calc(100% - 10px)', margin: '0 5px'}}>확인</CustomButton>
      </div>
    </div>
  );
};

export default Reservation;
