import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { Toolbar } from '@devexpress/dx-react-scheduler-material-ui';
import Avatar from '@mui/material/Avatar';
import AvatarGroup from '@mui/material/AvatarGroup';
import IconButton from '@mui/material/IconButton';
import { IoSettingsSharp } from "react-icons/io5";

import styles from '@styles/Calendar/Calendar.module.scss';

const SettingButton = () => {
  const navigate = useNavigate();
  const handleClick = useCallback(() => navigate('/setting'), []);

  return (
    <IconButton sx={{ marginLeft: '20px' }} onClick={handleClick}>
      <IoSettingsSharp />
    </IconButton>
  );
};

const CustomerList = () => (
  <div className={styles.avatar}>
    <AvatarGroup
      max={4}
      sx={{
        '& .MuiAvatar-root': { width: 30, height: 30, fontSize: 15 },
      }}
    >
      <Avatar alt="Remy Sharp" />
      <Avatar alt="Travis Howard" />
      <Avatar alt="Agnes Walker" />
      <Avatar alt="Trevor Henderson" />
      <Avatar alt="Trevor Henderson" />
    </AvatarGroup>
  </div>
);

export default function CustomToolbar() {
  return (
    <Toolbar
      rootComponent={({ children }) => <Toolbar.Root>{children}</Toolbar.Root>}
      flexibleSpaceComponent={() => (
        <Toolbar.FlexibleSpace className={styles['toolbar-right']}>
          <CustomerList />
          <SettingButton />
        </Toolbar.FlexibleSpace>
      )}
    />
  );
}
