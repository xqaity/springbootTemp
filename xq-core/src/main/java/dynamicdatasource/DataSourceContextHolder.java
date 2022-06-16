package dynamicdatasource;

/**
 * 数据源切换如何保证线程隔离？
 * 数据源属于一个公共的资源，在多线程的情况下如何保证线程隔离呢？不能我这边切换了影响其他线程的执行。
 *
 * 说到线程隔离，自然会想到ThreadLocal了，将切换数据源的KEY（用于从targetDataSources中取值）存储在ThreadLocal中，执行结束之后清除即可。
 *
 * 单独封装了一个DataSourceHolder，内部使用ThreadLocal隔离线程，代码如下：
 *
 * @author Created by lenovo
 * @date 2022/5/25 22:06
 */
/**
 * 利用ThreadLocal封装的保存数据源上线的上下文context
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> context = new ThreadLocal<>();

	/**
	 * 赋值
	 *
	 * @param datasourceType
	 */
	public static void set(String datasourceType) {
		context.set(datasourceType);
	}

	/**
	 * 获取值
	 * @return
	 */
	public static String get() {
		return context.get();
	}

	public static void clear() {
		context.remove();
	}
}
