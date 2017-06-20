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
<title>分类属性发布</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="height:700px">

	<div data-options="region:'north',split:true,iconCls:'icon-tip',title:'选择商品'" style="height:100px">
	<br>
		<select class="easyui-combobox" name="flbh1" id="attr_publish_class_1_select" >
		</select>
		<select class="easyui-combobox" name="flbh2" id="attr_publish_class_2_select" >
		</select>
		<br>
		</div>
		<div data-options="region:'center'">
		<div id = "attr_publish_inner">
			二级分类列表
		</div>
		
		<hr>
		<a href="javascript:;" onclick="attr_publish_goto_attr_add()">添加分类属性</a>
	<script type="text/javascript">
	var class_2_id = null;
	var class_2_name = null;
	$(function(){
		$("#attr_publish_class_1_select").combobox({
			method:"get",
			url:"js/json/class_1.js",
			textField:'flmch1',
			valueField:'id',
			value:'请选择',
			width:100,
			onSelect:function(){
				var class_1_id = $(this).combobox('getValue');
				$("#attr_publish_class_2_select").combobox({
					method:"get",
					url:"js/json/class_2_"+class_1_id+".js",
					textField:'flmch2',
					valueField:'id',
					value:'请选择',
					width:100,
					onSelect:function(){
						class_2_id = $(this).combobox('getValue');
						class_2_name = $(this).combobox('getText');
						$("#attr_publish_inner").datagrid({
							method:"post",
							url:"get_attr_by_class_2_id_json.do",
							queryParams:{"class_2_id":class_2_id},
							columns:[[    
							            {field:'shxm_mch',title:'属性名',width:100},    
							            {field:'shfqy',title:'是否启用',width:100},    
							            {field:'chjshj',title:'创建时间',width:150,
							            	formatter: function(value,row,index){
							            		var d = new Date(value);
							    				return d.toLocaleString();
							    			}	
							            },  
							            {field:'list_value',title:'属性值',width:400,
							            	formatter: function(value,row,index){
							            		// alert(row);
							            		// {"shxzh":"ssfd"}
							            		//console.debug(row);
							            		var valueStr = "";
							            		$(value).each(function(i,json){
							            			valueStr = valueStr+"/"+json.shxzh + json.shxzh_mch;
							            		});
							    				return valueStr;
							    			}	
							            }
							        ]]    
						});	
					}
				});
			}
		});
	});
	
	
	function attr_publish_goto_attr_add(){
		
		if(class_2_id==null||class_2_name==null){
			alert("请选择商品分类");
		}else{
			window.open("goto_attr_add/"+class_2_id+"/"+class_2_name+".do");
		}
		
	}
</script>	
</body>
</html>