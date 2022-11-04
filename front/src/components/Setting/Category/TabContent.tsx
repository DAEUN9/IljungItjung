import {
  FormControl,
  MenuItem,
  Select,
  Snackbar,
  TextField,
} from "@mui/material";
import { useEffect, useState } from "react";
import { CirclePicker } from "react-color";
import { ThemeProvider } from "@emotion/react";
import { useDispatch, useSelector } from "react-redux";

import styles from "@styles/Setting/TabContent.module.scss";
import CustomButton from "@components/common/CustomButton";
import theme from "@components/common/theme";
import { CategoryState } from "@components/types/types";
import { addCategory, editCategory, selectCategory } from "@modules/setting";
import { RootState } from "@modules/index";

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

interface TabContentProps {
  flag: boolean;
}

const TabContent = ({ flag }: TabContentProps) => {
  const categories = useSelector(
    (state: RootState) => state.setting.categories
  );
  const selectedCategory = useSelector(
    (state: RootState) => state.setting.selectedCategory
  );

  const dispatch = useDispatch();
  const onAddCategory = (category: CategoryState) =>
    dispatch(addCategory(category));
  const onEditCategory = (category: CategoryState) =>
    dispatch(editCategory(category));
  const onSelectCategory = (category: CategoryState) =>
    dispatch(selectCategory(category));

  const [add, setAdd] = useState({
    name: "",
    hour: "1",
    min: "00",
    color: "#D5EAEF",
  });
  const [edit, setEdit] = useState(selectedCategory);
  const [snackbar, setSnackbar] = useState(false);

  const init = { name: "", hour: "1", min: "00", color: "#D5EAEF" };

  useEffect(() => {
    setEdit(selectedCategory);
  }, [selectedCategory]);

  const handleSubmitForm = () => {
    // flag가 false인 경우 추가, true인 경우 변경
    if (!flag) {
      if (add.name.length > 0) {
        onAddCategory(add);
        setAdd(init);
      } else {
        setSnackbar(true);
      }
    } else {
      if (edit.name.length > 0) {
        onEditCategory(edit);
        onSelectCategory(init);
      } else {
        setSnackbar(true);
      }
    }
  };

  return (
    <div className={styles["tab-content"]}>
      {flag && selectedCategory.name === "" ? (
        <div className={styles["no-selected-category"]}>
          수정할 카테고리를 선택해 주세요.
        </div>
      ) : (
        <ThemeProvider theme={theme}>
          <div className={styles["category-name"]}>
            <h3>카테고리명</h3>
            <TextField
              FormHelperTextProps={{ className: styles["helper-text"] }}
              size="small"
              variant="standard"
              placeholder="카테고리명을 입력해주세요."
              inputProps={{ maxLength: 15 }}
              fullWidth
              value={!flag ? add.name : edit.name}
              onChange={(e) => {
                if (!flag) {
                  setAdd({ ...add, name: e.target.value });
                } else {
                  setEdit({ ...edit, name: e.target.value });
                }
              }}
              helperText={
                !flag ? `${add.name.length} / 15` : `${edit.name.length} / 15`
              }
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
                  value={!flag ? add.hour : edit.hour}
                  onChange={(e) => {
                    if (!flag) {
                      setAdd({ ...add, hour: e.target.value });
                    } else {
                      setEdit({ ...edit, hour: e.target.value });
                    }
                  }}
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
                  value={!flag ? add.min : edit.min}
                  onChange={(e) => {
                    if (!flag) {
                      setAdd({ ...add, min: e.target.value });
                    } else {
                      setEdit({ ...edit, min: e.target.value });
                    }
                  }}
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
              color={!flag ? add.color : edit.color}
              onChange={(color) => {
                if (!flag) {
                  setAdd({ ...add, color: color.hex });
                } else {
                  setEdit({ ...edit, color: color.hex });
                }
              }}
            />
          </div>
          <div className={styles["submit-button"]}>
            <CustomButton children="완료" onClick={handleSubmitForm} />
          </div>
        </ThemeProvider>
      )}
    </div>
  );
};

export default TabContent;
