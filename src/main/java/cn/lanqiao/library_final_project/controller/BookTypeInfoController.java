package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.module.dto.typeDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IBookTypeInfoService;
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
@RequestMapping("/book")
@Slf4j
public class BookTypeInfoController {

    @Autowired
    private IBookTypeInfoService iBookTypeInfoService;

    /***
     * 分页查询
     * @param typeDto
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(typeDto typeDto) {
        log.info("分页查询参数{}", typeDto);
        PageResult pageResult = iBookTypeInfoService.pagequery(typeDto);
        return Result.success(pageResult);
    }

    /***
     * 新增分类参数
     * @param bookTypeInfo
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody BookTypeInfo bookTypeInfo) {
        log.info("新增分类参数{}", bookTypeInfo);
        boolean result = iBookTypeInfoService.save(bookTypeInfo);
        if (result) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }

    }

    /***
     * 修改分类
     * @param bookTypeInfo
     * @return
     */
    @PutMapping
    public Result update(BookTypeInfo bookTypeInfo) {

        log.info("修改书籍分类信息{}", bookTypeInfo);
        //根据id来修改数据
        boolean result = iBookTypeInfoService.updateById(bookTypeInfo);
        if (result) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /***
     * 根据单个id删除单条数据
     * @param tid
     * @return
     */
    @DeleteMapping("/{tid}")
    public Result delete(@PathVariable Integer tid) {
        log.info("根据id删除单个数据{}", tid);
        boolean result = iBookTypeInfoService.removeById(tid);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /***
     * 批量删除分类数据
     * @param tids
     * @return
     */
    @DeleteMapping("/batchDelete")
    public Result delete(@RequestParam List<Long> tids) {
        log.info("根据id批量删除数据{}", tids);
        boolean result = iBookTypeInfoService.removeByIds(tids);
        if (result) {
            return Result.success("批量删除成功");
        } else {
            return Result.error("批量删除失败");
        }
    }



}
