import { AppointmentTooltip } from '@devexpress/dx-react-scheduler-material-ui';

export default function CustomAppointmentTooltip() {
  return (
    <AppointmentTooltip
      contentComponent={(props) => {
        const appointmentData = props.appointmentData;
        return (
          <AppointmentTooltip.Content
            {...props}
            children={
              <>
                <div>{appointmentData?.startDate?.toString()} - {appointmentData?.endDate?.toString()}</div>
                <div>{appointmentData?.phone}</div>
                <div>{appointmentData?.desc}</div>
                <div>{appointmentData?.nickname}</div>
              </>
            }
          />
        );
      }}
    />
  );
}
