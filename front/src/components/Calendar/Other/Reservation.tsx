import { useMemo } from 'react';
import { useSelector } from 'react-redux';
import { useForm, Controller, SubmitHandler } from 'react-hook-form';
import styled from '@emotion/styled';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import OutlinedInput from '@mui/material/OutlinedInput';
import MuiInputLabel from '@mui/material/InputLabel';
import MuiTextField from '@mui/material/TextField';
import MuiSelect from '@mui/material/Select';
import {
  FaThList,
  FaRegCalendar,
  FaRegClock,
  FaPhoneAlt,
} from 'react-icons/fa';

import styles from '@styles/Calendar/Calendar.module.scss';
import CustomButton from '@components/common/CustomButton';
import { SchedulerDateTime } from '@components/types/types';
import { RootState } from '@modules/index';

interface RequestData {
  category: string;
  phone: string;
  request?: string;
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

const Reservation = () => {
  const { handleSubmit, control } = useForm<RequestData>();
  const current = useSelector((state: RootState) => state.othercalendar.time);
  const time = useMemo(() => getFullDate(current?.startDate), [current]);

  const onSubmit: SubmitHandler<RequestData> = (data) => console.log(data);

  return (
    <div className={styles.reservation}>
      {!current && <div className={styles.center}>시간대를 선택해주세요</div>}
      {current && (
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
                      <MenuItem value={10}>
                        <div className={styles.menu}>
                          <div>예쁜 그림</div>
                          <div>1시간</div>
                        </div>
                      </MenuItem>
                      <MenuItem value={20}>
                        <div className={styles.menu}>
                          <div>멋진 그림</div>
                          <div>1시간 30분</div>
                        </div>
                      </MenuItem>
                      <MenuItem value={30}>
                        <div className={styles.menu}>
                          <div>예쁘고 멋진 그림</div>
                          <div>3시간</div>
                        </div>
                      </MenuItem>
                    </Select>
                  </FormControl>
                )}
              />
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaRegCalendar />
              </div>
              {time}
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaRegClock />
              </div>
              -
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
        </form>
      )}
    </div>
  );
};

export default Reservation;
