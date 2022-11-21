import { useSelector } from "react-redux";
import Avatar from "@mui/material/Avatar";
import AvatarGroup from "@mui/material/AvatarGroup";

import styles from "@styles/Calendar/Calendar.module.scss";
import { RootState } from "@modules/index";
import { parseList } from "../common/util";
import { SchedulerDate } from "@components/types/types";

const getUserImages = (list: SchedulerDate[]) => {
  const set = new Set<string>();
  let nicknames: string[] = [];

  for (let item of list) {
    if (!set.has(item.imagePath)) {
      set.add(item.imagePath);
    }
  }

  for (let item of set) {
    nicknames.push(item);
  }

  return nicknames;
};

const CustomerList = () => {
  const { list } = useSelector((state: RootState) => state.mycalendar);
  const parsed = parseList(list);
  const images = getUserImages(parsed);

  return (
    <div className={styles.avatar}>
      <AvatarGroup
        max={4}
        sx={{
          "& .MuiAvatar-root": { width: 30, height: 30, fontSize: 15 },
        }}
      >
        {images.map((image) => (
          <Avatar key={image} alt="user" src={image} />
        ))}
      </AvatarGroup>
    </div>
  );
};

export default CustomerList;
