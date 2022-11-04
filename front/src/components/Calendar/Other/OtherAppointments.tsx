import { Appointments } from '@devexpress/dx-react-scheduler-material-ui';

export default function OtherAppointments() {
  return (
    <Appointments
      appointmentComponent={(props) => (
        <Appointments.Appointment
          {...props}
          style={{ backgroundColor: '#6B7BB1' }}
        />
      )}
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
