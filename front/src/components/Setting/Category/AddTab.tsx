import {
  FormControl,
  Input,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { CirclePicker } from "react-color";
import { ThemeProvider } from "@emotion/react";
import { useDispatch } from "react-redux";
import { Controller, useForm } from "react-hook-form";

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

interface FormValue {
  name: string;
  hour: string;
  min: string;
}

const AddTab = () => {
  const { register, handleSubmit, watch, control } = useForm();
  const [color, setColor] = useState("#D5EAEF");

  const dispatch = useDispatch();
  const onAddCategory = (category: CategoryState) =>
    dispatch(addCategory(category));

  const handleClickSubmit = (data: FormValue) => {
    console.log(data);
    // if (category.name || category.name !== "") {
    //   onAddCategory(category);
    //   setCategory;
    // }
  };

  return (
    <div className={styles["tab"]}>
      <ThemeProvider theme={theme}>
        <div className={styles["category-name"]}>
          <h3>카테고리명</h3>
          <Controller
            name="name"
            control={control}
            rules={{ required: "카테고리명을 입력해 주세요." }}
            render={({
              field: { onChange, value },
              fieldState: { isDirty, error },
            }) => (
              <TextField
                size="small"
                variant="standard"
                placeholder="카테고리명을 입력해주세요."
                inputProps={{ maxLength: 15 }}
                fullWidth
                value={value}
                onChange={onChange}
                error={!!error}
                helperText={isDirty && `${value.length}/15`}
              />
            )}
          />
          {/* <TextField
            size="small"
            variant="standard"
            error={isSubmitted && (!categoryName || categoryName === "")}
            inputProps={{ maxLength: 15 }}
            placeholder="카테고리명을 입력해주세요."
            onChange={handleChangeCategory}
            className={styles["text-field"]}
            helperText={
              isSubmitted &&
              (!categoryName || categoryName === "") &&
              "카테고리명을 입력해주세요."
            }
          /> */}
        </div>
        <div className={styles["time-taken"]}>
          <div className={styles["title"]}>
            <h3>소요시간</h3>
            <span>* 30분 단위로 입력</span>
          </div>
          <div className={styles["dropdown"]}>
            <Controller
              name="hour"
              control={control}
              render={({ field: { onChange, value } }) => (
                <FormControl className={styles["hour"]} size="small">
                  <Select
                    label=""
                    className={styles["select"]}
                    value={value}
                    onChange={onChange}
                    displayEmpty
                  >
                    {hours.map((hour, index) => (
                      <MenuItem key={index} value={hour}>
                        {hour}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              )}
            />
            <span>시간</span>
            <Controller
              name="min"
              control={control}
              render={({ field: { onChange, value } }) => (
                <FormControl className={styles["min"]} size="small">
                  <Select
                    className={styles["select"]}
                    value={value}
                    onChange={onChange}
                    displayEmpty
                  >
                    <MenuItem value={"00"}>00</MenuItem>
                    <MenuItem value={"30"}>30</MenuItem>
                  </Select>
                </FormControl>
              )}
            />

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
          <CustomButton
            children="완료"
            onSubmit={handleSubmit(handleClickSubmit)}
          />
        </div>
      </ThemeProvider>
    </div>
  );
};

export default AddTab;
