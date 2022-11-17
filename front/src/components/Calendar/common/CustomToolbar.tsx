import { useCallback, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLock, faUnlock } from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import { Toolbar } from "@devexpress/dx-react-scheduler-material-ui";
import Avatar from "@mui/material/Avatar";
import AvatarGroup from "@mui/material/AvatarGroup";
import IconButton from "@mui/material/IconButton";
import { IoSettingsSharp } from "react-icons/io5";

import styles from "@styles/Calendar/Calendar.module.scss";
import { RootState } from "@modules/index";
import { deleteLockShade, lockShade, toggleLock } from "@modules/setting";
import { days } from "@components/Calendar/common/util";

const SettingButton = () => {
  const navigate = useNavigate();
  const handleClick = useCallback(() => navigate("/setting"), []);

  return (
    <IconButton sx={{ marginLeft: "20px" }} onClick={handleClick}>
      <IoSettingsSharp />
    </IconButton>
  );
};

const CustomerList = () => (
  <div className={styles.avatar}>
    <AvatarGroup
      max={4}
      sx={{
        "& .MuiAvatar-root": { width: 30, height: 30, fontSize: 15 },
      }}
    >
      <Avatar alt="Remy Sharp" />
      <Avatar alt="Travis Howard" />
      <Avatar alt="Agnes Walker" />
      <Avatar alt="Trevor Henderson" />
      <Avatar alt="Trevor Henderson" />
    </AvatarGroup>
  </div>
);

export default function CustomToolbar() {
  const [visible, setVisible] = useState(true);
  const location = useLocation();
<<<<<<< .merge_file_a02452
  const dispatch = useDispatch();
  const { set, lock, lockMap } = useSelector(
    (state: RootState) => state.setting
  );
=======

  const { set, lock, lockMap } = useSelector(
    (state: RootState) => state.setting
  );
  const dispatch = useDispatch();
  const onToggleLock = (index: number) => dispatch(toggleLock(index));
  const onLockShade = (day: number, time: string) =>
    dispatch(lockShade(day, time));
  const onDeleteLockShade = (day: number, time: string, all?: boolean) =>
    dispatch(deleteLockShade(day, time, all));
>>>>>>> .merge_file_a18036

  useEffect(() => {
    if (location.pathname.includes("setting")) {
      setVisible(false);
    }
  }, []);

  const handleClickLock = (index: number) => {
<<<<<<< .merge_file_a02452
    dispatch(toggleLock(index));
=======
    onToggleLock(index);
>>>>>>> .merge_file_a18036

    // set에서 해당하는 요일의 block된 시간들을 lockMap에 저장한다.
    const keys = set.keys();
    for (const key of keys) {
<<<<<<< .merge_file_a02452
      console.log(key + " in set");
      const blockDay = Number(key.substring(0, 1));
      if ((blockDay + 6) % 7 === index) {
        const time = key.substring(9);
        dispatch(lockShade(blockDay, time));
=======
      const blockDay = Number(key.substring(0, 1));
      if ((blockDay + 6) % 7 === index) {
        const time = key.substring(9);
        onLockShade(blockDay, time);
>>>>>>> .merge_file_a18036
      }
    }
  };

  const handleClickUnlock = (index: number) => {
<<<<<<< .merge_file_a02452
    dispatch(toggleLock(index));

    // lockMap의 저장된 시간들을 지운다.
    dispatch(deleteLockShade((index + 6) % 7, "", true));
=======
    onToggleLock(index);

    // lockMap의 저장된 시간들을 지운다.
    onDeleteLockShade((index + 6) % 7, "", true);
>>>>>>> .merge_file_a18036
  };

  return (
    <Toolbar
      rootComponent={({ children }) => <Toolbar.Root>{children}</Toolbar.Root>}
      flexibleSpaceComponent={() => (
        <Toolbar.FlexibleSpace className={styles["toolbar-right"]}>
          {visible ? (
            <>
              <CustomerList />
              <SettingButton />
            </>
          ) : (
            <>
              {lock.map((isLocked, index) => {
                if (isLocked)
                  return (
<<<<<<< .merge_file_a02452
                    <div className="day-lock" key={index}>
=======
                    <div className="day-lock">
>>>>>>> .merge_file_a18036
                      <span>{days[(index + 1) % 7]}</span>
                      <FontAwesomeIcon
                        className="lock"
                        icon={faLock}
                        onClick={() => handleClickUnlock(index)}
                      />
                    </div>
                  );
                else
                  return (
<<<<<<< .merge_file_a02452
                    <div className="day-lock" key={index}>
=======
                    <div className="day-lock">
>>>>>>> .merge_file_a18036
                      <span>{days[(index + 1) % 7]}</span>
                      <FontAwesomeIcon
                        className="unlock"
                        icon={faUnlock}
                        onClick={() => handleClickLock(index)}
                      />
                    </div>
                  );
              })}
            </>
          )}
        </Toolbar.FlexibleSpace>
      )}
    />
  );
}
