package cn.nice.nice_phy.codesenum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum NiceCode {
    REQUEST_OK(10000,"请求成功"),
    REQUEST_BAD(10001,"请求失败"),
    RESPONSE_OK(10002,"响应成功"),
    RESPONSE_BAD(10003,"响应失败"),
    ERROR(10004,"服务器维护中"),
    UNAME_ERROR(10005,"此邮箱不存在"),
    PASSWORD_ERROR(10006,"密码错误"),
    ;
    private Integer code;
    private String message;
}
