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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

  function shoppingCar_change_car_shfxz(car_id,sku_id,shfxz){
	  if(shfxz){
		  shfxz="1";
	  }else{
		  shfxz="0";
	  }
	  $.post("change_car.do",{car_id:car_id,sku_id:sku_id,shfxz:shfxz,tjshl:-1},function(data){
		 $("#sale_shoppingCar_inner").html(data);
		  });
	  }
  function shoppingCar_change_car_tjshl(car_id,sku_id,tjshl){
	  $.post("change_car.do",{car_id:car_id,sku_id:sku_id,shfxz:"",tjshl:tjshl},function(data){
		  $("#sale_shoppingCar_inner").html(data);
	  })
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/css.css">	
<link rel="stylesheet" href="css/style.css">
</head>
<style type="text/css">
	td{vertical-align: middle !important;}
	.form-group{padding: 5px 0;}
</style>
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
	<div>
		<img src="./images/top_img.jpg" alt="">
	</div>
	<div class="search">
		 <div class="logo"><img src="./images/logo.jpg" alt=""></div>
		<div class="search_on" >
			<div class="se" >
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索" style="height: 32px;">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		</div>
<div id="sale_shoppingCar_inner">
		<jsp:include page="sale_shoppingCar_inner.jsp"></jsp:include>
	</div>
</body>
</html>