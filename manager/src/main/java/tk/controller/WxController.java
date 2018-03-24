package tk.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.service.IMsgEventService;
import tk.utils.SignUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
@Controller
public class WxController {
    @Resource
    private IMsgEventService msgEventService;

    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    //    @RequestMapping("/index")
//    public String index(HttpServletRequest request, HttpServletResponse response) {
//
//        return "index";
//    }
    @RequestMapping(value = "/wx", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String weixin(HttpServletRequest request, String signature, String timestamp, String nonce, String echostr) {
        logger.info("收到信息{},{},{},{}", signature, timestamp, nonce, echostr);
        if (signature == null || timestamp == null || nonce == null) {
            return "success";
        }
        if (!SignUtils.checkSignature(signature, timestamp, nonce)) {
            logger.error("验证来源失败{},{},{},{}", signature, timestamp, nonce, echostr);
            return echostr;
        }
        //5fb23edb24c26ee9edd1a438fd87ec3baa2a9ca9,1521211394,1989420963,2481110428222742122
        if (echostr != null) {
            return echostr;
        }

        try {
            String xmlText = IOUtils.toString(request.getInputStream(), "utf-8");

            logger.info("收到事件xml:{}", xmlText);
            //处理逻辑
            String result = msgEventService.eventListener(xmlText);
            logger.info("回复:{}", result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("{}", e);
        }

        return "success";
    }

}
