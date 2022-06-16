package security.filter;/**
 * @author Created by lenovo
 * @date 2022/6/10 15:29
 */

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>xq-mode</h3>
 * <p>登录认证过滤器</p>
 *
 * @author : xq
 * @date : 2022-06-10 15:29
 **/
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationLoginFilter(){
		super(new AntPathRequestMatcher("/xq/login","POST"));
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
		return getAuthenticationManager().authenticate(authenticationToken);
	}
}
