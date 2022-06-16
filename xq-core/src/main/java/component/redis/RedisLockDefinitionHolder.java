package component.redis;/**
 * @author Created by lenovo
 * @date 2022/5/27 15:39
 */

import lombok.Data;

/**
 * <h3>xq-mode</h3>
 * <p>任务队列保存参数</p>
 *
 * @author : xq
 * @date : 2022-05-27 15:39
 **/
@Data
public class RedisLockDefinitionHolder {
	/**
	 * 业务唯一 key
	 */
	private String businessKey;
	/**
	 * 加锁时间 (秒 s)
	 */
	private Long lockTime;
	/**
	 * 上次更新时间（ms）
	 */
	private Long lastModifyTime;
	/**
	 * 保存当前线程
	 */
	private Thread currentTread;
	/**
	 * 总共尝试次数
	 */
	private int tryCount;
	/**
	 * 当前尝试次数
	 */
	private int currentCount;
	/**
	 * 更新的时间周期（毫秒）,公式 = 加锁时间（转成毫秒）/ 3
	 */
	private Long modifyPeriod;
	public RedisLockDefinitionHolder(String businessKey, Long lockTime, Long lastModifyTime, Thread currentTread, int tryCount) {
		this.businessKey = businessKey;
		this.lockTime = lockTime;
		this.lastModifyTime = lastModifyTime;
		this.currentTread = currentTread;
		this.tryCount = tryCount;
		this.modifyPeriod = lockTime * 1000 / 3;
	}
}
