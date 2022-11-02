import { SchedulerDate } from '@components/types/types';

/* action type */
const SET_BLOCK_LIST = 'othercalendar/SET_BLOCK_LIST' as const;
const SET_SELECTED_TIME = 'othercalendar/SET_SELECTED_TIME' as const;

/* action creator */
export const setBlockList = (block: SchedulerDate[]) => ({
  type: SET_BLOCK_LIST,
  payload: block,
});

export const setSelectedTime = (time: SchedulerDate) => ({
  type: SET_SELECTED_TIME,
  payload: time,
});

type OtherCalenderActions =
  | ReturnType<typeof setBlockList>
  | ReturnType<typeof setSelectedTime>;

export interface OtherCalenderState {
  block: SchedulerDate[];
  time?: SchedulerDate;
}

const initialState: OtherCalenderState = {
  block: [],
};

export default function reducer(
  state: OtherCalenderState = initialState,
  action: OtherCalenderActions
) {
  switch (action.type) {
    case SET_BLOCK_LIST:
      return { ...state, block: action.payload };
    case SET_SELECTED_TIME:
      return { ...state, time: action.payload };
    default:
      return state;
  }
}
