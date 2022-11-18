import { useState } from "react";

import Schedule from "@components/common/Schedule";
import styles from "@styles/Reservation/Reservation.module.scss";
import {
  CancelButton,
  CancelReason,
} from "@components/Reservation/ReservationItem";
import { ReservationTypes } from "@components/types/types";
import { makeFormat } from "@components/Calendar/common/util";

interface ReservationProps {
  date: string;
  list: ReservationTypes[];
}

const Reservation = ({ date, list }: ReservationProps) => {
  /**
   * 만료된 예약인지 아닌지 계산
   * @returns 만료되었다면 true, 그렇지 않다면 false
   */
  const calculateDate = (time: string): boolean => {
    const reservationDate = new Date(time);

    if (reservationDate < new Date()) return true;
    else return false;
  };

  const timeToString = (start: string, end: string): string => {
    const startTime = new Date(start);
    const endTime = new Date(end);

    const startString =
      makeFormat(startTime.getHours().toString()) +
      ":" +
      makeFormat(startTime.getMinutes().toString());
    const endString =
      makeFormat(endTime.getHours().toString()) +
      ":" +
      makeFormat(endTime.getMinutes().toString());

    return startString + "-" + endString;
  };

  return (
    <div className={styles.reservation}>
      <div className={styles.date}>{date}</div>
      <div className={styles.list}>
        {list.map((item, index) => {
          // 취소하거나 취소된 예약이면
          if (item.type === "CANCEL") {
            return (
              <Schedule
                key={index}
                isCanceled={true}
                color={item.color}
                time={timeToString(item.startDate, item.endDate)}
                userId={item.nickname}
                userImg={item.imagePath}
                userName={item.nickname}
                category={item.categoryName}
                render={() => (
                  <CancelReason
                    reason={item.reason as string}
                    detail={item.contents}
                    nickname={item.nickname}
                    cancelFrom={item.cancelFrom as string}
                  />
                )}
              />
            );
          } else {
            if (calculateDate(item.startDate)) {
              return (
                <Schedule
                  key={index}
                  color={item.color}
                  time={timeToString(item.startDate, item.endDate)}
                  userId={item.nickname}
                  userImg={item.imagePath}
                  userName={item.nickname}
                  category={item.categoryName}
                  render={() => (
                    <div className={styles["detail"]}>{item.contents}</div>
                  )}
                />
              );
            } else {
              return (
                <Schedule
                  key={index}
                  color={item.color}
                  time={timeToString(item.startDate, item.endDate)}
                  userId={item.nickname}
                  userImg={item.imagePath}
                  userName={item.nickname}
                  category={item.categoryName}
                  render={() => (
                    <CancelButton id={item.id} detail={item.contents} />
                  )}
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
