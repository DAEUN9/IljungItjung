import { BlockState, SchedulerDate } from "@components/types/types";

/* action type */
const SET_SCHEDULE_LIST = "mycalendar/SET_SCHEDULE_LIST" as const;
const SET_REQUEST_LIST = "mycalendar/SET_REQUEST_LIST" as const;
const ADD_SCHEDULE = "mycalendar/ADD_SCHEDULE" as const;
const DELETE_REQUEST = "mycalendar/DELETE_REQUEST" as const;
const SET_BLOCK_DAY = "mycalendar/SET_BLOCK_DAY" as const;
const SET_BLOCK_LIST = "mycalendar/SET_BLOCK_LIST" as const;
const SET_FIXED_BLOCK_LIST = "mycalendar/SET_FIXED_BLOCK_LIST" as const;

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

export const setBlockDay = (lock: boolean[]) => ({
  type: SET_BLOCK_DAY,
  payload: lock,
});

export const setBlockList = (blockList: Set<string>) => ({
  type: SET_BLOCK_LIST,
  payload: blockList,
})

/* function */
const filterRequest = (request: SchedulerDate[], id: number) => {
  const filtered = request.filter((item) => item.id !== id);
  return filtered;
};

type MyCalendarAction =
  | ReturnType<typeof setScheduleList>
  | ReturnType<typeof setRequestList>
  | ReturnType<typeof addSchedule>
  | ReturnType<typeof deleteRequest>
  | ReturnType<typeof setBlockDay>
  | ReturnType<typeof setBlockList>;

export interface MyCalendarState {
  list: SchedulerDate[];
  request: SchedulerDate[];
  lock: boolean[];
  fixedBlockList: Map<number, string>;
  blockList: Set<string>;
}

const initialState: MyCalendarState = {
  list: [],
  request: [],
  lock: [false, false, false, false, false, false, false],
  fixedBlockList: new Map<number, string>(),
  blockList: new Set<string>(),
};

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
    case SET_BLOCK_DAY:
      return { ...state, lock: action.payload };
    default:
      return state;
  }
}
