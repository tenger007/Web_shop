<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<link rel="shortcut icon" type="images/icon" href="images/jd.ico">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		//读取一级分类js数据
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#index_class_1_select").append("<li onmouseover = 'index_select_class_2_by_class_1_id(this.value)' value="+json.id+"><a href='javascript:;'>"+json.flmch1+"</a></li>");
			});
		});
		
		//加载到一级分类的下拉列表中
	});
	
	function index_select_class_2_by_class_1_id(class_1_id){
		// 获得被选中的一级分类id
		// var class_1_id = $("#index_class_1_select option:selected").val();
		
		// 根据一级分类id加载二级分类和商品信息
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#index_class_2_select").empty();
			$(data).each(function(i,json){
				$("#index_class_2_select").append("<li  style='width:80px' value="+json.id+"><a href='goto_search_index/"+json.id+"/"+json.flmch2+".do' target='_blank'>"+json.flmch2+"</a></li>");
			});
			$("#index_class_2_select").show();
		});
		
	}
	function class2_id_hide(){
		$("#index_class_2_select").hide();
		
	}
	function class2_id_show(){
		$("#index_class_2_select").show();
		
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城首页</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${user!=null}">
		    	<a href="javascript:;" >欢迎登录,${user.yh_nch}</a>  <a href="goto_logout.do" target="_blank">登出</a>
		    </c:if>
		    <c:if test="${user==null}">
		    	<a href="goto_regist.do" target="_blank">请注册</a>   <a href="goto_login.do" target="_blank">登录</a>
		    </c:if>
		</div>
	</div>
	<div>
		<img src="images/top_img.jpg" alt="">
	</div>
	
	<div class="search">
		<div class="logo"><img src="images/logo.jpg" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		<jsp:include page="sale_mini_shoppingCar.jsp"/>	
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul id="index_class_1_select">
							<li>
								<div class="two_nav" id="index_class_2_select" onmouseout="class2_id_hide()" onmouseover="class2_id_show()">
										
								</div>
							</li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="banner">
		<div class="ban">
			<img src="images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>

</body>
</html>
