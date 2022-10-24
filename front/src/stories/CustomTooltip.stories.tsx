import { ComponentStory, ComponentMeta } from '@storybook/react';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';

import CustomTooltip from '@components/common/CustomTooltip';

export default {
  title: 'Common/CustomTooltip',
  component: CustomTooltip,
} as ComponentMeta<typeof CustomTooltip>;

const Template: ComponentStory<typeof CustomTooltip> = (args) => (
  <CustomTooltip {...args} />
);

export const Default = Template.bind({});
Default.args = {
  title: 'Default Tooltip Title',
  children: <Button>Default</Button>,
};

export const TooltipWithIconButton = Template.bind({});
TooltipWithIconButton.args = {
  title: 'Tooltip with IconButton',
  children: (
    <IconButton>
      <HelpOutlineIcon />
    </IconButton>
  ),
};

export const CustomTooltipTitle = Template.bind({});
const CustomTitle = () => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column' }}>
      <div style={{ fontWeight: 600, fontSize: '15px' }}>
        Custom Tooltip Title
      </div>
      <div>Tooltip의 title props 타입은 ReactNode입니다.</div>
      <div>컴포넌트를 넣을 수 있다는 뜻이죠!</div>
    </div>
  );
};

CustomTooltipTitle.args = {
  title: <CustomTitle />,
  children: <Button>Tooltip</Button>,
};

export const TooltipPlacement = Template.bind({});
TooltipPlacement.args = {
  title: 'Change Tooltip Placement',
  children: <Button>Placement</Button>,
  placement: 'right-start',
};
