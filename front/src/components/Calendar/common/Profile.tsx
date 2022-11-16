import React from "react";
import Skeleton from "@mui/material/Skeleton";

import styles from "@styles/Calendar/Calendar.module.scss";
import { MyProfile } from "@components/types/types";

interface ProfileProps {
  profile: MyProfile;
}

const Profile = ({ profile }: ProfileProps) => {
  return (
    <div className={styles.profile}>
      <div className={styles["profile-inner"]}>
        <div className={styles["profile-title"]}>
          {!profile.imagePath ? (
            <Skeleton variant="circular" width={60} height={60} />
          ) : (
            <img src={profile.imagePath} alt="profile" />
          )}
          <div className={styles["profile-my"]}>
            {!profile.nickname ? (
              <Skeleton variant="text" width={100} sx={{ fontSize: "27px" }} />
            ) : (
              <div>{profile.nickname}</div>
            )}
            {!profile.introduction ? (
              <Skeleton variant="text" width={200} />
            ) : (
              <div>{profile.introduction}</div>
            )}
          </div>
        </div>
        <div className={styles["profile-desc"]}>
          {!profile.description ? (
            <Skeleton variant="rounded" width="100%" height="150px" />
          ) : (
            <div>{profile.description} </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default React.memo(Profile);
