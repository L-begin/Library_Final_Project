package cn.lanqiao.library_final_project.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int page; // 当前页码
    private int pageSize; // 每页大小

    private String username; //账号

    private String name;  //姓名
    private String code; //验证码信息
    private String password; //密码
    private int aid; // 用户ID，如果有

    private String email; // 邮箱
    private String phone; // 电话
    private String oldPassword; // 旧密码，用于修改密码时的验证
    private String confirmPassword; // 确认新密码
}
