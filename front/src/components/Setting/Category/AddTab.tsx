import {
  FormControl,
  MenuItem,
  Select,
  Snackbar,
  TextField,
} from "@mui/material";
import { useState } from "react";
import { CirclePicker } from "react-color";
import { ThemeProvider } from "@emotion/react";
import { useDispatch } from "react-redux";

import styles from "@styles/Setting/Tab.module.scss";
import CustomButton from "@components/common/CustomButton";
import theme from "@components/common/theme";
import { CategoryState } from "@components/types/types";
import { addCategory } from "@modules/setting";

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
  const [color, setColor] = useState("#D5EAEF");
  const [name, setName] = useState("");
  const [hour, setHour] = useState("1");
  const [min, setMin] = useState("00");
  const [snackbar, setSnackbar] = useState(false);

  const dispatch = useDispatch();
  const onAddCategory = (category: CategoryState) =>
    dispatch(addCategory(category));

  const handleSubmitForm = () => {
    if (name.trim().length > 0) {
      onAddCategory({
        name: name,
        hour: hour,
        min: min,
        color: color,
      });

      setName("");
      setHour("1");
      setMin("00");
      setColor("#D5EAEF");
    } else {
      setSnackbar(true);
    }
  };

  return (
    <div className={styles["tab"]}>
      <ThemeProvider theme={theme}>
        <div className={styles["category-name"]}>
          <h3>카테고리명</h3>
          <TextField
            size="small"
            variant="standard"
            placeholder="카테고리명을 입력해주세요."
            inputProps={{ maxLength: 15 }}
            fullWidth
            value={name}
            onChange={(e) => setName(e.target.value)}
            helperText={`${name.length}/15`}
          />
        </div>
        <Snackbar
          open={snackbar}
          autoHideDuration={6000}
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          onClose={() => setSnackbar(false)}
          message="카테고리 이름을 입력해주세요."
        />
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
                onChange={(e) => setHour(e.target.value)}
                displayEmpty
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
                onChange={(e) => setMin(e.target.value)}
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
          <CirclePicker
            className={styles["color-picker"]}
            colors={colorList}
            color={color}
            onChange={(color) => {
              setColor(color.hex);
            }}
          />
        </div>
        <div className={styles["submit-button"]}>
          <CustomButton children="완료" onClick={handleSubmitForm} />
        </div>
      </ThemeProvider>
    </div>
  );
};

export default AddTab;
