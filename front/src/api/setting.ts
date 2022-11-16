import { apiInstance } from "@api/index";
import { BlockListTypes, SettingCategoryState } from "@components/types/types";

const api = apiInstance();

const registerCategory = (
  categoryList: SettingCategoryState[],
  success: any
) => {
  api.post(`/categories`, { categoryList }).then(success);
};

const blockSchedule = (
  days: boolean[],
  blockList: BlockListTypes[],
  success: any
) => {
  api.post(`/reservations/block`, { days, blockList }).then(success);
};

export { registerCategory, blockSchedule };
