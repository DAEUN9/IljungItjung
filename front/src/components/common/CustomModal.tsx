import styles from '@styles/common/Custom.module.scss';
import { ReactNode } from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import CustomButton from './CustomButton';

interface ModalProps {
  open: boolean;
  setOpen?: (open: boolean) => void;
  confirmLabel?: string;
  cancelLabel?: string;
  children?: ReactNode;
  handleConfirm?: () => void;
  handleCancle?: () => void;
}

const CustomModal = ({
  open = true,
  setOpen,
  confirmLabel = '확인',
  cancelLabel = '취소',
  handleConfirm = undefined,
  handleCancle = undefined,
  children,
  ...rest
}: ModalProps) => {
  return (
    <div className="custom-modal">
      <Modal open={open}>
        <Box className={styles['modal-box']}>
          <div>{children}</div>
          <div className={styles['button-group']}>
            <CustomButton color="secondary">{cancelLabel}</CustomButton>
            <CustomButton>{confirmLabel}</CustomButton>
          </div>
        </Box>
      </Modal>
    </div>
  );
};

export default CustomModal;
