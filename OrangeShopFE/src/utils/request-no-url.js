import axios from 'axios'

// create an axios instance
const service = axios.create({
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

export default service
