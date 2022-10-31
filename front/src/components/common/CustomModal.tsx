import { ReactNode } from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';

import styles from '@styles/common/Custom.module.scss';
import CustomButton from './CustomButton';

interface ModalProps {
  open: boolean;
  setOpen: (open: boolean) => void;
  handleConfirm: () => void;
  confirmLabel?: string;
  cancelLabel?: string;
  children?: ReactNode;
}

const CustomModal = ({
  open = true,
  setOpen,
  handleConfirm,
  confirmLabel = '확인',
  cancelLabel = '취소',
  children,
  ...rest
}: ModalProps) => {
  const handleCancle = () => setOpen(false);

  return (
    <div className="custom-modal">
      <Modal open={open}>
        <Box className={styles['modal-box']}>
          <div>{children}</div>
          <div className={styles['button-group']}>
            <CustomButton onClick={handleCancle} color="secondary">{cancelLabel}</CustomButton>
            <CustomButton onClick={handleConfirm}>{confirmLabel}</CustomButton>
          </div>
        </Box>
      </Modal>
    </div>
  );
};

export default CustomModal;
