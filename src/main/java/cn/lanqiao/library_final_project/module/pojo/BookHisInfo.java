package cn.lanqiao.library_final_project.module.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@TableName("book_his_info")
@AllArgsConstructor
@NoArgsConstructor
public class BookHisInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "hid", type = IdType.AUTO)
    private Integer hid;

    @TableField("aid")
    private Integer aid;

    @TableField("bid")
    private String bid;

    @TableField("card")
    private String card;

    @TableField("book_name")
    private String bookName;

    @TableField("admin_name")
    private String adminName;

    @TableField("username")
    private String username;

    @TableField("begin_time")
    private LocalDate beginTime;

    @TableField("end_time")
    private LocalDate endTime;

    @TableField("status")
    private Integer status;

    private Integer count;

}
