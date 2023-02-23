/* eslint-disable */
import axios from "axios";
import { MessageBox, Message } from "element-ui";
import store from "@/store";
import { getToken } from "@/utils/auth";
import router from "@/router";
import { removeToken } from "@/utils/auth";

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API_CSS, // url = base url + request url

  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 65000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    // do something before request is sent
    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers["Authorization"] = "Bearer " + getToken();
      config.headers["portal"] = 1;
      config.headers["Content-Type"] = "application/json";
      config.responseType = "arraybuffer";
    }
    return config;
  },
  (error) => {
    //debugger
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  (response) => {
    const res = response.data;
    return res;
  },
  (error) => {
    console.log("err" + error); // for debug
    Message({
      message: error.response.data.message,
      type: "error",
      duration: 5 * 1000,
    });
    if (error.response.data.code === 401) {
      removeToken();
      router.push({ path: "/" });
    }
    if (error.response.data.code === 403) {
      router.push({ path: "/" });
    }
    return Promise.reject(error);
  }
);

export default service;
