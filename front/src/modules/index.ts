import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import search from "@modules/search";
import mycalendar from "@modules/mycalendar";

const rootReducer = combineReducers({
  reservation,
  search,
  mycalendar
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;