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
  introduction: string;
  description: string;
  categories: SearchCategoryState[];
}

<<<<<<< HEAD
=======
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

>>>>>>> front-develop
declare type SchedulerDateTime = Date | number | string;

interface SchedulerDate {
  startDate: SchedulerDateTime;
  endDate?: SchedulerDateTime;
  title?: string;
  allDay?: boolean;
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
  nickname: string;
  imagePath: string;
}

interface AcceptState extends RequestState {}

interface BlockState extends DateState {
  id: number;
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
    blockDayList: boolean[];
    cancelList: CancelState[];
  };
}

interface MyProfile {
  nickname: string;
  email: string;
  imagePath: string;
  introduction: string;
  description: string;
}

interface AppointmentsTypes {
  id: number;
  categoryName: string;
  startDate: string;
  endDate: string;
  color: string;
  nickname: string;
  contents: string;
  phonenum: string;
}

interface ReservationTypes {
  id: number;
  type: string;
  categoryName: string;
  color: string;
  contents: string;
  startDate: string;
  endDate: string;
  nickname: string;
  imagePath: string;
  cancelFrom?: string;
  reason?: string;
}

export type {
  SettingCategoryState,
  BlockListTypes,
  SearchCategoryState,
  SearchState,
  SchedulerDateTime,
  SchedulerDate,
  ScheduleApiData,
  MyProfile,
  CategoryState,
  AppointmentsTypes,
  BlockState,
  ReservationTypes,
};
