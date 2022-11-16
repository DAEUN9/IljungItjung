import { SearchState } from "@components/types/types";

const SELECT_USER = "search/SELECT_USER" as const;

const initialState: SearchState = {
  nickname: "",
  email: "",
  imagePath: "",
  introduction: "",
  description: "",
  categories: [],
};

export const selectUser = (user: SearchState) => ({
  type: SELECT_USER,
  payload: {
    ...user,
  },
});

type SearchAction = ReturnType<typeof selectUser>;

function search(
  state: SearchState = initialState,
  action: SearchAction
): SearchState {
  switch (action.type) {
    case SELECT_USER:
      return { ...action.payload };
    default:
      return state;
  }
}

export default search;
