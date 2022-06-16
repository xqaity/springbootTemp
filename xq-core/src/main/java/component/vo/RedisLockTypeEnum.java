package component.vo;/**
 * @author Created by lenovo
 * @date 2022/5/27 15:36
 */

/**
 * <h3>xq-mode</h3>
 * <p>锁业务属性枚举设定</p>
 *
 * @author : xq
 * @date : 2022-05-27 15:36
 **/
public enum RedisLockTypeEnum {
	/**
	 * 自定义 key 前缀
	 */
	ONE("Business1", "Test1"),
	TWO("Business2", "Test2");

	private String code;
	private String desc;
	RedisLockTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	public String getUniqueKey(String key) {
		return String.format("%s:%s", this.getCode(), key);
	}
}
