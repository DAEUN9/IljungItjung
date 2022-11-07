import axios from "axios";

const apiInstance = () => {
  const instance = axios.create({
    baseURL: "http://localhost:5173/api",
    headers: {
      "Content-type": "application/json",
    },
  });

  instance.interceptors.response.use((res) => res.data);

  return instance;
};

export { apiInstance };
