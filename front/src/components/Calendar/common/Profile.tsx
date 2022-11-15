import React from "react";

import styles from "@styles/Calendar/Calendar.module.scss";
import iljung from "@assets/defaultImg.png";
import { MyProfile } from "@components/types/types";

interface ProfileProps {
  profile: MyProfile;
}

const Profile = ({ profile }: ProfileProps) => {
  return (
    <div className={styles.profile}>
      <div className={styles["profile-inner"]}>
        <div className={styles["profile-title"]}>
          <img src={profile.imagePath} alt="profile" />
          <div className={styles["profile-my"]}>
            <div>{profile.nickname}</div>
            <div>IJIJ</div>
          </div>
        </div>
        <div className={styles["profile-desc"]}>{profile.description}</div>
      </div>
    </div>
  );
};

export default React.memo(Profile);
