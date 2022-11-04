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
import { toggleLock } from "@modules/setting";

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

  const isLocked = useSelector((state: RootState) => state.setting.lock);
  const dispatch = useDispatch();
  const onToggleLock = (index: number) => dispatch(toggleLock(index));

  useEffect(() => {
    if (location.pathname.includes("setting")) {
      setVisible(false);
    }
  }, []);

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
              {isLocked.map((lock, index) => {
                if (lock)
                  return (
                    <FontAwesomeIcon
                      className="lock"
                      icon={faLock}
                      onClick={() => onToggleLock(index)}
                    />
                  );
                else
                  return (
                    <FontAwesomeIcon
                      className="unlock"
                      icon={faUnlock}
                      onClick={() => onToggleLock(index)}
                    />
                  );
              })}
            </>
          )}
        </Toolbar.FlexibleSpace>
      )}
    />
  );
}
