import { AxiosResponse } from "axios";

import { apiInstance } from "@api/index";

const api = apiInstance();

// 시작하기 버튼 클릭 시 요청할 주소
function postRegister(nickname: string, description: string) {
	api
    .post(`/user`, { nickname: nickname, description: description})
}

export { postRegister };