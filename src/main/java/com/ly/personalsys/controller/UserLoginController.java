package com.ly.personalsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.entity.Uservisit;
import com.ly.personalsys.service.UserInfService;
import com.ly.personalsys.service.UservisitService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserLoginController {

    @Autowired
    private UserInfService userInfrService;

    @Autowired
    private UservisitService uservisitService;

//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;


    @RequestMapping("/login")
    public String login(UserInf user, String user_input_verifyCode, HttpSession session, Model model,HttpServletRequest request) {

        getClientInfo(request,user.getLoginname());

        String  code=(String) session.getAttribute("code");
        if (user_input_verifyCode == null ) {return "loginForm";}

        if(!user_input_verifyCode.equalsIgnoreCase(code)) {
            model.addAttribute("message", "验证码输入不正确！");
            return "loginForm";
        }
        // md5加密
       // String md5Str=ShiroMd5.md5(user.getPassword(), user.getLoginname());

        // 验证用户登录信息
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        System.out.println("user================="+user);
        QueryWrapper<UserInf> wrapper = new QueryWrapper<>();
        wrapper.eq("loginname",user.getLoginname()).eq("password",pwd);

        UserInf newUser=userInfrService.getOne(wrapper);
        System.out.println("newUser================="+newUser);
        if(newUser!=null) {
            session.setAttribute("user_session", newUser);
           return "index";
        }else{
            model.addAttribute("message", "用户名或秘密不正确！");
           return "loginForm";
        }

    }


    public  void getClientInfo(HttpServletRequest request, String loginname){
        String agent =request.getHeader("User-Agent");
        System.out.println(agent);

        //User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器
        //能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、
        //浏览器渲染引擎、浏览器语言、浏览器插件等
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);

        // 获取浏览器
        Browser browser = userAgent.getBrowser();
        //System.out.println(browser.getName());
        // 获取浏览器版本
        Version bv = userAgent.getBrowserVersion();
        //System.out.println(bv.getVersion());
        // 获取操作系统
        OperatingSystem os = userAgent.getOperatingSystem();
        //System.out.println(os.getName());

        String iphone = "";
        if (agent.contains("Windows NT")) {
            //pc型号获取方法实现
            String pc_regex = " \\((.*); ";
            Pattern pattern = Pattern.compile(pc_regex);
            Matcher matcher = pattern.matcher(agent);
            while (matcher.find()) {
                iphone = matcher.group(1);
            }
            agent = "PC端";
        } else {
            String type = "";
            if (agent.contains("iPhone") || agent.contains("iPod") || agent.contains("iPad")) {
                type = "ios";
            } else if (agent.contains("Android") || agent.contains("Linux")) {
                type = "apk";
            } else if (agent.indexOf("micromessenger") > 0) {
                type = "wx";
            }
            String iphone_regex = "\\((.*)\\) Apple";
            Pattern pattern = Pattern.compile(iphone_regex);
            Matcher matcher = pattern.matcher(agent);

            while (matcher.find()) {
                iphone = matcher.group(1);
            }
            agent = "移动端" + type;
        }

        System.out.println(iphone);
        System.out.println(agent);

        System.out.println("---------------------获取ip--------------------------");
        String ipAddress = null;
        if (request.getHeader("x-forwarded-for") == null) {
            ipAddress = request.getRemoteAddr();
        } else {
            if (request.getHeader("x-forwarded-for").length() > 15) {
                String[] aStr = request.getHeader("x-forwarded-for").split(",");
                ipAddress = aStr[0];
            } else {
                ipAddress = request.getHeader("x-forwarded-for");
            }
        }
        System.out.println(ipAddress);

        // 封装UservisitInf
        Uservisit uservisitInf =new Uservisit();
        uservisitInf.setLoginname(loginname);
        uservisitInf.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        uservisitInf.setVisitIp(ipAddress);
        uservisitInf.setUserAddress("");
        uservisitInf.setUserFrom(agent);
        uservisitInf.setBrowser(browser.getName());
        uservisitInf.setSystem(os.getName());
        uservisitInf.setVersion(bv.getVersion());
        uservisitInf.setIphone(iphone);

        // 写入数据库
        uservisitService.save(uservisitInf);
        String s = null;
        try {
            s = new ObjectMapper().writeValueAsString(uservisitInf);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 写入redis
        // redisTemplate.opsForList().leftPush("users",s);
        // 写入到session

        request.getSession().setAttribute("USERV_ISIT",uservisitInf);

    }


    @RequestMapping("/")
    public String login(){
        return "loginForm";
    }

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){

        return "welcome";
    }

    @RequestMapping("/testImg")
    public String testImg(){

        return "testImg";
    }


    @RequestMapping("/testEcharts")
    public String testEcharts(){

        return "testEcharts";
    }

    @RequestMapping("/exit")
    public String loginOut(HttpSession session){
        session.removeAttribute("user_session");
        return "loginForm";
    }

    @RequestMapping("/repassword")
    public String repassword() {
        return "repasswordPage";
    }

    @RequestMapping("/toFindPassword")
    public String toFindPassword(String loginname,String username,String user_input_verifyCode,HttpSession session,Model model) {

        System.out.println(loginname);
        System.out.println(username);
        System.out.println(user_input_verifyCode);
        UserInf userInfo = userInfrService.getOne(new QueryWrapper<UserInf>().eq("loginname",loginname).eq("username",username),false);

        String  code=(String) session.getAttribute("code");

        if(!user_input_verifyCode.equalsIgnoreCase(code)) {
            model.addAttribute("message", "验证码输入不正确！");
            return "repasswordPage";
        }

        if (userInfo == null){
            model.addAttribute("message", "查无此人！");
            return "repasswordPage";
        }

        model.addAttribute("user", userInfo);

        return "findPassword";
    }

    @RequestMapping("/rePassword")
    public String rePassword(String loginname,String password,String repassword,Model model) {
        System.out.println(loginname);
        System.out.println(password);
        System.out.println(repassword);

        if(password.equals(repassword)) {
            // md5加密
            String md5Str=DigestUtils.md5DigestAsHex(password.getBytes());
            // 更新数据库
            userInfrService.update(new UpdateWrapper<UserInf>().eq("loginname",loginname).set("password",md5Str));
            return "loginForm";
        }else {
            UserInf userInf = new UserInf();
            userInf.setLoginname(loginname);
            System.out.println("userInf============="+userInf);
            model.addAttribute("user", userInf);
            model.addAttribute("message", "两次输入的密码不一样！");
            return "findPassword";
        }
    }

    @ResponseBody
    @RequestMapping("/checkUsername")
    public String checkUsername(String username){
        UserInf userInf = userInfrService.getOne(new QueryWrapper<UserInf>().eq("username",username),false);
        if(userInf==null) {
            return  "用户名称不存！";
        }else {
            return  "";
        }
    }

    @ResponseBody
    @RequestMapping("/checkLoginName")
    public String checkLoginName(String loginname){
        UserInf userInf = userInfrService.getOne(new QueryWrapper<UserInf>().eq("loginname",loginname),false);
        if(userInf==null) {
            return  "用户名称不存！";
        }else {
            return  "";
        }
    }


    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        return "test";
    }


}
