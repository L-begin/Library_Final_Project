package cn.lanqiao.library_final_project.interceptor;


import cn.lanqiao.library_final_project.properties.JwtProperties;
import cn.lanqiao.library_final_project.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源

        //1、从cookie中获取令牌
        String token =null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }
        //2、校验令牌
        try {
//            log.info("jwt校验:{}", token);
            //3、通过，放行
            JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            return true;
        } catch (Exception ex) {
            // 4、不通过，重定向到登录页面
            response.sendRedirect("/html/common/login.html");
            return false;
        }
    }
}
