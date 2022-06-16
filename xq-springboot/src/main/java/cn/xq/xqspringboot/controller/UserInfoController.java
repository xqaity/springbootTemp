package cn.xq.xqspringboot.controller;

import annotation.RepeatSubmit;
import cn.xq.xqspringboot.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by lenovo
 * @date 2022/5/23 21:19
 */
@Slf4j
@RestController
@RepeatSubmit
public class UserInfoController {

	@Autowired
	private UserInfo userInfo;

	@GetMapping("/hello")
	public UserInfo getUserInfo(){
		log.info("获取用户数据测试");
		return userInfo;
	}
}
