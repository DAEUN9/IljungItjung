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
        const {data} = props;

        return (
          <Appointments.AppointmentContent
            {...props}
            children={
              <>
                <div className={styles['appointment-category']}>
                  {data.title}
                </div>
                <div className={styles['appointment-nickname']}>
                  <Avatar sx={{ width: '18px', height: '18px' }} />
                  <div className={styles['appointment-nickname-wrapper']}>
                    <div>
                      <span style={{ fontWeight: 600 }}>{data.nickname}</span>{' '}
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
