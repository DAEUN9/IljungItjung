import { SchedulerDate } from '@components/Calendar/common/util';

/* action type */
const SET_SCHEDULE_LIST = 'mycalendar/SET_SCHEDULE_LIST' as const;

/* action creator */
export const setScheduleList = (list:  SchedulerDate[]) => ({
  type: SET_SCHEDULE_LIST,
  payload: list,
});

type MyCalendarAction = ReturnType<typeof setScheduleList>;

export interface MyCalendarState {
  list?:  SchedulerDate[];
}

const initialState: MyCalendarState = {};

export default function reducer(
  state: MyCalendarState = initialState,
  action: MyCalendarAction
) {
  switch (action.type) {
    case SET_SCHEDULE_LIST:
      return { ...state, list: action.payload };
    default:
      return state;
  }
}
