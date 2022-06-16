package security.handler;/**
 * @author Created by lenovo
 * @date 2022/6/10 15:55
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.struts.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;



/**
 * <h3>xq-mode</h3>
 * <p>一旦认证成功，则会调用AuthenticationSuccessHandler进行处理</p>
 *
 * @author : xq
 * @date : 2022-06-10 15:55
 **/
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//生成令牌
	 String accessToken	= JWTUtil.sign(userDetails.getUsername(),userDetails.getPassword());
	 //生成刷新令牌,如果accessToken失效,用
	 String refreshToken = UUID.randomUUID().toString();

	 redisTemplate.opsForValue().set("refresh:"+userDetails.getUsername(),refreshToken,JWTUtil.REFRESH_EXPIRE_TIME, TimeUnit.MILLISECONDS);

	}

	public void  renderToken(HttpServletResponse response) throws IOException{

	}
}
