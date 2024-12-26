package cn.lanqiao.library_final_project.mapper;

import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Mapper
public interface BookTypeInfoMapper extends BaseMapper<BookTypeInfo> {
    /***
     *  获取书籍分类列表
     * @return
     */

    @Select("select * from book_type_info")
    List<BookTypeInfo> classification();
}
