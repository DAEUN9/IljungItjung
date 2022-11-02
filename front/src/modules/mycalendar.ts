import { SchedulerDate } from '@components/Calendar/common/util';

/* action type */
const SET_SCHEDULE_LIST = 'mycalendar/SET_SCHEDULE_LIST' as const;
const SET_REQUEST_LIST = 'mycalendar/SET_REQUEST_LIST' as const;

/* action creator */
export const setScheduleList = (list: SchedulerDate[]) => ({
  type: SET_SCHEDULE_LIST,
  payload: list,
});

export const setRequestList = (list: SchedulerDate[]) => ({
  type: SET_REQUEST_LIST,
  payload: list,
});

type MyCalendarAction =
  | ReturnType<typeof setScheduleList>
  | ReturnType<typeof setRequestList>;

export interface MyCalendarState {
  list?: SchedulerDate[];
  request: SchedulerDate[];
}

const initialState: MyCalendarState = { request: [] };

export default function reducer(
  state: MyCalendarState = initialState,
  action: MyCalendarAction
) {
  switch (action.type) {
    case SET_SCHEDULE_LIST:
      return { ...state, list: action.payload };
    case SET_REQUEST_LIST:
      return { ...state, request: action.payload };
    default:
      return state;
  }
}
