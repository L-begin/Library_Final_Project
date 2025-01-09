package cn.lanqiao.library_final_project.mapper;

import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Mapper
public interface  AdminInfoMapper extends BaseMapper<AdminInfo> {
    // 根据用户名选择管理员信息
    @Select("SELECT * FROM admin_info WHERE username = #{username}")
    AdminInfo selectByUsername(@Param("username") String username);
    // 更新管理员密码
    @Update("UPDATE admin_info SET password = #{password} WHERE username = #{username}")
    int updatePassword(@Param("username") String username, @Param("password") String password);
}
