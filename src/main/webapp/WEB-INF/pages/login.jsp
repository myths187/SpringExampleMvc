<jsp:useBean id="form" class="com.app.controller.MainClass">
	<jsp:setProperty name="form" property="*" />
</jsp:useBean>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>TroubleShooting Desk</title>
<link href="/SpringExampleMvc/styles.css" rel="stylesheet" />
<link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png"
	sizes="16x16">
<script>
	function validateForm() {
	   
		var uname = document.getElementById("id1").value;
		var pwd = document.getElementById("id2").value;
		if ((uname == null) || (uname == "")) {
			var a = document.getElementById("span1")
			a.innerHTML = "Enter valid username";
			return false;
		} else 
			if ((pwd == null) || (pwd == "")) {
				var b = document.getElementById("span2")
				b.innerHTML = "Enter valid password";
				var a = document.getElementById("span1").value;
				a.innerHTML = "";
				return false;
			}
			
		else{
			return true;
		}
	}
</script>

</head>


<body>
	<form action="<%= request.getContextPath() %>/TroubleShootingDesk/Login.html" method="post" onsubmit=" validateForm();">
		<div class="background1">
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
						<table align="center" cellpadding="10" border="0">
							<tbody>
								<tr>
									<th rowspan=2><img src="/SpringExampleMvc/images/image15.png"
										width="100" height="100"></th>

									<th>Enter User Name :</th>

									<td><input id="id1" type="text" name="username"
										placeholder="enter username" /> <span id="span1"></span>
									<td rowspan=3><img src="/SpringExampleMvc/images/icon19.jpg"></td>

									</td>
								</tr>
								<tr>
									<th>Enter Password :</th>
									<td><input id="id2" type="password" name="password"
										placeholder="Password" /> <span id="span2"></span></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit" name="submit"/> <br />
										<a href="<%= request.getContextPath()%>/UserTroubleShootingDesk/getRegister.html">New User</a></td>
								</tr>

							</tbody>
						</table>
			</div>
		</div>
	</form>
</body>

</html>
