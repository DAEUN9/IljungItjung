import {
  SchedulerDate,
  CategoryState,
  BlockState,
  RequestState,
} from "@components/types/types";
import { getStringFromDate } from "@components/Calendar/common/util";

interface BlockDate {
  startDate: Date;
  endDate: Date;
}

/* action type */
const SET_CURRENT = "othercalendar/SET_CURRENT" as const;
const DELETE_CURRENT = "othercalendar/DELETE_CURRENT" as const;
const SET_SELECTED_TIME = "othercalendar/SET_SELECTED_TIME" as const;
const SET_MINUTES = "othercalendar/SET_MINUTES" as const;
const SET_CATEGORY = "othercalendar/SET_CATEGORY" as const;
const SET_BLOCK_LIST = "mycalendar/SET_BLOCK_LIST" as const;

/* action creator */
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

export const setBlockList = (
  reservations: RequestState[],
  blockList: BlockState[],
  blockDayList: boolean[]
) => {
  const block = getBlockList(reservations, blockList, blockDayList);

  return {
    type: SET_BLOCK_LIST,
    payload: { block, blockDayList },
  };
};

/* functions */
function getBlockList(
  reservations: RequestState[],
  blockList: BlockState[],
  blockDayList: boolean[]
) {
  const map = new Map<string, BlockDate[]>();
  const fixedMap = new Map<number, BlockDate[]>();

  // 설정한 시간대 블락
  blockDayList.forEach((day, index) => {
    if (day) {
      fixedMap.set(index, []);
    }
  });

  blockList.forEach((block) => {
    const startDate = new Date(block.startDate);
    const endDate = new Date(block.endDate);
    const day = (startDate.getDay() + 6) % 7;

    if (blockDayList[day]) {
      fixedMap.get(day)?.push({ startDate, endDate });
    }
  });

  // 예약, 예약 요청 목록 시간대 블락
  reservations.forEach((reservation) => {
    const startDate = new Date(reservation.startDate);
    const endDate = new Date(reservation.endDate);

    let skip = 30;

    while (startDate < endDate) {
      const date = getStringFromDate(startDate);
      const newStartDate = new Date(startDate.toString());
      const newEndDate = new Date(startDate.toString());
      newEndDate.setMinutes(newEndDate.getMinutes() + 30);

      if (!map.get(date)) {
        map.set(date, []);
      }

      map.get(date)?.push({ startDate: newStartDate, endDate: newEndDate });
      startDate.setMinutes(startDate.getMinutes() + skip);
    }
  });

  return { map, fixedMap };
}

type OtherCalenderActions =
  | ReturnType<typeof setCurrent>
  | ReturnType<typeof deleteCurrent>
  | ReturnType<typeof setSelectedTime>
  | ReturnType<typeof setMinutes>
  | ReturnType<typeof setCategory>
  | ReturnType<typeof setBlockList>;

export interface OtherCalenderState {
  current: SchedulerDate[];
  selected?: SchedulerDate;
  minutes: number;
  category: CategoryState[];
  lock: boolean[];
  fixedBlockList: Map<number, string[]>;
  blockList: Set<string>;
  map: Map<string, BlockDate[]>;
  fixedMap: Map<number, BlockDate[]>;
}

const initialState: OtherCalenderState = {
  current: [],
  minutes: 0,
  category: [
    {
      id: 0,
      categoryName: "기본",
      time: "0100",
      color: "#D5EAEF",
    },
  ],
  lock: [false, false, false, false, false, false, false],
  fixedBlockList: new Map<number, string[]>(),
  blockList: new Set<string>(),
  map: new Map<string, BlockDate[]>(),
  fixedMap: new Map<number, BlockDate[]>(),
};

export default function reducer(
  state: OtherCalenderState = initialState,
  action: OtherCalenderActions
) {
  switch (action.type) {
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
    case SET_BLOCK_LIST:
      const { block, blockDayList } = action.payload;
      return {
        ...state,
        lock: blockDayList,
        map: block.map,
        fixedMap: block.fixedMap,
      };
    default:
      return state;
  }
}
