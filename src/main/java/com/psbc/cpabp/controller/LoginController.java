package com.psbc.cpabp.controller;

import com.psbc.cpabp.service.CpabpService;
import com.psbc.cpabp.entity.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j  //日志注解
@Controller
public class LoginController {
    @Autowired
   private CpabpService  cpasevice ;

    /**
     * 登录页面请求函数
     *
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(String name, String password, Model model) {
        log.info("login页面请求...");
        log.info("name:" + name);
        log.info("password:" + password);
        TbUser user = cpasevice.queryUserInfo(name, password);
        if (null == user) {
            log.info("用户不存在");
            model.addAttribute("name", name);
            return "notexist";
        }
        log.info(user.toString());
        model.addAttribute("user", user);
        return "/menu.html";
    }

    /**
     * 注册界面跳转
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        int flag = 0;
        log.info("register页面请求...");
        return "register";
    }

    @PostMapping("/adduser")
    public String adduser(TbUser user, Model model) {
        int flag = 0;
        log.info("adduser请求...");
        user.setState("0");
        flag = cpasevice.addUserInfo(user);
        if (flag > 0) {
            TbUser user2 = cpasevice.queryUserInfo(user.getName(), user.getPassword());
            model.addAttribute("user", user2);
            return "userdtl";
        } else {
            return "notexist";
        }
    }

    @RequestMapping("/userlist")
    public String userlist(Model model) {
        log.info("userlist页面请求...");
        List<TbUser> luser = new ArrayList<>();
        luser = cpasevice.queryUserList();
        if (null != luser) {
            model.addAttribute("luser", luser);
            return "userlist";
        } else {
            return "notexist";
        }
    }

    @RequestMapping("/updateuser")
    public String updateuser(TbUser user, Model model) {
        log.info("updateuser页面请求...");
        log.info(user.toString());
        log.info(user.getAddress());
        model.addAttribute("user", user);
        return "updateuser";
    }

    @RequestMapping("/update")
    public String update(TbUser user, Model model) {
        int flag = 0;
        log.info("修改个人信息请求...");
        user.setState("0");
        flag = cpasevice.updateUserByName(user);
        if (flag > 0) {

            TbUser newUser = cpasevice.queryUserInfo(user.getName(),user.getPassword());
            model.addAttribute("user", newUser);
            return "userdtl";
        } else {
            return "updatefail";
        }
    }


//    /**
//     * 登录请求处理方法
//     *
//     * @param name
//     * @param password
//     * @return
//     */
//    @RequestMapping("/login")
//    public ModelAndView login(String name, String password) {
//        log.info("login...");
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("name",name);
//        mav.setViewName("success");
//        return mav;
//    }
//    @RequestMapping("/login")
//    public String login(String name, String password, Model model) {
//        log.info("login页面请求...");
//        model.addAttribute("name", name);
//        model.addAttribute("password", password);
//
//        List<TbUser> luser = new ArrayList<>();
//
//        TbUser user = new TbUser();
//        user.setAddr("北京");
//        user.setAge("20");
//        user.setMail("xiaoming@163.com");
//        user.setPhone("18866667777");
//        model.addAttribute("user", user);
//
//        TbUser user2 = new TbUser();
//        user2.setAddr("上海");
//        user2.setAge("30");
//        user2.setMail("xiaoming@163.com");
//        user2.setPhone("18899997777");
//        model.addAttribute("user", user2);
//
//        luser.add(user);
//        luser.add(user2);
//
//        model.addAttribute("luser", luser);
//
//        return "success";
//    }

}
