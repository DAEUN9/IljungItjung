import { Appointments } from '@devexpress/dx-react-scheduler-material-ui';

export default function OtherAppointments() {
  return (
    <Appointments
      appointmentComponent={(props) => {
        const color = props.data.title === 'selected' ? '#6B7BB1' : '#F2F2F2';

        return (
          <Appointments.Appointment
            {...props}
            style={{ backgroundColor: color }}
          />
        );
      }}
      appointmentContentComponent={(props) => {
        return (
          <Appointments.AppointmentContent
            {...props}
            children={<div style={{ height: '100%' }}></div>}
          />
        );
      }}
    />
  );
}
