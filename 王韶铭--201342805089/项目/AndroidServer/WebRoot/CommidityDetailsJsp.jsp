<%@page import="org.model.TableCommodity"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="org.model.TableCommodity" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CommidityDetailsJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div>
  	<form action="index.jsp" method="post">
  	<table>
  	<%
  		List showList = (List)session.getAttribute("Showlist");
  		int i=0;
  		while(i<showList.size()){
  		TableCommodity tc = (TableCommodity)showList.get(0);
  	%>
  	<tr><td><%  tc.getCommodityTitle().toString(); %></td>
  	<td><%  tc.getCommodityDetail().toString(); %></td></tr>
  	<% 
  	i++;} %>
  		
  	</table>
  	</form>
  	</div>
  </body>
</html>
