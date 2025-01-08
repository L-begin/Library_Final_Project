package cn.lanqiao.library_final_project.module.Vo;

import cn.lanqiao.library_final_project.module.pojo.BookHisInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVO {
//    用户量
    private int userCount;
//    书籍类型
    private int bookTypeCount;
//书籍量
    private int bookCount;
//    书籍借阅量
    private int bookBorrowCount;
//    书籍借阅排名
    private List<BookHisInfo> bookBorrowRank;
}
