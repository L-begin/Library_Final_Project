package cn.lanqiao.library_final_project.service.impl;

import cn.lanqiao.library_final_project.module.dto.BooksInfoDto;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.mapper.BooksInfoMapper;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.service.IBooksInfoService;
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
public class BooksInfoServiceImpl extends ServiceImpl<BooksInfoMapper, BooksInfo> implements IBooksInfoService {
    @Autowired
    private BooksInfoMapper booksInfoMapper;


    /***
     * 分页查询
     * @param booksInfoDto
     * @return
     */
    public PageResult pagequery(BooksInfoDto booksInfoDto) {
        PageHelper.startPage(booksInfoDto.getPage(), booksInfoDto.getPageSize());
        Page<BooksInfo> page = booksInfoMapper.pagequery(booksInfoDto);
        //获取总记录数 和当前页数据
        long total = page.getTotal();
        List<BooksInfo> result = page.getResult();
        return new  PageResult (total,result);
    }

}
