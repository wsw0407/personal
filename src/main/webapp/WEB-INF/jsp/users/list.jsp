<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%--<%@ taglib uri="/pager-tags" prefix="fkjava" %>--%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>用户信息</title>
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
        var  count = ${count};
        if( count != 0 )
        {
            $("#count1").hide();
            $("#count2").show();
        }
      })
    </script>
   
    
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>用户信息</cite></a>
      </span>
      <button type="button" onclick="location.href='${pageContext.request.contextPath}/user/toadd'" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:inherit;margin-left:75%;;"  ><i class="layui-icon"></i>增加</button>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath}/user/list" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${pageContext.request.contextPath}/user/list">
          <input type="text" name="content" style="width:50%;"  placeholder="请输入查找内容" autocomplete="off" class="layui-input" value="${content}">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <!-- <th>ID</th> -->
            <th>登录名</th>
            <th>用户名</th>
            <th>状态</th>
            <th>注册日期</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="user" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            
            <%-- <td>${user.id}</td> --%>
            <td width="165px">${user.loginname }</td>
            <td width="150px">${user.username }</td>
            <td>
					 <c:choose>
					      <c:when test="${user.statusId==1}">已审核</c:when>
					      <c:otherwise><font><a style="color:red">未审核</a></font></c:otherwise>
					 </c:choose>
					  
			</td>
            <td><f:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${user.createdate}" type="both"/></td>
          
            <td class="td-manage">
             <%--  <a title="编辑"  onclick="x_admin_show('编辑用户信息','${pageContext.request.contextPath}/user/edit?id=${user.id}');" href="javascript:;"> --%>
             <a title="编辑"  href='${pageContext.request.contextPath}/user/toedit?id=${user.id}'>
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'${user.id }')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
		</c:forEach>
      </tbody>
    </table>
         <%--分页标签--%>
        <div style="margin-left: 230px;" id="pages">
        </div>



    </div>
    <script type="text/javascript">

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用  好处：就是快    坏处：万一后台删除出问题 没有删掉 
              $.get("${pageContext.request.contextPath}/user/delete?id="+id);
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


      layui.use('laypage', function(){
          var laypage = layui.laypage;

          //执行一个laypage实例
          laypage.render({
              elem: 'pages' //注意，这里的 test1 是 ID，不用加 # 号
              ,count: ${count} //数据总数，从服务端得到
              ,curr:${pageNo}
              ,limit:${pageSize}
              ,first:1
              ,last:${pages}
              ,layout:["first","prev","page","next","count","limit","skip","last"]
              ,limits:[5,10,15,20,15,30]
              ,jump: function(obj, first){

                  console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                  console.log(obj.limit); //得到每页显示的条数

                  //首次不执行
                  if(!first){
                      var content = "${content}"
                      if(content != null && content != ''){
                          location.href = "${pageContext.request.contextPath}/user/list?pageNo="+obj.curr+"&pageSize="+obj.limit+"&content="+content
                      }else {
                          location.href = "${pageContext.request.contextPath}/user/list?pageNo="+obj.curr+"&pageSize="+obj.limit
                      }
                  }
              }
          });
      });
    </script>
    
  </body>

</html>