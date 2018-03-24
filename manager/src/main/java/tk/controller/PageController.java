package tk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    //    @RequestMapping("/index")
//    public String index(HttpServletRequest request, HttpServletResponse response) {
//
//        return "index";
//    }
    @RequestMapping("/index")
    public String code(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

}
