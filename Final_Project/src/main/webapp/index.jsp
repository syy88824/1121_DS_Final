<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seeries</title>
<style>

    html{
        font-size: 62.5%;
    }

    body{
        background-image: url('./images/searchPage.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-attachment: fixed;
        display: flex;
    }

    div{
        display: flex;
    }

    #mainContainer{
        display: flex;
        align-items: center;
        align-self: center;
    }

    #title{
        color: #fff;
        text-align: center;
        letter-spacing: 36px;
        max-width: 46.4rem;
        font-size: 10rem;
        font-family: Garamond, serif;
        margin-top: 15rem;
        margin-bottom: 5rem;
        margin-left: 45rem;
        align-self: center;
    }

    #searchContainer{
        align-items: center;
        align-self: center;
    }

    #inputContainer{
        border: none;
        background-color: #D9D9D9;
        width: 35rem;
        height: 4rem;
        border-radius: 10rem 0 0 10rem;
        align-self: center;
        margin-right: 1rem;
        margin-left: 50rem;
    }

    #inputSearch{
        border: none;
        background-color: #D9D9D9;
        margin-left: 2rem;
        width: 33rem;        
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
    }

    #searchButton{
        background-image: url("./images/searchIcon.png");
        background-size: 2.5rem;
        background-color: #D9D9D9;
        background-repeat: no-repeat;
        width: 2.5rem;
        height: 3rem;
        border: none;
        margin-top: 0.7rem;
        margin-left: 0.2rem;
    }
    
</style>
     <script type="text/javascript" src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit">
		var googleTranslateElement; //global var for Google Translate Element
		
		function googleTranslateElementInit() {
		
		    if (googleTranslateElement) return; //var being non-empty means element already created
		    //store Google Translate Element in our var
		    googleTranslateElement = new google.translate.TranslateElement({pageLanguage: 'auto', includedLanguages: 'en,ko,ja,id,ms,th,vi,zh-CN,zh-TW', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL},'google_translate_element')return; 
	
		}
		
	</script>
</head>
<body>
     <form action='Servlet' method='get'>
        <div id="mainContainer">
            <p id="title">Seeries</p>
        </div>
        <div id="searchContainer">
            <div id="inputContainer">
                <input type='text' id="inputSearch" name='inputSearch' placeholder='Enter the keyword' onsubmit="googleTranslateElementInit()"/>
            </div>
            <div id="buttonContainer">
                <button id="searchButton" type="submit"></button>
            </div>

            
        </div>
        

</form>
</body>
</html>