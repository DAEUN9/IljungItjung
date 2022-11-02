import { CategoryState } from "@components/types/types";

const SET_CATEGORY = "setting/SET_CATEGORY" as const;
const ADD_CATEGORY = "setting/ADD_CATEGORY" as const;
const DEL_CATEGORY = "setting/DEL_CATEGORY" as const;

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

type SettingAction =
  | ReturnType<typeof setCategory>
  | ReturnType<typeof addCategory>
  | ReturnType<typeof delCategory>;

interface SettingState {
  categories: CategoryState[];
  // 기타 달력 정보
}

const initialState: SettingState = {
  categories: [{ name: "기본", color: "#f4f38a", hour: "1", min: "00" }],
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
    case DEL_CATEGORY: {
      return {
        ...state,
        categories: state.categories.filter(
          (category) => category.name !== action.payload.name
        ),
      };
    }
    default:
      return state;
  }
}

export default setting;
