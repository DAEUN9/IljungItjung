import { apiInstance } from "@api/index";

const api = apiInstance();

interface AcceptRequestType {
  accept: boolean;
  reason?: string;
}

interface ReservationType {
  userToNickname: string;
  date: string;
  startTime: string;
  contents: string;
  phone: string;
  categoryName: string;
}

// 캘린더 일정 조회
export function getSchedule(nickname: string, success: any) {
  api
    .get(`/schedules/${nickname}`)
    .then(success)
    .catch((err) => console.log(err));
}

// 요청 수락 or 거절
export function acceptRequest(
  id: number,
  data: AcceptRequestType,
  success: any
) {
  api
    .put(`/reservations/${id}`, data)
    .then(success)
    .catch((err) => console.log(err));
}

// 예약 요청
export function requestReservation(data: ReservationType, success: any) {
  api
    .post(`/reservations`, data)
    .then(success)
    .catch((err) => console.log(err));
}

// 타인 프로필
export function getOtherProfile(nickname: string, success: any) {
  const params = { nickname };

  api
    .get(`/users`, { params })
    .then(success)
    .catch((err) => console.log(err));
}

// 일정 상세 조회
export function getScheduleItem(id: number, success: any) {
  api
    .get(`/schedules/detail/${id}`)
    .then(success)
    .catch((err) => console.log(err));
}
