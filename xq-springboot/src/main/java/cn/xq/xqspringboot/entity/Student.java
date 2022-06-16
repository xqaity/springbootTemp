package cn.xq.xqspringboot.entity;

import base.XbootBaseEntity;
import cn.xq.xqspringboot.vo.Custom;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 总结：嵌套校验只需要在需要校验的元素（单个或者集合）上添加@Valid注解，接口层需要使用@Valid或者@Validated注解标注入参。
 * @author xq
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_student")
@TableName("t_student")
@ApiModel(value = "测试生成学生")
public class Student extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    //所有的校验注解都有一个groups属性用来指定分组，Class<?>[]类型，没有实际意义，因此只需要定义一个或者多个接口用来区分即可。 比如他在修改和添加的时候都不能为空
    @NotBlank(message = "内容不能为空",groups = {AddDTO.class,UpdateDTO.class})
    private String name;

    //如果调用修改接口修改数据的时候加上注解@Validated(value = Student.UpdateDTO.class) 时就会生效
    @NotBlank(message = "学号不能为空",groups = {UpdateDTO.class})
    private String cardId;

    //如果你想嵌套校验,嵌套的实体类中的校验的属性和接口中@Validated注解指定的分组不同，则不会校验。
    //嵌套校验简单的解释就是一个实体中包含另外一个实体，并且这两个或者多个实体都需要校验。只需要在嵌套的实体属性标注@Valid注解，则其中的属性也将会得到校验
    @Transient
    @NotNull(message = "班主任不能为空")
    private Teacher teacher;

    public interface UpdateDTO{}

    public interface AddDTO{}
}