import { AxiosResponse } from "axios";

import { apiInstance } from "@api/index";

const api = apiInstance();

interface DateProps {
  startDate: string;
  endDate: string;
}

interface ScheduleListProps {
  color: string;
  id: number;
  categoryName: string;
  startDate: string;
  endDate: string;
}

interface BlockListProps {
  id: number;
  categoryName: string;
  startDate: string;
  endDate: string;
}

interface CancelListProps {
  id: number;
  categoryName: string;
  startDate: string;
  endDate: string;
  reason: string;
}

interface GetReservationsResponse {
  requestList: ScheduleListProps;
  acceptList: ScheduleListProps;
  blockList: BlockListProps;
  cancelList: CancelListProps;
}

// 예약 목록 조회
function getReservations(nickname: string, date: DateProps) {
  console.log(nickname, date.startDate, date.endDate);
  api
    .get<GetReservationsResponse>(`/schedules/${nickname}`, { params: date })
    .then((data) => data);
}

// 취소 요청

export { getReservations };
