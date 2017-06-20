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
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="shortcut icon" type="image/icon" href="image/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function search_index_get_sku_by_attr_value_and_order_tm(){
		var class_2_id = ${class_2_id};
		var order = $("#search_index_order").val();
		var parm = "";
		$("#search_index_up input").each(function(i,json){
			var pjson = $.parseJSON(json.value);
			parm = parm + "list_attr_value["+i+"].shxm_id="+pjson.shxm_id+"&list_attr_value["+i+"].shxzh_id="+pjson.shxzh_id+"&";
		});
		parm = parm + "class_2_id="+class_2_id+"&order="+order;
	     $.ajax({
			type:"post",
			url:"search_index_attr_value.do",
			data:parm,
			success:function(data){
				$("#search_idnex_inner").html(data);
			}
		});
	}
	
	function search_index_show_attr_value_up(shxzh_shxzh_mch,attr_id,value_id){
		// 将被选择分类属性和属性值id，存储到一个特定区域，在上面显示被选择的分类属性和属性值名
		var a = "<div id = 'search_index_up_inner_"+attr_id+"' class='filter_div'>";
		var b = "<a href='javascript:search_index_show_attr_value_down("+attr_id+");'>"+shxzh_shxzh_mch+"</a>";
		var c = "<input type='hidden' value='{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+value_id+"}' />";
		var d = "</div>";
		$("#search_index_up").append(a+b+c+d);
		$("#search_index_attr_list_"+attr_id).hide();
		search_index_get_sku_by_attr_value_and_order_tm();
		
	}
	
	function search_index_show_attr_value_down(attr_id){

		$("#search_index_up_inner_"+attr_id).remove();
		
		$("#search_index_attr_list_"+attr_id).show();
		
		search_index_get_sku_by_attr_value_and_order_tm();
	}
	
	function search_index_order(order){
		
		var old_order = $("#search_index_order").val();
		
		if(old_order == order ){
			order = order + " desc";
		}
		
		$("#search_index_order").val(order);
		
		search_index_get_sku_by_attr_value_and_order_tm();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城检索页</title>
</head>
<body>
<input id="search_index_order" type="hidden" name="order" value="order by jg"/>
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
	<div class="top_img">
		<img src="image/top_img.jpg" alt="">
	</div>
	
	<div class="search">
		<div class="logo"><img src="image/logo.jpg" alt=""></div>
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



	<div class="filter" id = "search_index_up">
		<h2>${class_2_name} </h2>
		<span class="gt">&gt</span>
	</div>
	
	<div class="Sscreen">
		<div class="title">
			平板电视 商品筛选 共1205个商品
		</div>
		<div class="list">
			<span>品牌：</span>
			<c:forEach items="${list_tm}" var="tm">
				<a href="javascript:;">${tm.ppmch}</a>
			</c:forEach>
		</div>
		

		<c:forEach items="${list_object_attr}" var="object_attr">

			<div class="list"  id="search_index_attr_list_${object_attr.id}">
				<span>${object_attr.shxm_mch}：</span>
				<c:forEach items="${object_attr.list_value}" var="val">
					<a href="javascript:search_index_show_attr_value_up('${val.shxzh}${val.shxzh_mch}',${object_attr.id},${val.id});">${val.shxzh}${val.shxzh_mch}</a>
				</c:forEach>
			</div>	
		</c:forEach>
		<div class="list">
		

			<span class="list_span">
				<a href="javascript:search_index_order( 'order by jg');">价格 
			   </a> 
			</span>
			<span class="list_span"><a href="javascript:search_index_order( 'order by sku_xl');">销量 </a>  
			</span>
			<span class="list_span"><a href="javascript:search_index_order( 'order by chjshj');">上架时间</a> 
			</span> 

		</div>
	</div>
	<div id="search_idnex_inner">
		<jsp:include page="sale_search_index_inner.jsp"></jsp:include>
	</div>


	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="image/foot.jpg" alt=""></div>
	</div>



	<div >
		
	</div>
	
	
 


</body>
</html>