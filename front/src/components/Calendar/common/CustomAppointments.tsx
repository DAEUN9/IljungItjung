import { Appointments } from '@devexpress/dx-react-scheduler-material-ui';
import Avatar from '@mui/material/Avatar';

import styles from '@styles/Calendar/Calendar.module.scss';

export default function CustomAppointments() {
  return (
    <Appointments
      appointmentComponent={(props) => (
        <Appointments.Appointment
          {...props}
          style={{ backgroundColor: '#D7CBF4' }}
        />
      )}
      appointmentContentComponent={(props) => (
        <Appointments.AppointmentContent
          {...props}
          children={
            <>
              <div className={styles['appointment-category']}>
                카테고리명이 길면 우째
              </div>
              <div className={styles['appointment-nickname']}>
                <Avatar sx={{ width: '18px', height: '18px' }} />
                <div className={styles['appointment-nickname-wrapper']}>
                  <div>
                    <span style={{ fontWeight: 600 }}>nickname</span> 님
                  </div>
                </div>
              </div>
            </>
          }
        />
      )}
    />
  );
}
