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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	<div class="cart_pro">
		<h6>最新加入的商品</h6>
		<c:forEach items="${list_car}" var="car">
			<div class="one border" >
				<img width="70px" height="70px" src="upload/${car.shp_tp}"/>
				<span class="one_name">
					${car.sku_mch}
				</span>
				<span class="one_prece">
					<b>${car.sku_jg}</b>
					删除
				</span>
			</div>
		</c:forEach>
		<div class="gobottom">
			共<span>${shoppingcar.total }</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
			共计￥<span>${shoppingcar.hj }</span>
			<button onclick="window.location='goto_shoppingcar.do'" class="goprice">去购物车</button>
		</div>
	</div>
	
</body>
</html>