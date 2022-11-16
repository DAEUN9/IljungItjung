import { SchedulerDate } from "@components/types/types";

/* action type */
const SET_SCHEDULE_LIST = "mycalendar/SET_SCHEDULE_LIST" as const;
const SET_REQUEST_LIST = "mycalendar/SET_REQUEST_LIST" as const;
const ADD_SCHEDULE = "mycalendar/ADD_SCHEDULE" as const;

/* action creator */
export const setScheduleList = (list: SchedulerDate[]) => ({
  type: SET_SCHEDULE_LIST,
  payload: list,
});

export const setRequestList = (list: SchedulerDate[]) => ({
  type: SET_REQUEST_LIST,
  payload: list,
});

export const addSchedule = (schedule: SchedulerDate) => ({
  type: ADD_SCHEDULE,
  payload: schedule,
});

type MyCalendarAction =
  | ReturnType<typeof setScheduleList>
  | ReturnType<typeof setRequestList>
  | ReturnType<typeof addSchedule>;

export interface MyCalendarState {
  list: SchedulerDate[];
  request: SchedulerDate[];
}

const initialState: MyCalendarState = { list: [], request: [] };

export default function reducer(
  state: MyCalendarState = initialState,
  action: MyCalendarAction
) {
  switch (action.type) {
    case SET_SCHEDULE_LIST:
      return { ...state, list: action.payload };
    case SET_REQUEST_LIST:
      return { ...state, request: action.payload };
    case ADD_SCHEDULE:
      const list = state.list.concat([action.payload]);
      return { ...state, list };
    default:
      return state;
  }
}
