import CustomTooltip from '@components/common/CustomTooltip';
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
          <Appointments.AppointmentContent
            {...props}
            children={
              <CustomTooltip title="선택할 수 없는 시간대입니다." placement="top">
                <div style={{ height: '100%' }}></div>
              </CustomTooltip>
            }
          />
        );
      }}
    />
  );
}
