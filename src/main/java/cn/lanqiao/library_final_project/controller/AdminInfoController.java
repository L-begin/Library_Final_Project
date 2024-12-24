package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.constant.JwtClaimsConstant;
import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import cn.lanqiao.library_final_project.module.pojo.vo.AdminLoginVO;
import cn.lanqiao.library_final_project.properties.JwtProperties;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IAdminInfoService;
import cn.lanqiao.library_final_project.service.impl.AdminInfoServiceImpl;
import cn.lanqiao.library_final_project.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        @PostMapping(value = "/admin/login", produces = "application/json;charset=UTF-8")
        public Result<AdminLoginVO> login(@RequestBody AdminInfo adminInfo) {
            AdminInfo result = adminInfoService.lambdaQuery().eq(AdminInfo::getUsername, adminInfo.getUsername())
                    .eq(AdminInfo::getPassword, adminInfo.getPassword())
                    .one();
            if (result!=null){
                //        为用户生成jwt令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER_ID,result.getId());
                String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
                AdminLoginVO adminLoginVO = new AdminLoginVO();
                adminLoginVO.setId(result.getId());
                adminLoginVO.setToken(jwt);
                return Result.success(adminLoginVO);
            }
            return Result.error("登录失败");
        }
}
