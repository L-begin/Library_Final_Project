package cn.lanqiao.library_final_project.module.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksInfoDto {
    private int page;
    private int pageSize;
    private String name;
    private String author;
    private String type;
    private String card;
}
