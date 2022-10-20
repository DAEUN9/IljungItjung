import { ButtonHTMLAttributes } from 'react';
import { ThemeProvider } from '@mui/material';

import Button from '@mui/material/Button';
import theme from './theme';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  size?: 'small' | 'medium' | 'large';
  variant?: 'contained' | 'outlined';
  color?: 'primary' | 'secondary';
  disabled?: true | false;
  onClick?: () => void;
}

const CustomButton = ({
  size = 'medium',
  color = 'primary',
  variant = 'contained',
  children,
  ...rest
}: ButtonProps) => {
  return (
    <ThemeProvider theme={theme}>
      <Button {...rest} size={size} color={color} variant={variant}>
        {children}
      </Button>
    </ThemeProvider>
  );
};

export default CustomButton;
