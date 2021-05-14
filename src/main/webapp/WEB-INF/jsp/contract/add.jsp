<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>考勤添加页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/js/xadmin.js"></script>
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

var msg = "${msg}";
if(msg !=null && msg !=""){
    layui.use('layer', function () {
        var layer = layui.layer;

        if(msg == "true"){
            layer.msg('添加成功!',{icon:1,time:1000});
        }
        else if (msg == "不是员工"){
            layer.msg('不是员工,无法添加',{icon:2,time:1000});
        }
        else {
            layer.msg('添加失败!',{icon:2,time:1000});
        }
    });
}
</script>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="deptForm"  action="${pageContext.request.contextPath}/contract/add">
        <input type="hidden" name="id" id="id" value="${job.id }" >
          <div class="layui-form-item" >
              <label for="employee_name" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="empname" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${job.name }">
                <p class="x-red">员工必须存在才能添加！！！</p>
                <p style="color: red">${param.message}</p>
              </div>
             
          </div>
        
  			
          
            <div class="layui-form-item" >
              <label for="trainContract_id" class="layui-form-label">
                  <span class="x-red">*</span>劳动合同
              </label>
              <div class="layui-input-inline">
               <select id="trainContract_id" name="trainContract" class="valid">
                                    <option value="0">--请选择签署状态--</option>
									<option value="1">已签</option>
									<option value="2">未签</option>
				</select>
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="laborContract_id" class="layui-form-label">
                  <span class="x-red">*</span>培训合同
              </label>
              <div class="layui-input-inline">
               <select id="laborContract_id" name="laborContract" class="valid">
                                    <option value="0">--请选择签署状态--</option>
									<option value="1">已签</option>
									<option value="2">未签</option>
				</select>
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="confidentialityContract_id" class="layui-form-label">
                  <span class="x-red">*</span>保密，竞业合同
              </label>
              <div class="layui-input-inline">
               <select id="confidentialityContract_id" name="confidentialityContract" class="valid">
                                    <option value="0">--请选择签署状态--</option>
									<option value="1">已签</option>
									<option value="2">未签</option>
				</select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" id="L_repass" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>
      </form>
    </div>
    <script>
       

          //监听提交
          form.on('submit(add)', function(data){
        	  
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
            	document.getElementById('deptForm').submit();
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
               
            });
            return false;
          });

    </script>
    
  </body>

</html>