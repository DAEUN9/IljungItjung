import { SchedulerDate, SchedulerDateTime } from "@components/types/types";
<<<<<<< .merge_file_a13240
import { DateNavigatorProps } from "@devexpress/dx-react-scheduler";
=======
>>>>>>> .merge_file_a08760

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

export const getStringFromDate = (time: Date | string | undefined) => {
  if (!time) return "";

  let date = new Date();

  if (typeof time === "string") date = new Date(time);
  else date = time;

  const str =
    date.getFullYear().toString() +
<<<<<<< .merge_file_a13240
    (date.getMonth() + 1) +
=======
    date.getMonth().toString() +
>>>>>>> .merge_file_a08760
    date.getDate().toString();

  return str;
};

export const makeFormat = (time: string) => {
  if (time.length === 1) return "0" + time;
  return time;
};

<<<<<<< .merge_file_a13240
export const getFullStringFromDate = (time: Date, endTime: Date) => {
=======
export const getFullStringFromDate = (time: Date) => {
>>>>>>> .merge_file_a08760
  if (!time) return "";

  const day = time.getDay().toString();
  const year = time.getFullYear().toString();
<<<<<<< .merge_file_a13240
  const month = makeFormat((time.getMonth() + 1).toString());
  const date = makeFormat(time.getDate().toString());
  const hour = makeFormat(time.getHours().toString());
  const min = makeFormat(time.getMinutes().toString());
  const endHour = makeFormat(endTime.getHours().toString());
  const endMin = makeFormat(endTime.getMinutes().toString());

  return day + year + month + date + hour + min + endHour + endMin;
=======
  let month = makeFormat(time.getMonth().toString());
  let date = makeFormat(time.getDate().toString());
  let hour = makeFormat(time.getHours().toString());
  let min = makeFormat(time.getMinutes().toString());

  return day + year + month + date + hour + min;
>>>>>>> .merge_file_a08760
};

export const getFullDate = (date: SchedulerDateTime | undefined) => {
  switch (typeof date) {
    case "undefined":
      return "-";
    case "string":
      return date;
    case "object":
      return (
        date.getFullYear() +
        "년 " +
        (date.getMonth() + 1) +
        "월 " +
        date.getDate() +
        "일"
      );
  }
};
