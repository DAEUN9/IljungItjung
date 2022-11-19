const RERENDER = "reservation/RERENDER" as const;

export const rerender = (render: object) => ({
  type: RERENDER,
  payload: render,
});

type RenderAction = ReturnType<typeof rerender>;

interface RenderState {
  renderObj: object;
}

const initialState: RenderState = {
  renderObj: {},
};

function render(
  state: RenderState = initialState,
  action: RenderAction
): RenderState {
  switch (action.type) {
    case RERENDER:
      return { ...state, renderObj: action.payload };
    default:
      return state;
  }
}

export default render;
