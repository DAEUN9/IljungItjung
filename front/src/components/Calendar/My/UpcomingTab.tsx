import { useSelector } from "react-redux";

import styles from "@styles/Calendar/Calendar.module.scss";
import UpcomingItem from "./UpcomingItem";
import { TabPanelProps, parseList } from "@components/Calendar/common/util";
import { RootState } from "@modules/index";

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
