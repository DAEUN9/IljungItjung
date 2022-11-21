import { ReactNode, MouseEventHandler } from 'react';
import { ThemeProvider } from '@mui/material/styles';
import Chip from '@mui/material/Chip';

import styles from '@styles/common/Custom.module.scss';
import theme from './theme';

interface ChipProps {
  label: ReactNode;
  color?: 'primary' | 'secondary';
  active?: boolean;
  onClick?: MouseEventHandler<HTMLDivElement>;
  onDelete?: () => void;
}

const CustomChip = ({
  label,
  color = 'primary',
  active = true,
  ...rest
}: ChipProps) => {
  return (
    <ThemeProvider theme={theme}>
      <Chip
        className={active ? '' : styles['half-opacity']}
        label={label}
        color={color}
        {...rest}
      />
    </ThemeProvider>
  );
};

export default CustomChip;
