package cn.lanqiao.library_final_project.service;

import cn.lanqiao.library_final_project.module.dto.BooksInfoDto;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
public interface IBooksInfoService extends IService<BooksInfo> {
     /***
      * 分页
      * @param booksInfoDto
      * @return
      */
     PageResult pagequery(BooksInfoDto booksInfoDto);
}
