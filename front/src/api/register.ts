import { apiInstance } from "@api/index";

const api = apiInstance();

// 닉네임 중복체크 api
function getOverlap(nickname: string, success: any) {
  api.get(`/api/nicknames/${nickname}`).then(success);
}

// 회원가입 api
function postRegister(nickname: string, description: string, success: any) {
  api
    .post(`/users`, {
      nickname: nickname,
      description: description,
      phonenum: "01012341234",
    })
    .then(success);
}

// 회원탈퇴 api
function delWithdraw(success: any) {
  api.delete(`/user`).then(success);
}

export { getOverlap, postRegister, delWithdraw };
