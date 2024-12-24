package cn.lanqiao.library_final_project.interceptor;


import cn.lanqiao.library_final_project.properties.JwtProperties;
import cn.lanqiao.library_final_project.utils.JwtUtil;
import io.jsonwebtoken.Claims;
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
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

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
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
//            log.info("jwt校验:{}", token);
//            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
         /*   Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：{}", empId);
            BaseContext.setCurrentId(empId);*/
            //3、通过，放行
            return false;
        } catch (Exception ex) {
            // 4、不通过，重定向到登录页面
            response.sendRedirect("/html/admin/login.html");
            return false;
        }
    }
}
