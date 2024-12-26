package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.typeDto;
import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import cn.lanqiao.library_final_project.mapper.BookTypeInfoMapper;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.service.IBookTypeInfoService;
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
public class BookTypeInfoServiceImpl extends ServiceImpl<BookTypeInfoMapper, BookTypeInfo> implements IBookTypeInfoService {
        @Autowired
        private BookTypeInfoMapper bookTypeInfoMapper;
    /***
     * 获取书籍分类列表
     * @return
     */
    public List<BookTypeInfo> classification() {
        List<BookTypeInfo> bookTypeInfo = bookTypeInfoMapper.classification();
        return bookTypeInfo;
    }

    /***
     * 分页查询
     * @param typeDto
     * @return
     */
    public PageResult pagequery(typeDto typeDto) {
        PageHelper.startPage(typeDto.getPage(), typeDto.getPageSize());
        Page<BookTypeInfo> page = bookTypeInfoMapper.pagequery(typeDto);
        //获取总记录数 和当前页数据
        long total = page.getTotal();
        List<BookTypeInfo> result = page.getResult();
        return new  PageResult (total,result);
    }
}
