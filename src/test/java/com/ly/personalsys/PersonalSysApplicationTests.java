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
        //发件人
        helper.setFrom(from);
        //收件人
        helper.setTo(to);
        //标题
        helper.setSubject(subject);
        //true指的是html邮件，false指的是普通文本
        helper.setText(content, true);
        //添加图片
        FileSystemResource file = new FileSystemResource(new File(imgPath));
        helper.addInline(imgId, file);
        helper.addInline("test002",file);
        //发送邮件
        mailSender.send(message);


    }


    //发送带附件的邮件
    @Test
    public void sendAttachmentsMail() throws MessagingException {


        String form = "1075512441@qq.com";
        String to = "2862516131@qq.com";
        String content =  "这是一封带附件的邮件内容！";
        String filePath = "C:\\Users\\DELL\\Desktop\\B17040713_王世伟_开题报告--1.doc";
        String subject = "这是一封带附件的邮件";


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


    //发送HTML邮件
    @Test
    public void sendHtmlMail() throws MessagingException{
        MimeMessage message=mailSender.createMimeMessage();

        Context context=new Context();
        context.setVariable("id","006");

        String emailContent=templateEngine.process("mailHtml",context);

        String form = "1075512441@qq.com";
        String to = "2862516131@qq.com";
        String subject = "这是一封模板邮件";

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

        File f = new File("classpath:static/files","新建文本文档.txt");
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
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8a216da878005a8001788aeee68e33bd";
        String accountToken = "7e51234b9ce4415c9f4bbbdfaa19f236";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8a216da878005a8001788af5f05333d1";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = "18438609053";
        String templateId= "1";
        String[] datas = {"232323","1","变量3"};
        String subAppend="1234";  //可选 扩展码，四位数字 0~9999
        String reqId="fadfafxxassss";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }




}
