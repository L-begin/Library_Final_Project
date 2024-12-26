package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import cn.lanqiao.library_final_project.mapper.BookTypeInfoMapper;
import cn.lanqiao.library_final_project.service.IBookTypeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
