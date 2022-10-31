import { combineReducers } from "redux";

import mycalendar from "./mycalendar";

const rootReducer = combineReducers({
  mycalendar
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
