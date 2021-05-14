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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-26
 */
@Controller
@RequestMapping("/checkwork")
public class CheckworkController {

    @Autowired
    private CheckworkService checkworkService;

    @Autowired
    private  UserInfService userInfService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobInfService jobInfService;

    @Autowired
    private DeptInfService deptInfService;

    @Autowired
    private LeavetypeService leavetypeService;

    @Autowired
    private LeavestatusService leavestatusService;

    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Checkwork> page = new Page<Checkwork>(pageNo,pageSize);

        if(content == null || content.equals("")){
            checkworkService.getCheckworkList(page,null);
        }else {
            checkworkService.getCheckworkList(page,content);
            model.addAttribute("content",content);
        }

        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        model.addAttribute("count",page.getTotal());
        return "checkwork/list";
    }


    @RequestMapping("toadd")
    public String toAdd(){

        return "checkwork/add";
    }

    @RequestMapping("add")
    public String add(Checkwork checkwork,String employee_name, Model model){

        System.out.println(checkwork);
        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("name",employee_name),false);

        if (employee == null){
            model.addAttribute("msg","员工不存在");
            return "checkwork/add";
        }

        checkwork.setEmpId(employee.getId());
        checkwork.setDeptId(employee.getDeptId());
        checkwork.setJobId(employee.getJobId());
        checkwork.setCreatedate(new Date());

        System.out.println("checkwork============="+checkwork);

        boolean result = checkworkService.save(checkwork);
        System.out.println(result);

        if (result){
            model.addAttribute("msg","true");
        }else {
            model.addAttribute("msg","false");
        }

        return "checkwork/add";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  checkworkService.removeById(id);
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        Checkwork checkwork = checkworkService.getById(id);

        Employee employee = employeeService.getById(checkwork.getEmpId());
        checkwork.setEmpname(employee.getName());
        List<JobInf> job_list = jobInfService.list();
        List<DeptInf> dept_list = deptInfService.list();

        model.addAttribute("checkwork", checkwork);
        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);
        //model.addAttribute("notice",notice);

        return "checkwork/edit";
    }

    @RequestMapping("/edit")
    public String edit(Checkwork checkwork, Model model) {

        System.out.println("checkwork=============="+checkwork);

        boolean result = checkworkService.updateById(checkwork);

        if (result){
            return "redirect:/checkwork/list";
        }else {
            model.addAttribute("msg","修改失败");

            return "redirect:/checkwork/toedit?id="+checkwork.getId();
        }

    }


    @Autowired
    private LeaveService leaveService;

    @RequestMapping("/adminleavelist")
    public String toListleaf(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Leave> page = new Page<Leave>(pageNo,pageSize);

        if(content == null || content.equals("")){
            leaveService.getListPages(page,null);
        }else {
            leaveService.getListPages(page,content);
            model.addAttribute("content",content);
        }

        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        return "checkwork/adminleavelist";
    }



    @RequestMapping("/leaveadd")
    public String toLeaveadd(Integer userId,Model model,String from){

        System.out.println("userId====="+userId);
        if(userId == null || "".equals(userId)){
            model.addAttribute("msg","未登录");

        }else {

            //UserInf userInf = userInfService.getById(userId);
            Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("user_id",userId),false);
            if (employee == null){
                model.addAttribute("msg","不是员工");
            }else {

                List<Leavetype> leavetype = leavetypeService.list();
                model.addAttribute("leavetype_list",leavetype);
                model.addAttribute("employee",employee);
                model.addAttribute("msg","true");
            }

        }
        System.out.println(from+"=========================");
        if(from != null){
            model.addAttribute("msg","已申请");
        }

        return "checkwork/leaveadd";
    }

    @RequestMapping("/leaveInsert")
    public String leaveInsert(Leave leave, String startdate, Model model, HttpSession session) throws ParseException {

        //UserInf userInf = userInfService.getById(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        startdate = startdate.replace("T"," ");
        Date date = sdf.parse(startdate);



        System.out.println("leave=="+leave);
        System.out.println("startdate===="+startdate);
        System.out.println("date===="+date);
        if (leave.getEmpId() == null){

            model.addAttribute("msg","不是员工");
        }else {
            Employee employee = employeeService.getById(leave.getEmpId());

            Integer days = Integer.parseInt(leave.getLeavedays());
            leave.setStartdata(startdate);
            leave.setEnddata(addAndSubtractDaysByGetTime(date,days));
            leave.setLeavetime(new Date());
            leave.setDeptId(employee.getDeptId());
            leave.setJobId(employee.getJobId());

            System.out.println("leaveAll========="+leave);

            leaveService.save(leave);
            model.addAttribute("msg","true");
        }

        UserInf userInf = (UserInf) session.getAttribute("user_session");
        return "redirect:/checkwork/leaveadd?userId="+userInf.getId()+"&from=add";
    }

    @RequestMapping("/toadminleaveedit")
    public String toLeaveedit(Integer id,Model model) {

        Leave leave = leaveService.getById(id);

        List<Leavestatus> leavestatuse = leavestatusService.list();
        List<Leavetype> leavetype = leavetypeService.list();
        model.addAttribute("leavestatus_list",leavestatuse);
        model.addAttribute("leavetype_list",leavetype);
        model.addAttribute("leave",leave);

        return "checkwork/adminleaveedit";
    }

    @RequestMapping("/adminleaveedit")
    public String adminleaveedit(Leave leave,Model model) {

        System.out.println("leave=============="+leave);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String startdata = leave.getStartdata();
        try {
            Date date = sdf.parse(startdata);
            String endDate = addAndSubtractDaysByGetTime(date,Integer.parseInt(leave.getLeavedays()));
            leave.setEnddata(endDate);
        } catch (ParseException e) {

        }

        System.out.println("newleave=============="+leave);

        boolean result = leaveService.updateById(leave);
        if (result){
            return "redirect:/checkwork/adminleavelist";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/checkwork/toadminleaveedit?id="+leave.getId();
        }

    }



    public  String addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/,int n/*加减天数*/){

        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));

        return df.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L));
    }


    @RequestMapping("/deleteLeave")
    @ResponseBody
    public boolean delLeave(Integer id) {

        return  leaveService.removeById(id);
    }

    @RequestMapping("/plist")
    public String toplist(@RequestParam(value = "content",required = false) String content,
                         Model model,HttpSession session){

        UserInf userInf = (UserInf) session.getAttribute("user_session");


        List<Checkwork> list = new ArrayList<>();
        if(content == null || content.equals("")){
            list = checkworkService.getChecListByEmpId( userInf.getEmpId(),null);
        }else {
            list = checkworkService.getChecListByEmpId( userInf.getEmpId(),content);
            model.addAttribute("content",content);
        }

        model.addAttribute("list",list);

        return "checkwork/plist";
    }


    @RequestMapping("/pleavelist")
    public String topleavelist(@RequestParam(value = "content",required = false) String content,
                          Model model,HttpSession session){

        UserInf userInf = (UserInf) session.getAttribute("user_session");


        List<Leave> list = new ArrayList<>();
        if(content == null || content.equals("")){
            list = leaveService.getListByEmpId( userInf.getEmpId(),null);
        }else {
            list = leaveService.getListByEmpId( userInf.getEmpId(),content);
            model.addAttribute("content",content);
        }

        model.addAttribute("list",list);

        return "checkwork/pleavelist";
    }


    @RequestMapping("/deletePLeave")
    @ResponseBody
    public boolean deletePLeave(Integer id) {
        Leave leave = leaveService.getById(id);
        if (leave.getLeavestatus() == 2) {
            return  leaveService.removeById(id);
        }

        return false;
    }




}

