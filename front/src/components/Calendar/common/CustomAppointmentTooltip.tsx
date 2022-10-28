import { AppointmentTooltip } from '@devexpress/dx-react-scheduler-material-ui';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import PersonIcon from '@mui/icons-material/Person';
import PhoneIcon from '@mui/icons-material/Phone';
import DriveFileRenameOutlineIcon from '@mui/icons-material/DriveFileRenameOutline';

import styles from '@styles/Calendar/Calendar.module.scss';
import { Avatar } from '@mui/material';

const formatDate = (
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

        return (
          <AppointmentTooltip.Content
            {...props}
            children={
              <div className={styles['tooltip-content']}>
                <div className={styles.tooltip}>
                  <AccessTimeIcon />
                  <div className={styles['tooltip-inner']}>
                    {formatDate(startDate, endDate)}
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <PersonIcon />
                  <div className={styles['tooltip-inner']}>
                    {appointmentData?.nickname} 님
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <PhoneIcon />
                  <div className={styles['tooltip-inner']}>
                    {appointmentData?.phone}
                  </div>
                </div>
                <div className={styles.tooltip}>
                  <DriveFileRenameOutlineIcon />
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
