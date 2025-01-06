package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.UserDto;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.mapper.UserInfoMapper;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public PageResult pagequery(UserDto userDto) {
        PageHelper.startPage(userDto.getPage(), userDto.getPageSize());
        Page<UserInfo> page = userInfoMapper.pagequery(userDto);
        //获取总记录数 和当前页数据
        long total = page.getTotal();
        List<UserInfo> result = page.getResult();
        return new  PageResult (total,result);
    }

    @Override
    @Transactional
    public Result<?> editUserInfo(UserDto userDto) {
        try {
            // 验证旧密码
            String password = userDto.getOldPassword();
            userDto.setOldPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            UserInfo existingUser = userInfoMapper.selectByUsername(userDto.getUsername());
            if (existingUser == null || !userDto.getOldPassword().equals(existingUser.getPassword())) {
                return Result.error("旧密码不正确");
            }
            // 更新用户信息
            password =  userDto.getPassword();
            userDto.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
             boolean update = lambdaUpdate().eq(UserInfo::getUsername, userDto.getUsername()).set(UserInfo::getPassword, userDto.getPassword()).update();
            if (update) {
                return Result.success("用户信息更新成功");
            } else {
                return Result.error("用户信息更新失败：未找到指定用户");
            }
        } catch (Exception e) {
            log.error("Error updating user info", e);
            return Result.error("用户信息更新失败：" + e.getMessage());
        }
    }
}
