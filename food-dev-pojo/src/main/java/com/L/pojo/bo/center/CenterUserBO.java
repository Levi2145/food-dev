package com.L.pojo.bo.center;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class CenterUserBO {

    @ApiModelProperty(value="用户名", name="username", example="json", required = true)
    private String username;
    @ApiModelProperty(value="密码", name="password", example="123456", required = true)
    private String password;
    @ApiModelProperty(value="确认密码", name="confirmPassword", example="123456", required = true)
    private String confirmPassword;

    @NotBlank(message = "用户昵称不能为空")
    @Size(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value="用户昵称", name="nickname", example="杰森", required = true)
    private String nickname;

    @Size(max = 12, message = "用户真实姓名不能超过12位")
    @ApiModelProperty(value="真实姓名", name="realname", example="杰森", required = true)
    private String realname;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    @ApiModelProperty(value="手机号", name="mobile", example="13999999999", required = true)
    private String mobile;

    @Email
    @ApiModelProperty(value="邮箱地址", name="email", example="imooc@imooc.com", required = true)
    private String email;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 2, message = "性别选择不正确")
    @ApiModelProperty(value="性别", name="sex", example="0:女 1:男 2:保密", required = true)
    private Integer sex;

    @ApiModelProperty(value="生日", name="birthday", example="1900-01-01", required = true)
    private Date birthday;
}
