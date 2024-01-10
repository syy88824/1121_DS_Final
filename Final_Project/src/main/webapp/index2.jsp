<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style type="text/css">

    html{
        font-size: 62.5%;
    }

	.textStyle{
        color: #000000;
	}

    #photo{
        background-image: url("searchPage.jpg");
        width: 100%;
        height: 20rem;
        display: flex;
    }

    #brand{
        color: #fff;
        letter-spacing: 3rem;
        font-size: 5rem;
        font-family: Garamond, serif;
        margin-left: 13rem;
        margin-top: 0rem;
        padding-top: 7rem;
    }

    #searchContainer{
        display: flex;
    }

    #inputBox{
        border: none;
        background-color: #D9D9D9;
        width: 60rem;
        height: 4rem;
        border-radius: 10rem 0 0 10rem;
        margin-top: 9rem;
        margin-left: 8rem;
    }

    #inputSearch{
        border: none;
        background-color: #D9D9D9;
        margin-left: 2rem;
        margin-top: 1.1rem;
        width: 55rem;
    }

    #inputSearch:focus {
        outline: none;
    }

    #buttonContainer{
        width: 4rem;
        height: 4rem;
        background-color: #D9D9D9;
        border: none;
        border-radius: 0 10rem 10rem 0; 
        margin-top:9rem;
        margin-left: 1rem;
    }

    #searchButton{
        background-image: url("searchIcon.png");
        background-size: 2.5rem;
        background-color: #D9D9D9;
        background-repeat: no-repeat;
        width: 2.5rem;
        height: 3rem;
        border: none;
        margin-left: 0.2rem;
        margin-top: 0.7rem;
    }
    
    #result{
     letter-spacing:0.5rem;
        font-size: 2rem;
        padding-top: 5rem;
        width: 144rem;
        display: inline-block;
    }
    
    
    #divideLine{
     width: 144rem;
     height:0.1rem;
     background-color: #000000;
     margin-top: 5rem;
    }

</style>
</head>
<body>
<body style='background-color: #D9D9D9'>
<form action='Servlet' method='get'>
    <div id="photo">
        <p id="brand">Seeries</p>
        <div id="searchContainer">
            <div id="inputBox"> <!-- 這個div裡面裝的是第二頁最上面的input -->
                <input type='text' id="inputSearch" name='inputSearch' placeholder='請輸入關鍵字' value='<%=request.getParameter("keyword")%>'/>
            </div>
            <div id="buttonContainer">
                <button id="searchButton" type="submit"></button>
            </div>
        </div>
    </div>
    <div id="result"> <!-- 這個div裡面裝的是所有回傳回來的東西 -->
     <!-- 用二維陣列儲存query回傳的hashMap 再一個一個取出顯示-->
    <%
     String[][] orderList = (String[][]) request.getAttribute("query"); 
     for (int i = 0; i < orderList.length; i++) {
      String s=orderList[i][1];
     %>
    <i class = "textStyle"></i><a href='<%=orderList[i][0]%>'><%=orderList[i][1]%></a>
    <!-- 印出下面黑色的線 -->
    <div id = "divideLine"></div>
    <br>
    <%
    }
    %>
   
  </div>

</form>
</body>
</html>