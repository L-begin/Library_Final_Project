package cn.lanqiao.library_final_project.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HisDto {

    private int page; // 当前页码
    private int pageSize; // 每页大小

    private String bookName; //书名

    private String adminName; //借阅者账号

    private String username; //借阅者姓名

    private String card; //图书书号

    private String name;  //姓名  借阅者姓名
    private int aid;
}
