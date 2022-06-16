package cn.xq.xqspringboot.user.entity;

import base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xq
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_sys_user")
@TableName("t_sys_user")
@ApiModel(value = "系统用户")
public class SysUser extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String passWord;

    private String role;

    private String permission;
}