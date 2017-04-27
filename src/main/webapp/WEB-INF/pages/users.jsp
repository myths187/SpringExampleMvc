<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.app.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<table align="center" border="0">
			<tr>		<th>UserName </th>
							<th> Delete </th>
</tr>
					<%
					List list = (List) request.getAttribute("user");
								for (int i = 0; i < list.size(); i++) {
									User a = (User) list.get(i);
							%>
				
					<tr>
						<td> <c:out
								value="<%=a.getUserName() %>" /> <br /></td>
								<td><a href="<%=request.getContextPath()%>/TroubleShootingDesk/deleteUser.html?wanted=<%=a.getUserName() %>" >delete</a>
								</td>
					</tr>
					<% 
								
								} %>
				</table>


</body>
</html>