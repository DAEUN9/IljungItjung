import { MdExpandMore } from 'react-icons/md';
import { FaPhoneAlt, FaPen } from 'react-icons/fa';
import MuiAccordion from '@mui/material/Accordion';
import MuiAccordionSummary from '@mui/material/AccordionSummary';
import MuiAccordionDetails from '@mui/material/AccordionDetails';
import styled from '@emotion/styled';

import styles from '@styles/Calendar/Calendar.module.scss';
import Schedule from '@components/common/Schedule';
import iljung from '@assets/defaultImg.png';
import { SchedulerDate, formatTime } from '@components/Calendar/common/util';

interface UpcomingItemProps {
  item: SchedulerDate;
}

interface DetailIfnoProps {
  phone: string;
  desc: string;
}

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

const DetailInfo = (props: DetailIfnoProps) => {
  const { phone, desc } = props;

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
          <div className={styles['upcoming-item-content']}>{phone}</div>
        </div>
        <div className={styles['upcoming-item-inner']}>
          <FaPen />
          <div className={styles['upcoming-item-content']}>{desc}</div>
        </div>
      </MuiAccordionDetails>
    </Accordion>
  );
};

const UpcomingItem = ({ item }: UpcomingItemProps) => {
  const { color, startDate, endDate, title, nickname, phone, desc } = item;
  const time = formatTime(startDate?.toString(), endDate?.toString());

  return (
    <div className={styles['upcoming-item']}>
      <Schedule
        color={color}
        time={time ?? '-'}
        userId="유저아이디"
        userName={nickname}
        category={title ?? '-'}
        userImg={iljung}
        render={() => <DetailInfo phone={phone} desc={desc} />}
      />
    </div>
  );
};

export default UpcomingItem;
