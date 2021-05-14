package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.*;
import com.ly.personalsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private CompletionService completionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DeptInfService deptInfService;

    @Autowired
    private JobInfService jobInfService;

    @RequestMapping("")
    public String train(Model model) {
        return "train/train";
    }


    @RequestMapping("/admintrainlist")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Train> page = new Page<Train>(pageNo,pageSize);

        if(content == null || content.equals("")){

            trainService.getListPages(page,null);

        }else {
            trainService.getListPages(page,content);
            model.addAttribute("content",content);
        }


        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "train/admintrainlist";
    }

    @RequestMapping("/toadd")
    public String toAdd(Model model) {

        List<Completion> list = completionService.list();
        model.addAttribute("completion_list",list);
        return "train/add";
    }

    @RequestMapping("/add")
    public String add(Train train, Model model){

        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("name",train.getEmpname()),false);
        System.out.println(employee);
        System.out.println(train);
        if (employee == null){
            model.addAttribute("msg","不是员工");
            return "redirect:/train/toadd";
        }else {

            train.setDeptId(employee.getDeptId());
            train.setEmpId(employee.getId());
            train.setJobId(employee.getJobId());
            train.setTraintime(new Date());
            boolean result = trainService.save(train);

            System.out.println(result);

            if (result){
                model.addAttribute("msg","true");
            }else {
                model.addAttribute("msg","false");
            }
        }
        return "redirect:/train/admintrainlist";
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {


        Train train = trainService.getById(id);

        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();
        List<Completion> completion_list = completionService.list();
        Employee employee = employeeService.getById(train.getEmpId());

        train.setEmpname(employee.getName());

        model.addAttribute("completion_list",completion_list);
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);

        model.addAttribute("train",train);

        return "train/edit";
    }

    @RequestMapping("/edit")
    public String edit(Train train, Model model) {

        System.out.println("train=============="+train);

        boolean result = trainService.updateById(train);

        if (result){
            return "redirect:/train/admintrainlist";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/train/toedit?id="+train.getId();
        }

    }

    @RequestMapping("trainlist")
    public String trainlist(HttpSession session,Model model){
        UserInf userInf = (UserInf) session.getAttribute("user_session");
        List<Train> list = new ArrayList<>();
        if(userInf != null && userInf.getEmpId() != null){
            list = trainService.getTrainByEmpId(userInf.getEmpId());
        }
        model.addAttribute("list",list);

        return "train/trainlist";
    }

}

