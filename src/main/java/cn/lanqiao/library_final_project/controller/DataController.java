package cn.lanqiao.library_final_project.controller;

import cn.lanqiao.library_final_project.module.Vo.DataVO;
import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import cn.lanqiao.library_final_project.result.Result;
import cn.lanqiao.library_final_project.service.impl.BookHisInfoServiceImpl;
import cn.lanqiao.library_final_project.service.impl.BookTypeInfoServiceImpl;
import cn.lanqiao.library_final_project.service.impl.BooksInfoServiceImpl;
import cn.lanqiao.library_final_project.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DataController {
    @Autowired
    private BookHisInfoServiceImpl bookHisInfoService; //
    @Autowired
    private BooksInfoServiceImpl booksInfoService;
    @Autowired
    private BookTypeInfoServiceImpl bookTypeInfoService;
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @GetMapping("/data")
    public Result<DataVO> data() {
        int userCount = (int) userInfoService.count(); // 用户量
        int bookTypeCount = (int) bookTypeInfoService.count(); // 书籍类型
        int count = (int) booksInfoService.count(); // 书籍量
        int bookBorrowCount = (int) bookHisInfoService.count(); // 书籍借阅量
        List<Map<String, Object>> bookHisInfos = bookHisInfoService.countByBookName(); // 书籍借阅排名
        DataVO dataVO = new DataVO(userCount, bookTypeCount,count, bookBorrowCount, bookHisInfos);
        return Result.success(dataVO);
    }
}
