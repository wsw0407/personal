package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.*;
import com.ly.personalsys.service.DeptInfService;
import com.ly.personalsys.service.EmployeeService;
import com.ly.personalsys.service.JobInfService;
import com.ly.personalsys.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @since 2021-03-28
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DeptInfService deptInfService;

    @Autowired
    private JobInfService jobInfService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Salary> page = new Page<Salary>(pageNo,pageSize);

        if(content == null || content.equals("")){

            salaryService.getListPages(page,null);

        }else {
            salaryService.getListPages(page,content);
            model.addAttribute("content",content);
        }


        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "salary/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "salary/add";
    }

    @RequestMapping("/add")
    public String add(Salary salary, Model model, HttpSession session){

        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("name",salary.getEmpname()),false);
        System.out.println(employee);
        System.out.println(salary);
        if (employee == null){
            model.addAttribute("msg","不是员工");
            return "salary/add";
        }else {

            salary.setDeptId(employee.getDeptId());
            salary.setEmpId(employee.getId());
            salary.setJobId(employee.getJobId());
            salary.setCreateDate(new Date());
            boolean result = salaryService.save(salary);

            System.out.println(result);

            if (result){
                model.addAttribute("msg","true");
            }else {
                model.addAttribute("msg","false");
            }
        }
        return "redirect:/salary/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  salaryService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {


        Salary salary = salaryService.getById(id);

        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();
        Employee employee = employeeService.getById(salary.getEmpId());

        salary.setEmpname(employee.getName());
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);

        model.addAttribute("salary",salary);

        return "salary/edit";
    }

    @RequestMapping("/edit")
    public String edit(Salary salary, Model model) {

        System.out.println("salary=============="+salary);

        boolean result = salaryService.updateById(salary);

        if (result){
            return "redirect:/salary/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/salary/toedit?id="+salary.getId();
        }

    }

    @RequestMapping("/pedit")
    public String toplist(Model model,HttpSession session){

        UserInf userInf = (UserInf) session.getAttribute("user_session");
        Salary salary = new Salary();
        if(userInf != null && userInf.getEmpId() != null){
            salary = salaryService.getSalaryByEmpId(userInf.getEmpId());
        }
        model.addAttribute("salary",salary);

        return "salary/pedit";
    }


}

