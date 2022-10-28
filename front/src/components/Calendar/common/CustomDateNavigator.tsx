import { DateNavigator } from '@devexpress/dx-react-scheduler-material-ui';

import styles from '@styles/Calendar/Calendar.module.scss';

const formatText = (text: string | undefined) => {
  if (typeof text === 'undefined') return;

  const arr = text.split(' ');
  let value = '';

  switch (arr.length) {
    case 3:
      const arr2 = arr[0].split('-');
      value += arr[1] + ' ' + arr[2] + ' ' + arr2[0] + ' - ' + arr2[1];    
      break;
    case 6:
      value += arr[3] + ' ' + arr[0] + ' ' + arr[1] + ' ';
      value += arr[2] + ' ' + arr[4] + ' ' + arr[5];
      break;
    case 7:
      arr[0] = '20' + arr[0];
      arr[4] = '20' + arr[4];
      for(let a of arr) {
        value += a + ' ';
      }
      break;
  }

  return value;
};

export default function CustomDateNavigator() {
  return (
    <DateNavigator
      openButtonComponent={(props) => {
        return (
          <DateNavigator.OpenButton
            {...props}
            className={styles['open-button']}
            text={formatText(props.text)}
          />
        );
      }}
      navigationButtonComponent={(props) => (
        <DateNavigator.NavigationButton
          {...props}
          className={styles.navigator}
        />
      )}
    />
  );
}
