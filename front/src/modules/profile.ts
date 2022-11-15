import { MyProfile } from "@components/types/types";

const SET_PROFILE = "profile/SET_PROFILE" as const;

export const setProfile = (info: MyProfile) => ({
  type: SET_PROFILE,
  payload: info,
});

type ProfileAction = ReturnType<typeof setProfile>;

interface ProfileState {
  profile: MyProfile;
}

const initialState: ProfileState = {
  profile: {
    nickname: "",
    email: "",
    imagePath: "",
    introduction: "",
    description: "",
  },
};

export default function reducer(
  state: ProfileState = initialState,
  action: ProfileAction
) {
  switch (action.type) {
    case SET_PROFILE:
      return { ...state, profile: action.payload };
    default:
      return state;
  }
}
