import { getStringFromDate } from "@components/Calendar/common/util";
import { BlockState, SchedulerDate } from "@components/types/types";

/* action type */
const SET_SCHEDULE_LIST = "mycalendar/SET_SCHEDULE_LIST" as const;
const SET_REQUEST_LIST = "mycalendar/SET_REQUEST_LIST" as const;
const ADD_SCHEDULE = "mycalendar/ADD_SCHEDULE" as const;
const DELETE_REQUEST = "mycalendar/DELETE_REQUEST" as const;
const SET_BLOCK_LIST = "mycalendar/SET_BLOCK_LIST" as const;

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

export const setBlockList = (
  blockList: BlockState[],
  blockDayList: boolean[]
) => {
  const block = getBlockList(blockList, blockDayList);

  return {
    type: SET_BLOCK_LIST,
    payload: { block, blockDayList },
  };
};

/* function */
function filterRequest(request: SchedulerDate[], id: number) {
  const filtered = request.filter((item) => item.id !== id);
  return filtered;
}

function getBlockList(blockList: BlockState[], blockDayList: boolean[]) {
  const set = new Set<string>();
  const map = new Map<number, string[]>();

  blockDayList.forEach((day, index) => {
    if (day) {
      map.set(index, []);
    }
  });

  blockList.forEach((block) => {
    const startDate = new Date(block.startDate.toString());
    const day = (startDate.getDay() + 6) % 7;
    const time =
      startDate.getHours().toString() + startDate.getMinutes().toString();

    if (blockDayList[day]) {
      map.get(day)?.push(time);
    } else {
      const date = getStringFromDate(startDate) + time;
      set.add(date);
    }
  });

  return { set, map };
}

type MyCalendarAction =
  | ReturnType<typeof setScheduleList>
  | ReturnType<typeof setRequestList>
  | ReturnType<typeof addSchedule>
  | ReturnType<typeof deleteRequest>
  | ReturnType<typeof setBlockList>;

export interface MyCalendarState {
  list: SchedulerDate[];
  request: SchedulerDate[];
  lock: boolean[];
  fixedBlockList: Map<number, string[]>;
  blockList: Set<string>;
}

const initialState: MyCalendarState = {
  list: [],
  request: [],
  lock: [false, false, false, false, false, false, false],
  fixedBlockList: new Map<number, string[]>(),
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
    case SET_BLOCK_LIST:
      const { block, blockDayList } = action.payload;
      console.log(block);
      return {
        ...state,
        blockList: block.set,
        fixedBlockList: block.map,
        lock: blockDayList,
      };
    default:
      return state;
  }
}
