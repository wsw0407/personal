package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.JobInf;
import com.ly.personalsys.service.JobInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
@Controller
@RequestMapping("/job")
public class JobInfController {

    @Autowired
    private JobInfService jobInfService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<JobInf> page = new Page<JobInf>(pageNo,pageSize);

        if(content == null || content.equals("")){
            jobInfService.page(page,null);
        }else {
            QueryWrapper<JobInf> wrapper = new QueryWrapper<JobInf>();
            wrapper.like("name",content)
                    .or().like("remark",content);
            jobInfService.page(page,wrapper);
            model.addAttribute("content",content);
        }

        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "job/list";
    }


    @RequestMapping("toadd")
    public String toAdd(){

        return "job/add";
    }

    @RequestMapping("add")
    public String add(JobInf jobInf,Model model){

        System.out.println(jobInf);
        boolean result = jobInfService.save(jobInf);
        System.out.println(result);

        if (result){
            model.addAttribute("msg","true");
        }else {
            model.addAttribute("msg","false");
        }

        return "job/add";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  jobInfService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        JobInf jobInf = jobInfService.getById(id);
        model.addAttribute("job",jobInf);

        return "job/edit";
    }

    @RequestMapping("/edit")
    public String edit(JobInf jobInf,Model model) {

        System.out.println("jobInf=============="+jobInf);

        boolean result = jobInfService.updateById(jobInf);

        if (result){
            return "redirect:/job/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/job/toedit?id="+jobInf.getId();
        }

    }

}

