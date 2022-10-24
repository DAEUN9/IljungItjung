import { ComponentStory, ComponentMeta } from '@storybook/react';

import Schedule from '@components/common/Schedule';
import defaultImg from '@assets/defaultImg.png';

export default {
  title: 'Common/Schedule',
  component: Schedule,
} as ComponentMeta<typeof Schedule>;

const Template: ComponentStory<typeof Schedule> = (args) => (
  <Schedule {...args} />
);

export const Basic = Template.bind({});
Basic.args = {
  color: "#F4F38A",
  date: "10월 14일",
  time: "11:00-12:00",
  userImg: defaultImg,
  userName: "곰고구마",
  category: "예쁜 그림",
};

export const NoDate = Template.bind({});
NoDate.args = {
  color: "#F4F38A",
  time: "11:00-12:00",
  userId: "1",
  userImg: defaultImg,
  userName: "곰고구마",
  category: "예쁜 그림",
};

const renderSomeComponents = (): JSX.Element => {
  return (
    <div>
      * some components *
    </div>
  )
}

export const Render = Template.bind({});
Render.args = {
  color: "#F4F38A",
  date: "10월 14일",
  time: "11:00-12:00",
  userId: "1",
  userImg: defaultImg,
  userName: "곰고구마",
  category: "예쁜 그림",
  render: renderSomeComponents
};