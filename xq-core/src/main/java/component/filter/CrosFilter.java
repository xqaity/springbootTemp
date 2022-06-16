package component.filter;/**
 * @author Created by lenovo
 * @date 2022/5/27 10:57
 */

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>xq-mode</h3>
 * <p>跨域问题解决过滤</p>
 *
 * @author : xq
 * @date : 2022-05-27 10:57Content-Type, x-requested-with, X-Custom-Header, Authorization,token
 **/

@Component("CrosFilter")
public class CrosFilter implements Filter {

	@SneakyThrows
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers","Authorization,token,X-Custom-Header,Origin,User-Agent,X-Requested-With, Content-Type, Accept,Accept-Encoding");
		//继续执行下一个过滤器
		chain.doFilter(req, response);
	}
}
