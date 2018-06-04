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
<section id="contact">
	<div class="inner">
		<section>
			<header class="major">
				<h2>로그인</h2>
			</header>
			<form method="post" action="Login.do" id="loginForm">

				<div class="field">
					<label for="email">Email</label> <input type="text" name="email"
						id="email" />
				</div>
				<div class="field">
					<label for="password">비밀번호</label> <input type="text"
						name="password" id="password" />
				</div>
				<ul class="actions">
					<li><input type="submit" value="로그인" class="special" /></li>
					<li><input type="reset" value="취소" /></li>
				</ul>
			</form>
		</section>
		<section class="split">
			<section>
				<div class="contact-method">
					<span class="icon alt fa-envelope"></span>
					<h3>Email</h3>
					<a href="#">dudqls1994@ajou.ac.kr</a>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon alt fa-phone"></span>
					<h3>Phone</h3>
					<span>010-8638-9316</span>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon alt fa-home"></span>
					<h3>Address</h3>
					<span>경기도 수원시 영통구 광교호수공원로 45(32/2) <br /> 1009동 1902호 (원천동,광교 호반베르디움 )<br />
					</span>
				</div>
			</section>
		</section>
	</div>
</section>
<script type="text/javascript">
  <c:set value="${value}" var = "value"/> 
	  var fail = '<c:out value="${value}"/>';
	  if(fail=="fail"){
	  	alert("로그인 실패.");
	  }
</script>