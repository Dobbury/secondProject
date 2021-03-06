<%@page import="dto.JournalDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.DiarycommentDto"%>
<%@page import="dto.memberDto"%>
<%@page import="dto.DiaryDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
   request.setCharacterEncoding("utf-8");
   
   
   memberDto dto = (memberDto) session.getAttribute("user");
   
   String loginid = dto.getId();
   JournalDto journalDto = (JournalDto) request.getAttribute("JournalDto");
   List<DiaryDto> diarylist = (List<DiaryDto>)request.getAttribute("DiaryList");
   List<DiarycommentDto> commentview = (List<DiarycommentDto>)request.getAttribute("DiarycommentDto");
   
 
  for(int i=0;i<diarylist.size();i++){
     diarylist.get(i).toString();
  }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>site</title>
<!-- hover effects.css -->
<link href="Design/css/hovereffect.css" rel="stylesheet">

<style type="text/css">
/* Necessary for full page carousel*/
html, body, header, .view {
   height: 100%;
}

 main{
   background-image: url('img/bgSample13.jpg');
    background-size: 100% 100%;
    background-position: center center;
    transition: all 40s;
    display: block;
    position: relative;
    width: 100%;


    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;

} 
.container {
   display: table;
}

.navbar-menu {
   margin-bottom: 0;
}

.navbar-menu li {
   width: 100px;
   height: 30px;
   border: 1px solid blue;
   list-style: none;
   display: inline-block;
}

.diary-t {
   display: table;
   margin-top: 40px;
}

.diary-t div {
   width: 50%;
   display: table;
}

.diary-t div img {
   width: 100%;
}

.diary-t .map {
   float: left;
}

.diary-t .calendar {
   float: right;
}

.diary-cont .diary-title {
   display: inline-block;
   font-size: 28px;
   margin-right: 5px;
}

.diary-b {
   margin-top: 40px;
}

.diary-b .diary-title {
   display: inline-block;
   font-size: 28px;
   margin-right: 5px;
}

.diary-m img{
	width: 100%;
}
button{
  background:#003458;
  color:#fff;
  border:none;
  position:relative;
  height:60px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
button:hover{
  background:#fff;
  color:#003458;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #003458;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}


</style>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

</head>

<body style="background-color: #e9e9e9e9;" >

 <jsp:include page="header.jsp"></jsp:include> 



   <!-- ----------------------------------------html----------------------------------------- -->
   <!--여기서 하시면 됩니다-->
   <main style="padding-top:80px;">
   <div class="container" style="max-width: 900px;">

      <h1 style="font-weight: 700;
    border-bottom: 2px solid #ccc;
    width: 99%;
    padding-bottom: 10px;
    padding-top: 10px;
    text-align: left;
    margin-top: 30px;">작성중인 일정</h1>
      
      <div class="journal-title" >
   <h2 style="padding: 20px 0;">
      <%=journalDto.getTitle() %>
      </h2>
   </div>
      

   
      
      <%
               for(int i=0; i<diarylist.size(); i++ ){
            %>
               <div style="   padding: 40px;
    background-color: #fff;
       -webkit-box-shadow: 10px 10px 5px -3px rgba(0,0,0,0.13);
-moz-box-shadow: 10px 10px 5px -3px rgba(0,0,0,0.13);
box-shadow: 10px 10px 5px -3px rgba(0,0,0,0.13);margin-top: 20px;">
   
            <div class="diary-m" style="    width: 870px;">
         <div class="diary-cont">
            <p class="diary-title"><%=diarylist.get(i).getTitle() %></p>
            <span class="diary-date" style="color:#555"><%=diarylist.get(i).getTday().substring(0,11) %></span>
            <hr>
            

            <div class="diary-content" style="word-break: break-all;">
                <%=diarylist.get(i).getContent() %>
            </div>
			
         </div>
         
         <div align="right">
         	<a href="DiaryServlet?command=update&seq=<%=diarylist.get(i).getSeq() %>" class="menu__link hover9"><span class="menu__label hover9__label">Update</span></a></a>
         </div>
          </div>
          
      </div>
	
         <%
               }
      %>
     
	<br>
	<div align="center">
		<button class="btn btn-outline-dark" id="jourCancleBtn" ><font size="4">journal cancle</font></button>
	</div>
	<br>	
   </main>


   <script type="text/javascript">
   	$(function () {
		$("#jourCancleBtn").click(function () {
			var check=confirm("추가한 여행기록을 취소하시겠습니까?\n(기록한 일지는 그대로 남아 있습니다.)");
			if(check!=0){
				//YES
				location.href="DiaryServlet?command=jourCancle&seq=<%=journalDto.getSeq() %>";
				$.ajax({
					url:"DiaryServlet",
					type:"post",
					data:{
						command: "jourCancle",
						seq: <%=journalDto.getSeq() %>
					},
					datatype: "json",
					success:function(data){
						if(data == "true"){
							alert("success");
							location.href="CalendarWrite.jsp";
						}else{
							alert("fail");
						}
					},
					error:function(){
						
					}
					
				});
			}else{
				//NO
			}
		});
	});
   </script>

   <!-- --------------------------------------------------------------------------------- -->


<jsp:include page="footer.jsp"></jsp:include> 




</body>
</html>