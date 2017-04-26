<!DOCTYPE html>
<html>

<head>
<title>TroubleShooting Desk</title>
 <link rel="icon" href="/SpringExampleMvc/images/icon2.png" type="image/png" sizes="16x16">
     <style>
    div.background {
        background: url("/SpringExampleMvc/images/icon25.jpg");
        border: 2px solid black;
         background-repeat: no-repeat; 
         background-size: 100% 100%;
    }
    #h1{
    font-size: 50px;
    font-family: algerian;
    color: #228B22;
    text-shadow: 3px 3px 7px grey;
    text-align: center;

}
    
    div.box {
        margin: 30px;
        background-color: #ffffff;
        border: 1px solid black;
        opacity: 0.8;
    }
    
    div.box p {
        margin: 9%;
        font-weight: bold;
        color: #000000;
    }
    
   
    </style>
</head>

<body>
    <!-- need to define ajax calls and js code -->
    <div class="background">
        <div class="box">
<p> <img src="/SpringExampleMvc/images/icon24.png" width="90px" height="90px">Error in finding the page.. please try again or please check your credentials. </p>
<h4><a href="<%=request.getContextPath()%>/TroubleShootingDesk/logout.html"> try again</a> </h4>
</div>
</div>
</body>
</html>
