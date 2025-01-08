package cn.lanqiao.library_final_project.controller;


import cn.lanqiao.library_final_project.module.dto.BooksInfoDto;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.module.pojo.BookTypeInfo;
import cn.lanqiao.library_final_project.module.pojo.BooksInfo;
import cn.lanqiao.library_final_project.result.PageResult;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.IBookTypeInfoService;
import cn.lanqiao.library_final_project.service.IBooksInfoService;
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
//@RequestMapping("/book_info")
@Slf4j
public class BooksInfoController {

    @Autowired
    private IBooksInfoService iBooksInfoService;

    @Autowired
    private IBookTypeInfoService iBookTypeInfoService;


    /***
     * 分页查询
     * @return
     */
    @GetMapping("/book_info/page")
    public Result<PageResult> page(BooksInfoDto booksInfoDto) {
        log.info("分页查询参数{}", booksInfoDto);
        PageResult pageResult = iBooksInfoService.pagequery(booksInfoDto);
        return Result.success(pageResult);
    }


    /***
     * 获取书籍分类列表
     * @return
     */
    @GetMapping("/book/book_categories")
    public Result classification(){
        log.info("获取书籍分类列表");
        List<BookTypeInfo> bookTypeInfo = iBookTypeInfoService.classification();
        return Result.success(bookTypeInfo);
    }

    /***
     * 添加书籍
     * @param booksInfo
     * @return
     */
    @PostMapping("/book_info")
    public Result<String> save(@RequestBody BooksInfo booksInfo){
        log.info("新增图书参数{}", booksInfo);
        iBooksInfoService.save(booksInfo);
        return Result.success("添加成功");
    }


    /***
     * 批量删除
     */

    @DeleteMapping("/book_info")
    public Result delete(@RequestParam List<Long> bids){
        log.info("根据id批量删除数据{}",bids);
        boolean result = iBooksInfoService.removeByIds(bids);
        if (result){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /***
     * 修改书籍信息
     * @param booksInfo
     * @return
     */
    @PutMapping("/book_info")
    public  Result update(@RequestBody BooksInfo booksInfo){

        log.info("修改书籍信息{}",booksInfo);
        //根据id来修改数据
        boolean result = iBooksInfoService.updateById(booksInfo);
        if (result){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }


}