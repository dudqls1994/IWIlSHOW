<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page session="false"%>

<nav id="menu">
   <ul class="links">
      <%--     <c:if test="${value!='fail'}">
           <li>${value} 님 안녕하세요</li>
       </c:if> --%>
   <li><a href="Home.do">Home</a></li>
		<li><a href="searchimage.do">이미지 검색</a></li>
		<li><a href="webcam.do">표정 분석</a></li>
		<li><a href="userVol.do?name=${value}">봉사 게시판</a></li>
		<li><a href="userVoice.do?name=${value}">음성 게시판</a></li>
   </ul>
   <ul class="actions vertical">

      <li><a href="Logout.do" class="button special fit">${value} 님
            로그아웃</a></li>



   </ul>
</nav>
<!-- Banner -->
<!-- Note: The "styleN" class below should match that of the header element. -->
<section id="banner" class="style4">
   <div class="inner">
      <span class="image"> <img src="images/pic07.jpg" alt="" />
      </span>
      <header class="major">
         <h1>음성 게시판</h1>
      </header>
      <div class="content">
        <p>시각장애인들을 위해 음성파일을 올리는 게시판입니다. <br>
         여러분의 음성파일을 공유해주세요!
         </p>
      </div>
   </div>
</section>

<!-- Main -->
<div id="main" class="container">
   <!-- One -->
   <table id="noticeTable">
      
         <tr height="35" align="center">
            <td width="7%" height="35">번호</td>
            <td class="noticeTd"></td>
            <td width="48%">제목</td>
            <td class="noticeTd"></td>
            <td width="8%">작성자</td>
            <td class="noticeTd"></td>
            <td width="10%">작성일</td>
            <td class="noticeTd"></td>
         </tr>
         <c:forEach items="${list}" var="n">
            
            <tr height="35" align="center">
               <td>${n.idx}</td>
               <td>&nbsp;</td>
               <td align="left"><a href="goboarduser.do?idx=${n.idx}&name=${name}">${n.name}
               </a></td>
               <td>&nbsp;</td>
               <td>${n.writer}</td>
               <td>&nbsp;</td>
               <td>${n.date}</td>
               <td>&nbsp;</td>
            </tr>
            <tr></tr>
         </c:forEach>
      </table>

   <ul class="actions" >
      <li style="padding-top:30px;float:right; padding-right:200px"><a href="write.do?name=${name}" class="button" >글쓰기</a></li>
   </ul>


</div>
</section>
