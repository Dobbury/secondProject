<%@page import="dto.JournalDto"%>
<%@page import="dto.DiaryDto"%>
<%@page import="dao.DiaryDao"%>
<%@page import="Impl.DiaryImpl"%>
<%@page import="java.util.List"%>
<%@page import="dto.memberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
DiaryImpl dao = DiaryDao.getInstance();

int paging = Integer.parseInt(request.getParameter("page"));
String stext = request.getParameter("stext");
int jcount = dao.getSearchCountJournal(stext);

List<JournalDto> journallist = dao.getSearchJournalList(stext, paging);
int pagecount = jcount/9;
if(pagecount%jcount>0){
	pagecount++;
}

int startPage = 0;
int endPage = 0;
if(paging > 5){
	startPage = paging-5;
}
if(pagecount < paging+5){
	startPage = pagecount-10;
}
if(pagecount < 5){
	startPage = 0;
}
if(paging < 6){
	endPage = 10;
}else{
	endPage = paging+5;
}

%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>site</title>

 <style type="text/css">
 
 
 .scene {display: block; position: relative; width: 100%; 
	height: 100vh; background-repeat: no-repeat; background-attachment: fixed; background-size: cover;}
 .searchbg {
width:100%;height: 100%;padding-top: 280px;background-image: url('img/main_bg06.png');background-size: 100% 100%;
background-position: center center ;
transition: all 40s;
	
 }
 .searchbg:hover{
 	background-size: 150% 150%;
 }
 .searchbtn{
 	height: 40px;
    margin-left: -4px;
    background-color: #aaa;
    display: inline-block;
    width: 45px;
    vertical-align: top;
    font-size: 12px;
    padding-top: 6px;
    border-bottom-right-radius: 7px;
    border-top-right-radius: 7px;
 	}
 	.searchbtn:hover{
 		background-color: #999;
 	}
 </style>
  
</head>

<body>


 <jsp:include page="header.jsp"></jsp:include> 

  
  
<!-- ----------------------------------------html----------------------------------------- -->
  <!--여기서 하시면 됩니다-->
          <div class="scene searchbg" >
          <h1 style="text-align: center;color:#fff">검색어를 입력해주세요</h1>
          <div style="margin-top: 60px; text-align: center;">
          <form action="search.jsp" method="post">
          	<input type="hidden" name="command" value="search">
          	<input type="hidden" name="page" value="1">
			<input type="text" id="stext" name="stext" value="<%=stext %>" style="width: 550px;height: 40px;opacity: 0.8;border-top-left-radius: 7px;border-bottom-left-radius: 7px;
    border: 1px solid #aaa;">
			<input type="submit" class="searchbtn" style="height: 40px;margin-left: -4px;"><i class="fa fa-search fa-2x"></i>
			</form>
		  </div>
		</div>
		
  
<main style="padding-top:80px;">
   <div class="container">
      
		
		<h3 style="margin-left: 35px;font-weight: 700;">여행후기</h3>
			<div style="width:100%;text-align: center;  padding: 0 0 20px 0;display: table;">
	
			 <% 
			
			for(int i = 0; i < journallist.size();i++){
			%>
				<div class="diary" style="width: 300px;height: 300px;text-align: center;
				vertical-align: top;float: left;margin: 30px 34px 0 33px; border:none;">
					<a href="DiaryServlet?command=diaryDetail&seq=<%=journallist.get(i).getSeq()%>">
						<div class="Dimage" style="border:none">
						</div>
						<p style="margin-top: 10px;margin-bottom: 5px;color: #111;font-weight: 700;"><%=journallist.get(i).getTitle() %></p>
					</a>
					<span style="text-align: right;color: #888;font-size: 14px;">조회수</span>
					<span style="text-align: left;color: #888;font-size: 14px;"><%=journallist.get(i).getWdate().substring(0,10) %></span>	
				</div>
			<%
			}
			
			%>
			
			
			
			
			<div style="display: inline-block;">
				<!-- paging -->
				<div>
				<%
				if(paging != 1 || pagecount == 0){
					%>
					<a href="./search.jsp?page=<%=paging-1%>&stext=<%=stext%>">&lt;</a>
					<%
				}
				%>
				
			<%
			
			for(int i = startPage; i < pagecount; i++){
				if(i+1 != paging){
				%>				
				<a href="./search.jsp?page=<%=i+1%>&stext=<%=stext%>"><%=i+1 %></a>
				<%
				}else{
					%>
					<strong><%=paging %></strong>
					<%
				}
				if(i+1 == endPage){
					break;
				}
			}
			
			if(paging != pagecount || pagecount == 0){
			%>
			<a href="./search.jsp?page=<%=paging+1%>&stext=<%=stext%>">&gt;</a>
			<%
			}
			%>
				</div>
				 
				<!-- // paging -->
			</div>
			
			
			<div style="display: table;clear: both;width: 100%;padding: 20px 0 20px 0;">
		<button style="float: right;" onclick="gocal()">글쓰기</button>
	</div>

   </div>
			
		</div>
	
	
</main>
<!-- --------------------------------------------------------------------------------- -->


  
<jsp:include page="footer.jsp"></jsp:include> 

   
   
   
  <!-- SCRIPTS -->
  <script type="text/javascript">
  $(function(){
	 $("#stext").val(<%=stext%>); 
  });
  
  function gocal() {
	location.href= "CalendarServlet?command=gocal";	
}
  
  </script>
  <!-- 
<script type="text/javascript">

$(function(){
	$(".page").click(function () {
		$.ajax({
			url : "DiaryServlet",
			type : "GET",
			data : {
				command : "paging",
				page : $(this).html()
			},
			datatype : "json",
			success : function(data) {
				journallist = JSON.parse(data);
				
				var o = "";
				for (i = 0; i < journallist.length; i++) {
					o += "<div class='diary' style='width: 300px;height: 300px;text-align: center; vertical-align: top;float: left;margin: 30px 34px 0 33px; border:none;'>"
					+"<a href='DiaryServlet?command=diaryDetail&seq="+journallist[i].seq+"'>"
						+"<div class='Dimage' style='border:none'>"
						+"</div>"
						+"<p style='margin-top: 10px;margin-bottom: 5px;color: #111;font-weight: 700;'>"+journallist[i].title+"</p>"
					+"</a>"
					+"<span style='text-align: right;color: #888;font-size: 14px;'>" + journallist[i].readcount + "</span>"
					+"<span style='text-align: left;color: #888;font-size: 14px;'>"+journallist[i].wdate.substring(0,10)+"</span>"	
				+"</div>";
				}
				$("#tourSel").append(o);
			},
			error : function() {
			}
		});
		
	});
});

</script>
 -->

</body>
</html>