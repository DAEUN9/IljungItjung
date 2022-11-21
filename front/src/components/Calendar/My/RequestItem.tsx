import styles from "@styles/Calendar/Calendar.module.scss";
import Schedule from "@components/common/Schedule";
import DetailInfo from "./DetailInfo";
import {
  formatTime,
  getFullDate,
  InfoItemProps,
} from "@components/Calendar/common/util";
import RequestButtons from "./RequestButtons";

const RequestItem = ({ item }: InfoItemProps) => {
  const {
    color,
    startDate,
    endDate,
    categoryName,
    nickname,
    phonenum,
    contents,
    imagePath,
  } = item;
  const time = formatTime(startDate?.toString(), endDate?.toString());

  return (
    <div className={styles["info-item"]}>
      <Schedule
        color={color}
        date={getFullDate(new Date(startDate.toString()))}
        time={time ?? "-"}
        userId="유저아이디"
        userName={nickname}
        category={categoryName ?? "-"}
        userImg={imagePath}
        render={() => (
          <>
            <DetailInfo phone={phonenum} desc={contents} />
            <RequestButtons item={item} />
          </>
        )}
      />
    </div>
  );
};

export default RequestItem;
