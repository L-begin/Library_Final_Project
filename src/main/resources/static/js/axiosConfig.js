axios.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');

        // 如果token存在，添加到请求头
        if (token) {
            config.headers.Authorization = token;
        }
        return config;
    },
    error => {
        console.error('请求错误:', error);
        return Promise.reject(error);
    }
);