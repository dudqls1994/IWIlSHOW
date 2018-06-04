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

<!-- Contact -->
<section id="two">
	
	<div class="inner">
	
		<section>
			<header class="major">
					<h2>회원가입</h2>
			</header>
			<form method="post" action="Join.do" id="signupForm">
				<div class="field half first">
					<label for="name">이름</label> <input type="text" name="name"
						id="name" />
				</div>
				<div class="field half">
					<label for="email">이메일</label> <input type="text" name="email"
						id="email" />
				</div>
				<div class="field half first">
					<label for="name">비밀번호</label> <input type="password" name="password"
						id="password" />
				</div>
				<div class="field half">
					<label for="email">비밀번호확인</label> <input type="password" name="passwordconfirm"
						id="passwordconfirm" />
				</div>
				<div class="field">
					<label for="message">어떤 목적으로 오셨습니까?</label>
					<textarea name="message" id="message" rows="6"></textarea>
				</div>
				<div class="6u$ 12u$(small)">
					<input type="checkbox" id="demo-human" name="demo-human" value="1" checked>
					<label for="demo-human">당신은 시각장애인 입니까?</label>
				</div>
				<ul class="actions">
					<li><input type="submit" value="회원가입 완료" class="special" /></li>
					<li><input type="reset" value="취소" /></li>
				</ul>
			</form>
		</section>
	</div>
</section>

