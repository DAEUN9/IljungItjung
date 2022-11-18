import { useCallback, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLock, faUnlock } from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import { Toolbar } from "@devexpress/dx-react-scheduler-material-ui";
import IconButton from "@mui/material/IconButton";
import { IoSettingsSharp } from "react-icons/io5";

import styles from "@styles/Calendar/Calendar.module.scss";
import CustomerList from "@components/Calendar/My/CustomerList";
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

export default function CustomToolbar() {
  const [visible, setVisible] = useState(true);
  const location = useLocation();
  const dispatch = useDispatch();
  const { set, lock, lockMap } = useSelector(
    (state: RootState) => state.setting
  );

  useEffect(() => {
    if (location.pathname.includes("setting")) {
      setVisible(false);
    }
  }, []);

  const handleClickLock = (index: number) => {
    dispatch(toggleLock(index));

    // set에서 해당하는 요일의 block된 시간들을 lockMap에 저장한다.
    const keys = set.keys();
    for (const key of keys) {
      console.log(key + " in set");
      const blockDay = Number(key.substring(0, 1));
      if ((blockDay + 6) % 7 === index) {
        const time = key.substring(9);
        dispatch(lockShade(blockDay, time));
      }
    }
  };

  const handleClickUnlock = (index: number) => {
    dispatch(toggleLock(index));

    // lockMap의 저장된 시간들을 지운다.
    dispatch(deleteLockShade((index + 6) % 7, "", true));
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
                    <div className="day-lock" key={index}>
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
                    <div className="day-lock" key={index}>
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
