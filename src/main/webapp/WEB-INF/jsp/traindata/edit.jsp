<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>文件编辑页面</title>
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
		$("#deptForm").submit(function(){
		
			var title = $("#title");//标题
    		var remark= $("#remark");//描述
	        var fileInput = $('#file').get(0).files;
	        var msg = "";
	    if(!/^[\u4E00-\u9FA5]{3,15}$/.test($.trim(title.val()))){
	    	msg = "标题格式不正确！！！";
			title.focus(); 
	    }
	    else if (!/^[\u4E00-\u9FA5]{5,30}$/.test($.trim(remark.val()))){
			msg = "内容格式不正确！！！";
			remark.focus(); 
	    } 
	    else if(!fileInput){
	    	msg = "请选择上传文件！文件不能为空";
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
        <form class="layui-form" method="POST" id="deptForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/traindata/edit">
        <input type="hidden" name="id" id="id" value="${traindata.id }" >
          <div class="layui-form-item">
              <label for="title" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="title" name="title" required="" lay-verify="required"
                   placeholder="不少于3个汉字" autocomplete="off" class="layui-input" value="${traindata.title }">
              </div>
             
          </div>
        <div class="layui-form-item">
              <label for="remark" class="layui-form-label">
                  <span class="x-red">*</span>描述
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="remark" name="remark" required="" lay-verify="required"
                  placeholder="不少于5个汉字"  autocomplete="off" class="layui-input" value="${traindata.remark}">
              </div>
             
          </div>
         <div class="layui-form-item">
              <label for="file" class="layui-form-label">
                  <span class="x-red">*</span>上传文件
              </label>
              <div class="layui-input-inline">
                  <input type="hidden" id="filename" name="filename" value="${traindata.filename}">

                  <input type="file" id="file" name="file" >

                  <p>原视频：${traindata.fileStr}<button type="button" class="layui-btn layui-btn-xs" style="margin-left: 5px;" onclick="openVideo(this,'${traindata.filename }')">可播放</button></p>

                  <p class="x-red">请重新选择上传文件</p>
              </div>
             
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" id="L_repass" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>
      </form>
    </div>
    <script type="text/javascript">
        function openVideo(obj,filename) {
            // var $filename=$(filename).parent();
            // var classVideo = $filename.text();
            // alert(classVideo);
            // classVideo = classVideo.substring(0,classVideo.length-3);
            var classVideo = filename
            alert(classVideo);
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
    <script>
       

          //监听提交
          form.on('submit(add)', function(data){
        	  
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("修改成功", {icon: 6},function () {
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