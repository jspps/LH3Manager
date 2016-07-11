package com.admin.logic.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.admin.content.AppContext;
import com.admin.content.Svc;
import com.admin.logic.Utls;

/*** 拦截器 - 后台管理 - 所有请求 **/
@Repository
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			AppContext.loadAll();
		} catch (Exception e) {
		}

		String[] noFilters = new String[] { "doAdmin/login", "doAdmin/doLogin",
				"doAdmin/checkCode" };
		String uri = request.getRequestURI();
		int indexAdmin = uri.indexOf("/doAdmin");
		int indexCenter = uri.indexOf("/doCenter");
		int indexAgent = uri.indexOf("/doAgent");
		boolean isFilter = indexAdmin != -1 || indexCenter != -1
				|| indexAgent != -1;
		if (isFilter) {
			boolean beFilter = true;
			for (String s : noFilters) {
				if (uri.indexOf(s) != -1) {
					beFilter = false;
					break;
				}
			}

			if (beFilter) {
				HttpSession session = request.getSession();
				Object obj = null;
				if (indexAdmin > 0) {
					obj = session.getAttribute(Utls.KeyEnAdmin);
				}

				if (indexAgent > 0) {
					obj = session.getAttribute(Utls.KeyEnAgent);
				}

				if (indexCenter > 0) {
					obj = session.getAttribute(Utls.KeyEnCenter);
				}

				if (obj == null) {
					// PrintWriter out = response.getWriter();
					// StringBuilder builder = new StringBuilder();
					// builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
					// builder.append("alert(\"页面过期，请重新登录\");");
					// builder.append("window.top.location.href=\"");
					// builder.append("login");
					// builder.append("\";</script>");
					// out.print(builder.toString());
					// out.close();

					String path = request.getContextPath();
					if (indexAgent > 0) {
						response.sendRedirect(path + "/loginAgent");
						return false;
					}

					if (indexCenter > 0) {
						response.sendRedirect(path + "/loginLhub");
						return false;
					}

					response.sendRedirect(path + "/");
					return false;
				}
			}
		}
		
		debugLog(false, request);
		return super.preHandle(request, response, handler);
	}
	
	private void debugLog(boolean isDebug, HttpServletRequest request) {
		// 操作日志(可以添加日志记录)
		if (isDebug) {
			String method = request.getPathInfo();
			Map<String, String> parsMap = Svc.getMapAllParams(request);
			System.out.println(method + "=" + parsMap.toString());
		}
	}

}
