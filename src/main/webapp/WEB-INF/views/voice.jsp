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
		<li><a href="Volounteer.do">봉사 게시판</a></li>
		<li><a href="Voice.do">음성 게시판</a></li>
   </ul>
   <ul class="actions vertical">

      <li><a href="SignUp.do" class="button special fit">회원가입</a></li>
      <li><a href="Login.do" class="button fit">로그인</a></li>

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
<div id="main" style="text-align: center;">
   

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
               <td align="left"><a href="goboard.do?idx=${n.idx}">${n.name}
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
   
</div>
