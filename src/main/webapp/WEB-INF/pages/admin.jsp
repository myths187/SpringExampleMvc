<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.model.QuestionAndAnswer"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>TroubleShooting Desk</title>
<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<style>
input[type=button] {
	background-color: #6495ED;
	border: 2px solid grey;
	border-radius: 10px;
	color: white;
	padding: 20px 40px;
	text-align: center;
	display: inline;
	font-size: 16px;
}
div.background {
	background: url("/SpringExampleMvc/images/icon22.jpg");
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
	margin: 3%;
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

textarea {
	width: 70%;
	padding: 12px 20px;
	margin: 20px;
	font-size: 18pt;
	box-sizing: border-box;
	border: 2px solid red;
	background-color: lightblue;
}
</style>
</head>

<body>
	<%
		String name = null;
		if (session.getAttribute("name") == null) {
			response.sendRedirect("login.jsp");
		} else
			name = (String) session.getAttribute("name");
	%>
	<!-- need to define ajax calls and js code -->
	<div class="background">
		<div class="box">
			<h1 id="h1">
				<img src="/SpringExampleMvc/images/icon6.jpg" width="50"
					height="50" />Welcome to TroubleShooting Desk!
			</h1>
			<h1>
				<img src="/SpringExampleMvc/images/icon16.png" width="100px" height="100px"
					display="inline">Welcome &nbsp; &nbsp;<%=name%>!! to Admin
				Desk!
				<p>
					<img src="/SpringExampleMvc/images/icon12.png" height="75px" width="75px">Select
					the operation to be performed
				</p>
				
					<div float="right">
						<a
							href="<%=request.getContextPath()%>/TroubleShootingDesk/create.html">create
							new record</a>
					</div>



	<div>
			<table border="1px solid black" align="center">
						<tr>
							<th>Question</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
						<%
							List<QuestionAndAnswer> list = (List) session.getAttribute("Hardware");
							for (int i = 0; i < list.size(); i++) {
								QuestionAndAnswer a = (QuestionAndAnswer) list.get(i);
						%>
						<tr width="100" height="150">

							<c:forEach var="list" items="${Hardware}">
								<td><%=a.getQuestion()%></td>
								<td><a
									href="<%=request.getContextPath()%>/TroubleShootingDesk/update.html?wanted=<%=a.getQuestion()%>">update</a>
								</td>
								<td><a
									href="<%=request.getContextPath()%>/TroubleShootingDesk/delete.html?wanted=<%=a.getQuestion()%>">delete</a><br /></td>
								<br />
							</c:forEach>
							<%
								}
							%>
						</tr>


					</table>


</div>

					<%
						if (session.getAttribute("question") != null) {
					%>
					<c:out value="${question} has been updated" />
					<%
						}
					%>
					<br /> <b> <%
 	if (request.getAttribute("faliure") != null) {
 %> <%=request.getAttribute("faliure")%> <%
 	}
 %>
					</b> <b> <%
 	if (request.getAttribute("success") != null) {
 %> <%=request.getAttribute("success")%> <%
 	}
 %>
					</b> <b> <a
						href="/SpringExampleMvc/TroubleShootingDesk/logout.html">
							Logout</a>
					</b>
				</div>
		</div>
		</div>
</body>

</html>
