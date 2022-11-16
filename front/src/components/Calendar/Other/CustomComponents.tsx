import styled from "@emotion/styled";
import MuiTextField from "@mui/material/TextField";
import MuiInputLabel from "@mui/material/InputLabel";
import MuiSelect from "@mui/material/Select";

export const TextField = styled(MuiTextField)`
  > .Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }
`;

export const PhoneTextField = styled(TextField)`
  > .MuiInputBase-root > input {
    padding: 7px;
    padding-left: 12px;
  }
`;

export const Select = styled(MuiSelect)`
  &.Mui-focused > fieldset {
    border-color: #6b7bb1 !important;
  }

  & .MuiSelect-select > div {
    padding: 0;
  }
`;

export const InputLabel = styled(MuiInputLabel)`
  color: rgba(0, 0, 0, 0.5);
  background: #f5f5f5;
  padding: 0 5px;

  &.Mui-focused {
    color: #6b7bb1;
  }
`;
