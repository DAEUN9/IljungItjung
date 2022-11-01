import {
  FormControl,
  Input,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import React, { useState } from "react";
import { CirclePicker } from "react-color";
import { ThemeProvider } from "@emotion/react";

import styles from "@styles/Setting/Tab.module.scss";
import CustomButton from "@components/common/CustomButton";
import theme from "@components/common/theme";

const hours = [
  "0",
  "1",
  "2",
  "3",
  "4",
  "5",
  "6",
  "7",
  "8",
  "9",
  "10",
  "11",
  "12",
];

const colorList = [
  "#D5EAEF",
  "#FFC4C2",
  "#C3DBE3",
  "#F0F4C4",
  "#D7CBF4",
  "#F4F38A",
  "#F6D9C8",
  "#DEFFBC",
  "#D0E3CC",
  "#D7DFFF",
  "#FDEEC6",
  "#E8D5D5",
  "#ECF2DF",
  "#D5ECE4",
  "#FFE3F4",
  "#D8DFF1",
];

const AddTab = () => {
  const [categoryName, setCategoryName] = useState("");
  const [hour, setHour] = useState("1");
  const [min, setMin] = useState("00");
  const [value, setValue] = useState(1);

  const handleChangeCategory = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCategoryName(event.target.value);
  };

  const handleChangeHour = (event: SelectChangeEvent) => {
    setHour(event.target.value);
  };

  const handleChangeMin = (event: SelectChangeEvent) => {
    setMin(event.target.value);
  };

  const handleClickSubmit = () => {};

  return (
    <div className={styles["tab"]}>
      <ThemeProvider theme={theme}>
        <div className={styles["category-name"]}>
          <h3>카테고리명</h3>
          <Input
            placeholder="카테고리명을 입력해주세요."
            onChange={handleChangeCategory}
            className={styles["text-field"]}
          />
        </div>
        <div className={styles["time-taken"]}>
          <div className={styles["title"]}>
            <h3>소요시간</h3>
            <span>* 30분 단위로 입력</span>
          </div>
          <div className={styles["dropdown"]}>
            <FormControl className={styles["hour"]} size="small">
              <Select
                label=""
                className={styles["select"]}
                value={hour}
                onChange={handleChangeHour}
                displayEmpty
                inputProps={{ "aria-label": "Without label" }}
              >
                {hours.map((hour, index) => (
                  <MenuItem key={index} value={hour}>
                    {hour}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <span>시간</span>
            <FormControl className={styles["min"]} size="small">
              <Select
                className={styles["select"]}
                value={min}
                onChange={handleChangeMin}
                displayEmpty
              >
                <MenuItem value={"00"}>00</MenuItem>
                <MenuItem value={"30"}>30</MenuItem>
              </Select>
            </FormControl>
            <span>분</span>
          </div>
        </div>
        <div className={styles["category-color"]}>
          <h3>카테고리 색상</h3>
          <CirclePicker className={styles["color-picker"]} colors={colorList} />
        </div>
        <div className={styles["submit-button"]}>
          <CustomButton children="완료" onClick={handleClickSubmit} />
        </div>
      </ThemeProvider>
    </div>
  );
};

export default AddTab;
