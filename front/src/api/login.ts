import { AxiosResponse } from "axios";

import { apiInstance } from "@api/index";

const api = apiInstance();

// 로그인 버튼 클릭 시 요청할 주소
function getKakaoLogin() {
  api
    .get(`/api/login/kakao`)
    .then((res) => console.log(res))
    .catch((err) => console.log(err));
}

function getMyProfile(success: any) {
  api
    .get(`/users`)
    .then(success)
    .catch((err) => console.log(err));
}

export { getKakaoLogin, getMyProfile };

// 요청한 뒤 로그인 시 백엔드에서 302 또는 401 Response 를 보내주는데
// 302일 경우 메인페이지로 이동
// 401일 경우 회원가입 페이지로 이동
