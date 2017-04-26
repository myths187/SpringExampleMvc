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

	<form action="<%=request.getContextPath() %>/UserTroubleShootingDesk/register.html"  method="post">
		<div class="background2">
			<div class="box">
				<h1 id="h1">
					<img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50" />Welcome
					to TroubleShooting Desk!
				</h1>
				<%
					String[] errors = (String[]) request.getAttribute("errors");
					if (errors != null && errors.length > 0) {
				%>
				<b>Please Correct the Following Errors</b>
				<ul>
					<%
						for (int i = 0; i < errors.length; i++) {
					%>
					<li><%=errors[i]%> <%
 	}
 %> <%
 	}
 %>

						<table align="center" cellpadding="4" border="0">
							<tbody>
								<tr>
									<th colspan=2>Select one of the following roles:</th>
									<td><input type="radio" name="auth" value="admin">
										Admin <br /> <input type="radio" name="auth" value="user">
										User</td>
								<tr>
									<th rowspan=5><img src="/SpringExampleMvc/images/icon16.png"
										width="100" height="100"></th>
									<th>Enter First Name :</th>
									<td><input type="text" name="firstName"
										placeholder="First Name" /></td>
									<th>Enter Last Name :</th>
									<td><input type="text" name="lastName"
										placeholder="Last Name" /></td>
								</tr>
								<tr>
									<th>Enter User Name :</th>
									<td><input type="text" name="userName"
										placeholder="enter username" /></td>
								</tr>
								<tr>
									<th>Enter Email id :</th>
									<td><input type="email" name="email"
										placeholder="example@mail.com" /></td>
								</tr>
								<tr>
									<th>Enter Password :</th>
									<td><input type="password" id="pwd1" name="password"
										placeholder="Password" /></td>
								</tr>
																<tr>
									<td colspan="5" align="center"><input type="submit" value="submit" /></td>
								</tr>
							</tbody>
						</table>
			</div>
		</div>
	</form>
</body>

</html>
