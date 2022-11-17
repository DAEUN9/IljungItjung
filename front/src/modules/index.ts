import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import search from "@modules/search";
import mycalendar from "@modules/mycalendar";
import othercalendar from "@modules/othercalendar";
import setting from "@modules/setting";
<<<<<<< HEAD
import profile from "@modules/profile";
=======
>>>>>>> 653a39ec835717dd46a43023cbbfaa11b42ac025

const rootReducer = combineReducers({
  reservation,
  search,
  mycalendar,
  othercalendar,
  setting,
<<<<<<< HEAD
  profile,
=======
>>>>>>> 653a39ec835717dd46a43023cbbfaa11b42ac025
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
