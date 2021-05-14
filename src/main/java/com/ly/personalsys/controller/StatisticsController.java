package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.personalsys.entity.Employee;
import com.ly.personalsys.entity.JobInf;
import com.ly.personalsys.entity.SexStatis;
import com.ly.personalsys.service.EmployeeService;
import com.ly.personalsys.service.JobInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobInfService jobInfService;

    @RequestMapping("")
    public String statistics(){

        return "statistics/statistics";
    }

    @ResponseBody
    @RequestMapping("/sexStatis")
    public List<SexStatis>  sexStatis(){

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sex_id","COUNT(*) userId").groupBy("sex_id");
        List<Employee> list = employeeService.list(queryWrapper);
        List<SexStatis> liseSex = new ArrayList<>();

        for (Employee employee:list){

           SexStatis sexStatis = new SexStatis();

           if(employee.getSexId() == 1){
               sexStatis.setName("男");
           }else {
               sexStatis.setName("女");
           }
            sexStatis.setValue(employee.getUserId());
            liseSex.add(sexStatis);
        }
        return liseSex;
    }

    @ResponseBody
    @RequestMapping("/jobStatis")
    public List<SexStatis>  jobStatis(){

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        //userId 暂时装在统计数量
        queryWrapper.select("job_id","COUNT(job_id) userId").groupBy("job_id");
        List<SexStatis> listJob = new ArrayList<>();
        List<Employee> list = employeeService.list(queryWrapper);
        List<JobInf> job_list = jobInfService.list();
        for (Employee employee:list){

            SexStatis jobStatis = new SexStatis();

            for (JobInf jobInf : job_list){
                if(employee.getJobId() != null && employee.getJobId() == jobInf.getId()){
                    jobStatis.setName(jobInf.getName());
                }
            }
            jobStatis.setValue(employee.getUserId());
            listJob.add(jobStatis);
        }
        return listJob;
    }

    @ResponseBody
    @RequestMapping("/addressStatis")
    public List<SexStatis>  addressStatis(){

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        //userId 暂时装在统计数量
        queryWrapper.select("LEFT(address, 3) address","COUNT(*) userId").groupBy("LEFT(address, 3)");
        List<SexStatis> listAddress = new ArrayList<>();
        List<Employee> list = employeeService.list(queryWrapper);

        for (Employee employee:list){

            SexStatis sexStatis = new SexStatis(employee.getAddress(),employee.getUserId());
            listAddress.add(sexStatis);
        }
        return listAddress;
    }



}
