import { SettingCategoryState } from "@components/types/types";

// 카테고리 관련
const SET_CATEGORY = "setting/SET_CATEGORY" as const;
const ADD_CATEGORY = "setting/ADD_CATEGORY" as const;
const DEL_CATEGORY = "setting/DEL_CATEGORY" as const;
const EDIT_CATEGORY = "setting/EDIT_CATEGORY" as const;
const SELECT_CATEGORY = "setting/SELECT_CATEGORY" as const;
const TOGGLE_LOCK = "setting/TOGGLE_LOCK" as const;

// 달력 관련
const TOGGLE_SHADE = "setting/TOGGLE_SHADE" as const;

export const setCategory = (categories: SettingCategoryState[]) => ({
  type: SET_CATEGORY,
  payload: categories,
});

export const addCategory = (category: SettingCategoryState) => ({
  type: ADD_CATEGORY,
  payload: category,
});

export const delCategory = (category: SettingCategoryState) => ({
  type: DEL_CATEGORY,
  payload: category,
});

export const editCategory = (category: SettingCategoryState) => ({
  type: EDIT_CATEGORY,
  payload: category,
});

export const selectCategory = (category: SettingCategoryState) => ({
  type: SELECT_CATEGORY,
  payload: category,
});

export const toggleLock = (index: number) => ({
  type: TOGGLE_LOCK,
  payload: index,
});

export const toggleShade = (date: string) => ({
  type: TOGGLE_SHADE,
  payload: date,
});

type SettingAction =
  | ReturnType<typeof setCategory>
  | ReturnType<typeof addCategory>
  | ReturnType<typeof delCategory>
  | ReturnType<typeof editCategory>
  | ReturnType<typeof selectCategory>
  | ReturnType<typeof toggleLock>
  | ReturnType<typeof toggleShade>;

interface SettingState {
  // 카테고리 관련 상태
  categories: SettingCategoryState[];
  selectedCategory: SettingCategoryState;
  // 달력 관련 상태
  lock: boolean[];
  set: Set<string>;
}

const initialState: SettingState = {
  categories: [{ name: "기본", color: "#D5EAEF", hour: "1", min: "00" }],
  selectedCategory: { name: "", color: "", hour: "", min: "" },
  lock: [false, false, false, false, false, false, false],
  set: new Set<string>(),
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
    case TOGGLE_SHADE: {
      const copy: Set<string> = new Set<string>(state.set);
      if (copy.has(action.payload)) copy.delete(action.payload);
      else copy.add(action.payload);

      return {
        ...state,
        set: copy,
      };
    }
    default:
      return state;
  }
}

export default setting;
