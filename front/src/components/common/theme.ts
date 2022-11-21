import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#6B7BB1',
      contrastText: '#fff',
    },
    secondary: {
      main: '#E0E0E0',
    },
    warning: {
      main: '#E6330F',
    },
  },
});

export default theme;
