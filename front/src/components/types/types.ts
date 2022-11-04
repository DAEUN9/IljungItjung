// 검색 관련 타입
interface SearchState {
  id: number;
  name: string;
  imgUrl: string;
  desc: string;
  detail: string;
  categories: string[];
}

declare type SchedulerDateTime = Date | number | string;

interface SchedulerDate {
  startDate: SchedulerDateTime;
  endDate?: SchedulerDateTime;
  title?: string;
  allDay?: boolean;
  id?: number | string;
  rRule?: string | undefined;
  exDate?: string | undefined;
  [propertyName: string]: any;
}

export type { SearchState, SchedulerDateTime, SchedulerDate };
