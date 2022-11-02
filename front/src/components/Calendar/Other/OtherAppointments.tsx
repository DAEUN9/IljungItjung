import { Appointments } from '@devexpress/dx-react-scheduler-material-ui';

export default function OtherAppointments() {
  return (
    <Appointments
      appointmentComponent={(props) => (
        <Appointments.Appointment
          {...props}
          style={{ backgroundColor: '#F2F2F2' }}
        />
      )}
      appointmentContentComponent={(props) => {
        return (
          <Appointments.AppointmentContent {...props} children={<div></div>} />
        );
      }}
    />
  );
}
