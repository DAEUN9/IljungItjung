import { apiInstance } from "@api/index";
<<<<<<< HEAD
import { SearchState } from "@components/types/types";
=======
>>>>>>> dbd1ac01c8aae2f820e17b90e5012067890a0a7c

const api = apiInstance();

// 사용자 검색
<<<<<<< HEAD
function getSearchList(search: string) {
  api
    .get<SearchState>(`/users`, { params: { nickname: search } })
    .then((data) => data);
=======
function getSearchList(search: string, success: any) {
  api
    .get(`/users`, { params: { nickname: search, isSearch: true } })
    .then(success);
>>>>>>> dbd1ac01c8aae2f820e17b90e5012067890a0a7c
}

export { getSearchList };
