//package component.filter;/**
// * @author Created by lenovo
// * @date 2022/6/6 12:13
// */
//
//import annotation.DataSourceSwitcher;
//import dynamicdatasource.DataSourceContextHolder;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.lang.reflect.Method;
//
///**
// * <h3>xq-mode</h3>
// * <p>数据库切换</p>
// *
// * @author : xq
// * @date : 2022-06-06 12:13
// **/
//public class DataSourceSwitchFilter implements Filter {
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		Filter.super.init(filterConfig);
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) servletRequest;
//		req.get
//		DataSourceSwitcher dataSourceSwitcher = method.getAnnotation(DataSourceSwitcher.class);
//		clear = dataSourceSwitcher.clear();
//		DataSourceContextHolder.set(dataSourceSwitcher.value().getDataSourceName());
//		log.info("数据源切换至：{}", dataSourceSwitcher.value().getDataSourceName());
//		filterChain.doFilter(servletRequest,servletResponse);
//	}
//
//	@Override
//	public void destroy() {
//		Filter.super.destroy();
//	}
//}
