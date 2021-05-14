package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.*;
import com.ly.personalsys.service.*;
import com.ly.personalsys.vo.EmployeeVo;
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
 * @since 2021-03-24
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserInfService userInfService;

    @Autowired
    private SexInfService sexInfService;

    @Autowired
    private JobInfService jobInfService;

    @Autowired
    private DeptInfService deptInfService;

    @Autowired
    private EducationService educationService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<EmployeeVo> page = new Page<EmployeeVo>(pageNo,pageSize);

        if(content == null || content.equals("")){

            employeeService.getEmpList(page,null);

        }else {
            employeeService.getEmpList(page,content);
            model.addAttribute("content",content);
        }

        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "employee/list";
    }

    @RequestMapping("/association")
    public String toAdd() {
        return "/employee/association";
    }

    @RequestMapping("/toassociation")
    public String toassociation(String loginname, Model model) {
        UserInf user = userInfService.getOne(new QueryWrapper<UserInf>().eq("loginname",loginname),false);

        if (user == null) {
            model.addAttribute("message", "登录名称不存在！");
            return "/employee/association";
        } else {
            Employee employee = employeeService.getById(user.getId());
            if (employee != null) {
                model.addAttribute("message", "员工已经存在！");
                return "/employee/association";
            } else {
                List<SexInf> sex_list = sexInfService.list();
                List<JobInf> job_list = jobInfService.list();
                List<DeptInf> dept_list = deptInfService.list();
                List<Education> education_list = educationService.list();

                model.addAttribute("sex_list", sex_list);
                model.addAttribute("job_list", job_list);
                model.addAttribute("dept_list", dept_list);
                model.addAttribute("education_list", education_list);
                model.addAttribute("user_id", user.getId());

                return "/employee/add";
            }

        }

    }

    @RequestMapping("add")
    public String add(Employee employee,Model model){

        System.out.println(employee);
        boolean result = employeeService.save(employee);
        userInfService.update(new UpdateWrapper<UserInf>().set("emp_id",employee.getId()).eq("id",employee.getUserId()));

        System.out.println(result);

        if (result){
            model.addAttribute("msg","true");
        }else {
            model.addAttribute("msg","false");
        }
        return "/employee/association";

    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  employeeService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Integer pageNo,Integer pageSize,Model model) {

        Employee employee = employeeService.getById(id);

        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();
        List<SexInf> sex_list = sexInfService.list();
        List<Education> education_list = educationService.list();

        model.addAttribute("sex_list", sex_list);
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("education_list", education_list);
        model.addAttribute("employee",employee);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("pageSize",pageSize);

        return "employee/edit";
    }

    @RequestMapping("/edit")
    public String edit(Employee employee,Integer pageNo,Integer pageSize,Model model) {

        System.out.println("employee=============="+employee);

        boolean result = employeeService.updateById(employee);
        if (result){
            return "redirect:/employee/list?pageNo="+pageNo+"&pageSize="+pageSize;
        }else {
            model.addAttribute("msg","修改失败");

            return "redirect:/employee/toedit?id="+employee.getId()+"&pageNo="+pageNo+"&pageSize="+pageSize;
        }

    }


    @RequestMapping("/plist")
    public String toPlist(Integer id, Model model,HttpSession session){

        UserInf userInf = (UserInf) session.getAttribute("user_session");
        id = userInf.getEmpId();

        EmployeeVo employeeVo = employeeService.getEmpById(id);
        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();
        List<SexInf> sex_list = sexInfService.list();
        List<Education> education_list = educationService.list();

        model.addAttribute("sex_list", sex_list);
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("education_list", education_list);
        model.addAttribute("employee",employeeVo);
        return "employee/plist";
    }


    @RequestMapping("/topadd")
    public String toPadd(Integer id, Model model){


        EmployeeVo employeeVo = new EmployeeVo();
        UserInf userInf = userInfService.getById(id);
        if(userInf.getEmpId() == null){
            model.addAttribute("message","未注册员工,可注册");
            employeeVo.setName(userInf.getUsername());
        }else {
            model.addAttribute("message","已注册员工,可完善信息");
            employeeVo = employeeService.getEmpById(userInf.getEmpId());
        }

        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();
        List<SexInf> sex_list = sexInfService.list();
        List<Education> education_list = educationService.list();

        model.addAttribute("sex_list", sex_list);
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("education_list", education_list);
        model.addAttribute("employee",employeeVo);

        return "employee/padd";
    }


    @RequestMapping("/padd")
    public String padd(Employee employee, Model model, HttpSession session){


        UserInf userInf = userInfService.getById(employee.getUserId());
        if(userInf.getEmpId()==null){
            employee.setCreatedate(new Date());
            employeeService.saveOrUpdate(employee);
            System.out.println("empId============="+employee.getId());
            userInfService.update(new UpdateWrapper<UserInf>().set("emp_id",employee.getId()).eq("id",employee.getUserId()));
            userInf.setEmpId(employee.getId());
            session.setAttribute("user_session", userInf);

        }else {
            employeeService.saveOrUpdate(employee);
        }
        System.out.println("employee=========="+employee);

        return "redirect:/employee/topadd?id="+employee.getUserId();
    }


}

