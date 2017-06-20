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
	function a(){}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>

	<div class="Sbox">
		<c:forEach items="${list_object_sku}" var="object_sku">
			<div class="list">
				<div class="img"><img width="100%" src="upload/${object_sku.spu.shp_tp}" alt=""></div>
				<div class="price">¥${object_sku.jg}</div>
				<div class="title"><a href="goto_sku_detail/${object_sku.id}/${object_sku.shp_id}.do" target="_blank">${object_sku.sku_mch}</a></div>
			</div>
		</c:forEach>
	</div>
		
</body>
</html>