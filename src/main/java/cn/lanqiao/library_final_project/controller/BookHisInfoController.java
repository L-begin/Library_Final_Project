package cn.lanqiao.library_final_project.controller;

import cn.lanqiao.library_final_project.module.dto.HisDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IBooksInfoService;
import cn.lanqiao.library_final_project.service.IBookHisInfoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器，整合了图书借阅历史相关的多个接口方法
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

    @GetMapping("/his_admin")
    public Result<PageResult> page(HisDto hisDto) {
        log.info("分页查询参数{}", hisDto);
        PageResult pageResult = iBookHisInfoService.pagequery(hisDto);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{hid}")
    public Result delete(@PathVariable Integer hid) {  // 添加 @PathVariable 注解
        log.info("根据id{}删除", hid);

        // 先查询是否在借阅中 在借阅中 不能删除
        BookHisInfo bookHisInfo = iBookHisInfoService.lambdaQuery()
                .eq(BookHisInfo::getHid, hid)
                .one();  // 先获取记录

        if (bookHisInfo == null) {
            return Result.error("记录不存在");
        }

        if (bookHisInfo.getStatus() == 1) {
            return Result.error("该书籍正在借阅中，不能删除");
        }

        boolean result = iBookHisInfoService.removeById(hid);
        if (result) {
            return Result.success("删除成功");
        } else {
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
        if (existingRecord!= null) {
            return Result.error("该图书正在借阅中，不能再次借阅");
        }
        // 设置借阅状态为借阅中（假设 1 表示借阅中）
        bookHisInfo.setStatus(1);
        booksInfoService.lambdaUpdate().eq(BooksInfo::getBid, bookHisInfo.getBid()).set(BooksInfo::getStatus, 1).update();
        // 保存借阅信息
        boolean save = iBookHisInfoService.save(bookHisInfo);
        if (save) {
            return Result.success("借阅成功");
        } else {
            return Result.error("借阅失败");
        }
    }

    @GetMapping("/page")
    public Result<PageResult> pageQueryForUser(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String card) {

        HisDto hisDto = new HisDto();
        hisDto.setPage(page);
        hisDto.setPageSize(pageSize);
        hisDto.setName(name);
        hisDto.setCard(card);

        PageResult pageResult = iBookHisInfoService.pagequery(hisDto);
        return Result.success(pageResult);
    }

    @GetMapping
    @Transactional
    public Result returnBook(@RequestParam Integer hid) {
        log.info("处理还书请求: hid={}", hid);

        BookHisInfo bookHisInfo = iBookHisInfoService.getById(hid);
        if (bookHisInfo == null) {
            return Result.error("借阅记录不存在");
        }

        if (bookHisInfo.getStatus() != 1) {
            return Result.error("该图书已经归还");
        }

        // 更新借阅记录状态
        bookHisInfo.setStatus(2);
        bookHisInfo.setEndTime(LocalDate.now());
        boolean updateResult = iBookHisInfoService.updateById(bookHisInfo);

        if (updateResult) {
            // 更新图书状态为未借出
            booksInfoService.lambdaUpdate()
                    .eq(BooksInfo::getBid, bookHisInfo.getBid())
                    .set(BooksInfo::getStatus, 2)
                    .update();

            return Result.success("还书成功");
        }

        return Result.error("还书失败");
    }

}