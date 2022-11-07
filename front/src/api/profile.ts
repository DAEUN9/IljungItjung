import { apiInstance } from "@api/index";

const api = apiInstance();

function getMyInfo(success: any) {
  api.get(`/users`).then(success);
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
