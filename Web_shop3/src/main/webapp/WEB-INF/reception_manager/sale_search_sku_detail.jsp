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
<link rel="shortcut icon" type="images/icon" href="images/jd.ico">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function a(){}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${user!=null}">
		    	<a href="javascript:;" >欢迎登录，${user.yh_nch}</a>  <a href="goto_logout.do" target="_blank">登出</a>
		    </c:if>
		    <c:if test="${user==null}">
		    	<a href="goto_regist.do" target="_blank">请注册</a>   <a href="goto_login.do" target="_blank">登录</a>
		    </c:if>
		</div>
	</div>
	<div class="search searchBac">
		<div class="logo"><img src="images/logo.png" alt=""></div>
		<jsp:include page="sale_mini_shoppingCar.jsp"/>	
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini" style="display:none">
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
	<img width="200px" src="upload/${object_sku.spu.shp_tp}">
	<hr>
	<c:forEach items="${list_sku}" var="sku">
		<a href="goto_sku_detail/${sku.id}/${sku.shp_id}.do">${sku.sku_mch}</a><br>
	</c:forEach>
	商品属性：<br>
		<c:forEach items="${object_sku.list_attr_value_name}" var="val">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${val.attr_name}:${val.value_name}<br>
	    </c:forEach>
	<form action="add_car.do" method="post" class="gobottom">
		<input type="hidden" name="sku_mch" value = "${object_sku.sku_mch}"/>
		<input type="hidden" name="sku_jg" value = "${object_sku.jg}"/>
		<input type="hidden" name="tjshl" value = "1"/>
		<input type="hidden" name="hj" value = "${object_sku.jg}"/>
		<input type="hidden" name="sku_id" value = "${object_sku.id}"/>
		<input type="hidden" name="shp_tp" value = "${object_sku.spu.shp_tp}"/>
	    <hr>评价：
	${object_sku.spu.shp_msh}
		<br>
		<button class="goprice" id="tijiao" onclick="">添加</button>
	</form>
</body>
</html>