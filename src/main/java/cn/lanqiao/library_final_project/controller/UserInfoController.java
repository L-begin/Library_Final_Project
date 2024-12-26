package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.module.dto.userDto;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.module.pojo.UserInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;
    /***
     * 分页查询
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(userDto userDto) {
        log.info("分页查询参数{}", userDto);
        PageResult pageResult = iUserInfoService.pagequery(userDto);
        return Result.success(pageResult);
    }

    /***
     * 批量删除
     * @param aids
     * @return
     */
    @DeleteMapping
    public Result delete (@RequestParam List<Long> aids){
        log.info("根据id批量删除数据{}",aids);
        boolean result = iUserInfoService.removeByIds(aids);
        if (result){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /***
     * 修改读者信息
     * @param userInfo
     * @return
     */
    @PostMapping("/edit")
    public  Result update(@RequestBody UserInfo userInfo){

        log.info("修改用户信息{}",userInfo);
        //根据id来修改数据
        boolean result = iUserInfoService.updateById(userInfo);
        if (result){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

}
