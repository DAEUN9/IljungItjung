import { apiInstance } from "@api/index";
import { SearchState } from "@components/types/types";

const api = apiInstance();

// 사용자 검색
function getSearchList(search: string) {
  api
    .get<SearchState>(`/users`, { params: { nickname: search } })
    .then((data) => data);
}

export { getSearchList };
