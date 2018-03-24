package tk.utils;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final List<Charset> availableCharsets;

    private boolean writeAcceptCharset = true;

    public UTF8StringHttpMessageConverter() {
        super(new MediaType("text","plain",DEFAULT_CHARSET),MediaType.ALL);
        this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }

    @Override
    protected boolean supports(Class clazz) {
        return String.class.equals(clazz);
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(),charset));
    }

    @Override
    protected Long getContentLength(Object o, MediaType contentType) throws IOException {
        Charset charset = getContentTypeCharset(contentType);
        String s = (String)o;
        try{
            return (long)s.getBytes(charset.name()).length;
        }catch (UnsupportedEncodingException e){
            throw new InternalError(e.getMessage());
        }
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if(writeAcceptCharset){
            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
        FileCopyUtils.copy((String)o, new OutputStreamWriter(outputMessage.getBody(),charset));
    }

    protected  List<Charset> getAcceptedCharsets(){
        return this.availableCharsets;
    }

    private Charset getContentTypeCharset(MediaType contentType){
        if(contentType!=null&&contentType.getCharSet()!=null){
            return contentType.getCharSet();
        }else{
            return DEFAULT_CHARSET;
        }
    }
}
