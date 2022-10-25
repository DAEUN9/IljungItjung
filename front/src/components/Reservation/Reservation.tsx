import { Button } from "@mui/material";
import DeleteForeverOutlinedIcon from "@mui/icons-material/DeleteForeverOutlined";

import Schedule from "@components/common/Schedule";
import styles from "@styles/Reservation/Reservation.module.scss";

interface ListType {
  isCanceled: boolean; // 취소 여부
  cancelReason?: string; // 취소 사유 => 있다면 취소'된' 것, 없다면 취소'한' 것
  detail: string;
  color: string;
  date?: string;
  time: string;
  userId: string;
  userImg: string;
  userName: string;
  category: string;
}

interface ReservationProps {
  date: string;
  list: ListType[];
}

const Reservation = ({ date, list }: ReservationProps) => {
  return (
    <div className={styles.reservation}>
      <div className={styles.date}>{date}</div>
      <div className={styles.list}></div>
    </div>
  );
};

export default Reservation;
