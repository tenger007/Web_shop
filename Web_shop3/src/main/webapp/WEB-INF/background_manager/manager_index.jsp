<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	
	$(function(){	
		var url = "${url}"+"/success.do";
		var title = "${title}";
		if(url!=1&&title!=1){
			index_add_tabs(url,title);
		}
	});

	function index_add_tabs(url,title){
		
		if(url!=""&&title!=""){
			if(!$('#tt').tabs('exists',title)){
				
				$('#tt').tabs('add',{
					title: title,
					closable:true,
					href:url
				});
			}
		}
	}
	
 /* 	function index_add_tabs_2(){
		$.get("goto_spu_publish/.do",function(data){
			$('#tt').tabs('add',{
				title: '新选项卡面板',
				closable:true,
				content:data
			});
		});
	}  */
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
	
		<ul  class="easyui-tree">
			<li>
				<span>系统管理</span>
				<ul>
					<li><a href="javascript:index_add_tabs('goto_spu_publish/.do','spu发布管理');" >spu发布管理</a></li>
					<li><a href="javascript:index_add_tabs('goto_attr_publish/.do','属性发布管理')" >属性发布管理</a></li>
					<li><a href="javascript:index_add_tabs('goto_sku_publish/.do','sku发布管理')" >sku发布管理</a></li>
				</ul>
			</li>
			<li>
				<span>缓存管理</span>
				<ul>
					<li><a href="javascript:;" >分类检索缓存管理</a></li>
					<li>用户缓存管理</li>
				</ul>
			</li>
		</ul>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tt" class="easyui-tabs" >   
 
		</div>  
	</div>

	
</body>
</html>