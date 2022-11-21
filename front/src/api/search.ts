import { apiInstance } from "@api/index";

const api = apiInstance();

// 사용자 검색
function getSearchList(search: string, success: any, fail: any) {
  api
    .get(`/users`, { params: { nickname: search, isSearch: true } })
    .then(success)
    .catch(fail);
}

export { getSearchList };
