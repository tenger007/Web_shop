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
	function mini_shoppingCar_show(){
		$.post("mini_car.do",function(data){
			$("#mini_shoppingCar_show").html(data);
		});
		$("#mini_shoppingCar_show").show();
		
	}
	function mini_shoppingCar_show2(){
		 $("#mini_shoppingCar_show").show(); 
	}
	function mini_shoppingCar_hide(){
		 $("#mini_shoppingCar_show").hide(); 
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
		<div class="card">
			<a href="javascript:;" onmouseover="mini_shoppingCar_show()" >购物车<div class="num">${shoppingcar.total} </div></a>
			<div id="mini_shoppingCar_show" style="display:none;" onmouseover="mini_shoppingCar_show2()" onmouseout="mini_shoppingCar_hide()">
				
			</div>
		</div>

</body>
</html>