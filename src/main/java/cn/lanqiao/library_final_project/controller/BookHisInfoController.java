package cn.lanqiao.library_final_project.controller;



import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IBookHisInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@RestController
@RequestMapping("/book_his")
@Slf4j
public class BookHisInfoController {

    @Autowired
    private IBookHisInfoService iBookHisInfoService;

    /***
     * 分页
     * @param hisDto
     * @return
     */
    @GetMapping("/his_admin")
    public Result<PageResult> page(HisDto hisDto) {
        log.info("分页查询参数{}", hisDto);
        PageResult pageResult = iBookHisInfoService.pagequery(hisDto);
        return Result.success(pageResult);
    }

    /***
     * 删除
     */
    @DeleteMapping("/{hid}")
    public Result delete( Integer hid) {
        log.info("根据id{}删除", hid);
        //先查询是否在借阅中 在借阅中 不能删除
        BookHisInfo bookHisInfo = iBookHisInfoService.lambdaQuery().eq(BookHisInfo::getHid, hid).eq(BookHisInfo::getStatus, 1).one();
        if (bookHisInfo != null){
            return Result.error("该图书正在借阅中，不能删除");
        }
        boolean result = iBookHisInfoService.removeById(hid);
        if (result){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }


}
