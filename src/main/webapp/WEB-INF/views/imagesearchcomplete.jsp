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
		<li><a href="picture.html">표정 분석</a></li>
		<li><a href="voluteer.html">봉사 게시판</a></li>
		<li><a href="voice.html">음성 게시판</a></li>
	</ul>
	<ul class="actions vertical">
	
	    	<li><a href="SignUp.do" class="button special fit">회원가입</a></li>
			<li><a href="Login.do" class="button fit">로그인</a></li>
	
	</ul>
</nav>

<section id="banner" class="style2">
	<div class="inner">
		<span class="image"> <img src="images/pic07.jpg" alt="" />
		</span>
		<header class="major">
			<h1>이미지로 검색해방</h1>
		</header>
		<div class="content">
			<p>
				Lorem ipsum dolor sit amet nullam consequat<br /> sed veroeros.
				tempus adipiscing nulla.
			</p>
		</div>
	</div>
</section>
<!-- Main -->
<div id="main">
	<!-- One -->

	<!-- Two -->
	<section id="two" class="spotlights">
		<section>
			<a href="generic.html" class="image"> <img src="images/KakaoTalk_Moim_1OFbXU1YfyaNWbTvObDwPZP5wM77ou.jpg"
				alt="" data-position="center center" />
			</a>
			<div class="content">
				<div class="inner">
					<header class="major">
						<h3>이미지 업로드</h3>
					</header>

					<form action="upload.do" enctype="multipart/form-data"
						method="post">
						<ul class="actions">

							<li><input type="file" name="uploadfile" required="required"></li>
						</ul>
						<br> <input type="submit" value="Upload">
					</form>
					<p>
						
							${tt}
						
					</p>
				</div>
			</div>
		</section>
		<section>
			<a href="generic.html" class="image"> <img src="images/pic09.jpg"
				alt="" data-position="top center" />
			</a>
			<div class="content">
				<div class="inner">
					<header class="major">
						<h3>Rhoncus magna</h3>
					</header>
					<p>Nullam et orci eu lorem consequat tincidunt vivamus et
						sagittis magna sed nunc rhoncus condimentum sem. In efficitur
						ligula tate urna. Maecenas massa sed magna lacinia magna
						pellentesque lorem ipsum dolor. Nullam et orci eu lorem consequat
						tincidunt. Vivamus et sagittis tempus.</p>
					<ul class="actions">
						<li><a href="generic.html" class="button">Learn more</a></li>
					</ul>
				</div>
			</div>
		</section>
		<section>
			<a href="generic.html" class="image"> <img src="images/pic10.jpg"
				alt="" data-position="25% 25%" />
			</a>
			<div class="content">
				<div class="inner">
					<header class="major">
						<h3>Sed nunc ligula</h3>
					</header>
					<p>Nullam et orci eu lorem consequat tincidunt vivamus et
						sagittis magna sed nunc rhoncus condimentum sem. In efficitur
						ligula tate urna. Maecenas massa sed magna lacinia magna
						pellentesque lorem ipsum dolor. Nullam et orci eu lorem consequat
						tincidunt. Vivamus et sagittis tempus.</p>
					<ul class="actions">
						<li><a href="generic.html" class="button">Learn more</a></li>
					</ul>
				</div>
			</div>
		</section>
	</section>

	<!-- Three -->
	<section id="three">
		<div class="inner">
			<header class="major">
				<h2>Massa libero</h2>
			</header>
			<p>Nullam et orci eu lorem consequat tincidunt vivamus et
				sagittis libero. Mauris aliquet magna magna sed nunc rhoncus
				pharetra. Pellentesque condimentum sem. In efficitur ligula tate
				urna. Maecenas laoreet massa vel lacinia pellentesque lorem ipsum
				dolor. Nullam et orci eu lorem consequat tincidunt. Vivamus et
				sagittis libero. Mauris aliquet magna magna sed nunc rhoncus amet
				pharetra et feugiat tempus.</p>
			<ul class="actions">
				<li><a href="generic.html" class="button next">Get Started</a></li>
			</ul>
		</div>
	</section>

</div>

<!-- Contact -->
<section id="contact">
	<div class="inner">
		<section>
			<form method="post" action="#">
				<div class="field half first">
					<label for="name">Name</label> <input type="text" name="name"
						id="name" />
				</div>
				<div class="field half">
					<label for="email">Email</label> <input type="text" name="email"
						id="email" />
				</div>
				<div class="field">
					<label for="message">Message</label>
					<textarea name="message" id="message" rows="6"></textarea>
				</div>
				<ul class="actions">
					<li><input type="submit" value="Send Message" class="special" /></li>
					<li><input type="reset" value="Clear" /></li>
				</ul>
			</form>
		</section>
		<section class="split">
			<section>
				<div class="contact-method">
					<span class="icon alt fa-envelope"></span>
					<h3>Email</h3>
					<a href="#">information@untitled.tld</a>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon alt fa-phone"></span>
					<h3>Phone</h3>
					<span>(000) 000-0000 x12387</span>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon alt fa-home"></span>
					<h3>Address</h3>
					<span>1234 Somewhere Road #5432<br /> Nashville, TN 00000<br />
						United States of America
					</span>
				</div>
			</section>
		</section>
	</div>
</section>


