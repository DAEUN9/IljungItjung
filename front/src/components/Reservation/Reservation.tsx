import Schedule from "@components/common/Schedule";
import styles from "@styles/Reservation/Reservation.module.scss";
import {
  CancelButton,
  CancelReason,
} from "@components/Reservation/ReservationItem";

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

/**
 * 만료된 예약인지 아닌지 계산
 * @returns 만료되었다면 true, 그렇지 않다면 false
 */
const calculateDate = (date: string, time: string): boolean => {
  const today = new Date();
  return false;
};

const Reservation = ({ date, list }: ReservationProps) => {
  return (
    <div className={styles.reservation}>
      <div className={styles.date}>{date}</div>
      <div className={styles.list}>
        {list.map((item, index) => {
          // 취소하거나 취소된 예약이면
          if (item.isCanceled) {
            if (item.cancelReason) {
              return (
                <Schedule
                  key={index}
                  isCanceled={true}
                  color={item.color}
                  time={item.time}
                  userId={item.userId}
                  userImg={item.userImg}
                  userName={item.userName}
                  category={item.category}
                  render={() => (
                    <CancelReason
                      reason={item.cancelReason as string}
                      detail={item.detail}
                    />
                  )}
                />
              );
            } else {
              return (
                <Schedule
                  key={index}
                  isCanceled={true}
                  color={item.color}
                  time={item.time}
                  userId={item.userId}
                  userImg={item.userImg}
                  userName={item.userName}
                  category={item.category}
                  render={() => (
                    <div
                      className={`${styles["detail"]} ${styles["canceled"]}`}
                    >
                      {item.detail}
                    </div>
                  )}
                />
              );
            }
          } else {
            if (calculateDate(date, item.time)) {
              return (
                <Schedule
                  key={index}
                  color={item.color}
                  time={item.time}
                  userId={item.userId}
                  userImg={item.userImg}
                  userName={item.userName}
                  category={item.category}
                  render={() => (
                    <div className={styles["detail"]}>{item.detail}</div>
                  )}
                />
              );
            } else {
              return (
                <Schedule
                  key={index}
                  color={item.color}
                  time={item.time}
                  userId={item.userId}
                  userImg={item.userImg}
                  userName={item.userName}
                  category={item.category}
                  render={() => <CancelButton detail={item.detail} />}
                />
              );
            }
          }
        })}
      </div>
    </div>
  );
};

export default Reservation;
