package cn.lanqiao.library_final_project.service;

import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
public interface IBookHisInfoService extends IService<BookHisInfo> {

    /***
     * 分页查询
     * @param hisDto
     * @return
     */

    PageResult pagequery(HisDto hisDto);

}
