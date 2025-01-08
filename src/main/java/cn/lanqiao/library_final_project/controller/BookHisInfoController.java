package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.service.IBooksInfoService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IBookHisInfoService;
import lombok.extern.slf4j.Slf4j;
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
    private IBooksInfoService booksInfoService;

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
    @PostMapping
    @Transactional
    public Result cyx(@RequestBody BookHisInfo bookHisInfo) {
        log.info("处理图书借阅请求: {}", bookHisInfo);

        // 检查书籍是否已经在借阅中
        BooksInfo existingRecord = booksInfoService.lambdaQuery()
                .eq(BooksInfo::getBid, bookHisInfo.getBid())
                .eq(BooksInfo::getStatus, 1) // 假设 1 表示借阅中
                .one();
        if (existingRecord != null) {
            return Result.error("该图书正在借阅中，不能再次借阅");
        }
        // 设置借阅状态为借阅中（假设 1 表示借阅中）
        bookHisInfo.setStatus(1);
        booksInfoService.lambdaUpdate().eq(BooksInfo::getBid, bookHisInfo.getBid()).set(BooksInfo::getStatus,1).update();
        // 保存借阅信息
        boolean save = iBookHisInfoService.save(bookHisInfo);
        if (save) {
            return Result.success("借阅成功");
        } else {
            return Result.error("借阅失败");
        }
    }

}
