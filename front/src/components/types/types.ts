// 카테고리 관련 타입
interface SettingCategoryState {
  categoryName: string;
  color: string;
  time: string;
}

interface BlockListTypes {
  startTime: string;
  endTime: string;
  date: string;
}

interface SearchCategoryState {
  categoryName: string;
}

// 검색 관련 타입
interface SearchState {
  nickname: string;
  email: string;
  imagePath: string;
  // desc: string;
  // detail: string;
  categories: SearchCategoryState[];
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

export type {
  SettingCategoryState,
  BlockListTypes,
  SearchCategoryState,
  SearchState,
  SchedulerDateTime,
  SchedulerDate,
};
