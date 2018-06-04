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


<!-- Main -->
<div id="main">

	<!-- One -->
	<section id="one" class="tiles">
		<article>
			<span class="image"> <img src="images/findimage1.png" alt="" />
			</span>
			<header class="major">
				<h3>
					<a href="searchimage.do" class="link">이미지 검색</a>
				</h3>
				<p>이미지를 분석해 회원님에게 정보를 줍니다 !</p>
			</header>
		</article>
		<article>
			<span class="image"> <img src="images/vol2.jpg" alt="" />
			</span>
			<header class="major">
				<h3>
					<a href="webcam.do" class="link">실시간 표정 도우미</a>
				</h3>
				<p>실시간으로 표정 훈련 및 정보들을 줍니다 ! </p>
			</header>
		</article>
		<article>
			<span class="image"> <img src="images/talk3.png" alt="" />
			</span>
			<header class="major">
				<h3>
					<a href="Volounteer.do" class="link">봉사 게시판</a>
				</h3>
				<p>자원봉사 지원 및 조회 게시판 입니다. </p>
			</header>
		</article>
		<article>
			<span class="image"> <img src="images/findimage.jpg" alt="" />
			</span>
			<header class="major">
				<h3>
					<a href="Voice.do" class="link">음성 공유 게시판</a>
				</h3>
				<p>시각장애인들을 위해 음성파일을 올려주세요 !</p>
			</header>
		</article>

	</section>

	<!-- Two -->
	<section id="two">
		<div class="inner">
			<header class="major">
				<h2>I WILL SHOW</h2>
			</header>
			<p>저희 플랫폼을 이용해주셔서 감사합니다.<br>
			저희는 앞으로도 회원님분들의 편리성을 위해 더욱 발전할 것입니다. <br>
			감사합니다.</p> <br>
		</div>
	</section>

</div>



<!-- Contact -->



