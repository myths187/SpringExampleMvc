<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TroubleShooting Desk</title>
<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<style>
div.background {
	background: url("/SpringExampleMvc/images/icon26.jpg");
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
  div.box p {
        margin: 9%;
        font-weight: bold;
        color: #000000;
        font-size: 18px;
    }
</style>
</head>
<body>
<div class="background">
		<div class="box">
			<h1 id="h1">
				<img src="/SpringExampleMvc/images/icon6.jpg" width="50"
					height="50" />Welcome to TroubleShooting Desk!
			</h1>
<p>
		<%
		String ans = (String)request.getAttribute("answer");
						if (ans != null) {
					%>
		<img src="/SpringExampleMvc/images/icon8.png" width="50px" height="50px"/><c:out value="<%= ans%>" />
		<%
						}
					%>
	</p>
<h4><a href="<%=request.getContextPath()%>/UserTroubleShootingDesk/back.html"> back </a></h4>
</div>
</div>


</body>
</html>