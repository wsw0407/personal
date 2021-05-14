<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>个人合同信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    <script type="text/javascript">
$(function(){
	$("#deptForm").submit(function(){
		var employee_name = $("#employee_name");
		var msg = "";
		if (!/^[\u4E00-\u9FA5]{2,4}$/.test($.trim(employee_name.val()))){
			msg = "姓名格式不正确！！！";
			employee_name.focus(); 
	    }
		if (msg != ""){
			 alert(msg);
			return false;
		}else{
			return true;
			$("#deptForm").submit();
		}
	});
});
</script>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/contract/add">
        <input type="hidden" name="id" id="id" value="${contract.id}" >
          <div class="layui-form-item" >
              <label for="employee_name" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="employee_name" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.empname}">
                  <p class="x-red">请联系管理员修改姓名等信息</p>
              </div>
             
          </div>
          
          <div class="layui-form-item" >
              <label for="jobname" class="layui-form-label">
                  <span class="x-red">*</span>职位
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="jobname" name="jobname" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.jobname}">
                  
              </div>
             
          </div>
          
          <div class="layui-form-item" >
              <label for="deptname" class="layui-form-label">
                  <span class="x-red">*</span>部门
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="deptname" name="deptname" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.deptname}">
                  
              </div>
             
          </div>
          
          
          <div class="layui-form-item" >
              <label for="trainContract" class="layui-form-label">
                  <span class="x-red">*</span>劳动合同
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="trainContract" name="trainContract" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.trainContract == 1 ? '已签':'未签'}">
                  
              </div>
             
          </div>
          
          <div class="layui-form-item" >
              <label for="laborContract" class="layui-form-label">
                  <span class="x-red">*</span>培训合同
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="laborContract" name="laborContract" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.laborContract == 1 ? '已签':'未签'}">
                  
              </div>
             
          </div>
          
          <div class="layui-form-item" >
              <label for="confidentialityContract" class="layui-form-label">
                  <span class="x-red">*</span>保密，竞业合同
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="confidentialityContract" name="confidentialityContract" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${contract.confidentialityContract == 1 ? '已签':'未签'}">
                  
              </div>
          </div>
      </form>
    </div>
    
  </body>

</html>