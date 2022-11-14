import { Appointments } from "@devexpress/dx-react-scheduler-material-ui";
import { RiDeleteBinLine } from "react-icons/ri";
import { IconButton } from "@mui/material";

import styles from "@styles/Setting/SettingAppointments.module.scss";

export default function SettingAppointments() {
  const handleDelete = (data: object) => {
    console.log(data);
  };

  return (
    <Appointments
      appointmentComponent={(props) => (
        <Appointments.Appointment
          {...props}
          style={{ backgroundColor: props.data.color }}
        />
      )}
      appointmentContentComponent={(props) => {
        const { data } = props;

        return (
          <Appointments.AppointmentContent
            {...props}
            children={
              <div className={styles["delete-button"]}>
                <IconButton onClick={() => handleDelete(data)}>
                  <RiDeleteBinLine size="16" />
                </IconButton>
              </div>
            }
          />
        );
      }}
    />
  );
}
