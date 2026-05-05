import axios from "axios";

const API = axios.create({
  baseURL: `${process.env.VITE_BASE_URL}/api`,
});
delete API.defaults.headers.common["Authorization"];
export default API;
