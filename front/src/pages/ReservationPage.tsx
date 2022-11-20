import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import Sidebar from "@components/common/Sidebar";
import Period from "@components/Reservation/Period";
import Reservation from "@components/Reservation/Reservation";
import styles from "@styles/Reservation/Reservation.module.scss";
import { getReservations } from "@api/reservation";
import { RootState } from "@modules/index";
import {
  formatReservationDate,
  makeFormat,
} from "@components/Calendar/common/util";
import { ReservationTypes } from "@components/types/types";
import { setReservations } from "@modules/reservation";

const ReservationPage = () => {
  const dispatch = useDispatch();
  const { reservations, startDate, endDate } = useSelector(
    (state: RootState) => state.reservation
  );
  const renderObj = useSelector((state: RootState) => state.render.renderObj);

  useEffect(() => {
    const map = new Map<string, ReservationTypes[]>();
    getReservations(
      formatReservationDate(startDate),
      formatReservationDate(endDate),
      (res: any) => {
        res.data.reservationViewDtoList.map((item: ReservationTypes) => {
          const obj = new Date(item.startDate);
          const date =
            obj.getFullYear().toString() +
            makeFormat((obj.getMonth() + 1).toString()) +
            makeFormat(obj.getDate().toString());

          if (!map.has(date)) {
            const arr: ReservationTypes[] = [];
            map.set(date, arr);
          }

          map.get(date)?.push(item);
        });
        res.data.reservationCancelViewDtoList.map((item: ReservationTypes) => {
          const obj = new Date(item.startDate);
          const date =
            obj.getFullYear().toString() +
            makeFormat((obj.getMonth() + 1).toString()) +
            makeFormat(obj.getDate().toString());

          if (!map.has(date)) {
            const arr: ReservationTypes[] = [];
            map.set(date, arr);
          }

          map.get(date)?.push(item);
        });
        dispatch(setReservations(new Map([...map].sort().reverse())));
      }
    );
  }, [startDate, endDate, renderObj]);

  return (
    <div className={styles["reservation-page"]}>
      <Sidebar />
      <div className={styles["content"]}>
        <div className={styles["header"]}>
          <h2>내 예약 목록</h2>
          <Period />
        </div>
        <div className={styles["reservations"]}>
          {Array.from(reservations.keys()).map((item, index) => (
            <Reservation
              key={index}
              date={`${item.substring(4, 6)}월 ${item.substring(6)}일`}
              list={
                reservations
                  .get(item)
                  ?.sort((a: ReservationTypes, b: ReservationTypes): number => {
                    if (a.startDate > b.startDate) return 1;
                    if (a.startDate < b.startDate) return -1;
                    return 0;
                  }) as ReservationTypes[]
              }
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default ReservationPage;
