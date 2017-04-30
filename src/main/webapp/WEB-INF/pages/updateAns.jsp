<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TroubleShooting Desk</title>
<link href="/SpringExampleMvc/styles.css" rel="stylesheet" />
<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<style>
div.background {
	background: url("/SpringExampleMvc/images//icon26.jpg");
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
	margin: 2%;
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



input[type=submit] {
	background-color: #6495ED;
	border: 2px solid grey;
	border-radius: 10px;
	color: white;
	padding: 20px 40px;
	text-align: center;
	display: inline;
	font-size: 16px;
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
</style>
</head>
<body>

	<div class="background">
		<div class="box">
			<h1 id="h1">
				<img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50" />Welcome
				to TroubleShooting Desk!
			</h1>
			<form action="<%=request.getContextPath()%>/TroubleShootingDesk/updateAnswers.html" method="get">
				<h1>
					<img src="/SpringExampleMvc/images/icon1.png" width="100px" height="100px">Enter the Answer here
				</h1>
				<p>The Answer Selected is :</p>
				<input type="text" value="<%=request.getAttribute("Answer")%>"
					 width="100" readonly> <br />
					 <input type="hidden" name="id" value="<%= request.getAttribute("id") %>"/>
				<p>The Updated Solution is :</p>
				<input type="text" name="answer" width="100" required> <br />
			
				<input type="submit" value="submit" /> <b>  <br/>
				<a href="<%=request.getContextPath()%>/TroubleShootingDesk/back.html">back</a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a
					href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html">
						Logout</a>
				</b>
			</form>
		</div>
	</div>


</body>
</html>