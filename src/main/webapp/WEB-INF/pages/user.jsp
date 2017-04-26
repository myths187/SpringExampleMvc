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
<%
String name = null;
if(session.getAttribute("name") == null){
	response.sendRedirect("login.jsp");
}else name = (String) session.getAttribute("name");


%>
<form action ="<%=request.getContextPath()%>/UserTroubleShootingDesk/choice.html" method="post">
    <!-- need to define ajax calls and js code -->
    <div class="background">
        <div class="box">
         <h1 id="h1"> <img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50"/>Welcome to TroubleShooting Desk! </h1>
         
            <h1><img src="/SpringExampleMvc/images/icon1.png" width="100px" height="100px" display="inline">Welcome &nbsp; &nbsp;<%=name %>!!</h1>
               
               <p><img src="/SpringExampleMvc/images/icon12.png" height="75px" width="75px">Choose the following search options : </p>
            <div id="div1">
            <p>
            <table align="center" border="0" padding="10">
            <tr>
            <td> <input type="radio" id="HardWare" name="choice" value="user_hardware" checked> </td>
            <td><img src="/SpringExampleMvc/images/icon23.png" width="90px" height="90px" /> </td>
            <td></td>
            <td> <input type="radio" id="SoftWare" name="choice" value="user_software"></td>
            <td><img src="/SpringExampleMvc/images/icon10.png" width="90px" height="90px" /> </td>
            <td></td>
            </tr>
            <tr>
            <td colspan="3"> Hardware </td>
            <td colspan="3"> Software </td>
            </tr>
            <tr>
            <td colspan="3"> <p> </p> </td>
            <td colspan="3"> <p> </p> </td>
            </tr>
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
