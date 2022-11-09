import axios from "axios";

const apiInstance = () => {
  const instance = axios.create({
    baseURL: "https://k7d106.p.ssafy.io/api",
    headers: {
      Accept: "application/json",
    },
  });

  instance.interceptors.response.use((res) => res.data);

  return instance;
};

export { apiInstance };
