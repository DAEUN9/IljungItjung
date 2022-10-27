import { Appointments } from '@devexpress/dx-react-scheduler-material-ui';
import Avatar from '@mui/material/Avatar';

import styles from '@styles/Calendar/Calendar.module.scss';

export default function CustomAppointments() {
  return (
    <Appointments
      appointmentComponent={(props) => (
        <Appointments.Appointment
          {...props}
          style={{ backgroundColor: props.data.color }}
        />
      )}
      appointmentContentComponent={(props) => {
        const [nickname] = props.resources;

        return (
          <Appointments.AppointmentContent
            {...props}
            children={
              <>
                <div className={styles['appointment-category']}>
                  {props.data.title}
                </div>
                <div className={styles['appointment-nickname']}>
                  <Avatar sx={{ width: '18px', height: '18px' }} />
                  <div className={styles['appointment-nickname-wrapper']}>
                    <div>
                      <span style={{ fontWeight: 600 }}>{nickname.text}</span>{' '}
                      님
                    </div>
                  </div>
                </div>
              </>
            }
          />
        );
      }}
    />
  );
}
