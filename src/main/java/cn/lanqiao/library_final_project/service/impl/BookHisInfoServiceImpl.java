package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.mapper.BookHisInfoMapper;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.service.IBookHisInfoService;
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
public class BookHisInfoServiceImpl extends ServiceImpl<BookHisInfoMapper, BookHisInfo> implements IBookHisInfoService {
    @Autowired
    private BookHisInfoMapper bookHisInfoMapper;
    @Override
    public PageResult pagequery(HisDto hisDto) {
        PageHelper.startPage(hisDto.getPage(), hisDto.getPageSize());
        Page<BookHisInfo> page = bookHisInfoMapper.pagequery(hisDto);
        //获取总记录数 和当前页数据
        long total = page.getTotal();
        List<BookHisInfo> result = page.getResult();
        return new  PageResult (total,result);
    }

    @Override
    public BookHisInfo getHistoryById(Long id) {
        return bookHisInfoMapper.selectById(id);
    }
}
