package cn.xq.xqspringboot.controller;

import annotation.RedisLockAnnotation;
import annotation.RepeatSubmit;
import cn.xq.xqspringboot.vo.Custom;
import component.vo.RedisLockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.Map;

/**
 * @author Created by lenovo
 * @date 2022/5/25 9:54
 */
@Slf4j
//@RepeatSubmit
@RestController
public class CustomController {
	@Autowired(required=false)
	@Qualifier(value = "linux")
	private Custom linux;

	@Autowired(required=false)
	@Qualifier(value = "windows")
	private Custom windows;

	@GetMapping("/testCustom")
	public Map<String,Object> res(){
		System.out.println(linux);
		System.out.println(windows);
		return null;
	}
	@GetMapping("/testRedisLock")
	@RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE)
	public Book testRedisLock(@RequestParam("userId") Long userId) {
		try {
			log.info("睡眠执行前");
			Thread.sleep(20000);
			log.info("睡眠执行后");
		} catch (Exception e) {
			// log error
			log.info("has some error", e);
		}
		return null;
	}
}
