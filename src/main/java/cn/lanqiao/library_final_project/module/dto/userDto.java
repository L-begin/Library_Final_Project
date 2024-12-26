package cn.lanqiao.library_final_project.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDto {

    private int page; // 当前页码
    private int pageSize; // 每页大小

    private String username; //账号

    private String name;  //姓名
}
