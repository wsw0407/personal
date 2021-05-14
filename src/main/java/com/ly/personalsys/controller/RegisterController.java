package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.personalsys.entity.CodeInf;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.service.CodeInfService;
import com.ly.personalsys.service.UserInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegisterController {

    @Autowired
    private CodeInfService codeInfService;

    @Autowired
    private UserInfService userInfService;

    @RequestMapping("/toregistCode")
    public String toregistCode(String registCode, Model model) {
        if (registCode == null || registCode == "") {
            model.addAttribute("message", "注册码不能为空！");
            return "registCode";
        }

        CodeInf codeInf = codeInfService.getOne(new QueryWrapper<CodeInf>().eq("code",registCode));

        if (codeInf == null) {
            model.addAttribute("message", "注册码不正确！");
            return "registCode";
        }

        return "regist";
    }

//    @RequestMapping("/registCode")
//    public String registCode() {
//        return "registCode";
//    }

    @RequestMapping("/register")
    public String register(UserInf userInf,Model model) {
        System.out.print(userInf);
        if (userInf.getLoginname() != "" && userInf.getUsername() != "" && userInf.getEmail() != ""
                && userInf.getPassword() != "") {
            // userInfo 写入数据库
            // md5加密

            String pwd = DigestUtils.md5DigestAsHex(userInf.getPassword().getBytes());

            userInf.setStatusId(2);
            userInf.setPassword(pwd);
            System.out.println("register=========="+userInf);
            userInfService.save(userInf);

            return "loginForm";
        }else {
            model.addAttribute("message", "信息不完整，请完善信息！");
            return "regist";
        }
    }

    @RequestMapping("/check_Register_loginname")
    @ResponseBody
    public String check_Register_loginname(String loginname){
        UserInf userInf = userInfService.getOne(new QueryWrapper<UserInf>().eq("loginname",loginname));
        if (userInf != null) {
            return  "登录名已经使用！";
        } else {
            return "";
        }
    }

    @RequestMapping("/check_Register_email")
    @ResponseBody
    public String check_Register_email(String email){
        System.out.println("====================");
        UserInf userInf = userInfService.getOne(new QueryWrapper<UserInf>().eq("email",email),false);
        System.out.println(email+"===="+userInf);
        if (userInf != null) {
            return "邮箱已经使用！";
        } else {
            return "";
        }
    }

}
