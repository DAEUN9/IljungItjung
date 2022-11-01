import { MdExpandMore } from 'react-icons/md';
import { FaPhoneAlt, FaPen } from 'react-icons/fa';
import MuiAccordion from '@mui/material/Accordion';
import MuiAccordionSummary from '@mui/material/AccordionSummary';
import MuiAccordionDetails from '@mui/material/AccordionDetails';
import styled from '@emotion/styled';

import styles from '@styles/Calendar/Calendar.module.scss';

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

export default DetailInfo;