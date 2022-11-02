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
      <div>
        <div>
          <FaThList /> 카테고리 선택
        </div>
        <div>
          <FaRegCalendar /> 날짜
        </div>
        <div>
          <FaRegClock /> 시간
        </div>
        <div>
          <FaPhoneAlt /> 폰번호
        </div>
        <div>
          <div>요청사항</div>
          <TextField />
        </div>
        <CustomButton>확인</CustomButton>
      </div>
    </div>
  );
};

export default Reservation;
