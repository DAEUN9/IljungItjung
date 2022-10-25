import { FormControl, FormControlLabel, Radio, RadioGroup, ThemeProvider } from "@mui/material";
import { useState } from "react";

import styles from "@styles/Reservation/Reservation.module.scss";
import theme from "@components/common/theme";

const Period = () => {
  const [value, setValue] = useState("1w");

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setValue((event.target as HTMLInputElement).value);
  }

  return (
    <div className={styles["period"]}>
      <ThemeProvider theme={theme}>
        <FormControl>
          <RadioGroup row value={value} onChange={handleChange}>
            <FormControlLabel value="1w" control={<Radio />} label="일주일" />
            <FormControlLabel value="1m" control={<Radio />} label="1개월" />
            <FormControlLabel value="3m" control={<Radio />} label="3개월" />
            <FormControlLabel value="6m" control={<Radio />} label="6개월" />
            <FormControlLabel value="1y" control={<Radio />} label="1년" />
          </RadioGroup>
        </FormControl>
      </ThemeProvider>
    </div>
  );
}

export default Period;