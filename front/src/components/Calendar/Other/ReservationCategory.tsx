import { FaThList } from "react-icons/fa";
import { Controller, useFormContext } from "react-hook-form";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import OutlinedInput from "@mui/material/OutlinedInput";

import styles from "@styles/Calendar/Calendar.module.scss";
import {
  Select,
  InputLabel,
} from "@components/Calendar/Other/CustomComponents";

const items = [
  {
    categoryName: "예쁜 그림",
    time: "0100",
  },
  {
    categoryName: "멋진 그림",
    time: "0130",
  },
  {
    categoryName: "예쁘고 멋진 그림",
    time: "0300",
  },
];

const ReservationCategory = () => {
  const {
    control,
    register,
    formState: { errors },
  } = useFormContext();

  const getTime = (time: string) => {
    const hours = parseInt(time.slice(0, 2));
    const minutes = parseInt(time.slice(2));
    let fullTime = "";

    if (hours !== 0) fullTime += hours + "시간 ";
    if (minutes !== 0) fullTime += minutes + "분";

    return fullTime;
  };

  return (
    <div className={styles["reservation-item"]}>
      <div className={styles["icon-long"]}>
        <FaThList />
      </div>
      <div style={{ width: "100%" }}>
        <Controller
          control={control}
          name="category"
          defaultValue=""
          render={({ field }) => (
            <FormControl fullWidth>
              <InputLabel id="select-label" size="small">
                카테고리 선택
              </InputLabel>
              <Select
                labelId="select-label"
                id="select"
                label="카테고리 선택"
                input={
                  <OutlinedInput id="select-category" label="카테고리 선택" />
                }
                size="small"
                {...field}
                {...register("category", {
                  required: "카테고리를 선택해주세요",
                })}
              >
                {items.map((item) => (
                  <MenuItem key={item.categoryName} value={item.categoryName}>
                    <div className={styles.menu}>
                      <div>{item.categoryName}</div>
                      <div>{getTime(item.time)}</div>
                    </div>
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
        />
        {errors?.category && (
          <div className={styles.error}>errors.category.message</div>
        )}
      </div>
    </div>
  );
};

export default ReservationCategory;
