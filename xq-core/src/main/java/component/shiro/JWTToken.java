package component.shiro;/**
 * @author Created by lenovo
 * @date 2022/5/31 15:04
 */

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <h3>xq-mode</h3>
 * <p>JWTToken 差不多就是 Shiro 用户名密码的载体，AuthenticationToken 是前后端分离，就弄了一个字段 等后期维护</p>
 *
 * @author : xq
 * @date : 2022-05-31 15:04
 **/
@Component
public class JWTToken implements AuthenticationToken {
	// 密钥
	private String token;

	public JWTToken(){

	}

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
