import { ComponentStory, ComponentMeta } from '@storybook/react';
import CustomModal from '@components/common/CustomModal';
import { Box } from '@mui/material';
import iljung from '@assets/iljung.png';

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

export const ModalWithContent = Template.bind({});

const style = {
  display: 'flex',
  justifyContent: 'center',
};

const ModalContent = () => {
  return (
    <Box sx={style}>
      <div
        style={{ display: 'flex', flexDirection: 'column', padding: '10px' }}
      >
        <div style={{ width: '100px' }}>
          <img style={{ width: '100%' }} src={iljung} alt="iljung img" />
        </div>
        <div>modal content</div>
      </div>
    </Box>
  );
};

ModalWithContent.args = {
  children: <ModalContent />,
};
