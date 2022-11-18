import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { TextField } from "@mui/material";

import { RootState } from "@modules/index";
import CustomModal from "@components/common/CustomModal";
import styles from "@styles/Setting/DeleteModal.module.scss";
import { deleteSchedule } from "@api/setting";
import iljung from "@assets/iljung.png";
import theme from "@components/common/theme";
import { ThemeProvider } from "@emotion/react";

const DeleteModal = () => {
  const [reason, setReason] = useState("");
  const [deleteOpen, setDeleteOpen] = useState(false);
  const { deleteItem } = useSelector((state: RootState) => state.setting);

  useEffect(() => {
    if (deleteItem.categoryName.length > 0) setDeleteOpen(true);
  }, [deleteItem]);

  const handleDelete = () => {
    deleteSchedule(deleteItem.id, reason, (res: any) => {
      console.log(deleteItem.id, reason);
    });

    setDeleteOpen(false);
  };

  return (
    <ThemeProvider theme={theme}>
      <CustomModal
        open={deleteOpen}
        setOpen={setDeleteOpen}
        confirmLabel="확인"
        cancelLabel="취소"
        handleConfirm={handleDelete}
        children={
          <div className={styles["modal-content"]}>
            <div className={styles.img}>
              <img src={iljung} />
            </div>
            <div className={styles.text}>예약을 취소하시겠습니까?</div>
            <TextField
              className={styles.textfield}
              multiline
              rows={3}
              placeholder="예약을 취소하는 이유를 알려주세요."
              fullWidth
              onChange={(e) => setReason(e.target.value)}
            />
          </div>
        }
      />
    </ThemeProvider>
  );
};

export default DeleteModal;
