package component.redis;/**
 * @author Created by lenovo
 * @date 2022/5/27 15:45
 */

import annotation.RedisLockAnnotation;
import component.vo.RedisLockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * <h3>xq-mode</h3>
 * <p>核心切面拦截的操作</p>
 *
 * @author : xq
 * @date : 2022-05-27 15:45
 **/
@Component
//@Order(1) // 设置优先级最高
@Slf4j
@Aspect
public class RedisLockAspect {

	@Autowired(required=true)
	private RedisTemplate<Object, Object> redisTemplate;

	// 扫描的任务队列
	private static ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList = new ConcurrentLinkedQueue();
	/**
	 * @annotation 中的路径表示拦截特定注解
	 */
	@Pointcut("@annotation(annotation.RedisLockAnnotation)")
	public void redisLockPC() {
	}

	@Around(value = "redisLockPC()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 解析参数
		MethodSignature signature = (MethodSignature)pjp.getSignature();
		Method method = signature.getMethod();
		RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
		RedisLockTypeEnum typeEnum = annotation.typeEnum();
		Object[] params = pjp.getArgs();
		String ukString = params[annotation.lockFiled()].toString();
		// 省略很多参数校验和判空
		String businessKey = typeEnum.getUniqueKey(ukString);
		String uniqueValue = UUID.randomUUID().toString();
		boolean isSuccess = true;
		// 加锁
		Object result = null;
		try {
			isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue);
			log.info("==================================="+isSuccess+"=========================+"+businessKey);
			if (!isSuccess) {
				throw new Exception("You can't do it，because another has get the lock =-=");
			}
			redisTemplate.expire(businessKey, annotation.lockTime(), TimeUnit.SECONDS);
			Thread currentThread = Thread.currentThread();
			// 将本次 Task 信息加入「延时」队列中
			holderList.add(new RedisLockDefinitionHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(),
					currentThread, annotation.tryCount()));

			if (currentThread.isInterrupted()) {
				throw new InterruptedException("You had been interrupted =-=");
			}
			// 执行业务操作
			return pjp.proceed(pjp.getArgs());
			// 线程被中断，抛出异常，中断此次请求
		} catch (InterruptedException e ) {
			log.error("Interrupt exception, rollback transaction", e);
			throw new Exception("Interrupt exception, please send request again");
		} catch (Exception e) {
			log.error("has some error, please check again", e);
		} finally {
			// 请求结束后，强制删掉 key，释放锁
			if(isSuccess){
				redisTemplate.delete(businessKey);
			}
			log.info("release the lock, businessKey is [" + businessKey + "]");
		}
		return result;
	}
	/**
	 * 线程池，维护keyAliveTime
	 */
	public static ThreadFactory springThreadFactory = new CustomizableThreadFactory("redisLock-schedule-pool");
	private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(10,
			springThreadFactory);
	{
		// 两秒执行一次「续时」操作
		SCHEDULER.scheduleAtFixedRate(() -> {
			// 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
			Iterator<RedisLockDefinitionHolder> iterator = holderList.iterator();
			while (iterator.hasNext()) {
				RedisLockDefinitionHolder holder = iterator.next();
				// 判空
				if (holder == null) {
					iterator.remove();
					continue;
				}
				// 判断 key 是否还有效，无效的话进行移除
				if (redisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
					iterator.remove();
					continue;
				}
				// 超时重试次数，超过时给线程设定中断
				if (holder.getCurrentCount() > holder.getTryCount()) {
					holder.getCurrentTread().interrupt();
					iterator.remove();
					continue;
				}
				// 判断是否进入最后三分之一时间
				long curTime = System.currentTimeMillis();
				boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
				if (shouldExtend) {
					holder.setLastModifyTime(curTime);
					redisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
					log.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentCount());
					holder.setCurrentCount(holder.getCurrentCount() + 1);
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
