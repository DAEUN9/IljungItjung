import { createContext, useContext, useState } from "react";
import Snackbar from "@mui/material/Snackbar";

const useSnackbar = () => {
  const [open, setOpen] = useState(false);
  const [message, setMessage] = useState("");

  const component = (
    <Snackbar
      anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
      autoHideDuration={6000}
      open={open}
      onClose={() => setOpen(false)}
      message={message}
    />
  );

  return { component, setMessage, setOpen };
};

export const Context = createContext<ReturnType<typeof useSnackbar> | null>(null);

export const useSnackbarContext = () => {
  const snackbar = useContext(Context);
  if(!snackbar) throw new Error();
  return snackbar;
}

export default useSnackbar;
