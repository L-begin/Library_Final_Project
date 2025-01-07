package cn.lanqiao.library_final_project.mapper;

import cn.lanqiao.library_final_project.module.dto.UserDto;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /***
     * 分页查询
     * @param userDto
     * @return
     */

    Page<UserInfo> pagequery(UserDto userDto);

    UserInfo selectByUsername(String username);
    int updateUser(@Param("userDto") UserDto userDto);
}
