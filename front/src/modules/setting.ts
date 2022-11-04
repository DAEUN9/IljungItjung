import { CategoryState } from "@components/types/types";

const SET_CATEGORY = "setting/SET_CATEGORY" as const;
const ADD_CATEGORY = "setting/ADD_CATEGORY" as const;
const DEL_CATEGORY = "setting/DEL_CATEGORY" as const;
const EDIT_CATEGORY = "setting/EDIT_CATEGORY" as const;
const SELECT_CATEGORY = "setting/SELECT_CATEGORY" as const;
const TOGGLE_LOCK = "setting/TOGGLE_LOCK" as const;

export const setCategory = (categories: CategoryState[]) => ({
  type: SET_CATEGORY,
  payload: categories,
});

export const addCategory = (category: CategoryState) => ({
  type: ADD_CATEGORY,
  payload: category,
});

export const delCategory = (category: CategoryState) => ({
  type: DEL_CATEGORY,
  payload: category,
});

export const editCategory = (category: CategoryState) => ({
  type: EDIT_CATEGORY,
  payload: category,
});

export const selectCategory = (category: CategoryState) => ({
  type: SELECT_CATEGORY,
  payload: category,
});

export const toggleLock = (index: number) => ({
  type: TOGGLE_LOCK,
  payload: index,
});

type SettingAction =
  | ReturnType<typeof setCategory>
  | ReturnType<typeof addCategory>
  | ReturnType<typeof delCategory>
  | ReturnType<typeof editCategory>
  | ReturnType<typeof selectCategory>
  | ReturnType<typeof toggleLock>;

interface SettingState {
  // 카테고리 관련 상태
  categories: CategoryState[];
  selectedCategory: CategoryState;
  // 달력 관련 상태
  lock: boolean[];
}

const initialState: SettingState = {
  categories: [{ name: "기본", color: "#D5EAEF", hour: "1", min: "00" }],
  selectedCategory: { name: "", color: "", hour: "", min: "" },
  lock: [false, false, false, false, false, false, false],
};

function setting(
  state: SettingState = initialState,
  action: SettingAction
): SettingState {
  switch (action.type) {
    case SET_CATEGORY:
      return {
        ...state,
        categories: action.payload,
      };
    case ADD_CATEGORY:
      return {
        ...state,
        categories: state.categories.concat(action.payload),
      };
    case DEL_CATEGORY:
      return {
        ...state,
        categories: state.categories.filter(
          (category) => category.name !== action.payload.name
        ),
      };
    case EDIT_CATEGORY:
      return {
        ...state,
        categories: state.categories.map((category) => {
          if (category.name === state.selectedCategory.name)
            return action.payload;
          return category;
        }),
      };
    case SELECT_CATEGORY:
      return {
        ...state,
        selectedCategory: action.payload,
      };
    case TOGGLE_LOCK:
      return {
        ...state,
        lock: state.lock.map((day, index) => {
          if (index === action.payload) return !day;
          return day;
        }),
      };
    default:
      return state;
  }
}

export default setting;
