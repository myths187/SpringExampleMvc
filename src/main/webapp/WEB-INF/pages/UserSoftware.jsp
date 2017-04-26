<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.model.QuestionGetter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TroubleShooting Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<style>
div.background {
	background: url("/SpringExampleMvc/images/icon28.jpg");
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

.right {
	position: relative;
	right: 0px;
	width: 140px;
	border: 3px solid #0000FF;
	padding: 10px;
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
	<form
		action="<%= request.getContextPath() %>/UserTroubleShootingDesk/answer.html"
		method="post">
		<div class="background">
			<div class="box">
			   <h1 id="h1"> <img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50"/>Welcome to TroubleShooting Desk! </h1>
				<h1>The Software Questions are:</h1>
				<h2>please select a question from the following to obtain the
					answer:</h2>
				<table align="center" border="0">
					<%
					List list = (List) request.getAttribute("question");
								for (int i = 0; i < list.size(); i++) {
									QuestionGetter a = (QuestionGetter) list.get(i);
							%>

					<tr>
						<td><input type="radio" name="wanted"
							value="<%= a.getQuestion() %>"> &nbsp; &nbsp; <c:out
								value="<%=a.getQuestion() %>" /> <br /></td>
					</tr>
					<% 
								
								} %>
				</table>
	</form>
	<p>
		<%
		String ans = (String)request.getAttribute("answer");
						if (ans != null) {
					%>
		<c:out value="<%= ans%>" />
		<%
						}
					%>
	</p>

	<h3>
		<input type="submit" value="submit" />
	</h3>
	<h3>
		<a href="<%=request.getContextPath()%>/UserTroubleShootingDesk/back.html">Back</a>
	</h3>
	<h3>
		<a
			href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html">
			Logout</a>
	</h3>
</body>
</html>