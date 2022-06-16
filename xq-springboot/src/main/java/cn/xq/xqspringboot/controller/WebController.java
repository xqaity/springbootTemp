package cn.xq.xqspringboot.controller;/**
 * @author Created by lenovo
 * @date 2022/5/31 13:50
 */

import cn.xq.xqspringboot.user.entity.SysUser;
import cn.xq.xqspringboot.user.service.ISysUserService;
import component.vo.Result;
import globalexception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utils.JWTUtil;
import utils.ResultUtil;

/**
 * <h3>xq-mode</h3>
 * <p>生成验证码Controller</p>
 *
 * @author : xq
 * @date : 2022-05-31 13:50
 **/
@RestController("/xq/web")
public class WebController {

	private static final Logger LOGGER = LogManager.getLogger(WebController.class);
	@Autowired
	private ISysUserService userService;


	//登入
	@PostMapping("/login")
	public Result login(@RequestParam("username") String username,
	                    @RequestParam("password") String password) {
		SysUser userBean = userService.getById("123");
		if (userBean.getPassWord().equals(password)) {
			return new ResultUtil<String>().setData( JWTUtil.sign(username, password),"Login success");
		} else {
			throw new ServiceException();
		}
	}
	//所有人都可以访问，但是用户与游客看到的内容不同
	@GetMapping("/article")
	public Result article() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new ResultUtil<String>().setData("You are already logged in");
		} else {
			return new ResultUtil<String>().setData("You are guest");
		}
	}
	//登入的用户才可以进行访问
	@GetMapping("/require_auth")
	@RequiresAuthentication
	public Result requireAuth() {
		return new ResultUtil<String>().setData("You are authenticated");
	}

	//	admin的角色用户才可以登入
	@GetMapping("/require_role")
	@RequiresRoles("admin")
	public Result requireRole() {
		return new ResultUtil<String>().setData("You are visiting require_role");
	}
	//拥有view和edit权限的用户才可以访问
	@GetMapping("/require_permission")
	@RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
	public Result requirePermission() {
		return new ResultUtil<String>().setData( "You are visiting permission require edit,view");
	}

	@RequestMapping(path = "/401")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Result unauthorized() {
		return new ResultUtil<String>().setData( "Unauthorized");
	}
}

