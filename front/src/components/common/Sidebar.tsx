import { Drawer, IconButton } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  IoSearchOutline,
  IoDocumentTextOutline,
  IoExitOutline,
  IoPersonOutline,
} from "react-icons/io5";

import styles from "@styles/common/Sidebar.module.scss";
import logo from "@assets/logo.png";
import defaultImg from "@assets/defaultImg.png";

const Sidebar = () => {
  const navigate = useNavigate();

  const url = new URL(window.location.href);
  const [menu, setMenu] = useState(
    url.pathname.substring(1, url.pathname.length)
  );

  const handleClick = (type: string) => {
    navigate(`/${type}`);
    setMenu((menu) => type);
  };

  return (
    <Drawer
      variant="permanent"
      anchor="left"
      className={styles["drawer"]}
      PaperProps={{ className: styles["paper"] }}
    >
      <div className={styles["sidebar"]}>
        <div className={styles["top"]}>
          <img src={logo} onClick={() => handleClick("calendar/my")} />
          <div className={styles["icons"]}>
            <IconButton
              className={styles[`${menu === "search" ? "click-icon" : ""}`]}
              onClick={() => handleClick("search")}
            >
              <IoSearchOutline />
            </IconButton>
            <IconButton
              className={
                styles[`${menu === "reservation" ? "click-icon" : ""}`]
              }
              onClick={() => handleClick("reservation")}
            >
              <IoDocumentTextOutline />
            </IconButton>
          </div>
        </div>
        <div className={styles["bottom"]}>
          <div className={styles["icons"]}>
            <IconButton className={styles["logout"]}>
              <IoExitOutline />
            </IconButton>
            <IconButton
              className={styles[`${menu === "profile" ? "click-icon" : ""}`]}
              onClick={() => handleClick("profile")}
            >
              <IoPersonOutline />
            </IconButton>
          </div>
          <img
            className={
              styles[`${menu === "calendar/my" ? "click-profile" : ""}`]
            }
            src={defaultImg}
            onClick={() => handleClick("calendar/my")}
          />
        </div>
      </div>
    </Drawer>
  );
};

export default Sidebar;
