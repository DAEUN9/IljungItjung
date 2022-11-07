import { useEffect, useMemo, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useForm, Controller, SubmitHandler } from 'react-hook-form';
import styled from '@emotion/styled';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import OutlinedInput from '@mui/material/OutlinedInput';
import MuiInputLabel from '@mui/material/InputLabel';
import MuiTextField from '@mui/material/TextField';
import MuiSelect from '@mui/material/Select';
import Snackbar from '@mui/material/Snackbar';
import {
  FaThList,
  FaRegCalendar,
  FaRegClock,
  FaPhoneAlt,
} from 'react-icons/fa';

import styles from '@styles/Calendar/Calendar.module.scss';
import CustomButton from '@components/common/CustomButton';
import {
  formatTime,
  getStringFromDate,
} from '@components/Calendar/common/util';
import { SchedulerDate, SchedulerDateTime } from '@components/types/types';
import { RootState } from '@modules/index';
import {
  setCurrent,
  setSelectedTime,
  setMinutes,
} from '@modules/othercalendar';

interface RequestData {
  category: string;
  phone: string;
  request: string;
  time: string;
}

const TextField = styled(MuiTextField)`
  > .Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }
`;

const PhoneTextField = styled(TextField)`
  > .MuiInputBase-root > input {
    padding: 7px;
    padding-left: 12px;
  }
`;

const Select = styled(MuiSelect)`
  &.Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }

  & .MuiSelect-select > div {
    padding: 0;
  }
`;

const InputLabel = styled(MuiInputLabel)`
  color: rgba(0, 0, 0, 0.5);

  &.Mui-focused {
    color: #6b7bb1;
  }
`;

const getFullDate = (date: SchedulerDateTime | undefined) => {
  switch (typeof date) {
    case 'undefined':
      return '-';
    case 'string':
      return date;
    case 'object':
      return (
        date.getFullYear() +
        '년 ' +
        (date.getMonth() + 1) +
        '월 ' +
        date.getDate() +
        '일'
      );
  }
};

const getTime = (time: string) => {
  const hours = parseInt(time.slice(0, 2));
  const minutes = parseInt(time.slice(2));
  let fullTime = '';

  if (hours !== 0) fullTime += hours + '시간 ';
  if (minutes !== 0) fullTime += minutes + '분';

  return fullTime;
};

const getMinutes = (time: string) => {
  const hours = parseInt(time.slice(0, 2));
  const minutes = parseInt(time.slice(2));

  return hours * 60 + minutes;
};

const items = [
  {
    categoryName: '예쁜 그림',
    time: '0100',
  },
  {
    categoryName: '멋진 그림',
    time: '0130',
  },
  {
    categoryName: '예쁘고 멋진 그림',
    time: '0300',
  },
];

const Reservation = () => {
  const { handleSubmit, control, watch, setValue } = useForm<RequestData>();
  const watchCategory = watch('category', '');
  const { selected, map } = useSelector(
    (state: RootState) => state.othercalendar
  );
  const dispatch = useDispatch();
  const fullDate = useMemo(() => getFullDate(selected?.startDate), [selected]);
  const [open, setOpen] = useState(false);

  const onSubmit: SubmitHandler<RequestData> = (data) => console.log(data);
  const handleClose = () => setOpen(false);

  // 카테고리가 선택됐을 때
  useEffect(() => {
    if (watchCategory && selected) {
      const endDate = new Date(selected.startDate.toString());
      const list = map.get(getStringFromDate(endDate));
      const time = items.filter(
        (item) => item.categoryName === watchCategory
      )[0].time;
      const minutes = getMinutes(time);

      endDate.setMinutes(endDate.getMinutes() + minutes);

      if (list) {
        for (let item of list) {
          const itemDate = new Date(item.startDate.toString());

          if (endDate >= itemDate) {
            setOpen(true);
            setValue('category', '');
            return;
          }
        }
      }

      const newSelected: SchedulerDate = { startDate: selected.startDate };

      newSelected.endDate = endDate;
      newSelected.title = 'selected';

      dispatch(setSelectedTime(newSelected));
      dispatch(setMinutes(minutes));
    }
  }, [watchCategory]);

  // selectedTime이 변경됐을 때
  useEffect(() => {
    if (selected && selected.endDate) {
      dispatch(setCurrent());
    }
  }, [selected]);

  return (
    <div className={styles.reservation}>
      {!selected && <div className={styles.center}>시간대를 선택해주세요</div>}
      {selected && (
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className={styles['reservation-inner']}>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaThList />
              </div>
              <Controller
                control={control}
                name="category"
                defaultValue=""
                render={({ field }) => (
                  <FormControl fullWidth>
                    <InputLabel id="select-label" size="small">
                      카테고리 선택
                    </InputLabel>
                    <Select
                      labelId="select-label"
                      id="select"
                      label="카테고리 선택"
                      input={
                        <OutlinedInput
                          id="select-category"
                          label="카테고리 선택"
                        />
                      }
                      size="small"
                      {...field}
                    >
                      {items.map((item) => (
                        <MenuItem
                          key={item.categoryName}
                          value={item.categoryName}
                        >
                          <div className={styles.menu}>
                            <div>{item.categoryName}</div>
                            <div>{getTime(item.time)}</div>
                          </div>
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                )}
              />
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaRegCalendar />
              </div>
              {fullDate}
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaRegClock />
              </div>
              {selected.endDate
                ? formatTime(
                    selected.startDate.toString(),
                    selected.endDate.toString()
                  )
                : '-'}
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaPhoneAlt />
              </div>
              <Controller
                control={control}
                name="phone"
                defaultValue=""
                render={({ field }) => (
                  <PhoneTextField placeholder="연락처" {...field} />
                )}
              />
            </div>
            <div className={styles['reservation-request']}>
              <div>요청사항</div>
              <Controller
                control={control}
                name="request"
                defaultValue=""
                render={({ field }) => (
                  <TextField fullWidth multiline rows={2} {...field} />
                )}
              />
            </div>
            <CustomButton
              style={{ width: 'calc(100% - 10px)', margin: '0 5px' }}
              type="submit"
            >
              신청하기
            </CustomButton>
          </div>
          <Snackbar
            anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
            open={open}
            onClose={handleClose}
            message="카테고리를 선택할 수 없습니다."
          />
        </form>
      )}
    </div>
  );
};

export default Reservation;
