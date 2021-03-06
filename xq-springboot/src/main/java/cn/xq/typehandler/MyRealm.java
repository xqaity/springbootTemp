package cn.xq.typehandler;/**
 * @author Created by lenovo
 * @date 2022/5/31 15:08
 */

import cn.xq.xqspringboot.user.entity.SysUser;
import cn.xq.xqspringboot.user.service.ISysUserService;
import component.shiro.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JWTUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h3>xq-mode</h3>
 * <p>realm 的用于处理用户是否合法的这一块，需要我们自己实现。</p>
 *
 * @author : xq
 * @date : 2022-05-31 15:08
 **/
@Slf4j
@Service
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private ISysUserService userService;

	@Autowired

	/**
	 * 大坑！，必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JWTUtil.getUsername(principals.toString());
		SysUser user = userService.getUserByUserName(username);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole(user.getRole());
		Set<String> permission = new HashSet<String>(Arrays.asList(user.getPermission().split(",")));
		simpleAuthorizationInfo.addStringPermissions(permission);
		return simpleAuthorizationInfo;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token invalid");
		}

		SysUser userBean = userService.getUserByUserName(username);
		if (userBean == null) {
			throw new AuthenticationException("User didn't existed!");
		}
		if (! JWTUtil.verify(token, username, userBean.getPassWord())) {
			throw new AuthenticationException("Username or password error");
		}

		return new SimpleAuthenticationInfo(token, token, "my_realm");
	}
}
