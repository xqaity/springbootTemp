package cn.xq.base;/**
 * @author Created by lenovo
 * @date 2022/6/2 16:23
 */

/**
 * <h3>xq-mode</h3>
 * <p>性别枚举</p>
 *
 * @author : xq
 * @date : 2022-06-02 16:23
 **/
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements IEnum<Integer> {
	MAN(1, "男"),
	WOMAN(2, "女");
	private Integer code;
	private String name;

	SexEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public Integer getValue() {
		return code;
	}

	public String getName() {
		return name;
	}

}
