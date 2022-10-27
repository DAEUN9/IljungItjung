import { DateNavigator } from '@devexpress/dx-react-scheduler-material-ui';

import styles from '@styles/Calendar/Calendar.module.scss';

export default function CustomDateNavigator() {
  return (
    <DateNavigator
      openButtonComponent={(props) => (
        <DateNavigator.OpenButton
          {...props}
          className={styles['open-button']}
        />
      )}
      navigationButtonComponent={(props) => (
        <DateNavigator.NavigationButton
          {...props}
          className={styles.navigator}
        />
      )}
    />
  );
}
