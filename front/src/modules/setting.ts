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
const LOCK_SHADE = "setting/LOCK_SHADE" as const;
const DELETE_LOCK_SHADE = "setting/DELETE_LOCK_SHADE" as const;

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

export const lockShade = (day: number, time: string) => ({
  type: LOCK_SHADE,
  payload: {
    day: day,
    time: time,
  },
});

export const deleteLockShade = (day: number, time: string, all?: boolean) => ({
  type: DELETE_LOCK_SHADE,
  payload: {
    day: day,
    time: time,
    all: all,
  },
});

type SettingAction =
  | ReturnType<typeof setCategory>
  | ReturnType<typeof addCategory>
  | ReturnType<typeof delCategory>
  | ReturnType<typeof editCategory>
  | ReturnType<typeof selectCategory>
  | ReturnType<typeof toggleLock>
  | ReturnType<typeof toggleShade>
  | ReturnType<typeof lockShade>
  | ReturnType<typeof deleteLockShade>;

interface SettingState {
  // 카테고리 관련 상태
  categories: SettingCategoryState[];
  selectedCategory: SettingCategoryState;
  // 달력 관련 상태
  lock: boolean[];
  set: Set<string>;
  lockMap: Map<number, string[]>; // 요일, 시간
}

const initialState: SettingState = {
  categories: [{ name: "기본", color: "#D5EAEF", hour: "1", min: "00" }],
  selectedCategory: { name: "", color: "", hour: "", min: "" },
  lock: [false, false, false, false, false, false, false],
  set: new Set<string>(),
  lockMap: new Map<number, string[]>(),
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
    case LOCK_SHADE: {
      const copy: Map<number, string[]> = new Map<number, string[]>(
        state.lockMap
      );
      if (!copy.has(action.payload.day)) {
        const arr: string[] = [];
        copy.set(action.payload.day, arr);
      }

      copy.get(action.payload.day)?.push(action.payload.time);

      return {
        ...state,
        lockMap: copy,
      };
    }
    case DELETE_LOCK_SHADE: {
      // all 옵션이 주어지면 전부 지운다.
      if (action.payload.all) {
        return {
          ...state,
          lockMap: new Map<number, string[]>(),
        };
      } else {
        const copy: Map<number, string[]> = new Map<number, string[]>(
          state.lockMap
        );
        let list = copy.get(action.payload.day);

        if (list) {
          list = list.filter((item) => item !== action.payload.time);
          copy.set(action.payload.day, list);
        }

        return {
          ...state,
          lockMap: copy,
        };
      }
    }
    default:
      return state;
  }
}

export default setting;
