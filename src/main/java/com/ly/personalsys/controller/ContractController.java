package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.*;
import com.ly.personalsys.service.ContractService;
import com.ly.personalsys.service.DeptInfService;
import com.ly.personalsys.service.EmployeeService;
import com.ly.personalsys.service.JobInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @since 2021-03-28
 */
@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

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

        Page<Contract> page = new Page<Contract>(pageNo,pageSize);

        if(content == null || content.equals("")){

            contractService.getListPages(page,null);

        }else {
            contractService.getListPages(page,content);
            model.addAttribute("content",content);
        }


        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "contract/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "contract/add";
    }

    @RequestMapping("/add")
    public String add(Contract contract, Model model, HttpSession session){

        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq("name",contract.getEmpname()),false);
        System.out.println(employee);
        System.out.println(contract);
        if (employee == null){
            model.addAttribute("msg","不是员工");
            return "contract/add";
        }else {

            contract.setEmpId(employee.getId());
            contract.setJobId(employee.getJobId());
            contract.setDeptId(employee.getDeptId());
            contract.setCreateDate(new Date());
            boolean result = contractService.save(contract);

            System.out.println(result);

            if (result){
                model.addAttribute("msg","true");
            }else {
                model.addAttribute("msg","false");
            }
        }
        return "redirect:/contract/list";
    }


    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {
        Contract contract = contractService.getById(id);
        System.out.println(contract+"=========="+id);
        List<DeptInf> dept_list = deptInfService.list();
        List<JobInf> job_list = jobInfService.list();
        Employee employee = employeeService.getById(contract.getEmpId());

        List<ContractStatus> contractStatus_list = new ArrayList<>();
        contractStatus_list.add(new ContractStatus(1,"已签"));
        contractStatus_list.add(new ContractStatus(2,"未签"));
        contract.setEmpname(employee.getName());

        model.addAttribute("job_list", job_list);
        model.addAttribute("dept_list", dept_list);
        model.addAttribute("contractStatus_list", contractStatus_list);
        model.addAttribute("contract",contract);

        return "contract/edit";
    }


    @RequestMapping("/edit")
    public String edit(Contract contract, Model model) {

        System.out.println("contract=============="+contract);

        boolean result = contractService.updateById(contract);

        if (result){
            return "redirect:/contract/list";
        }else {
            model.addAttribute("msg","修改失败");
            return "redirect:/contract/toedit?id="+contract.getId();
        }

    }


    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {

        return  contractService.removeById(id);
    }

    @RequestMapping("/pedit")
    public String pedit(Integer id,HttpSession session,Model model) {

        UserInf userInf = (UserInf) session.getAttribute("user_session");
        Contract contract = new Contract();
        if(userInf != null && userInf.getEmpId() != null){
            contract = contractService.getContractByEmpId(userInf.getEmpId());
        }
        model.addAttribute("contract",contract);

        return  "/contract/pedit";
    }

}

