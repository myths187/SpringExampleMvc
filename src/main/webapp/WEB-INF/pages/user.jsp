<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>TroubleShooting Desk</title>
 <link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png" sizes="16x16">
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
    #h1{
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

<form action ="<%=request.getContextPath()%>/UserTroubleShootingDesk/choice.html" method="get">
    <!-- need to define ajax calls and js code -->
    <div class="background">
        <div class="box">
         <h1 id="h1"> <img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50"/>Welcome to TroubleShooting Desk! </h1>
         
            <h1><img src="/SpringExampleMvc/images/icon1.png" width="100px" height="100px" display="inline">Welcome &nbsp; &nbsp;<%=session.getAttribute("name") %>!!</h1>
               
              <b> <%if(request.getAttribute("error") != null){  %>
					<%= request.getAttribute("error") %>
					<%} %>
					</b>
					  <b> <%if(request.getAttribute("success") != null){  %>
					<%= request.getAttribute("success") %>
					<%} %>
					</b>
					
					
					   <p><img src="/SpringExampleMvc/images/icon12.png" height="75px" width="75px">Choose the following options : </p>
            <div id="div1">
            <p>
            <table align="center" border="0" padding="10">
           
            <tr>
           <td><h3> <input type="radio" name="choice" value="question"/> Enter a Question with Answer</h3> </td>
           <td><h3><input type="radio" name="choice" value="ask"/> Ask the admin </h3></td>
           <td> <h3><input type="radio" name="choice" value="obtain"/> Find the answers from existing questions </h3></td>
           
            </tr>
            <tr>
            <td colspan="3"> <p> </p> </td>
            <td colspan="3"> <p> </p> </td>
            </tr><br/>
            <tr>
            <td colspan="3"><input type="submit" value="submit"/></td>
            <td colspan="3"> <h3> <a href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html"> Logout</a> </h3></td>

</table>

                 
    </div>
    </div>
    
    </form>
    </body>
</body>

</html>
