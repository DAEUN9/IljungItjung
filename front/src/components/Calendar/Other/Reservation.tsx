import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useForm, Controller } from 'react-hook-form';
import styled from '@emotion/styled';
import MuiTextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import MuiSelect, { SelectChangeEvent } from '@mui/material/Select';
import {
  FaThList,
  FaRegCalendar,
  FaRegClock,
  FaPhoneAlt,
} from 'react-icons/fa';

import styles from '@styles/Calendar/Calendar.module.scss';
import CustomButton from '@components/common/CustomButton';
import { RootState } from '@modules/index';
import { InputLabel } from '@mui/material';

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
  }
`;

const Select = styled(MuiSelect)`
  &.Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }

  > fieldset > legend > span {
    display: none;
  }

  > .MuiSelect-select {
    padding-top: 7px;
    padding-bottom: 7px;
    padding-left: 7px;
  }
`;

const Reservation = () => {
  const { handleSubmit, control } = useForm();
  const current = useSelector((state: RootState) => state.othercalendar.time);
  const [age, setAge] = useState('');

  const handleChange = (event: SelectChangeEvent) => {
    setAge(event.target.value as string);
  };

  const onSubmit = (data: RequestData) => console.log(data);

  return (
    <div className={styles.reservation}>
      {!current && <div className={styles.center}>시간대를 선택해주세요</div>}
      {current && (
        <form>
          <div className={styles['reservation-inner']}>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaThList />
              </div>
              <FormControl fullWidth>
                <Select
                  labelId="select-label"
                  id="select"
                  value={age}
                  label="Age"
                >
                  <MenuItem value={10}>Ten</MenuItem>
                  <MenuItem value={20}>Twenty</MenuItem>
                  <MenuItem value={30}>Thirty</MenuItem>
                </Select>
              </FormControl>
            </div>
            <div className={styles['reservation-item']}>
              <div className={styles.center}>
                <FaRegCalendar />
              </div>
              -
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
              <PhoneTextField placeholder="연락처" />
            </div>
            <div className={styles['reservation-request']}>
              <div>요청사항</div>
              <TextField fullWidth multiline rows={2} />
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
