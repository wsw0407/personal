<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ page isELIgnored="false" %>

  
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>个人考勤信息列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/js/xadmin.js"></script>
    
    
    <script type="text/javascript">
    $(function(){
    	var  count = ${count}
    	if(count!=0){
    		$("#count1").hide();
    		$("#count2").show();
    	}
    	var username = "${sessionScope.user_session.loginname}";
    	if(username=="admin"||username=="manager"){
    		$("#aaa").show();  
    		$("#bbb").show();  
    		$("#do").css("display", "block"); 
    		$("#ID").show(); 
    		$('tr').find('td:eq(0)').show();
    		$('tr').find('td:eq(6)').show();
    	}else{
    		$("#aaa").show();
    		$("#bbb").hide();
    		$("#do").css("display", "none"); 
    		$("#ID").hide();
    		$('tr').find('td:eq(8)').hide();
    	};
    })
  </script>
    
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>考勤管理</cite></a>
      </span>
      <a id="aaa" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath }/checkwork/plist"  title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${pageContext.request.contextPath }/checkwork/plist">
          
          <input type="text" name="content" style="width:50%;"  placeholder="请输入查找内容" autocomplete="off" class="layui-input" value="${content}">
          <button class="layui-btn"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox"  lay-skin="primary" ><i class="layui-icon">&#xe605;</i></div>
            </th>
              <td>姓名</td>
			  <th>职位</th>
			  <th>部门</th>
			  <th>上班天数</th>
			  <th>请假天数</th>
			  <th>迟到天数</th>
			  <th>建档日期</th>

        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="checkwork" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
             <td>${checkwork.empname }</td>
             <td>${checkwork.jobname }</td>
             <td>${checkwork.deptname }</td>
             <td>${checkwork.workingdays}</td>
			 <td>${checkwork.daysleave}</td>
			 <td>${checkwork.daysout}</td>
             <td>${checkwork.creatTimeStr}</td>

          </tr>
         </c:forEach>
        </tbody>
      
      </table>
         <!-- 分页标签 -->
     <div style="margin-left: 400px;" id="pages">

     </div>

    </div>
    <script>
      
      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用
              $.get("${pageContext.request.contextPath}/checkwork/delete?id="+id);
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }
      function delAll (argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }


    </script>
    
  </body>

</html>