// 카테고리 관련 타입
interface SettingCategoryState {
  name: string;
  color: string;
  hour: string;
  min: string;
}

interface SearchCategoryState {
  categoryName: string;
}

// 검색 관련 타입
interface SearchState {
  nickname: string;
  email: string;
  imagePath: string;
  introduction: string;
  description: string;
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

interface CommonState {
  id: number;
  categoryName: string;
}

interface DateState {
  startDate: string;
  endDate: string;
}

interface CategoryState extends CommonState {
  color: string;
  time: string;
}

interface RequestState extends CommonState, DateState {
  color: string;
  contents: string;
  phonenum: string;
}

interface AcceptState extends RequestState {}

interface BlockState extends CategoryState, DateState {
  contents: string;
  block: boolean;
}

interface CancelState extends CategoryState, DateState {
  reason: string;
  cancelFrom: string;
  contents: string;
  phonenum: string;
}

interface ScheduleApiData {
  status: string;
  data: {
    categoryList: CategoryState[];
    requestList: RequestState[];
    acceptList: AcceptState[];
    blockList: BlockState[];
    cancelList: CancelState[];
  };
}

interface MyProfile {
  nickname: string;
  email: string;
  imagePath: string;
  introduction: string | null;
  description: string | null;
}

export type {
  SettingCategoryState,
  SearchCategoryState,
  SearchState,
  SchedulerDateTime,
  SchedulerDate,
  ScheduleApiData,
  MyProfile,
};
