import { Controller, useFormContext } from "react-hook-form";

import styles from "@styles/Calendar/Calendar.module.scss";
import { TextField } from "@components/Calendar/Other/CustomComponents";

const ReservationRequest = () => {
  const { control, register } = useFormContext();

  return (
    <div className={styles["reservation-request"]}>
      <div>요청사항</div>
      <Controller
        control={control}
        name="request"
        defaultValue=""
        render={({ field }) => (
          <TextField
            fullWidth
            multiline
            rows={2}
            {...field}
            {...register("request", {
              maxLength: 100,
            })}
          />
        )}
      />
      <div></div>
    </div>
  );
};

export default ReservationRequest;
