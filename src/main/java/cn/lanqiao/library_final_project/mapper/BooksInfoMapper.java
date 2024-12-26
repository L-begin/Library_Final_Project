package cn.lanqiao.library_final_project.mapper;

import cn.lanqiao.library_final_project.module.dto.BooksInfoDto;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Mapper
public interface BooksInfoMapper extends BaseMapper<BooksInfo> {

    /***
     * 分页查询
     * @param booksInfoDto
     * @return
     */
    Page<BooksInfo> pagequery(BooksInfoDto booksInfoDto);

}
