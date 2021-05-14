package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Notice;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Notice> page = new Page<Notice>(pageNo,pageSize);

        if(content == null || content.equals("")){

            noticeService.getNoticeList(page,null);

        }else {
            noticeService.getNoticeList(page,content);
            model.addAttribute("content",content);
        }


        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "notice/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "notice/add";
    }


    @RequestMapping("add")
    public String add(Notice notice, Model model, HttpSession session){

        UserInf userInf = (UserInf) session.getAttribute("user_session");
        if (userInf == null){
                model.addAttribute("msg","未登录");
        }else {

                System.out.println(notice);
                notice.setCreatedate(new Date());
                boolean result = noticeService.save(notice);

                System.out.println(result);

                if (result){
                    model.addAttribute("msg","true");
                }else {
                    model.addAttribute("msg","false");
                }
        }
        return "notice/add";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  noticeService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.select("id","title","content","user_id").eq("id",id);
        Notice notice = noticeService.getOne(wrapper,false);

        model.addAttribute("notice",notice);

        return "notice/edit";
    }

    @RequestMapping("/edit")
    public String edit(Notice notice, Model model) {

        System.out.println("notice=============="+notice);

        boolean result = noticeService.updateById(notice);

        if (result){
            return "redirect:/notice/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/notice/toedit?id="+notice.getId();
        }

    }



}










