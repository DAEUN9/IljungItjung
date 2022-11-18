import { SchedulerDate, CategoryState } from "@components/types/types";
import { getStringFromDate } from "@components/Calendar/common/util";

/* action type */
const SET_DISABLED_MAP = "othercalendar/SET_DISABLED_MAP" as const;
const SET_CURRENT = "othercalendar/SET_CURRENT" as const;
const DELETE_CURRENT = "othercalendar/DELETE_CURRENT" as const;
const SET_SELECTED_TIME = "othercalendar/SET_SELECTED_TIME" as const;
const SET_MINUTES = "othercalendar/SET_MINUTES" as const;
const SET_CATEGORY = "othercalendar/SET_CATEGORY" as const;

/* action creator */
export const setDisabledMap = (list: SchedulerDate[]) => ({
  type: SET_DISABLED_MAP,
  payload: list,
});

export const setCurrent = () => ({
  type: SET_CURRENT,
});

export const deleteCurrent = () => ({
  type: DELETE_CURRENT,
});

export const setSelectedTime = (selected: SchedulerDate) => ({
  type: SET_SELECTED_TIME,
  payload: selected,
});

export const setMinutes = (minutes: number) => ({
  type: SET_MINUTES,
  payload: minutes,
});

export const setCategory = (category: CategoryState[]) => ({
  type: SET_CATEGORY,
  payload: category,
});

type OtherCalenderActions =
  | ReturnType<typeof setDisabledMap>
  | ReturnType<typeof setCurrent>
  | ReturnType<typeof deleteCurrent>
  | ReturnType<typeof setSelectedTime>
  | ReturnType<typeof setMinutes>
  | ReturnType<typeof setCategory>;

export interface OtherCalenderState {
  current: SchedulerDate[];
  selected?: SchedulerDate;
  minutes: number;
  map: Map<string, SchedulerDate[]>;
  category: CategoryState[];
}

const initialState: OtherCalenderState = {
  current: [],
  minutes: 0,
  map: new Map<string, SchedulerDate[]>(),
  category: [
    {
      id: 0,
      categoryName: "기본",
      time: "0100",
      color: "#D5EAEF",
    },
  ],
};

export default function reducer(
  state: OtherCalenderState = initialState,
  action: OtherCalenderActions
) {
  switch (action.type) {
    case SET_DISABLED_MAP:
      for (let item of action.payload) {
        const str = getStringFromDate(item.startDate.toString());

        if (!state.map.has(str)) {
          const list: SchedulerDate[] = [];
          state.map.set(str, list);
        }

        state.map.get(str)?.push(item);
      }

      return state;
    case SET_CURRENT:
      if (state.selected) {
        return { ...state, current: [state.selected] };
      } else {
        return state;
      }
    case DELETE_CURRENT:
      return { ...state, current: [] };
    case SET_SELECTED_TIME:
      return { ...state, selected: action.payload };
    case SET_MINUTES:
      return { ...state, minutes: action.payload };
    case SET_CATEGORY:
      return { ...state, category: action.payload };
    default:
      return state;
  }
}
