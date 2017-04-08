<%@page import="javax.xml.crypto.Data"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*,beans.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校园环境检测</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table.hovertable{
			font-family: verdana.arial,sans-serif;
			font-size: 13px;
			color:#333333;
			border-width: 1px;
			border-color: #999999;
			border-collapse: collapse;
		}
		
		table.hovertable th{
			background-color: #c3dde0;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #a9c6c9;
		}
		
		table.hovertable tr{
			background-color: #d4e3e8;
		}
		
		table.hovertable td{
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #a9c6c9;
		}
	</style>
	
	
  </head>
  
  <body>
   
    <center><table class="hovertable">
    	<tr>
    		<th colspan="5">校园环境</th>    	
    	</tr>
    
    	<tr>
    		<th>节点号</th>
    		<th>温度（℃）</th>
    		<th>相对湿度（%）</th>
    	<!--  	<th>烟雾浓度</th>-->
    		<th>时间</th>
    		  
    	</tr>
 
      <% 
    	List list = null;
    	if(session.getAttribute("NODE")!=null){
    		list=(List)session.getAttribute("NODE");
    		
    		if(list.size()>0){
    			
    		
    			Node nd;
    			for(int i=0;i<list.size();i++){
    				nd=new Node();
    				nd=(Node)list.get(i);
    				
    				

    	%>
	    	    <tr onmouseover="this.style.backgroundColor='#ffff66';"
		    		onmouseout="this.style.backgroundColor='#d4e3e5';">
		    		<td><%=nd.getNumid() %></td>
    				<td><%=nd.getTemperature() %></td>
    				<td><%=nd.getHumidity() %></td>
    			<!--  <td><%//=nd.getConcentration() %></td>-->
    				<td><%=nd.getDate() %></td>
		    	</tr>		

    	<%
    				
    		}	
    		
    		}
  	
    	}
    	
   
    %>
    </table></center>
     <br>
     <form action="servlet/ShowReport" method="post">
    	<center><input type="submit" value="最新数据"></center>
    </form>
    <form action="temp.jsp" method="post">
    	<center><input type="submit" value="历史温度"></center>
    </form>
 <center> <%SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");%>
   <p>现在时间: <%=sdf.format(new Date()) %></p></center>

  </body>
</html>
