import React from 'react';

import styles from '@styles/Calendar/Calendar.module.scss';
import iljung from '@assets/defaultImg.png';

const Profile = () => {
  return (
    <div className={styles.profile}>
      <div className={styles['profile-inner']}>
        <div className={styles['profile-title']}>
          <img src={iljung} alt="iljung" />
          <div className={styles['profile-my']}>
            <div>일정이</div>
            <div>IJIJ</div>
          </div>
        </div>
        <div className={styles['profile-desc']}>안녕하세요 일정있정입니다.</div>
      </div>
    </div>
  );
};

export default React.memo(Profile);
