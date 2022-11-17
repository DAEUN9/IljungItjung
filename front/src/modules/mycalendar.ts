import { SchedulerDate } from "@components/types/types";

/* action type */
const SET_SCHEDULE_LIST = "mycalendar/SET_SCHEDULE_LIST" as const;
const SET_REQUEST_LIST = "mycalendar/SET_REQUEST_LIST" as const;
const ADD_SCHEDULE = "mycalendar/ADD_SCHEDULE" as const;
const DELETE_REQUEST = "mycalendar/DELETE_REQUEST" as const;

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

export const deleteRequest = (id: number) => ({
  type: DELETE_REQUEST,
  payload: id,
});

/* function */
const filterRequest = (request: SchedulerDate[], id: number) => {
  const filtered = request.filter((item) => item.id !== id);
  return filtered;
};

type MyCalendarAction =
  | ReturnType<typeof setScheduleList>
  | ReturnType<typeof setRequestList>
  | ReturnType<typeof addSchedule>
  | ReturnType<typeof deleteRequest>;

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
      return {
        ...state,
        list,
        request: filterRequest(state.request, action.payload.id),
      };
    case DELETE_REQUEST:
      const request = filterRequest(state.request, action.payload);
      return { ...state, request };
    default:
      return state;
  }
}
