package com.ly.personalsys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.personalsys.entity.Traindata;
import com.ly.personalsys.service.TraindataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/traindata")
public class TraindataController {

    @Autowired
    private TraindataService traindataService;


    @RequestMapping("/list")
    public String toList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content,
                         Model model){

        Page<Traindata> page = new Page<>(pageNo,pageSize);

        if(content == null || content.equals("")){

            traindataService.getListPages(page,null);

        }else {
            traindataService.getListPages(page,content);
            model.addAttribute("content",content);
        }


        model.addAttribute("pageSize",page.getSize());
        model.addAttribute("count",page.getTotal());
        model.addAttribute("pageNo",page.getCurrent());
        model.addAttribute("pages",page.getPages());
        model.addAttribute("list",page.getRecords());
        return "traindata/list";
    }

    @RequestMapping("/play")
    public String play(String filename,Model model){

        model.addAttribute("filename", filename);

        return "/traindata/play";
    }

    @RequestMapping("/toadd")
    public String toadd(){

        return "traindata/add";
    }

    @Value("${train.location}")
    private String path;

    @RequestMapping("/add")
    public String add(Traindata traindata, MultipartFile file, Model model){

        if(traindata.getUserId() == null){
            model.addAttribute("msg","未登录");
            return  "traindata/add";
        }

        //String path = req.getServletContext().getRealPath("/static/files");
        System.out.println("path==============="+path);
        System.out.println("traindata=========="+traindata);

        String fileName = file.getOriginalFilename();
        Date date = new Date();
        fileName = getFileName(fileName, date);

        try {
            file.transferTo(new File(path, fileName));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        traindata.setFilename(fileName);
        traindata.setCreatedate(date);
        System.out.println("traindata===+++++++++++======="+traindata);
        boolean result = traindataService.save(traindata);
        if (result){
            model.addAttribute("msg","true");
        }else {
            model.addAttribute("msg","false");
        }

        return  "redirect:/traindata/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delUser(Integer id,String filename) {

        if(filename != null) {
            boolean delFlag = delFile(filename);
        }

        boolean result  = traindataService.removeById(id);
        System.out.println("result="+result);

        return  result;
    }

    @ResponseBody
    @RequestMapping("/filesExists")
    public String filesExists(String filename){

        System.out.println(filename);

        File f = new File(path,filename);
        if(f.exists()){
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("/downLoad")
    public ResponseEntity<byte[]> download(String filename) throws Exception{
        System.out.println(filename);

        File f = new File(path,filename);
        if(f.exists()){
            InputStream is = new FileInputStream(f);
            byte[] body = new byte[is.available()];
            is.read(body);//把要下载的文件 都 读到 body 字节数组中了  字节适用于所有的文件

            //4.得到这个body缓存 返回浏览器
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);

            is.close();

            return entity;
        }
        else {

            return  null;
        }
    }

    @RequestMapping("/toedit")
    public String toEdit(Integer id,Model model) {

        Traindata traindata = traindataService.getById(id);
        System.out.println(traindata);
        model.addAttribute("traindata",traindata);
        return "traindata/edit";
    }

    @RequestMapping("/edit")
    public String upd(Traindata traindata, MultipartFile file, Model model) {
        System.out.println("file==========***********=============");

        Date date = new Date();
        System.out.println("file======================="+file.getOriginalFilename());

        String upFile = file.getOriginalFilename();
        //如果file为空，则没有上传文件，不修改文件

        System.out.println((file != null && !"".equals(upFile))+"===============+=="+upFile);

        if (file != null && !"".equals(upFile)) {
            System.out.println("+++========file========");
            String fileName = traindata.getFilename();

            System.out.println((fileName != null)+"+++========file========"+fileName);

            if(fileName != null) {
                System.out.println(" delFile(fileName);==============");
                delFile(fileName);
            }

            fileName = file.getOriginalFilename();
            fileName = getFileName(fileName, date);
            traindata.setFilename(fileName);

            try {
                System.out.println(path+"____________-"+fileName);
                file.transferTo(new File(path, fileName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

        }else {
            //从前端的图片路径有项目路径，不能需要 更改数据库保存的路径
            traindata.setFilename(null);
        }
        System.out.println("================"+traindata);
        traindataService.updateById(traindata);

        return "redirect:/traindata/list";
    }




    public String getFileName(String fileName, Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSS");

        String strings[] = fileName.split("\\.");

        System.err.println("getTime==========="+strings[1]+strings[0]);
        fileName = strings[0] +"_"+sdf.format(date)+"."+strings[1];
        System.err.println("========fileName========"+fileName);

        return fileName;
    }

    public boolean delFile(String fileName) {

        String uppath = path + File.separator +fileName;
        File file = new File(uppath);
        System.out.println("path==================="+path);
        System.out.println("uppath==================="+uppath);

        System.out.println("file.exists=============="+file.exists());

        if(file.exists()) {
            file.delete();
            System.out.println(fileName+"删除");
            return true;
        }
        return false;
    }

}

