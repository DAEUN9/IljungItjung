import { SchedulerDate, SchedulerDateTime } from "@components/types/types";

/* type, interface */
export interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

export interface InfoItemProps {
  item: SchedulerDate;
}

/* variables */
export const days = ["일", "월", "화", "수", "목", "금", "토"];

/* function */
export const getDay = (
  nextDate: SchedulerDateTime | undefined,
  nextOptions: any
) => {
  const date =
    typeof nextDate === "object"
      ? nextDate
      : typeof nextDate === "string"
      ? new Date(nextDate)
      : new Date();
  const { day } = nextOptions;

  let value = "";

  if (day) {
    value = date.getDate().toString();
  } else {
    value = days[date.getDay()];
  }

  return value;
};

export const formatTime = (
  startDate: string | undefined,
  endDate: string | undefined
) => {
  if (typeof startDate === "undefined" || typeof endDate === "undefined")
    return;

  const start = new Date(startDate);
  const end = new Date(endDate);

  let startHour = start.getHours();
  const startMinutes =
    start.getMinutes() < 10
      ? "0" + start.getMinutes().toString()
      : start.getMinutes().toString();

  let endHour = end.getHours();
  const endMinutes =
    end.getMinutes() < 10
      ? "0" + end.getMinutes().toString()
      : end.getMinutes().toString();

  let startTime = startHour < 12 ? "오전 " : "오후 ";
  if (startHour > 12) {
    startHour -= 12;
  }
  startTime += startHour + ":" + startMinutes;

  let endTime = endHour < 12 ? "오전 " : "오후 ";
  if (endHour > 12) {
    endHour -= 12;
  }
  endTime += endHour + ":" + endMinutes;

  return startTime + " - " + endTime;
};
