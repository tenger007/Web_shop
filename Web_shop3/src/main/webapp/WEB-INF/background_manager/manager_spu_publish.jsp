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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spu发布</title>
</head>
<body>
<form action="spu_publish.do" method="post"  enctype="multipart/form-data">
<div class="easyui-layout" data-options="fit:true" style="height:700px">

	<div data-options="region:'north',split:true,iconCls:'icon-tip',title:'选择商品'" style="height:100px">
		<br>
		<select class="easyui-combobox" name="flbh1" id="spu_publish_class_1_select" onChange="spu_publish_select_class_2_by_class_1_id(this.value)">
		</select>
		
		<select name="flbh2"  class="easyui-combobox" id="spu_publish_class_2_select" >
		</select>
	
		<select name="pp_id"  class="easyui-combobox" id="spu_publish_tm_select" >
		</select>
	</div>
	<div data-options="region:'center',split:true,iconCls:'icon-tip',title:'添加图片'" style="height:100px"">
		<div id="spu_publish_show_num"></div><br>
		商品名称:<input type="text" name="shp_mch"/><br>
		商品描述:<input type="text" name="shp_msh"/><br>
		商品图片:<br>
		<div id="spu_publish_image_append">
			<img id="img_1" width="100px" src="image/upload_hover.png" style="cursor:pointer;" onclick="spu_publish_item_click(1)">
		    <input style="display:none;" id="file_1" type="file" name="files" onChange="spu_publish_hover_show(1)"/>
		</div>
		<input type="submit" value="提交发布"/>
	</div>
</div>
</form>
	
	<script type="text/javascript">

	$(function(){
		// 3 访问class_1_id.js的静态数据是
		//$("#spu_publish_class_1_select").combobox('select','请选择');
		//读取一级分类js数据
		$("#spu_publish_class_1_select").combobox({
			method:"get",
			url:"js/json/class_1.js",
			textField:'flmch1',
			valueField:'id',
			value:'请选择',
			width:100,
			onSelect:function(){
				var class_1_id = $(this).combobox('getValue');
				$("#spu_publish_class_2_select").combobox({
					method:"get",
					url:"js/json/class_2_"+class_1_id+".js",
					textField:'flmch2',
					valueField:'id',
					value:'请选择',
					width:100
				});
				
				$("#spu_publish_tm_select").combobox({
					method:"get",
					url:"js/json/trade_mark_"+class_1_id+".js",
					textField:'ppmch',
					valueField:'id',
					value:'请选择',
					width:100
				});
			}
		});
	});
	
	function spu_publish_item_click(index){
		// 点击file对象
	    $("#file_"+index).click();		
	}
	
	function spu_publish_hover_show(index){
	    var file = $("#file_"+index);
	    
	    var url = window.URL.createObjectURL(file[0].files[0]);
	    
	    $("#img_"+index).attr("src",url);
	    
	    var length = $("#spu_publish_image_append input").length;
	    
	    $("#spu_publish_show_num").html("您还可以继续上传"+(5-index)+"张图片");
	    
	    if(index<5&&index==length){
	    	spu_publish_add_image(index);
	    }

	}
	
	function spu_publish_add_image(index){
		$("#spu_publish_image_append").append('<img id="img_'+(index+1)+'" width="100px" src="image/upload_hover.png" style="cursor:pointer;" onclick="spu_publish_item_click('+(index+1)+')">');
		$("#spu_publish_image_append").append('<input style="display:none;" id="file_'+(index+1)+'" type="file" name="files" onChange="spu_publish_hover_show('+(index+1)+')"/>');
		
	}
	
</script>
</body>
</html>