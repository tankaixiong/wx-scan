package tk.service;

import tk.pojo.User;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/8/21
 * @Version: 1.0
 * @Description:
 */
public interface IUserService {

    void save(User user);

    User findByName(String username);

    void update(User user);
}
