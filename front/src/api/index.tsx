import axios from 'axios';

const apiInstance = () => {
  const instance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
      'Content-type': 'application/json',
    },
  });

  return instance;
};

export { apiInstance };
