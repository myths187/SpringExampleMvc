<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
    <title>TroubleShooting Desk</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png" sizes="16x16">
        <style>
        div.background {
            background: url("/SpringExampleMvc/images/icon21.jpg");
            border: 2px solid black;
             background-repeat: no-repeat;
              background-size: 100% 100%;
        }
        
        div.box {
            margin: 30px;
            background-color: #ffffff;
            border: 1px solid black;
            opacity: 0.7;
        }
        
        div.box p {
            margin: 2%;
            font-weight: bold;
            color: #000000;
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
        <title>Insert title here</title>
    </head>

    <body>
        <form action="<%=request.getContextPath()%>/UserTroubleShootingDesk/ask.html" method="post">
            <div class="background">
                <div class="box">
                <h1 id="h1">
					<img src="/SpringExampleMvc/images/icon6.jpg" width="50" height="50" />Welcome
					to TroubleShooting Desk!
				</h1>
                <h1>
					<img src="/SpringExampleMvc/images/icon1.png" width="100px" height="100px"
						display="inline">Enter a New Record Here!!</h1>
                <p>
                <b> <%if(request.getAttribute("success") != null){  %>
					<%= request.getAttribute("success") %>
					<%} %>
					</b>
                
                    <h3> Write your question here</h3>
                    <input type="text" name="question" />
                    <br/>
                    </p>
                   
                   <b> <a href="<%=request.getContextPath()%>/UserTroubleShootingDesk/back.html">back</a> &nbsp; &nbsp; &nbsp; </b>
                   </form> &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
                    <input type="submit" value="submit" /> &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
                    <b><a href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html"> Logout</a> </b>
                </div>
            </div>
        
    </body>

    </html>
