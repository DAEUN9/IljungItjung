import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { Toolbar } from '@devexpress/dx-react-scheduler-material-ui';
import Avatar from '@mui/material/Avatar';
import AvatarGroup from '@mui/material/AvatarGroup';
import IconButton from '@mui/material/IconButton';
import SettingsIcon from '@mui/icons-material/Settings';

import styles from '@styles/Calendar/Calendar.module.scss';

const SettingButton = () => {
  const navigate = useNavigate();
  const handleClick = useCallback(() => navigate('/setting'), []);

  return (
    <IconButton sx={{ marginLeft: '20px' }} onClick={handleClick}>
      <SettingsIcon />
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
      <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
      <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg" />
      <Avatar alt="Agnes Walker" src="/static/images/avatar/4.jpg" />
      <Avatar alt="Trevor Henderson" src="/static/images/avatar/5.jpg" />
      <Avatar alt="Trevor Henderson" src="/static/images/avatar/5.jpg" />
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
