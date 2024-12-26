package cn.lanqiao.library_final_project.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class typeDto {

    private int page; // 当前页码
    private int pageSize; // 每页大小
    private String name;  //分类名称
}
