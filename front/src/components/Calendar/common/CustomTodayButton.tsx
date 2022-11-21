import { TodayButton } from "@devexpress/dx-react-scheduler-material-ui";

import styles from "@styles/Calendar/Calendar.module.scss";

export default function CustomTodayButton() {
  return (
    <TodayButton
      messages={{ today: "오늘" }}
      buttonComponent={(props) => (
        <TodayButton.Button {...props} className={styles.today} />
      )}
    />
  );
}
