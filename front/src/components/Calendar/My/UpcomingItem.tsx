import { useState } from 'react';
import { MdExpandMore } from 'react-icons/md';
import { FaPhoneAlt, FaPen } from 'react-icons/fa';
import styled from '@emotion/styled';

import styles from '@styles/Calendar/Calendar.module.scss';
import Schedule from '@components/common/Schedule';
import iljung from '@assets/defaultImg.png';
import MuiAccordion from '@mui/material/Accordion';
import MuiAccordionSummary from '@mui/material/AccordionSummary';
import MuiAccordionDetails from '@mui/material/AccordionDetails';

const Accordion = styled(MuiAccordion)`
  box-shadow: unset;
  color: #6b7bb1;

  &:before {
    background-color: unset;
  }
`;

const AccordionSummary = styled(MuiAccordionSummary)`
  flex-direction: row-reverse;
  padding: 0;
  min-height: unset;

  & .MuiAccordionSummary-expandIconWrapper {
    color: inherit;
    margin-right: 5px;
  }

  &.Mui-expanded {
    min-height: unset;
  }

  > .Mui-expanded:first-child {
    margin: 0;
  }
`;

const DetailInfo = () => {
  return (
    <Accordion sx={{ marginTop: '10px' }}>
      <AccordionSummary
        expandIcon={<MdExpandMore />}
        aria-controls="panel1a-content"
        id="panel1a-header"
      >
        상세정보
      </AccordionSummary>
      <MuiAccordionDetails>
        <div className={styles['upcoming-item-inner']}>
          <FaPhoneAlt />
          <div className={styles['upcoming-item-content']}>010-1111-1111</div>
        </div>
        <div className={styles['upcoming-item-inner']}>
          <FaPen />
          <div className={styles['upcoming-item-content']}>요청사항이 너무 길어서 줄바꿈이 되면 어떻게 보일까요 요청사항이 너무 길어서 줄바꿈이 되면 어떻게 보일까요</div>
        </div>
      </MuiAccordionDetails>
    </Accordion>
  );
};

const UpcomingItem = () => {
  return (
    <div className={styles['upcoming-item']}>
      <Schedule
        color="blue"
        time="시간"
        userId="유저아이디"
        userName="유저명"
        category="카테고리"
        userImg={iljung}
        render={DetailInfo}
      />
    </div>
  );
};

export default UpcomingItem;
