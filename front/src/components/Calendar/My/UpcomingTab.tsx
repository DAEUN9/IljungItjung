import { useSelector } from "react-redux";

import styles from "@styles/Calendar/Calendar.module.scss";
import UpcomingItem from "./UpcomingItem";
import { TabPanelProps } from "@components/Calendar/common/util";
import { RootState } from "@modules/index";
import { SchedulerDate } from "@components/types/types";

// 이번주 일정 중에서 오늘 날짜보다 큰 것만 표시
const plus = [0, 6, 5, 4, 3, 2, 1];

const parseList = (list: SchedulerDate[] | undefined) => {
  if (!list) return [];

  let parsed: SchedulerDate[] = [];
  const today = new Date();
  const lastDay = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate() + plus[today.getDay()]
  );
  lastDay.setHours(23);
  lastDay.setMinutes(59);

  for (let item of list) {
    const { startDate } = item;
    if (!startDate) continue;

    const start = new Date(startDate);

    if (start >= today && start <= lastDay) {
      parsed.push(item);
    }
  }

  return parsed;
};

const UpcomingTab = (props: TabPanelProps) => {
  const { value, index, ...other } = props;
  const list = useSelector((state: RootState) => state.mycalendar.list);
  const parsed = parseList(list);

  return (
    <div
      className={styles.tab}
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${index}`}
      {...other}
    >
      {value === index &&
        (parsed.length === 0 ? (
          <div className={styles.center}>다음 일정이 없습니다.</div>
        ) : (
          <div className={styles["tab-inner"]}>
            {parsed.map((item) => (
              <UpcomingItem key={item.id} item={item} />
            ))}
          </div>
        ))}
    </div>
  );
};

export default UpcomingTab;
