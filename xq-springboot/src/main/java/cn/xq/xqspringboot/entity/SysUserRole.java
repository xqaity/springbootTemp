package cn.xq.xqspringboot.entity;

import base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "t_sys_user_role")
@TableName("t_sys_user_role")
@ApiModel(value = "系统用户角色")
public class SysUserRole extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

}