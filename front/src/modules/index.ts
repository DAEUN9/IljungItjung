import { combineReducers } from "redux";

import reservation from "@modules/reservation";
import search from "@modules/search";

const rootReducer = combineReducers({
  reservation,
  search,
});

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
