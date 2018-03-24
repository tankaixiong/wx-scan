package tk.service.impl;

import common.support.redis.RedisSupport;
import common.support.utils.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.common.Application;
import tk.common.Constants;
import tk.pojo.ErrorCode;
import tk.pojo.ImgText;
import tk.pojo.Tags;
import tk.pojo.Ticket;
import tk.service.IMsgEventService;
import tk.utils.HttpClientUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tank on 2018/3/16.
 */
@Service
public class MsgEventServiceImpl implements IMsgEventService {

    private Logger logger = LoggerFactory.getLogger(MsgEventServiceImpl.class);

    public Tags getTags() {
        try {
            String token = Application.getInstance().getAccessToken().getAccess_token();

            String json = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + token);

            Tags tags = JsonUtil.toBean(json, Tags.class);

            logger.info("{}", json);

            return tags;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}", e);
        }

        return null;
    }

    @Override
    public String eventListener(String xmlText) {
        String result = "success";
        try {
            Document document = DocumentHelper.parseText(xmlText);

            Element rootElement = document.getRootElement();


            String msgType = rootElement.element("MsgType").getText();


            if ("event".equals(msgType)) {
                String event = rootElement.element("Event").getText();
                if (event.equals("subscribe") || event.equals("SCAN")) {
                    tagUser(rootElement);//打标签
                    result = getSubText(rootElement);//回复
                } else {
                    logger.error("事件类型找不到处理方法:{},{}", msgType, event);
                }
            } else if ("text".equals(msgType)) {

                String content = rootElement.element("Content").getText();
                if ("imgtxt".equals(content)) {
                    result = getImgTextXml(rootElement, null);
                } else {
                    //消息自动回复
                    result = textResponse(rootElement);
                }
            }


            //qrscene_123123;
            rootElement = null;

        } catch (DocumentException e) {
            e.printStackTrace();
            logger.error("{}", e);
        }

        return result;
    }

    public String textResponse(Element rootElement) {

        String openID = rootElement.element("FromUserName").getText();
        String toUserName = rootElement.element("ToUserName").getText();
        //String wxid = Application.getInstance().getConfig().getProperty("wxid");
        String respText = RedisSupport.getInstance().get(Constants.MSG_TEXT_RESP);

        String responseText = "<xml><ToUserName><![CDATA[" + openID + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[" + respText + "]]></Content>\n" +
                "</xml>";

        return responseText;
    }

    public String getSubText(Element rootElement) {

        String openID = rootElement.element("FromUserName").getText();
        String toUserName = rootElement.element("ToUserName").getText();
        String respText = RedisSupport.getInstance().get(Constants.MSG_TEXT_SUB);

        String responseText = "<xml><ToUserName><![CDATA[" + openID + "]]></ToUserName>\n" +
                "<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[" + respText + "]]></Content>\n" +
                "</xml>";

        return responseText;
    }

    public void tagUser(Element rootElement) {
        String openID = rootElement.element("FromUserName").getText();
        String eventKey = rootElement.element("EventKey").getText();//qrscene_2

        if (eventKey.startsWith("qrscene_")) {
            eventKey = eventKey.substring(8);
        }
        logger.info("获取的参数为:{}", eventKey);

        if (StringUtils.isEmpty(eventKey)) {
            return;
        }
//        http请求方式：POST（请使用https协议）
//        https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN

        String token = Application.getInstance().getAccessToken().getAccess_token();

        String data = "{\"openid_list\":[\"" + openID + "\"],\"tagid\":" + eventKey + "}";

        try {
            String json = HttpClientUtils.postParameters("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + token, data);

            ErrorCode errorCode = JsonUtil.toBean(json, ErrorCode.class);
            if (errorCode.getErrcode() != 0) {
                logger.error("打标签失败:{},{}", errorCode.getErrcode(), errorCode.getErrmsg());
            } else {
                logger.info("打标签成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}", e);
        }

    }

    @Override
    public void addTag(String tagName) {

        try {
            String data = "{\"tag\":{\"name\":\"" + tagName + "\"}}";

            String token = Application.getInstance().getAccessToken().getAccess_token();

            String json = HttpClientUtils.postParameters("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + token, data);


            logger.info("{}", json);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}", e);
        }


    }

    @Override
    public Ticket createQrcode(String tagId) {
//        http请求方式: POST
//        URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
//        POST数据格式：json
//        POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
//
//        或者也可以使用以下POST数据创建字符串形式的二维码参数：
//        {"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}

        try {
            String data = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + tagId + "\"}}}";

            String token = Application.getInstance().getAccessToken().getAccess_token();

            String json = HttpClientUtils.postParameters("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token, data);

            Ticket ticket = JsonUtil.toBean(json, Ticket.class);

            ticket.setEncodeTicket(URLEncoder.encode(ticket.getTicket(), "utf-8"));
            logger.info("{}", json);

            return ticket;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}", e);
        }


        return null;
    }


    public String getImgTextXml(Element rootElement, List<ImgText> imgTextList) {

        String openID = rootElement.element("FromUserName").getText();
        String appId = rootElement.element("ToUserName").getText();

        imgTextList = new ArrayList<>();

        ImgText imgText = new ImgText();
        imgText.setTitle("标题");
        imgText.setDescription("描述");
        imgText.setPicUrl("http://www.baidu.com/img/bd_logo1.png");
        imgText.setUrl("http://www.baidu.com");

        imgTextList.add(imgText);

        StringBuilder sb = new StringBuilder();
        int size = imgTextList.size();
        sb.append("<xml><ToUserName><![CDATA[")
                .append(openID)
                .append("]]></ToUserName><FromUserName><![CDATA[")
                .append(appId)
                .append("]]></FromUserName><CreateTime>")
                .append(System.currentTimeMillis() / 1000)
                .append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>")
                .append(size)
                .append("</ArticleCount><Articles>");

        for (ImgText item : imgTextList) {
            sb.append(item.parseXml());
        }
        sb.append("</Articles></xml>");

        return sb.toString();
    }
}
