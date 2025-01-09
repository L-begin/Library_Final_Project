package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.AdminDto;
import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import cn.lanqiao.library_final_project.mapper.AdminInfoMapper;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {

    @Autowired
    private AdminInfoMapper adminInfoMapper;
    @Override
    @Transactional
    public Result<?> editAdminInfo(AdminDto adminDto) {
        try {
            // 验证旧密码
            String oldPassword = DigestUtils.md5DigestAsHex(adminDto.getOldPassword().getBytes());
            AdminInfo existingAdmin = adminInfoMapper.selectByUsername("admin"); // 直接使用 "admin" 作为固定用户名
            if (existingAdmin == null || !oldPassword.equals(existingAdmin.getPassword())) {
                return Result.error("旧密码不正确");
            }

            // 检查新密码和确认密码是否匹配
            if (!adminDto.getNewPassword().equals(adminDto.getConfirmPassword())) {
                return Result.error("新密码和确认密码不一致");
            }

            // 更新用户信息
            String newPassword = DigestUtils.md5DigestAsHex(adminDto.getNewPassword().getBytes());

            boolean update = lambdaUpdate()
                    .eq(AdminInfo::getUsername, "admin") // 使用固定的管理员账号
                    .set(AdminInfo::getPassword, newPassword)
                    .update();

            if (update) {
                return Result.success("管理员信息更新成功");
            } else {
                return Result.error("管理员信息更新失败：未找到指定管理员");
            }
        } catch (Exception e) {
            log.error("Error updating admin info", e);
            return Result.error("管理员信息更新失败：" + e.getMessage());
        }
    }
}
