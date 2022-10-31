import { AppointmentTooltip } from '@devexpress/dx-react-scheduler-material-ui';
import SvgIcon from '@mui/material/SvgIcon';
import { FaRegClock, FaUserAlt, FaPhoneAlt, FaPen } from 'react-icons/fa';

import styles from '@styles/Calendar/Calendar.module.scss';
import { days } from './util';

const formatDate = (preDate: string | undefined) => {
  if (typeof preDate === 'undefined') return;

  const newDate = new Date(preDate);

  const year = newDate.getFullYear();
  const month = newDate.getMonth();
  const date = newDate.getDate();
  const day = days[newDate.getDay()];

  const fullDate =
    year + '년 ' + (month + 1) + '월 ' + date + '일 ' + day + '요일';

  return fullDate;
};

const formatTime = (
  startDate: string | undefined,
  endDate: string | undefined
) => {
  if (typeof startDate === 'undefined' || typeof endDate === 'undefined')
    return;

  const start = new Date(startDate);
  const end = new Date(endDate);

  let startHour = start.getHours();
  const startMinutes =
    start.getMinutes() < 10
      ? '0' + start.getMinutes().toString()
      : start.getMinutes().toString();

  let endHour = end.getHours();
  const endMinutes =
    end.getMinutes() < 10
      ? '0' + end.getMinutes().toString()
      : end.getMinutes().toString();

  let startTime = startHour < 12 ? '오전 ' : '오후 ';
  if (startHour > 12) {
    startHour -= 12;
  }
  startTime += startHour + ':' + startMinutes;

  let endTime = endHour < 12 ? '오전 ' : '오후 ';
  if (endHour > 12) {
    endHour -= 12;
  }
  endTime += endHour + ':' + endMinutes;

  return startTime + ' - ' + endTime;
};

export default function CustomAppointmentTooltip() {
  return (
    <AppointmentTooltip
      contentComponent={(props) => {
        const appointmentData = props.appointmentData;
        const startDate = appointmentData?.startDate?.toString();
        const endDate = appointmentData?.endDate?.toString();

        formatDate(startDate);

        return (
          <AppointmentTooltip.Content
            {...props}
            children={
              <div className={styles['tooltip-content']}>
                <div className={styles['tooltip-title']}>
                  <SvgIcon
                    className={styles.svg}
                    sx={{ color: appointmentData?.color }}
                  >
                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2z"></path>
                  </SvgIcon>
                  <div>
                    <div>{appointmentData?.title}</div>
                    <div>{formatDate(startDate)}</div>
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <FaRegClock />
                  <div className={styles['tooltip-inner']}>
                    {formatTime(startDate, endDate)}
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <FaUserAlt />
                  <div className={styles['tooltip-inner']}>
                    {appointmentData?.nickname} 님
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <FaPhoneAlt />
                  <div className={styles['tooltip-inner']}>
                    {appointmentData?.phone}
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <FaPen />
                  <div className={styles['tooltip-inner']}>
                    {appointmentData?.desc}
                  </div>
                </div>
              </div>
            }
          />
        );
      }}
    />
  );
}
