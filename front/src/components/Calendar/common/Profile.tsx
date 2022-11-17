<<<<<<< .merge_file_a16984
import React, { useEffect, useState } from "react";
import Skeleton from "@mui/material/Skeleton";

import styles from "@styles/Calendar/Calendar.module.scss";
import { MyProfile } from "@components/types/types";

interface ProfileProps {
  profile: MyProfile;
}

const Profile = ({ profile }: ProfileProps) => {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 1500);
  }, []);

  return (
    <div className={styles.profile}>
      <div className={styles["profile-inner"]}>
        <div className={styles["profile-title"]}>
          {!profile.imagePath && loading && (
            <Skeleton variant="circular" width={60} height={60} />
          )}
          {profile.imagePath && <img src={profile.imagePath} alt="profile" />}
          <div className={styles["profile-my"]}>
            {!profile.nickname && loading && (
              <Skeleton variant="text" width={100} sx={{ fontSize: "27px" }} />
            )}
            {profile.nickname && <div>{profile.nickname}</div>}
            {!profile.introduction && loading && (
              <Skeleton variant="text" width={200} />
            )}
            {!profile.introduction && !loading && (
              <div style={{ color: "gray" }}>한줄 소개가 없습니다.</div>
            )}
            {profile.introduction && <div>{profile.introduction}</div>}
          </div>
        </div>
        <div className={styles["profile-desc"]}>
          {!profile.description && loading && (
            <Skeleton variant="rounded" width="100%" height="150px" />
          )}
          {profile.description && <div>{profile.description}</div>}
        </div>
=======
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
>>>>>>> .merge_file_a03596
      </div>
    </div>
  );
};

export default React.memo(Profile);
