package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.constant.JwtClaimsConstant;
import cn.lanqiao.library_final_project.context.BaseContext;
import cn.lanqiao.library_final_project.module.dto.UserDto;
import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.properties.JwtProperties;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IUserInfoService;
import cn.lanqiao.library_final_project.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;
    @Autowired
    private JwtProperties jwtProperties;
    /***
     * 分页查询
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(UserDto userDto) {
        log.info("分页查询参数{}", userDto);
        PageResult pageResult = iUserInfoService.pagequery(userDto);
        return Result.success(pageResult);
    }





    /**
     * 用户登录
     * @param userDto
     * @param response
     * @return
     */
    @PostMapping( "/login")
    public Result<UserInfo> login(@RequestBody UserDto userDto, HttpServletResponse response, HttpSession session) {
//            数据加密
        String password = userDto.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        userDto.setPassword(password);
//        校验验证码
        String captchaCode = (String) session.getAttribute("captchaCode");
        if (userDto.getCode() !=null && captchaCode.equalsIgnoreCase(userDto.getCode())) {
//            校验登录
            UserInfo result = iUserInfoService.lambdaQuery().eq(UserInfo::getUsername, userDto.getUsername())
                    .eq(UserInfo::getPassword, userDto.getPassword())
                    .one();
            if (result != null) {
                //        为用户生成jwt令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER_ID, result.getAid());
                String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
                // 创建Cookie
                Cookie cookie = new Cookie("token", jwt);
                cookie.setHttpOnly(true); // 设置为HttpOnly，防止JavaScript访问
                cookie.setSecure(true); // 如果使用HTTPS，设置为true
                cookie.setPath("/"); // 设置Cookie的路径
                cookie.setMaxAge(7200000); // 设置Cookie的有效时间，单位为秒
                // 将Cookie添加到响应中
                response.addCookie(cookie);
                return Result.success(new UserInfo(result.getUsername()));
            }
            return Result.error("账户或密码输入错误");
        }else {
            return Result.error("验证码输入错误");
        }
    }

    /**
     * 用户注销
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletResponse response) {
        // 创建Cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true); // 设置为HttpOnly，防止JavaScript访问
        cookie.setSecure(true); // 如果使用HTTPS，设置为true
        cookie.setPath("/"); // 设置Cookie的路径
        cookie.setMaxAge(0); // 设置Cookie的有效时间，单位为秒
        // 将Cookie添加到响应中
        response.addCookie(cookie);
        BaseContext.removeCurrentId();
        return Result.success();
    }

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/checkUsername")
    public Result checkUsername(@RequestParam String username) {
        if (username == null || username.isEmpty()) {
            return Result.error("用户名不能为空");
        }
        UserInfo userInfo = iUserInfoService.lambdaQuery().eq(UserInfo::getUsername, username).one();
        if (userInfo != null){
            return Result.error("用户名存在");
        }
        return Result.success();
    }
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserInfo userInfo) {
        log.info("用户注册信息{}", userInfo);
        UserInfo selectUser = iUserInfoService.lambdaQuery().eq(UserInfo::getUsername, userInfo.getUsername()).one();
        if (selectUser != null){
            return Result.error("用户名已存在");
        }
//        数据加密
        String password = userInfo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(password);
        iUserInfoService.save(userInfo);
        return Result.success();
    }

    @PostMapping("/editUser")
    public Result<?> editUserInfo(@RequestBody UserDto userDto) {
        return iUserInfoService.editUserInfo(userDto);
    }
}
