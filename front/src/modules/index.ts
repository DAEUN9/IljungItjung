import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import search from "@modules/search";
import mycalendar from "@modules/mycalendar";
import othercalendar from "@modules/othercalendar";
import setting from "@modules/setting";
import profile from "@modules/profile";
import render from "@modules/render";

const rootReducer = combineReducers({
  reservation,
  search,
  mycalendar,
  othercalendar,
  setting,
  profile,
  render,
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
