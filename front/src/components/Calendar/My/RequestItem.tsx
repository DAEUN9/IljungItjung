import { useState, useCallback } from 'react';
import MuiTextField from '@mui/material/TextField';
import styled from '@emotion/styled';

import styles from '@styles/Calendar/Calendar.module.scss';
import Schedule from '@components/common/Schedule';
import iljung from '@assets/defaultImg.png';
import DetailInfo from './DetailInfo';
import { formatTime, InfoItemProps } from '@components/Calendar/common/util';
import CustomButton from '@components/common/CustomButton';

const TextField = styled(MuiTextField)`
  > .Mui-focused > fieldset {
    border-color: #6B7BB1 !important;
  }
`

const RequestButtons = () => {
  const [open, setOpen] = useState(false);
  const [text, setText] = useState('');

  const handleDeny = useCallback(() => {
    setOpen(true);
  }, []);

  const handleAccept = () => {};

  const handleCancle = useCallback(() => {
    setOpen(false);
    setText('');
  }, []);

  const handleConfirm = () => {};

  return (
    <div>
      {!open && (
        <div className={styles['request-buttons']}>
          <CustomButton variant="outlined" onClick={handleDeny}>
            거절
          </CustomButton>
          <CustomButton>수락</CustomButton>
        </div>
      )}
      {open && (
        <div>
          <TextField
            fullWidth
            multiline
            sx={{ marginBottom: '10px' }}
            placeholder="거절 사유를 입력해주세요"
            rows={3}
            value={text}
            onChange={(e) => {
              const current = e.currentTarget.value;
              if(current.length <= 100) {
                setText(current);
              }
            }}
          />
          <div className={styles['request-text-length']}>{text.length}/100</div>
          <div className={styles['request-buttons']}>
            <CustomButton variant="outlined" onClick={handleCancle}>
              취소
            </CustomButton>
            <CustomButton disabled={text.length > 0 ? false : true}>확인</CustomButton>
          </div>
        </div>
      )}
    </div>
  );
};

const RequestItem = ({ item }: InfoItemProps) => {
  const { color, startDate, endDate, title, nickname, phone, desc } = item;
  const time = formatTime(startDate?.toString(), endDate?.toString());

  return (
    <div className={styles['info-item']}>
      <Schedule
        color={color}
        time={time ?? '-'}
        userId="유저아이디"
        userName={nickname}
        category={title ?? '-'}
        userImg={iljung}
        render={() => (
          <>
            <DetailInfo phone={phone} desc={desc} />
            <RequestButtons />
          </>
        )}
      />
    </div>
  );
};

export default RequestItem;
