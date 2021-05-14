package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.DeptInf;
import com.ly.personalsys.entity.StatusInf;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.service.DeptInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
@Controller
@RequestMapping("/dept")
public class DeptInfController {

    @Autowired
    private DeptInfService deptInfService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<DeptInf> page = new Page<DeptInf>(pageNo,pageSize);

        if(content == null || content.equals("")){
            deptInfService.page(page,null);
        }else {
            QueryWrapper<DeptInf> wrapper = new QueryWrapper<>();
            wrapper.like("name","%"+content+"%")
                    .or().like("remark","%"+content+"%");

            deptInfService.page(page,wrapper);
            model.addAttribute("content",content);
        }

        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "dept/list";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  deptInfService.removeById(id);
    }

    @RequestMapping("toadd")
    public String toAdd(){

        return "dept/add";
    }

    @RequestMapping("add")
    public String add(DeptInf deptInf,Model model){


        System.out.println(deptInf);
        boolean result = deptInfService.save(deptInf);
        System.out.println(result);

        if (result){
            model.addAttribute("msg","true");
        }else {
            model.addAttribute("msg","false");
        }

        return "dept/add";
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        DeptInf deptInf = deptInfService.getById(id);
        model.addAttribute("dept",deptInf);
        return "dept/edit";
    }

    @RequestMapping("/edit")
    public String edit(DeptInf deptInf,Model model) {

        System.out.println("deptInf=============="+deptInf);

        boolean result = deptInfService.updateById(deptInf);
        if (result){
            return "redirect:/dept/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/dept/toedit?id="+deptInf.getId();
        }

    }





}

