package cn.lanqiao.library_final_project.interceptor;


import cn.lanqiao.library_final_project.constant.JwtClaimsConstant;
import cn.lanqiao.library_final_project.context.BaseContext;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //2、校验令牌
        try {
//            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long adminId = Long.valueOf(claims.get(JwtClaimsConstant.ADMIN_ID).toString());
//            log.info("当前管理员用户id：{}", adminId);
            BaseContext.setCurrentId(adminId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            // 4、不通过，重定向到登录页面
            response.sendRedirect("/html/admin/login.html");
            return false;
        }
    }
}
