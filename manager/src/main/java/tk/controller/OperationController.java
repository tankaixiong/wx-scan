package tk.controller;

import common.support.redis.RedisSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tk.common.Constants;
import tk.pojo.Tags;
import tk.pojo.Ticket;
import tk.service.IMsgEventService;

import javax.annotation.Resource;
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
public class OperationController {
    @Resource
    private IMsgEventService msgEventService;

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @RequestMapping("/mg/tag-add")
    public String addTag(HttpServletRequest request, String tagName) {


        msgEventService.addTag(tagName);

        return "redirect:/mg/tag-list";
    }

    @RequestMapping("/mg/tag-list")
    public ModelAndView tagList() {

        ModelAndView view = new ModelAndView("tag-list");

        Tags tags = msgEventService.getTags();

        view.addObject("tags", tags);

        return view;
    }

    @RequestMapping("/mg/qrcode")
    public ModelAndView qrcode(HttpServletRequest request, String tagId) {

        ModelAndView view = new ModelAndView("tag-code");

        Ticket ticket = msgEventService.createQrcode(tagId);


        view.addObject("ticket", ticket);

        return view;
    }

    @RequestMapping("/mg/msg-list")
    public ModelAndView msgList() {

        ModelAndView view = new ModelAndView("msg-list");

        String subText = RedisSupport.getInstance().get(Constants.MSG_TEXT_SUB);
        String respText = RedisSupport.getInstance().get(Constants.MSG_TEXT_RESP);

        view.addObject("sbuText", subText);
        view.addObject("respText", respText);

        return view;
    }

    @RequestMapping("/mg/msg-sub")
    public ModelAndView msgSub(String subtext) {

        ModelAndView view = new ModelAndView("redirect:/mg/msg-list");

        RedisSupport.getInstance().set(Constants.MSG_TEXT_SUB, subtext);
        //view.addObject("tags", tags);

        return view;
    }

    @RequestMapping("/mg/msg-resp")
    public ModelAndView msgResponse(String resptext) {

        ModelAndView view = new ModelAndView("redirect:/mg/msg-list");

        RedisSupport.getInstance().set(Constants.MSG_TEXT_RESP, resptext);

        //view.addObject("tags", tags);

        return view;
    }


    @RequestMapping("/mg/menu")
    public String code(HttpServletRequest request, HttpServletResponse response) {
        return "menu-bar";
    }

}
