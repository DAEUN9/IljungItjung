// 검색 관련 타입
interface SearchState {
  id: number;
  name: string;
  imgUrl: string;
  desc: string;
  detail: string;
  categories: string[];
}

// 카테고리 관련 타입
interface CategoryState {
  id: number;
  name: string;
  color: string;
  hour: string;
  min: string;
}

export type { SearchState, CategoryState };
