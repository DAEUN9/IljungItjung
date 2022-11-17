import axios from "axios";

const apiInstance = () => {
  const instance = axios.create({
<<<<<<< HEAD
<<<<<<< HEAD
    baseURL: "http://localhost:5173/api",
    headers: {
      Accept: "application/json",
=======
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
      "Content-type": "application/json",
>>>>>>> 653a39ec835717dd46a43023cbbfaa11b42ac025
=======
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
      Accept: "application/json",
>>>>>>> 5d8f8f59d8ec19d8971dbe9e724ebc3c5f8058a5
    },
  });

  instance.interceptors.response.use((res) => res.data);

  return instance;
};

export { apiInstance };
