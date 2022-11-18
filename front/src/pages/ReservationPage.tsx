import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import Sidebar from "@components/common/Sidebar";
import Period from "@components/Reservation/Period";
import Reservation from "@components/Reservation/Reservation";
import styles from "@styles/Reservation/Reservation.module.scss";
import { getReservations } from "@api/reservation";
import { RootState } from "@modules/index";
import { makeFormat } from "@components/Calendar/common/util";
import { ReservationTypes } from "@components/types/types";

const ReservationPage = () => {
  const [reservations, setReservations] = useState<
    Map<string, ReservationTypes[]>
  >(new Map<string, ReservationTypes[]>());
  const profile = useSelector((state: RootState) => state.profile.profile);

  useEffect(() => {
    // 첫 렌더링 시 현재 날짜로부터 15일 전후를 기간으로 요청한다.
    const date = new Date();
    const start = new Date(
      date.getFullYear(),
      date.getMonth(),
      date.getDate() - 15
    );
    const end = new Date(
      date.getFullYear(),
      date.getMonth(),
      date.getDate() + 15
    );

    const sMonth = start.getMonth() + 1;
    const eMonth = end.getMonth() + 1;
    const sDate = start.getDate();
    const eDate = end.getDate();

    const startDate = `${start.getFullYear()}${makeFormat(
      sMonth.toString()
    )}${makeFormat(sDate.toString())}`;
    const endDate = `${end.getFullYear()}${makeFormat(
      eMonth.toString()
    )}${makeFormat(eDate.toString())}`;

    const map = new Map<string, ReservationTypes[]>();
    getReservations(profile.nickname, startDate, endDate, (res: any) => {
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
      setReservations(new Map([...map].sort().reverse()));
      console.log(map);
    });
  }, []);

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
