export declare type SchedulerDateTime = Date | number | string;

export interface SchedulerDate {
  startDate: SchedulerDateTime;
  endDate?: SchedulerDateTime;
  title?: string;
  allDay?: boolean;
  id?: number | string;
  rRule?: string | undefined;
  exDate?: string | undefined;
  [propertyName: string]: any;
}

export interface ResourceInstance {
  id: number | string;
  color?: string;
  text?: string;
};

export interface Resource {
  fieldName: string;
  title?: string;
  allowMultiple?: boolean;
  instances: Array<ResourceInstance>;
};

export const days = ['일', '월', '화', '수', '목', '금', '토'];

export const getDay = (nextDate: SchedulerDateTime | undefined, nextOptions: any) => {
  const date =
    typeof nextDate === 'object'
      ? nextDate
      : typeof nextDate === 'string'
      ? new Date(nextDate)
      : new Date();
  const { day } = nextOptions;

  let value = '';

  if (day) {
    value = date.getDate().toString();
  } else {
    value = days[date.getDay()];
  }

  return value;
};