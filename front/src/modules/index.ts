import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import search from "@modules/search";
import mycalendar from "@modules/mycalendar";
import othercalendar from "@modules/othercalendar";

const rootReducer = combineReducers({
  reservation,
  search,
  mycalendar,
  othercalendar
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
