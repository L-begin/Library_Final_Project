package cn.lanqiao.library_final_project.module.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zyh
 * @since 2024-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("books_info")
public class BooksInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    @TableField("name")
    private String name;

    @TableField("card")
    private String card;

    @TableField("author")
    private String author;

    @TableField("num")
    private Integer num;

    @TableField("press")
    private String press;

    @TableField("type")
    private String type;

    @TableField("status")
    private Integer status;


}
