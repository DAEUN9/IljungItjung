import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useForm, SubmitHandler, FormProvider } from "react-hook-form";
import { useParams } from "react-router-dom";
import Snackbar from "@mui/material/Snackbar";

import styles from "@styles/Calendar/Calendar.module.scss";
import CustomButton from "@components/common/CustomButton";
import {
  getStringFromDate,
  makeFormat,
} from "@components/Calendar/common/util";
import { SchedulerDate, SchedulerDateTime } from "@components/types/types";
import { RootState } from "@modules/index";
import {
  setCurrent,
  setSelectedTime,
  setMinutes,
  deleteCurrent,
} from "@modules/othercalendar";
import ReservationCategory from "@components/Calendar/Other/Reservation/ReservationCategory";
import ReservationDate from "@components/Calendar/Other/Reservation/ReservationDate";
import ReservationTime from "@components/Calendar/Other/Reservation/ReservationTime";
import ReservationPhone from "@components/Calendar/Other/Reservation/ReservationPhone";
import ReservationRequest from "@components/Calendar/Other/Reservation/ReservationRequest";
import { requestReservation } from "@api/calendar";

interface RequestData {
  category: string;
  phone: string;
  request: string;
  time: string;
}

interface RequestApiData {
  status: string;
  data: {
    id: number;
  };
}

const messages = [
  "카테고리를 선택할 수 없습니다.",
  "해당 시간대는 선택할 수 없습니다.",
  "예약 요청이 완료되었습니다.",
];

const end = new Date();
end.setHours(22);
end.setMinutes(0);

const Reservation = () => {
  const methods = useForm<RequestData>();
  const { handleSubmit, watch, setValue } = methods;
  const watchCategory = watch("category", "");
  const { selected, category, lock, fixedMap, map } = useSelector(
    (state: RootState) => state.othercalendar
  );
  const dispatch = useDispatch();
  const { nickname } = useParams();
  const [open, setOpen] = useState(false);
  const [id, setId] = useState(0);

  // form onSubmit 핸들러
  const onSubmit: SubmitHandler<RequestData> = (data) => {
    if (selected) {
      let startDate =
        typeof selected.startDate === "object"
          ? selected.startDate
          : new Date(selected.startDate);

      const requestData = {
        userToNickname: nickname ?? "",
        date: getStringFromDate(startDate),
        startTime:
          makeFormat(startDate.getHours().toString()) +
          makeFormat(startDate.getMinutes().toString()),
        contents: data.request,
        phone: data.phone,
        categoryName: data.category,
      };

      requestReservation(requestData, (res: RequestApiData) => {
        openSnackbar(2);
      });
    }
  };

  // 시간대, 카테고리 선택됐을 때 endDate 구하기
  const getScheduleEndDate = (startDate: string) => {
    const endDate = new Date(startDate);
    const time = category.filter(
      (item) => item.categoryName === watchCategory
    )[0].time;

    const hours = parseInt(time.slice(0, 2));
    const minutes = parseInt(time.slice(2));
    const endDateMinutes = hours * 60 + minutes;

    endDate.setMinutes(endDate.getMinutes() + endDateMinutes);

    return { endDate, minutes: endDateMinutes };
  };

  // 블락된 시간대와 겹치는지 확인
  const isOverlapWithSchedule = (
    startDate: SchedulerDateTime,
    endDate: SchedulerDateTime
  ) => {
    let isOverlap = false;
    const _startDate = new Date(startDate.toString());
    const _endDate = new Date(endDate.toString());
    const day = (_startDate.getDay() + 6) % 7;

    if (lock[day]) {
      const list = fixedMap.get(day);

      if (list) {
        for (let item of list) {
          if (_startDate < item.startDate && _endDate >= item.endDate) {
            isOverlap = true;
            break;
          }
        }
      }

      if (!isOverlap) {
        const key = getStringFromDate(_startDate);
        const list = map.get(key);

        if (list) {
          for (let item of list) {
            if (_startDate < item.startDate && _endDate >= item.endDate) {
              isOverlap = true;
              break;
            }
          }
        }
      }
    }

    return isOverlap;
  };

  // 선택할 수 없는 카테고리 or 시간대이면 캘린더에 표시된 예약 삭제
  const unsetSelected = (id: number = 0, startDate: SchedulerDateTime) => {
    dispatch(deleteCurrent());
    dispatch(setSelectedTime({ startDate }));
    openSnackbar(id);
    setValue("category", "");
  };

  // 스낵바 open 핸들러
  const openSnackbar = useCallback((id: number = 0) => {
    setId(id);
    setOpen(true);
  }, []);

  // 스낵바 close 핸들러
  const handleClose = useCallback(() => setOpen(false), []);

  // 카테고리가 선택됐을 때
  useEffect(() => {
    if (watchCategory && selected) {
      const { endDate, minutes } = getScheduleEndDate(
        selected.startDate.toString()
      );

      if (isOverlapWithSchedule(selected.startDate, endDate) || endDate > end) {
        unsetSelected(0, selected.startDate);
      } else {
        const newSelected: SchedulerDate = { startDate: selected.startDate };

        newSelected.endDate = endDate;

        dispatch(setSelectedTime(newSelected));
        dispatch(setMinutes(minutes));
      }
    }
  }, [watchCategory]);

  // selectedTime이 변경됐을 때
  useEffect(() => {
    if (selected && selected.endDate) {
      const endDate = new Date(selected.endDate.toString());

      if (isOverlapWithSchedule(selected.startDate, endDate) || endDate > end) {
        unsetSelected(0, selected.startDate);
      } else {
        dispatch(setCurrent());
      }
    }
  }, [selected]);

  return (
    <div className={styles.reservation}>
      {!selected && <div className={styles.center}>시간대를 선택해주세요</div>}
      {selected && (
        <FormProvider {...methods}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className={styles["reservation-inner"]}>
              <ReservationCategory />
              <ReservationDate />
              <ReservationTime />
              <ReservationPhone />
              <ReservationRequest />
              <CustomButton
                style={{ width: "calc(100% - 10px)", margin: "0 5px" }}
                type="submit"
              >
                신청하기
              </CustomButton>
            </div>
            <Snackbar
              anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
              autoHideDuration={6000}
              open={open}
              onClose={handleClose}
              message={messages[id]}
            />
          </form>
        </FormProvider>
      )}
    </div>
  );
};

export default Reservation;
