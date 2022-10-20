import { ComponentStory, ComponentMeta } from '@storybook/react';
import CustomModal from '@components/common/CustomModal';

export default {
  title: 'Common/CustomModal',
  component: CustomModal,
} as ComponentMeta<typeof CustomModal>;

const Template: ComponentStory<typeof CustomModal> = (args) => (
  <CustomModal {...args} />
);

export const Default = Template.bind({});

export const CustomButtonLabel = Template.bind({});
CustomButtonLabel.args = {
  confirmLabel: '예약 취소',
  cancelLabel: '닫기',
};
