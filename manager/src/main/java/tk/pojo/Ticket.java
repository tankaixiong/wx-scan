package tk.pojo;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2018/3/17
 * @Version: 1.0
 * @Description:
 */
public class Ticket {
    private String ticket;
    private int expire_seconds;
    private String url;
    private String encodeTicket;//urlencode之后的值

    public static void main(String[] args) {
        System.out.println("qrscene_2".substring(8));
    }
    public String getEncodeTicket() {
        return encodeTicket;
    }

    public void setEncodeTicket(String encodeTicket) {
        this.encodeTicket = encodeTicket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
