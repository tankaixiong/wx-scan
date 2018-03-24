package tk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.pojo.User;
import tk.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:wangfeng
 * @Date:2017/10/13
 * @Version:1.0
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
        Map<String, Object> result = new HashMap<>();
        if (user.getUsername() == null) {
            result.put("info", "用户名不能为空!");
            return result;
        }
        if (user.getPassword() == null) {
            result.put("info", "密码不能为空!");
            return result;
        }
        User dBUser = userService.findByName(user.getUsername());
        if (dBUser == null) {
            result.put("info", "用户名不存在!");
            return result;
        }

        if (!dBUser.getPassword().equals(user.getPassword())) {
            result.put("info", "密码不正确!");
            return result;
        }

        request.getSession().setAttribute("user", dBUser);
        request.getSession().setAttribute("userId", dBUser.getId());
        request.getSession().setAttribute("username", dBUser.getUsername());
        result.put("info", "");
        logger.info("用户【{}】登录了系统", user.getUsername());
        return result;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }

    //新增与修改
    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
        Map<String, Object> result = new HashMap<>();
        if (user.getUsername() == null) {
            result.put("info", "用户名不能为空!");
            return result;
        }
        if (user.getPassword() == null) {
            result.put("info", "密码不能为空!");
            return result;
        }

        User dBUser = userService.findByName(user.getUsername());
        if (dBUser != null) {
            dBUser.setPassword(user.getPassword());

            userService.update(user);
            result.put("info", "修改成功!");

            return result;
        } else {
            user.setCreateDate(new Date());
            userService.save(user);

            result.put("info", "恭喜,添加成功");
        }

        return result;
    }


//    @RequestMapping("/update")
//    @ResponseBody
//    public Map<String,Object> update(String username,String password,String id){
//        Map<String,Object> result = new HashMap<>();
//        if(username==null){
//            result.put("info","用户名不能为空!");
//            return result;
//        }
//        if(password==null){
//            result.put("info","密码不能为空!");
//            return result;
//        }
//
//        User user = userService.findByName(id);
//        if(user==null){
//            result.put("info","未知用户!");
//            return result;
//        }
//        user.setUsername(username);
//        user.setPassword(password);
//
//        userService.update(user);
//        result.put("info","修改成功!");
//        return result;
//    }

//    @RequestMapping("/delete")
//    @ResponseBody
//    public Map<String,String> delete(@RequestParam(value="ids[]") String[] ids){
//        //List<Integer> ids = new ArrayList<>();
//        Map<String,String> result = new HashMap<>();
//        if(ids.length<=0){
//            result.put("info","请选择记录！");
//            return result;
//        }
//
//        for (String id : ids) {
//            if(id==null){
//                result.put("info","找不到该记录!");
//                return result;
//            }
//        }
//
//        for (String id : ids) {
//            User user = userService.findById(id);
//            if(user==null){
//                result.put("info","找不到该记录!");
//                return result;
//            }
//            userService.delete(user);
//        }
//
//        result.put("info","删除成功!");
//        return result;
//    }


}
