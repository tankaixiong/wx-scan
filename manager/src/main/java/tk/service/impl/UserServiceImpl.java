package tk.service.impl;

import common.support.redis.RedisSupport;
import common.support.utils.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.common.Constants;
import tk.pojo.User;
import tk.service.IUserService;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    RedisSupport redisSupport = RedisSupport.getInstance();

    @Override
    public void save(User user) {

        Long userId = redisSupport.incr(Constants.REDIS_USER_ID);//自增ID

        user.setId(userId);

        redisSupport.hset(Constants.REDIS_DOMAIN_USER, user.getUsername(), JsonUtil.toJson(user));
    }

    @Override
    public User findByName(String username) {
        String json = redisSupport.hget(Constants.REDIS_DOMAIN_USER, username);
        if (!StringUtils.isEmpty(json)) {
            return JsonUtil.toBean(json, User.class);
        }
        return null;
    }

    @Override
    public void update(User user) {
        redisSupport.hset(Constants.REDIS_DOMAIN_USER, user.getUsername(), JsonUtil.toJson(user));
    }
}
