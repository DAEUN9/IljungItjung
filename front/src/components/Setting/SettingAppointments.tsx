import { Appointments } from "@devexpress/dx-react-scheduler-material-ui";
import { RiDeleteBinLine } from "react-icons/ri";
import { IconButton } from "@mui/material";
import { useDispatch } from "react-redux";

import styles from "@styles/Setting/SettingAppointments.module.scss";
import { setDeleteSchedule } from "@modules/setting";
import { AppointmentsTypes } from "@components/types/types";

export default function SettingAppointments() {
  const dispatch = useDispatch();

  return (
    <>
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
                  <IconButton
                    onClick={() => {
                      dispatch(setDeleteSchedule(data as AppointmentsTypes));
                    }}
                  >
                    <RiDeleteBinLine size="16" />
                  </IconButton>
                </div>
              }
            />
          );
        }}
      />
    </>
  );
}
