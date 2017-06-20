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
	<form action="login_buy.do" method="post">
		用户名：<input type="text" name="yh_mch"/><br>
		密码：<input type="text" name="yh_mm"/><br>
		用户类型：
		05级用户<input type="radio" value="05" name="db_type"/>
		06级用户<input type="radio" value="06" name="db_type"/>
		认证用户<input type="radio" value="u" name="db_type"/><br>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>