import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import mycalendar from "./mycalendar";

const rootReducer = combineReducers({
  reservation,
  mycalendar
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
