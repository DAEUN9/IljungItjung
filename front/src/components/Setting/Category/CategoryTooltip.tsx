import { IoMdAdd } from "react-icons/io";
import { AiFillDelete } from "react-icons/ai";
import { MdEdit } from "react-icons/md";

import styles from "@styles/Setting/SettingTooltip.module.scss";

export const CategoryTooltip = () => {
  return (
    <div className={styles["tooltip-category"]}>
      <b>
        모든 변경사항은 상단의 <span>저장</span> 버튼을 눌러야 반영됩니다.
      </b>
      <div className={styles.func}>
        <div className={styles.title}>
          <AiFillDelete size="18px" />
          <h2>카테고리 삭제</h2>
        </div>
        <div className={styles.content}>
          카테고리 목록에 있는 각 칩의 X 버튼을 눌러 카테고리를 삭제할 수
          있습니다. <br />
          카테고리의 개수가 1개인 경우, 해당 카테고리는 삭제되지 않습니다.
        </div>
      </div>
      <div className={styles.func}>
        <div className={styles.title}>
          <IoMdAdd size="18px" />
          <h2>카테고리 추가</h2>
        </div>
        <div className={styles.content}>
          카테고리 추가 탭에서 필요한 정보를 입력한 후 완료 버튼을 눌러
          카테고리를 추가할 수 있습니다.
        </div>
      </div>
      <div className={styles.func}>
        <div className={styles.title}>
          <MdEdit size="18px" />
          <h2>카테고리 변경</h2>
        </div>
        <div className={styles.content}>
          카테고리 목록에 있는 칩 중 수정하고자 하는 카테고리를 선택하고, <br />
          카테고리 변경 탭에서 수정 후 완료 버튼을 눌러 변경할 수 있습니다.
        </div>
      </div>
    </div>
  );
};
