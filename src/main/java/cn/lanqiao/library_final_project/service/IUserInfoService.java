package cn.lanqiao.library_final_project.service;

import cn.lanqiao.library_final_project.module.dto.UserDto;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
public interface IUserInfoService extends IService<UserInfo> {

    /***
     * 分页查询
     * @param userDto
     * @return
     */
    PageResult pagequery(UserDto userDto);
}
