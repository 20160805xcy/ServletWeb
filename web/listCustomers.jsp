<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath}/servlet/ControllerServlet?op=delMulti" method="post">
  	<table align="center" width="80%">
  		<tr>
  			<td>
  				<a href="${pageContext.request.contextPath}/addCustomer.jsp">添加</a>
  				<a href="javascript:delMulti()">删除</a>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				<table border="1" width="100%">
  					<tr>
						<th nowrap="nowrap"><span id="selectAll">全选</span>/<span id="deSelectAll">全不选</span>/<span id="reSelect">反选</span></th>
  						<th nowrap="nowrap">姓名</th>
  						<th nowrap="nowrap">性别</th>
  						<th nowrap="nowrap">生日</th>
  						<th nowrap="nowrap">电话</th>
  						<th nowrap="nowrap">邮箱</th>
  						<th nowrap="nowrap">爱好</th>
  						<th nowrap="nowrap">类型</th>
  						<th nowrap="nowrap">简介</th>
  						<th nowrap="nowrap">操作</th>
  					</tr>
  					
  					<c:forEach items="${cs}" var="c" varStatus="vs">
  						<tr class="${vs.index%2==0?'odd':'even' }">
	  						<td nowrap="nowrap">
	  							<input type="checkbox" name="ids" value="${c.id}"/>
	  						</td>
	  						<td nowrap="nowrap">${c.name}</td>
	  						<td nowrap="nowrap">${c.gender=='male'?'男性':'女性'}</td>
	  						<td nowrap="nowrap">${c.birthday}</td>
	  						<td nowrap="nowrap">${c.phonenum }</td>
	  						<td nowrap="nowrap">${c.email}</td>
	  						<td nowrap="nowrap">${c.hobby}</td>
	  						<td nowrap="nowrap">${c.type}</td>
	  						<td nowrap="nowrap">${fn:length(c.description)>3?fn:substring(c.description,0,3):''}...</td>
	  						<td nowrap="nowrap">
	  							[<a href="${pageContext.request.contextPath}/servlet/ControllerServlet?op=editCustomerUI&customerId=${c.id}">修改</a>]
  								[<a href="javascript:delOne('${c.id}')">删除</a>]
	  						</td>
	  					</tr>
  					</c:forEach>
  				</table>
  			</td>
  		</tr>
  	</table>
  </form>

  <script type="text/javascript">
      window.onload=function(){
          document.getElementById("selectAll").onclick=function(){
              var idsObjArray = document.getElementsByName("ids");
              for(var i=0;i<idsObjArray.length;i++){
                  idsObjArray[i].checked=true;
              }
          }
          document.getElementById("deSelectAll").onclick=function(){
              var idsObjArray = document.getElementsByName("ids");
              for(var i=0;i<idsObjArray.length;i++){
                  idsObjArray[i].checked=false;
              }
          }
          document.getElementById("reSelect").onclick=function(){
              var idsObjArray = document.getElementsByName("ids");
              for(var i=0;i<idsObjArray.length;i++){
                  idsObjArray[i].checked=!idsObjArray[i].checked;
              }
          }

      }

      function delOne(customerId){
          var sure = window.confirm("确定要删除所选记录吗?");
          if(sure){
              window.location.href="${pageContext.request.contextPath}/servlet/ControllerServlet?op=delOne&customerId="+customerId;
          }
      }
      function delMulti(){
          //确定用户有没有选中记录
          var idsObjArray = document.getElementsByName("ids");
          var selected = false;//选中标记
          for(var i=0;i<idsObjArray.length;i++){
              if(idsObjArray[i].checked){
                  selected=true;
                  break;
              }
          }
          if(!selected){
              alert("请先选择要删除的记录");
              return;
          }
          //选择了
          var sure = window.confirm("确定要删除所选记录吗?");
          if(sure){
              document.forms[0].submit();
          }
      }
  </script>

  </body>
</html>
