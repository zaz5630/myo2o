package cn.azzhu.myo2o.interceptor;

import cn.azzhu.myo2o.utils.SysConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author azzhu
 * @create 2019-09-08 19:14:19
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取session中的user
        Object loginUser = request.getSession().getAttribute(SysConstants.SESSION_USER);
        if(loginUser != null) {
            return true;
        }
        // 2. 没登录，跳转到登录页面
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open ('" + request.getContextPath()
                + "/shop/login.html','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
