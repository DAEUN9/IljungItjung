import { ComponentStory, ComponentMeta } from '@storybook/react';

import CustomChip from '@components/common/CustomChip';

export default {
  title: 'Common/CustomChip',
  component: CustomChip,
} as ComponentMeta<typeof CustomChip>;

const Template: ComponentStory<typeof CustomChip> = (args) => (
  <CustomChip {...args} />
);

export const Default = Template.bind({});
Default.args = {
  label: 'Default Chip',
  onClick: undefined,
  onDelete: undefined,
};

export const Inactive = Template.bind({});
Inactive.args = {
  label: 'Inactive Chip',
  active: false,
  onClick: undefined,
  onDelete: undefined,
};

export const Secondary = Template.bind({});
Secondary.args = {
  label: 'Secondary Chip',
  color: 'secondary',
  onClick: undefined,
  onDelete: undefined,
};

export const Click = Template.bind({});
Click.args = {
  label: 'Clickable Chip',
  onClick: () => {},
  onDelete: undefined,
};

export const Delete = Template.bind({});
Delete.args = {
  label: 'Delete icon Chip',
  onClick: undefined,
  onDelete: () => {},
};

export const ClickAndDelete = Template.bind({});
ClickAndDelete.args = {
  label: 'Click and Delete icon Chip',
  onClick: () => {},
  onDelete: () => {},
};
