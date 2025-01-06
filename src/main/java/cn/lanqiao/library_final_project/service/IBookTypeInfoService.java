package cn.lanqiao.library_final_project.service;

import cn.lanqiao.library_final_project.module.dto.typeDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
public interface IBookTypeInfoService extends IService<BookTypeInfo> {
    /***
     *  获取书籍分类列表
     * @return
     */
    List<BookTypeInfo> classification();


    /***
     * 分页查询
     * @param typeDto
     * @return
     */

    PageResult pagequery(typeDto typeDto);

}
