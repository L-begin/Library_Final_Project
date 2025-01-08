package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.constant.JwtClaimsConstant;
import cn.lanqiao.library_final_project.context.BaseContext;
import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.properties.JwtProperties;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IAdminInfoService;
import cn.lanqiao.library_final_project.service.IUserInfoService;
import cn.lanqiao.library_final_project.service.impl.AdminInfoServiceImpl;
import cn.lanqiao.library_final_project.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
@Slf4j
public class AdminInfoController {
    @Autowired
    private IAdminInfoService adminInfoService;
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private IUserInfoService iUserInfoService;
        @PostMapping( "/admin/login")
        public Result<AdminInfo> login(@RequestBody AdminInfo adminInfo, HttpServletResponse response) {
//            数据加密
            String password = adminInfo.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            adminInfo.setPassword(password);
            AdminInfo result = adminInfoService.lambdaQuery().eq(AdminInfo::getUsername, adminInfo.getUsername())
                    .eq(AdminInfo::getPassword, adminInfo.getPassword())
                    .one();
            if (result!=null){
                //        为用户生成jwt令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.ADMIN_ID,result.getId());
                String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
                // 创建Cookie
                Cookie cookie = new Cookie("token", jwt);
                cookie.setHttpOnly(true); // 设置为HttpOnly，防止JavaScript访问
                //cookie.setSecure(true); // 如果使用HTTPS，设置为true
                cookie.setPath("/"); // 设置Cookie的路径
                cookie.setMaxAge(7200000); // 设置Cookie的有效时间，单位为秒
                // 将Cookie添加到响应中
                response.addCookie(cookie);
                BaseContext.setCurrentId(Long.valueOf(result.getId()));
                return Result.success(new AdminInfo(result.getUsername()));
            }
            return Result.error("登录失败");
        }
        @GetMapping("/admin/logout")
        public Result logout(HttpServletResponse response) {
            Cookie cookie = new Cookie("token", null);
            cookie.setMaxAge(0); // Set max age to 0 to remove the cookie
            cookie.setHttpOnly(true);
           // cookie.setSecure(true); // Use true if you are using HTTPS
            cookie.setPath("/");
            response.addCookie(cookie);
            // 设置SameSite属性
            BaseContext.removeCurrentId();
            return Result.success();
        }


    /***
     * 读者批量删除
     * @param aids
     * @return
     */
    @DeleteMapping("/admin")
    public Result delete (@RequestParam List<Long> aids){
        log.info("根据id批量删除数据{}",aids);
        boolean result = iUserInfoService.removeByIds(aids);
        if (result){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }


    /***
     * 修改读者信息
     * @param userInfo
     * @return
     */
    @PostMapping("/admin/edit")
    public  Result update(@RequestBody UserInfo userInfo){

        log.info("修改用户信息{}",userInfo);
        //根据id来修改数据
        boolean result = iUserInfoService.updateById(userInfo);
        if (result){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }
}
