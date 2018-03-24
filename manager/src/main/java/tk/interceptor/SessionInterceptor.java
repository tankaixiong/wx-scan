package tk.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tk.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        HttpSession session = request.getSession();

        //忽略请求
        List<String> ignoreUrls = new ArrayList<>(Arrays.asList(
                "/",
                "/wx",
                "/index",
                "/user/login",
                //"/user/register",
                "/user/logout"
        ));

        if(ignoreUrls.contains(url.substring(request.getContextPath().length()))){
            return true;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.info("Not Login!");
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // String url= request.getRequestURI();

    }
}
