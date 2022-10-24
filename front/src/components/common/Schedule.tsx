import { useEffect, useRef } from "react";

import styles from "@styles/common/Schedule.module.scss";

interface ScheduleProps {
  color: string,
  date?: string,
  time: string,
  userId: string,
  userImg: string,
  userName: string,
  category: string,
  render?: () => JSX.Element
}

const Schedule = ({ color, date = "", time, userId, userImg, userName, category, render }: ScheduleProps) => {
  const colorRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (colorRef.current) colorRef.current.style.backgroundColor = color;
  }, [])

  const handleClickProfile = () => {
    // 해당 유저의 페이지로 이동
    window.location.href = `/calendar/${userId}`;
  }

  return (
    <div className={styles["schedule"]}>
      <div className={styles["top"]}>
        <div className={styles["left"]}>
          <div className={`${styles["color"]}`} ref={colorRef}></div>
          <div className={styles["dateTime"]}>
            {date &&
              <span className={styles["date"]}>{date}&nbsp;</span>
            }
            <span className={styles["time"]}>{time}</span>
          </div>
        </div>
        <div className={styles["right"]} onClick={handleClickProfile}>
          <img src={userImg} />
          <div>{userName}</div>
        </div>
      </div>
      <div className={styles["bottom"]}>
        <div className={styles["category"]}>{category}</div>
        {render && render()}
      </div>
    </div>
  );
}

export default Schedule;