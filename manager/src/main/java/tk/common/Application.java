package tk.common;

import common.support.utils.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.pojo.AccessToken;
import tk.utils.HttpClientUtils;

import java.util.Properties;

/**
 * Created by tank on 2018/3/16.
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static AccessToken accessToken;

    private Properties config;
    private static Application application = new Application();

    private Application() {
        loadConfig();
    }

    public static Application getInstance() {
        return application;
    }

    public String getToken() {
        return getConfig().getProperty("token");
    }

    public String getAppId() {
        return getConfig().getProperty("appid");
    }

    public String getAppSecret() {
        return getConfig().getProperty("appsecret");
    }

    //获取最新的AccessToken
    public AccessToken getAccessToken() {

        if (accessToken == null || accessToken.getExpireTime() <= System.currentTimeMillis()) {

            String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + getAppId() + "&secret=" + getAppSecret();
            try {
                String json = HttpClientUtils.get(path);

                AccessToken newToken = JsonUtil.toBean(json, AccessToken.class);
                newToken.setExpireTime(System.currentTimeMillis() + (newToken.getExpires_in() * 1000));
                if (newToken.getErrcode() == 0) {
                    accessToken = newToken;
                } else {
                    logger.error("{}", json);
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("{}", e);
            }
        }
        return accessToken;
    }

    public Properties getConfig() {
        return config;
    }

    private Properties loadConfig() {
        logger.info("正在加载应用程序配置 ...");

        try {
            config = new Properties();

            String configFileName = "server.properties";

            config.load(Application.class.getClassLoader().getResourceAsStream(configFileName));


        } catch (Exception e) {
            logger.error("{}", e);
            throw new RuntimeException("加载应用程序配置失败", e);
        }

        return config;
    }
}
