import { useSelector } from "react-redux";

import styles from "@styles/Calendar/Calendar.module.scss";
import { TabPanelProps } from "@components/Calendar/common/util";
import { RootState } from "@modules/index";
import RequestItem from "./RequestItem";
import useSnackbar, { Context } from "./useSnackbar";

const RequestTab = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;
  const request = useSelector((state: RootState) => state.mycalendar.request);
  const snackbar = useSnackbar();

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
        (request.length === 0 ? (
          <div className={styles.center}>예약 요청이 없습니다.</div>
        ) : (
          <Context.Provider value={snackbar}>
            <div className={styles["tab-inner"]}>
              {request.map((item) => (
                <RequestItem key={item.id} item={item} />
              ))}
            </div>
          </Context.Provider>
        ))}
      {snackbar.component}
    </div>
  );
};

export default RequestTab;
