import { Drawer, IconButton } from "@mui/material";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import DescriptionOutlinedIcon from "@mui/icons-material/DescriptionOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import PersonOutlineOutlinedIcon from "@mui/icons-material/PersonOutlineOutlined";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import styles from "@styles/common/Sidebar.module.scss";
import logo from "@assets/logo.png";
import defaultImg from "@assets/defaultImg.png";

const Sidebar = () => {
  const navigate = useNavigate();

  const url = new URL(window.location.href);
  const [menu, setMenu] = useState(url.pathname.substring(1, url.pathname.length));

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
            <IconButton className={styles[`${menu === "search" ? "click-icon" : ""}`]} onClick={() => handleClick("search")}>
              <SearchOutlinedIcon />
            </IconButton>
            <IconButton className={styles[`${menu === "reservation" ? "click-icon" : ""}`]} onClick={() => handleClick("reservation")}>
              <DescriptionOutlinedIcon />
            </IconButton>
          </div>
        </div>
        <div className={styles["bottom"]}>
          <div className={styles["icons"]}>
            <IconButton className={styles["logout"]}>
              <LogoutOutlinedIcon />
            </IconButton>
            <IconButton className={styles[`${menu === "profile" ? "click-icon" : ""}`]} onClick={() => handleClick("profile")}>
              <PersonOutlineOutlinedIcon />
            </IconButton>
          </div>
          <img className={styles[`${menu === "calendar/my" ? "click-profile" : ""}`]} src={defaultImg} onClick={() => handleClick("calendar/my")} />
        </div>
      </div>
    </Drawer>
  );
};

export default Sidebar;
