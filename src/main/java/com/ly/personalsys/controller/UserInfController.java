package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.StatusInf;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.service.StatusInfService;
import com.ly.personalsys.service.UserInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-22
 */
@Controller
@RequestMapping("/user")
public class UserInfController {

    @Autowired
    private UserInfService userInfService;

    @Autowired
    private StatusInfService statusInfService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<UserInf> page = new Page<UserInf>(pageNo,pageSize);

        if(content == null || content.equals("")){
            userInfService.page(page,null);
        }else {
            QueryWrapper<UserInf> wrapper = new QueryWrapper<UserInf>();
            wrapper.like("loginname","%"+content+"%")
                   .or().like("username","%"+content+"%");
            userInfService.page(page,wrapper);
            model.addAttribute("content",content);
        }

        System.out.println("getSize:"+page.getSize());
        System.out.println("getOrders:"+page.getOrders());
        System.out.println("getCurrent:"+page.getCurrent());
        System.out.println("getTotal:"+page.getTotal());
        System.out.println("getPages:"+page.getPages());
        System.out.println("hasNext:"+page.hasNext());
        System.out.println("hasPrevious:"+page.hasPrevious());

        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "users/list";
    }

    @RequestMapping("toadd")
    public String toAdd(){

        return "users/add";
    }

    @RequestMapping("add")
    public String add(UserInf userInf){

        userInf.setStatusId(2);
        userInf.setCreatedate(new Date());
        System.out.println(userInf);
        boolean result = userInfService.save(userInf);
        System.out.println(result);
        return "redirect:/user/toadd";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  userInfService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        List<StatusInf> list = statusInfService.list();
        model.addAttribute("status_list",list);
        UserInf userInf = userInfService.getById(id);
        model.addAttribute("user",userInf);
        return "users/edit";
    }

    @RequestMapping("/edit")
    public String edit(UserInf userInf,Model model) {

        System.out.println("userInf=============="+userInf);

        boolean result = userInfService.updateById(userInf);
        if (result){
            return "redirect:/user/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/user/toedit?id="+userInf.getId();
        }

    }

    @RequestMapping("/topedit")
    public String topedit(Integer id,Model model) {

        UserInf userInf = userInfService.getById(id);
        model.addAttribute("user",userInf);
        return "users/pedit";
    }

    @RequestMapping("/pedit")
    public String pedit(UserInf userInf, Model model, HttpSession session) {

        System.out.println("userInf=============="+userInf);



        String pwd = DigestUtils.md5DigestAsHex(userInf.getPassword().getBytes());

        System.out.println("user================="+userInf);

        UserInf newUser= userInfService.getById(userInf.getId());
        System.out.println("newUser================="+newUser);

        if( pwd==null || !pwd.equals(newUser.getPassword())){
            model.addAttribute("message","密码错误");
        }else {

            userInf.setPassword(null);
            boolean result = userInfService.updateById(userInf);
            if( !result ){
                model.addAttribute("message","修改失败");
            }
        }

        UserInf userInf1 = userInfService.getById(userInf.getId());
        session.setAttribute("user_session", userInf1);
        model.addAttribute("user",userInf1);
        return "users/pedit";

    }


}

