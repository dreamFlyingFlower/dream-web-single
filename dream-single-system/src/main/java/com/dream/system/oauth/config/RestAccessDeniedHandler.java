package com.dream.system.oauth.config;
//package com.wy.gateway.config;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import com.wy.enums.TipEnum;
//import com.wy.gateway.util.WebUtil;
//import com.wy.result.Result;
//
//public class RestAccessDeniedHandler implements AccessDeniedHandler {
//
//	@Override
//	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
//	        throws IOException, ServletException {
//		WebUtil.write(response, Result.error(TipEnum.TIP_AUTH_DENIED));
//	}
//}