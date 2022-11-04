import { apiInstance } from "@api/index";
import { SearchState } from "@components/types/types";

const api = apiInstance();

function getMyInfo() {
  api.get<SearchState>(`/users`).then((data) => data);
}

// 프로필 수정 요청
function putProfile(
  nickname: string,
  introduction: string,
  description: string,
  success: any
) {
  api
    .put(`/users`, {
      nickname: nickname,
      introduction: introduction,
      description: description,
    })
    .then(success);
}

export { getMyInfo, putProfile };
