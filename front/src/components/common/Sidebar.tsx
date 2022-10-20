import { Drawer, IconButton } from "@mui/material";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import DescriptionOutlinedIcon from "@mui/icons-material/DescriptionOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import PersonOutlineOutlinedIcon from "@mui/icons-material/PersonOutlineOutlined";
import { useNavigate } from "react-router-dom";

import styles from "@styles/common/Sidebar.module.scss";
import logo from "@assets/images/logo.png";
import defaultImg from "@assets/images/defaultImg.png";

const Sidebar = () => {
  const navigate = useNavigate();

  const handleClick = (type: string) => {
    navigate(`/${type}`);
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
            <IconButton onClick={() => handleClick("search")}>
              <SearchOutlinedIcon />
            </IconButton>
            <IconButton onClick={() => handleClick("reservation")}>
              <DescriptionOutlinedIcon />
            </IconButton>
          </div>
        </div>
        <div className={styles["bottom"]}>
          <div className={styles["icons"]}>
            <IconButton className={styles["logout"]}>
              <LogoutOutlinedIcon />
            </IconButton>
            <IconButton onClick={() => handleClick("profile")}>
              <PersonOutlineOutlinedIcon />
            </IconButton>
          </div>
          <img src={defaultImg} onClick={() => handleClick("calendar/my")} />
        </div>
      </div>
    </Drawer>
  );
};

export default Sidebar;
