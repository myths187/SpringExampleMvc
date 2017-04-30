<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.app.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!DOCTYPE html>

<html>

<head>
<title>TroubleShooting Desk</title>
<link rel="icon" href="/SpringExampleMvc/images/icon2.png"
	type="image/png" sizes="16x16">
    <style>
div.background {
	background: url("/SpringExampleMvc/images/icon17.jpg");
	border: 2px solid black;
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

div.box {
	margin: 30px;
	background-color: #ffffff;
	border: 1px solid black;
	opacity: 0.8;
}

div.box p {
	margin: 4%;
	font-weight: bold;
	color: #000000;
}

#h1 {
	font-size: 50px;
	font-family: algerian;
	color: #228B22;
	text-shadow: 3px 3px 7px grey;
	text-align: center;
}

div.box input[type=text] {
	width: 70%;
	padding: 12px 20px;
	height: 50px;
	margin: 20px;
	font-size: 18pt;
	box-sizing: border-box;
	border: 2px solid blue;
	background-color: lightgrey;
}

input[type=submit] {
	background-color: #6495ED;
	border: 2px solid grey;
	border-radius: 10px;
	color: white;
	padding: 5px 10px;
	text-align: center;
	display: inline;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="background">
		<div class="box">
			<h1 id="h1">
				<img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50" />Welcome
				to TroubleShooting Desk!
			</h1>

			<h1>
				<img src="/SpringExampleMvc/images/icon1.png" width="100px"
					height="100px" display="inline">Welcome &nbsp; &nbsp;<%=session.getAttribute("name")%>!!
			</h1>

			<table align="center" border="0">
				<tr>
					<th>UserName</th>
					<th>Delete</th>
				</tr>
				<%
					List list = (List) request.getAttribute("user");
					for (int i = 0; i < list.size(); i++) {
						User a = (User) list.get(i);
				%>

				<tr>
					<b>
						<td><c:out value="<%=a.getUserName()%>" /> <br /></td>
						<td><a
							href="<%=request.getContextPath()%>/TroubleShootingDesk/deleteUser.html?wanted=<%=a.getUserName()%>">delete</a>
					</td>
					</b>
				</tr>
				<%
					}
				%>
			</table>

		</div>
	</div>
</body>
</html>