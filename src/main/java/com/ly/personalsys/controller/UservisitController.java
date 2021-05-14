package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Uservisit;
import com.ly.personalsys.service.UservisitService;
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
 * @since 2021-03-23
 */
@Controller
@RequestMapping("/uservisit")
public class UservisitController {

    @Autowired
    private UservisitService uservisitService;

//    @Autowired
//    private StringRedisTemplate redisTemplate;


    @RequestMapping("list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

/*
        List<String> users = redisTemplate.opsForList().range("users", 0, 10);
        List<Uservisit> list =new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            try {
                Uservisit uservisitInf = new ObjectMapper().readValue(users.get(i), Uservisit.class);
                list.add(uservisitInf);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // redis数据是空的时候取数据库
        if(list.isEmpty()||list.size()<1){
            System.out.println("=============取数据库===================");
            list = uservisitService.list(null);
            // 存入到redis数据库
            for (Uservisit uservisitInf:list){
                try {
                    redisTemplate.opsForList().leftPush("users",new ObjectMapper().writeValueAsString(uservisitInf));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
*/

        Page<Uservisit> page = new Page<Uservisit>(pageNo,pageSize);

        if(content == null || content.equals("")){
            uservisitService.page(page,new QueryWrapper<Uservisit>().orderByDesc("id"));
        }else {
            QueryWrapper<Uservisit> wrapper = new QueryWrapper<Uservisit>();
            wrapper.like("loginname","%"+content+"%")
                    .or().like("visit_ip","%"+content+"%");

            uservisitService.page(page,wrapper);
            model.addAttribute("content",content);
        }

        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());

       // model.addAttribute("list",list);

        return "uservisit/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id) {
        return  uservisitService.removeById(id);
    }



    @RequestMapping("add")
    public String add(Uservisit uservisit){


        System.out.println(uservisit);
        boolean result = uservisitService.save(uservisit);
        System.out.println(result);

        return "redirect:/uservisit/toadd";
    }
}

