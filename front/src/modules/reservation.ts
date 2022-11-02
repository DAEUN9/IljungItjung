const SET_RESERVATIONS = "reservation/SET_RESERVATIONS" as const;

export const setReservations = (reservations: object[]) => ({
  type: SET_RESERVATIONS,
  payload: reservations,
});

type ReservationAction = ReturnType<typeof setReservations>;

interface ReservationState {
  reservations: object[];
}

const initialState: ReservationState = {
  reservations: [],
};

function reservation(
  state: ReservationState = initialState,
  action: ReservationAction
): ReservationState {
  switch (action.type) {
    case SET_RESERVATIONS:
      return { reservations: action.payload };
    default:
      return state;
  }
}

export default reservation;
