package com.ly.personalsys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ly.personalsys.entity.Employee;
import com.ly.personalsys.entity.Notice;
import com.ly.personalsys.entity.UserInf;
import com.ly.personalsys.mapper.EmployeeMapper;
import com.ly.personalsys.mapper.UserInfMapper;
import com.ly.personalsys.service.EmployeeService;
import com.ly.personalsys.service.NoticeService;
import com.ly.personalsys.vo.EmployeeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@SpringBootTest
class PersonalSysApplicationTests {

    @Autowired
    private UserInfMapper userInfMapper;

    @Test
    void contextLoads() {
        List<UserInf> list=userInfMapper.selectList(null);
        for (UserInf userInf : list){

            System.out.println(userInf);

        }

    }

    @Autowired
    private EmployeeService employeeService;

    @Test
    void  getEmpList(){

        Page<EmployeeVo> page = employeeService.getEmpList(new Page<>(1,3),"U");

        for (EmployeeVo employeeVo : page.getRecords()){
            System.out.println(employeeVo);
        }
    }

    @Autowired
    private NoticeService noticeService;

    @Test
    void  getNoticeList(){

        Page<Notice> page = noticeService.getNoticeList(new Page<>(1,3),null);

        for (Notice notice : page.getRecords()){
            System.out.println(notice);
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    @Test
    void sendMail(){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("kkkk");
        mailMessage.setText("pppppppp===pppppppp");
        mailMessage.setFrom("1075512441@qq.com");
        mailMessage.setTo("2862516131@qq.com");

        mailSender.send(mailMessage);

    }

    @Test
    void sendImage() throws MessagingException {

        String from = "1075512441@qq.com";
        String to = "2862516131@qq.com";
        String content =  "<h1 style='color:red'>helloWorld</h1><img src='cid:test001'/><img src='cid:test002'/>";
        String imgId = "test001";
        String subject = "helloWorld";
        String imgPath = "G:\\intellij-image\\2.jpg";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //?????????
        helper.setFrom(from);
        //?????????
        helper.setTo(to);
        //??????
        helper.setSubject(subject);
        //true?????????html?????????false?????????????????????
        helper.setText(content, true);
        //????????????
        FileSystemResource file = new FileSystemResource(new File(imgPath));
        helper.addInline(imgId, file);
        helper.addInline("test002",file);
        //????????????
        mailSender.send(message);


    }


    //????????????????????????
    @Test
    public void sendAttachmentsMail() throws MessagingException {


        String form = "1075512441@qq.com";
        String to = "2862516131@qq.com";
        String content =  "???????????????????????????????????????";
        String filePath = "C:\\Users\\DELL\\Desktop\\B17040713_?????????_????????????--1.doc";
        String subject = "??????????????????????????????";


        MimeMessage message=mailSender.createMimeMessage();

        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        helper.setFrom(form);

        FileSystemResource file=new FileSystemResource(new File(filePath));
        String fileName=file.getFilename();
        helper.addAttachment(fileName,file);

        mailSender.send(message);
    }

/*
    @Resource
    TemplateEngine templateEngine;


    //??????HTML??????
    @Test
    public void sendHtmlMail() throws MessagingException{
        MimeMessage message=mailSender.createMimeMessage();

        Context context=new Context();
        context.setVariable("id","006");

        String emailContent=templateEngine.process("mailHtml",context);

        String form = "1075512441@qq.com";
        String to = "2862516131@qq.com";
        String subject = "????????????????????????";

        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(emailContent,true);
        helper.setFrom(form);

        mailSender.send(message);

    }
*/


    @Test
    public void getPath(){

        String fileName = "personal_20210326101911174.sql";

        Integer num1 = fileName.lastIndexOf('_');

        System.out.println(num1);
        String fileprefix = fileName.substring(0,num1);
        System.out.println(fileprefix);
        System.out.println(fileName);
        Integer num2 = fileName.lastIndexOf('.');
        System.out.println(num2);
        String filesuffix = fileName.substring(num2);

        fileName=fileprefix+filesuffix;

        System.out.println(fileprefix);
        System.out.println(filesuffix);
        System.out.println(fileName);

    }


    @Test
    void getfilePath(){

        File f = new File("classpath:static/files","??????????????????.txt");
        if(f.exists()){
            System.out.println( "true");
        }else {
            System.out.println( "false");

        }
        System.out.println(f.getAbsolutePath());

    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void insertEmpResultId(){

        Employee employee = new Employee();
        employee.setId(22);
        employee.setName("lllll");
        employee.setDeptId(1);
        employee.setJobId(7);
        employee.setSexId(2);
        employee.setUserId(25);

        //employeeMapper.insert(employee);

        //employeeService.insertEmpResultId(employee);

        employeeService.saveOrUpdate(employee);

        System.out.println(employee.getId()+"===================");
    }

    @Test
    public void sentInfo(){
        //???????????????????????????app.cloopen.com
        String serverIp = "app.cloopen.com";
        //????????????
        String serverPort = "8883";
        //?????????,????????????????????????,?????????????????????????????????????????????ACCOUNT SID??????????????????AUTH TOKEN
        String accountSId = "8a216da878005a8001788aeee68e33bd";
        String accountToken = "7e51234b9ce4415c9f4bbbdfaa19f236";
        //?????????????????????????????????????????????APPID
        String appId = "8a216da878005a8001788af5f05333d1";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = "18438609053";
        String templateId= "1";
        String[] datas = {"232323","1","??????3"};
        String subAppend="1234";  //?????? ???????????????????????? 0~9999
        String reqId="fadfafxxassss";  //?????? ????????????????????????id???????????????32???????????????????????????????????????????????????????????????
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
        if("000000".equals(result.get("statusCode"))){
            //??????????????????data???????????????map???
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //??????????????????????????????????????????
            System.out.println("?????????=" + result.get("statusCode") +" ????????????= "+result.get("statusMsg"));
        }
    }




}
