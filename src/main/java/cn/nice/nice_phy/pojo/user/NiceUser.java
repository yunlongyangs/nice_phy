package cn.nice.nice_phy.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NiceUser implements Serializable{
    private String id;
    private String email;
    private String password;
    private Integer login_count;
    private Date register_date;
    private Date last_login_date;

}
