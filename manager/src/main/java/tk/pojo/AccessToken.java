package tk.pojo;

/**
 * Created by tank on 2018/3/16.
 */
public class AccessToken extends ErrorCode {
    private String access_token;
    private int expires_in;
    private long expireTime;//过期时间点


    public AccessToken() {
    }


    public AccessToken(String access_token, int expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;

        expireTime = System.currentTimeMillis() + (expires_in * 1000);
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
