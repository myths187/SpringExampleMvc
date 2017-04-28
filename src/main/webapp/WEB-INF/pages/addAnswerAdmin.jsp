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
			<form action="<%=request.getContextPath()%>/TroubleShootingDesk/addAnswers.html" method="post">
				<h1>
					<img src="/SpringExampleMvc/images/icon1.png" width="100px" height="100px">Update
					a Row Here
				</h1>

				<p>The Question Selected is :</p>
				<input type="text" value="<%=session.getAttribute("question")%>"
					name="question" width="100" readonly> <br />
				<p>The Relevant Solution is :</p>
				<input type="text" name="answer" width="100"> <br />
			
				<input type="submit" value="submit" /> <b>
				<a
					href="<%=request.getContextPath()%>/TroubleShootingDesk/back.html">back </a>
					 <a
					href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html">
						Logout</a>
				</b>
			</form>
		</div>
	</div>


</body>
</html>