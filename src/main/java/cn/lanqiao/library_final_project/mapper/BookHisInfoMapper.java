package cn.lanqiao.library_final_project.mapper;

import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Mapper
public interface BookHisInfoMapper extends BaseMapper<BookHisInfo> {

    /***
     * 分页查询
     * @param hisDto
     * @return
     */

    Page<BookHisInfo> pagequery(HisDto hisDto);
    // 根据条件查询图书借阅历史记录分页数据



}
