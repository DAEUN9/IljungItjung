import { combineReducers } from "redux";

import reservation from "@modules/reservation";

const rootReducer = combineReducers({
  reservation,
});

export default rootReducer;
