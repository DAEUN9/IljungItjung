import Sidebar from "@components/common/Sidebar";
import Period from "@components/Reservation/Period";
import Reservation from "@components/Reservation/Reservation";
import styles from "@styles/Reservation/Reservation.module.scss";
import defaultImg from "@assets/defaultImg.png";

const reservations = [
  {
    date: "10월 16일",
    list: [
      {
        isCanceled: true,
        cancelReason: "지송합니다.",
        detail: "예쁘게 그려주세요.",
        time: "12:00-13:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "예쁜 그림",
        color: "#F4F38A",
      },
      {
        isCanceled: false,
        time: "16:00-17:00",
        userImg: defaultImg,
        detail: "잘 그려주세요.",
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
      {
        isCanceled: false,
        time: "16:00-17:00",
        userImg: defaultImg,
        detail: "잘 그려주세요.",
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
      {
        isCanceled: true,
        time: "16:00-17:00",
        userImg: defaultImg,
        detail: "잘 그려주세요.",
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
      {
        isCanceled: true,
        time: "16:00-17:00",
        userImg: defaultImg,
        detail: "잘 그려주세요.",
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
    ],
  },
  {
    date: "10월 14일",
    list: [
      {
        isCanceled: false,
        detail: "멋지게 그려주세요.",
        time: "12:00-13:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "예쁜 그림",
        color: "#F4F38A",
      },
      {
        isCanceled: false,
        detail: "귀엽게 그려주세요.",
        time: "16:00-17:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
    ],
  },
  {
    date: "10월 12일",
    list: [
      {
        isCanceled: false,
        detail: "멋지게 그려주세요.",
        time: "12:00-13:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "예쁜 그림",
        color: "#F4F38A",
      },
      {
        isCanceled: false,
        detail: "귀엽게 그려주세요.",
        time: "16:00-17:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
    ],
  },
  {
    date: "10월 12일",
    list: [
      {
        isCanceled: false,
        detail: "멋지게 그려주세요.",
        time: "12:00-13:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "예쁜 그림",
        color: "#F4F38A",
      },
      {
        isCanceled: false,
        detail: "귀엽게 그려주세요.",
        time: "16:00-17:00",
        userImg: defaultImg,
        userName: "곰고구마",
        userId: "1",
        category: "멋진 그림",
        color: "#D7CBF4",
      },
    ],
  },
];

const ReservationPage = () => {
  return (
    <div className={styles["reservation-page"]}>
      <Sidebar />
      <div className={styles["content"]}>
        <div className={styles["header"]}>
          <h2>내 예약 목록</h2>
          <Period />
        </div>
        <div className={styles["reservations"]}>
          {reservations.map((reservation, index) => (
            <Reservation
              key={index}
              date={reservation.date}
              list={reservation.list}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default ReservationPage;
