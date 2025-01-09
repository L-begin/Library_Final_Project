package cn.lanqiao.library_final_project.service;

import cn.lanqiao.library_final_project.module.dto.AdminDto;
import cn.lanqiao.library_final_project.module.pojo.AdminInfo;
import cn.lanqiao.library_final_project.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
public interface IAdminInfoService extends IService<AdminInfo> {
    Result<?> editAdminInfo(AdminDto adminDto);
}
