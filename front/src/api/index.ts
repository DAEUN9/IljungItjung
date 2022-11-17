import axios from "axios";

const apiInstance = () => {
  const instance = axios.create({
<<<<<<< HEAD
    baseURL: "http://localhost:5173/api",
    headers: {
      Accept: "application/json",
=======
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
      "Content-type": "application/json",
>>>>>>> 653a39ec835717dd46a43023cbbfaa11b42ac025
    },
  });

  instance.interceptors.response.use((res) => res.data);

  return instance;
};

export { apiInstance };
