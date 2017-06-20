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
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/css.css">	
<link rel="stylesheet" href="css/style.css">
</head>
<style type="text/css">
	td{vertical-align: middle !important;}
	.form-group{padding: 5px 0;}
</style>
<body>
	<div class="Cbox" id="shopping_car">
	<table class="table table-striped table-bordered table-hover">
	   <thread>
	   <tr>
	    <th>商品图片</th>
		       <th>商品名称</th>
		       <th>商品价格</th>
		       <th>商品数量</th>
		       <th>操作</th>
		     </tr>
		   </thread>
		   <tbody>
		<c:forEach items="${list_car}" var="car">
		<tr>
			<td>
			<input type="checkbox" ${car.shfxz==1?"checked":""} onchange="shoppingCar_change_car_shfxz(${car.id},${car.sku_id},this.checked)"/>
		<img width="70px" height="70px" src="upload/${car.shp_tp}"/></td>
				<td><span>
					${car.sku_mch}
				</span></td>
				<td><span>
					<b>${car.sku_jg}</b>
				</span></td>
				<td><span>
					<a href="javascript:shoppingCar_change_car_tjshl(${car.id},${car.sku_id},${car.tjshl-1});"><img src="images/btn_unfold.gif"/></a><b>&nbsp;&nbsp;${car.tjshl}&nbsp;&nbsp;</b><a href="javascript:shoppingCar_change_car_tjshl(${car.id},${car.sku_id},${car.tjshl+1});"><img src="images/btn_fold.gif"/></a>
				</span></td>
				<td>删除</td>
				</tr>
		</c:forEach>
		</tbody>
		</table>
		<div class="gobottom">
			共<span>${count }</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
			共计￥<span>${sum}</span>
			<button onclick="window.location='check_order.do'" class="goprice">去结算</button>
		</div>
	</div>
</body>
</html>