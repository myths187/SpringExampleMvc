<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
<title>TroubleShooting Desk</title>
<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<link href="/SpringExampleMvc/styles.css" rel="stylesheet" />
<style>
div.background {
	background: url("/SpringExampleMvc/images/icon27.jpg");
	border: 2px solid black;
	 background-repeat: no-repeat;
	  background-size: 100% 100%;
}
</style>
</head>

<body>

	<form:form method="POST" action="/SpringExampleMvc/UserTroubleShootingDesk/register.html" commandName="loginFormBackingObject">
		<div class="background2">
			<div class="box">
				<h1 id="h1">
					<img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50" />Welcome
					to TroubleShooting Desk!
				</h1>
				

						<table align="center" cellpadding="4" border="0">
							<tbody>
								<tr>
									<th colspan=2>Select one of the following roles:</th>
								
								<td>
								<form:radiobutton path="auth" value="user"/> User &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<form:radiobutton path="auth" value="admin"/> Admin<span><form:errors path="auth" cssStyle="color: #ff0000;" /></span>
								</td>
								<tr>
									<th rowspan=5><img src="/SpringExampleMvc/images/icon16.png"
										width="100" height="100"></th>
									<th>Enter First Name :</th>
									<td><form:input  path="firstName"
										placeholder="First Name"  /><span><form:errors path="firstName" cssStyle="color: #ff0000;" /></span></td>
									<th>Enter Last Name :</th>
									<td><form:input path="lastName"
										placeholder="Last Name" /><span><form:errors path="lastName" cssStyle="color: #ff0000;" /></span></td>
								</tr>
								<tr>
									<th>Enter User Name :</th>
									<td><form:input path="userName"
										placeholder="enter username"  /><span><form:errors path="userName" cssStyle="color: #ff0000;" /></span></td>
								</tr>
								<tr>
									<th>Enter Email id :</th>
									<td><form:input  path="email"
										placeholder="example@mail.com" /><span><form:errors path="email" cssStyle="color: #ff0000;" /></span></td>
								</tr>
								<tr>
									<th>Enter Password :</th>
									<td><form:input type="password" id="password" path="password"
										placeholder="Password" /><span><form:errors path="password" cssStyle="color: #ff0000;" /></span></td>
								</tr>
																<tr>
									<td colspan="5" align="center"><input type="submit" value="submit" /></td>
								</tr>
							</tbody>
						</table>
			</div>
		</div>
	</form:form>
</body>

</html>
