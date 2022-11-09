import { FaPhoneAlt } from "react-icons/fa";
import { Controller, useFormContext } from "react-hook-form";

import styles from "@styles/Calendar/Calendar.module.scss";
import { PhoneTextField } from "@components/Calendar/Other/CustomComponents";

const ReservationPhone = () => {
  const {
    control,
    register,
    formState: { errors },
  } = useFormContext();

  return (
    <div className={styles["reservation-item"]}>
      <div className={styles["icon-long"]}>
        <FaPhoneAlt />
      </div>
      <div style={{ width: "100%" }}>
        <Controller
          control={control}
          name="phone"
          defaultValue=""
          render={({ field }) => (
            <PhoneTextField
              placeholder="연락처"
              {...field}
              {...register("phone", {
                required: "* 연락처를 입력해주세요",
              })}
            />
          )}
        />
        {errors?.phone && (
          <div className={styles.error}>errors.phone.message</div>
        )}
      </div>
    </div>
  );
};

export default ReservationPhone;
