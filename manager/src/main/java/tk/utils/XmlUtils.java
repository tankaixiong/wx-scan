package tk.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by tank on 2018/3/16.
 */
public class XmlUtils {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(XmlUtils.class);


    public static void main(String[] args) throws DocumentException {
        String text = "<xml><URL><![CDATA[http://fukongabc.w3.luyouxia.net/wx]]></URL><ToUserName><![CDATA[tankaixiong]]></ToUserName><FromUserName><![CDATA[wx1109e65f732a6df2]]></FromUserName><CreateTime>1989420963</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><Latitude></Latitude><Longitude></Longitude><Precision></Precision><MsgId>188</MsgId></xml>";
        Document document = DocumentHelper.parseText(text);

        Element rootElement = document.getRootElement();

        System.out.println(rootElement.element("FromUserName").getText());
        System.out.println(rootElement.element("URL").getText());
    }

    public static List<Element> readXmlText(String text) {

        List<Element> elementList = null;
        try {

            Document document = DocumentHelper.parseText(text);

            Element root = document.getRootElement();
            elementList = root.elements();

            logger.debug("XMLUtil.readXml root name:" + root.getName());
        } catch (Exception e) {
            logger.error("XMLUtil.readXml error: " + e);
            return null;
        } finally {
        }
        return elementList;
    }

    public static List<Element> readXml(String filePath) {

        InputStream in = null;
        List<Element> elementList = null;
        try {
            SAXReader reader = new SAXReader();
            //in = XMLUtil.class.getClassLoader().getResourceAsStream(filePath);
            in = new FileInputStream(new File(filePath));
            Document doc = reader.read(in);

            Element root = doc.getRootElement();
            elementList = root.elements();
            logger.debug("XMLUtil.readXml root name:" + root.getName());
        } catch (Exception e) {
            logger.error("XMLUtil.readXml error: " + e);
            return null;
        } finally {
            close(null, null, in);
        }
        return elementList;
    }

    public static void close(XMLWriter xmlWriter, OutputStream outputStream, InputStream inputStream) {

        if (xmlWriter != null) {
            try {
                xmlWriter.close();
            } catch (IOException e) {
                logger.error("XMLUtil.close error: " + e);
            }
            xmlWriter = null;
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error("XMLUtil.close error: " + e);
            }
            outputStream = null;
        }

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("XMLUtil.close error: " + e);
            }
            inputStream = null;
        }
    }
}
