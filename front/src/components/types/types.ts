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

<<<<<<< .merge_file_a12524
interface MyProfile {
  nickname: string;
  email: string;
  imagePath: string;
  introduction?: string;
  description: string;
}

=======
>>>>>>> .merge_file_a08552
export type {
  SettingCategoryState,
  SearchCategoryState,
  SearchState,
  SchedulerDateTime,
  SchedulerDate,
<<<<<<< .merge_file_a12524
  MyProfile,
=======
>>>>>>> .merge_file_a08552
};
