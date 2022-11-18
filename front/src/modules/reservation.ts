import { formatReservationDate } from "@components/Calendar/common/util";
import { ReservationTypes } from "@components/types/types";

const SET_RESERVATIONS = "reservation/SET_RESERVATIONS" as const;
const SET_START_DATE = "reservation/SET_START_DATE" as const;
const SET_END_DATE = "reservation/SET_END_DATE" as const;

export const setReservations = (
  reservations: Map<string, ReservationTypes[]>
) => ({
  type: SET_RESERVATIONS,
  payload: reservations,
});

export const setStartDate = (date: Date) => ({
  type: SET_START_DATE,
  payload: date,
});

export const setEndDate = (date: Date) => ({
  type: SET_END_DATE,
  payload: date,
});

type ReservationAction =
  | ReturnType<typeof setReservations>
  | ReturnType<typeof setStartDate>
  | ReturnType<typeof setEndDate>;

interface ReservationState {
  reservations: Map<string, ReservationTypes[]>;
  startDate: Date;
  endDate: Date;
}

const initialState: ReservationState = {
  reservations: new Map<string, ReservationTypes[]>(),
  // 첫 렌더링 시 현재 날짜로부터 15일 전후를 기간으로 요청한다.
  startDate: new Date(new Date().setDate(new Date().getDate() - 15)),
  endDate: new Date(new Date().setDate(new Date().getDate() + 15)),
};

function reservation(
  state: ReservationState = initialState,
  action: ReservationAction
): ReservationState {
  switch (action.type) {
    case SET_RESERVATIONS:
      return { ...state, reservations: action.payload };
    case SET_START_DATE:
      return { ...state, startDate: action.payload };
    case SET_END_DATE:
      return { ...state, endDate: action.payload };
    default:
      return state;
  }
}

export default reservation;
