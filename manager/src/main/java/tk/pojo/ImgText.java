package tk.pojo;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2018/3/22
 * @Version: 1.0
 * @Description:
 */
public class ImgText {

    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;


    public String parseXml() {
        StringBuilder sb = new StringBuilder();

        sb.append("<item><Title><![CDATA[")
                .append(this.Title)
                .append("]]></Title><Description><![CDATA[")
                .append(this.Description)
                .append("]]></Description><PicUrl><![CDATA[")
                .append(this.PicUrl)
                .append("]]></PicUrl><Url><![CDATA[")
                .append(this.Url)
                .append("]]></Url></item>");

        return sb.toString();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
