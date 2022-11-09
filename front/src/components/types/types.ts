// 검색 관련 타입
interface CategoryState {
  categoryName: string;
}

interface SearchState {
  nickname: string;
  email: string;
  imagePath: string;
  desc: string;
  detail: string;
  categories: CategoryState[];
}

export type { SearchState };
