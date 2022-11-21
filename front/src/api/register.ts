import { apiInstance } from "@api/index";

const api = apiInstance();

// 닉네임 중복체크 api
function getOverlap(nickname: string, success: any, fail: any) {
  api.get(`/users/${nickname}`).then(success).catch(fail);
}

// 휴대폰 인증번호 전송 api
function postPhone(phone: string, success: any, fail: any) {
  api.post(`/phones/${phone}`).then(success).catch(fail);
}

// 인증번호 확인 api
function putNum(phone: string, num: string, success: any, fail: any) {
  api
    .put(`/phones`, {
      phonenum: phone,
      randomNumber: num,
    })
    .then(success)
    .catch(fail);
}

// 회원가입 api
function postRegister(
  nickname: string,
  introduction: string,
  phonenum: string,
  success: any
) {
  api
    .post(`/users`, {
      nickname: nickname,
      introduction: introduction,
      phonenum: phonenum,
    })
    .then(success);
}

// 회원탈퇴 api
function delWithdraw(success: any) {
  api.delete(`/users`).then(success);
}

export { getOverlap, postPhone, putNum, postRegister, delWithdraw };
