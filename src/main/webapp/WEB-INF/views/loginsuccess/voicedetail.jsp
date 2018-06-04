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

           <li><a href="Logout.do" class="button special fit">${value} 님  로그아웃</a></li>

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
         <p>
            시각장애인들을 위해 음성파일을 올리는 게시판입니다. <br>
         여러분의 음성파일을 공유해주세요!
         </p>
      </div>
   </div>
</section>
<section id="two">
   
   <div class="inner">
   

         <header class="major">
               <h2>음성 게시판</h2>
         </header>
   <c:forEach items="${list}" var="n">         
  <table class="view_table total-notice">
    <colgroup>
        <col style="width:73px" ></col>
        <col />
        <col style="width:60px" />
        <col style="width:95px" />
        <col style="width:60px" />
        <col style="width:95px" />
        <col style="width:75px" />
        <col style="width:49px" />
      <col style="width:60px" />
      <col style="width:45px" />
    </colgroup>
    <tr>
     
    </tr>
    <tr>
       <th scope="row" class="th3 color7">제목</th>
     <td colspan="10" class="td title bg_view"><input type="text" name="name" value="${n.name }"
                  id="name" style="background: transparent;border:none"/>
      </td>
    </tr>
     <tr>
     
    </tr>
    <tr>
      <th scope="row" class="th3 color7">작성자</th>
         <td colspan="7" class="td_pd51 color7"><input type="text" name="writer"
                  id="writer" value="${n.writer}"  disabled="disabled" style="background: transparent" />              
    </tr>
    <!--  첨부 파일  게시판일경우   -->
     <tr>
     
    </tr>
    <tr>
      <th  style="width:150px; padding:0.75em" scope="row" class="th_bg_wh color7 th3">첨부파일</th>
      <td class="td_pd5 color7" colspan="9"> 
      
         
        <ul class="attach_list" style="margin:0;list-style:none"">
        
        
         
             
             <a href="download.do?path=C:/test&fileName=${n.filename}" >${n.filename}</a>
   
        </ul>
        
      </td>
   
    </tr>
         <tr>
     
    </tr>
    
    <!--  첨부 파일  끝 -->

    <tr>
      <td colspan="10" style="padding:0">
      
      
      
       <!--  첨부 파일  게시판이면서 이미지일경우(옵션에따라 이미지 표시) -->
       
       <!--  첨부 파일  끝 -->
      
      
      <div id="article_text">
         <p style="font-size: 13pt; word-break: keep-all; font-weight: bold; 
         text-align: center; letter-spacing: 0pt; text-autospace: 0; background: transparent">
         <textarea name="contents" id="contents" rows="9" style="background: transparent; padding-left:0.75em">${n.contents }</textarea>
   </p>




      </div>
      
      
      
      </td>
    </tr>
    
    
  </table>
  </c:forEach>
  <ul class="actions">
               <li data-toggle="modal" class="button fit" style="width:10%"><a href="userVoice.do?name=${value}" style="text-decoration:none" >게시글보기</a></li>
            </ul>
</div>         
      </section>
   </div>
</section>