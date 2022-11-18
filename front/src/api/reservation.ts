import { apiInstance } from "@api/index";

const api = apiInstance();

// 예약 목록 조회
function getReservations(startDate: string, endDate: string, success: any) {
  api
    .get(`/reservations`, {
      params: { startDate, endDate },
    })
    .then(success);
}

// 취소 요청

export { getReservations };
