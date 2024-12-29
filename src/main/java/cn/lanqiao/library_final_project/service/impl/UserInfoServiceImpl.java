package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.UserDto;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.mapper.UserInfoMapper;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
