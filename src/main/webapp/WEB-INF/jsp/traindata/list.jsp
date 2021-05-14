<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>培训资源</title>
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
    	/** 下载文档功能 */
    	/*$("a[id^='down_']").click(function(){
    		/!** 得到需要下载的文档的id *!/
    		var filename = this.id.replace("down_","");
    		/!** 下载该文档 *!/
    		window.location = "${pageContext.request.contextPath}/traindata/downLoad?filename="+filename;
    	})*/


        $("a[id^='down_']").click(function(){
            /** 得到需要下载的文档的   id  */
            var filename = this.id.replace("down_","");
            /** 下载该文档 */

            $.ajax({
                method:'get',
                url:'${pageContext.request.contextPath}/traindata/filesExists?filename='+filename,
                dataType:'text',
                success:function(data){
                    alert(data)
                    if (data == "true"){
                        window.location = "${pageContext.request.contextPath}/traindata/downLoad?filename="+filename;
                    }else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('文件丢失!下载失败',{icon:2,time:1500});
                        });

                    }
                }
            });
        })
    })
    </script>
    
    <script type="text/javascript">
    $(function(){
        var count = ${count}
    	if(count != 0){
    		$("#count1").hide();
    		$("#count2").show();
    	}
    	var username = "${sessionScope.user_session.loginname}";
    	if(username=="admin"||username=="manager"){
    		$("#aaa").show(); 
    		$("#bbb").show(); 
    		$("#do").css("display", "block"); 
    		$("#ID").css("display", "block"); 
    		$('tr').find('td:eq(0)').show();
    		$('tr').find('td:eq(8)').show();
    	}else{
    		$("#aaa").hide();
    		$("#bbb").hide(); 
    		$("#do").css("display", "none"); 
    		$("#ID").css("display", "none");
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
          <cite>培训资源</cite></a>
      </span>
            <button id="aaa" type="button" onclick="location.href='${pageContext.request.contextPath}/traindata/toadd'" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:inherit;margin-left:75%;;"  lay-submit="" lay-filter="sreach"><i class="layui-icon"></i>增加</button>
      
      <a id="bbb" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${pageContext.request.contextPath }/traindata/list" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${pageContext.request.contextPath }/traindata/list">
          <input type="text" name="content" style="width:50%;"  placeholder="请输入查找内容" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      
     
      
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>标题</th>
            <th>描述</th>
            <th>文件名</th>
            <th>发布日期</th>
            <th>发布用户</th>
            <th>下载</th>
            <th id="do">操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="dept" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox"  lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            
            <td>${dept.id}</td>
            <td>${dept.title }</td>
            <td>${dept.remark }</td>
            <td id="videourl">${dept.fileStr}<button class="layui-btn layui-btn-xs" style="margin-left: 5px;" onclick="openVideo(this,'${dept.filename }')">可播放</button></td>
            <td>${dept.creatTimeStr}</td>
            <td>${dept.username }</td>
            <td align="center"  width="40px;"><a href="#" id="down_${dept.filename }">
							<img width="20" height="20" title="下载" src="${pageContext.request.contextPath }/public/images/downLoad.png"/></a>
					  </td>
            <td class="td-manage">
              
              <a title="编辑"  href='${pageContext.request.contextPath}/traindata/toedit?id=${dept.id}'>
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'${dept.id }','${dept.filename}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
          
            <script type="text/javascript">
    function openVideo(obj,filename) {
    	// var $filename=$(filename).parent();
        // var classVideo = $filename.text();
        // alert(classVideo);
        // classVideo = classVideo.substring(0,classVideo.length-3);
        var classVideo = filename
        //alert(classVideo);
        var index = layer.open({
            type: 2,
            content: '${pageContext.request.contextPath}/traindata/play?filename='+classVideo,  //  视频播放页面
            area: ['600px', '450px'],
            offset:'t',
            maxmin: true,
            end: function () {

            }
        });
    }
</script>
			</c:forEach>
        </tbody>
      </table>
         <!-- 分页标签 -->
     <div style="margin-left: 400px;" id="pages">
     </div>

    </div>
   
   
    <script>
      /*用户-删除*/
      function member_del(obj,id,filename){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用
              $.get("${pageContext.request.contextPath}/traindata/delete?id="+id+"&filename="+filename);
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
                          location.href = "${pageContext.request.contextPath}/traindata/list?pageNo="+obj.curr+"&pageSize="+obj.limit+"&content="+content
                      }else {
                          location.href = "${pageContext.request.contextPath}/traindata/list?pageNo="+obj.curr+"&pageSize="+obj.limit
                      }
                  }
              }
          });
      });

      function refresh() {
          var content = "${content}"
          var pageNo =${pageNo}
          var pageSize =${pageSize}
          if(content != null && content != ''){
              location.href = "${pageContext.request.contextPath}/traindata/list"
          }else {
              location.href = "${pageContext.request.contextPath}/traindata/list?pageNo="+pageNo+"&pageSize="+pageSize
          }
      }

    </script>
    
  
  </body>

</html>