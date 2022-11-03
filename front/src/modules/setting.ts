import { CategoryState } from "@components/types/types";

const SET_CATEGORY = "setting/SET_CATEGORY" as const;
const ADD_CATEGORY = "setting/ADD_CATEGORY" as const;
const DEL_CATEGORY = "setting/DEL_CATEGORY" as const;
const EDIT_CATEGORY = "setting/EDIT_CATEGORY" as const;
const SELECT_CATEGORY = "setting/SELECT_CATEGORY" as const;

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

type SettingAction =
  | ReturnType<typeof setCategory>
  | ReturnType<typeof addCategory>
  | ReturnType<typeof delCategory>
  | ReturnType<typeof editCategory>
  | ReturnType<typeof selectCategory>;

interface SettingState {
  categories: CategoryState[];
  selectedCategory: CategoryState;
  // 기타 달력 정보
}

const initialState: SettingState = {
  categories: [{ id: 0, name: "기본", color: "#D5EAEF", hour: "1", min: "00" }],
  selectedCategory: { id: 0, name: "", color: "", hour: "", min: "" },
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
          (category) => category.id !== action.payload.id
        ),
      };
    case EDIT_CATEGORY:
      return {
        ...state,
        categories: state.categories.map((category) => {
          if (category.id === action.payload.id) return action.payload;
          return category;
        }),
      };
    case SELECT_CATEGORY:
      return {
        ...state,
        selectedCategory: action.payload,
      };
    default:
      return state;
  }
}

export default setting;
