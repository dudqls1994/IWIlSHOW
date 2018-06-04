<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page session="false"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
<link href="${pageContext.request.contextPath}/css/dragdrop.css"
	rel="stylesheet">
	

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

<style>

.skillbar {
	position:relative;
	display:block;
	margin-bottom:15px;
	width:100%;
	background:#eee;
	height:45px;
	border-radius:3px;
	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	-webkit-transition:0.4s linear;
	-moz-transition:0.4s linear;
	-ms-transition:0.4s linear;
	-o-transition:0.4s linear;
	transition:0.4s linear;
	-webkit-transition-property:width, background-color;
	-moz-transition-property:width, background-color;
	-ms-transition-property:width, background-color;
	-o-transition-property:width, background-color;
	transition-property:width, background-color;
}

.skillbar-title {
	position:absolute;
	top:0;
	left:0;
width:110px;
	font-weight:bold;
	font-size:13px;
	color:#000000;
	-webkit-border-top-left-radius:3px;
	-webkit-border-bottom-left-radius:4px;
	-moz-border-radius-topleft:3px;
	-moz-border-radius-bottomleft:3px;
	border-top-left-radius:3px;
	border-bottom-left-radius:3px;
}

.skillbar-title span {
	display:block;
	background:rgba(0, 0, 0, 0);
	
	height:45px;
	
	-webkit-border-top-left-radius:3px;
	-webkit-border-bottom-left-radius:3px;
	-moz-border-radius-topleft:3px;
	-moz-border-radius-bottomleft:3px;
	border-top-left-radius:3px;
	border-bottom-left-radius:3px;
}

.skillbar-bar {
	height:45px;
	width:0px;
	background:#6adcfa;
	border-radius:3px;
	-moz-border-radius:3px;
	-webkit-border-radius:3px;
}

.skill-bar-percent {
	position:absolute;
	right:10px;
	top:0;
	font-size:11px;
	height:45px;
	line-height:35px;
	color:#ffffff;
	color:rgba(0, 0, 0, 0.4);
}

#container {
	text-align: center;
	margin: 20px;
}
#face{
	float:right;
}

h2 {
	color: #CCC;
}

.bar-main-container {
	margin: 10px auto;
	width: 350px;
	height: 70px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	font-family: sans-serif;
	font-weight: normal;
	font-size: 0.8em;
	color: #FFF;
}

.wrap {
	padding: 8px;
}

.bar-percentage {
	padding: 8px;
	float: left;
	background: rgba(0, 0, 0, 0.13);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	padding: 9px 0px;
	width: 18%;
	margin-left:5px;
	
}

.bar-container {

	float: right;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	height: 10px;
	background: rgba(0, 0, 0, 0.13);
	width: 78%;
	margin: 12px 0px;
	overflow: hidden;
	
}

.bar {
	float: left;
	background: #FFF;
	height: 100%;
	-webkit-border-radius: 10px 0px 0px 10px;
	-moz-border-radius: 10px 0px 0px 10px;
	border-radius: 10px 0px 0px 10px;
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
	filter: alpha(opacity = 100);
	-moz-opacity: 1;
	-khtml-opacity: 1;
	opacity: 1;
	
}

.progress-title{
    display: block;
    font-size: 15px;
    font-weight: 700;
    color: #ffffff;
    bottom: -3px;
    left: -90px;
}
/* COLORS */
.azure {
	background: #38B1CC;
}

.emerald {
	background: #2CB299;
}

.violet {
	background: #8E5D9F;
}

.yellow {
	background: #EFC32F;
}

.red {
	background: #E44C41;
}


.loader {
  width: 200px;
  height: 200px;
  line-height: 200px;
  margin: 100px auto;
  position: relative;
  box-sizing: border-box;
  text-align: center;
  z-index: 0;
  text-transform: uppercase;
}

.loader:before,
.loader:after {
  opacity: 0;
  box-sizing: border-box;
  content: "\0020";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 100px;
  border: 5px solid #fff;
  box-shadow: 0 0 50px #fff, inset 0 0 50px #fff;
}

.loader:after {
  z-index: 1;
  -webkit-animation: gogoloader 2s infinite 1s;
}

.loader:before {
  z-index: 2;
  -webkit-animation: gogoloader 2s infinite;
}

@-webkit-keyframes gogoloader {
  0% {
    -webkit-transform: scale(0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    -webkit-transform: scale(1);
    opacity: 0;
  }
}
</style>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.music_download').hide();
						$('#container').hide();
						$('#two .content .inner .loader').hide();
						var objDragAndDrop = $(".dragAndDropDiv");

						$(document).on("dragenter", ".dragAndDropDiv",
								function(e) {
									e.stopPropagation();
									e.preventDefault();
									$(this).css('border', '2px solid #0B85A1');
								});
						$(document).on("dragover", ".dragAndDropDiv",
								function(e) {
									e.stopPropagation();
									e.preventDefault();
								});
						$(document).on("drop", ".dragAndDropDiv", function(e) {

							$(this).css('border', '2px dotted #0B85A1');
							e.preventDefault();
							var files = e.originalEvent.dataTransfer.files;
							  $('#two .content .inner .dragAndDropDiv ').hide();
			                     $('#two .content .inner .loader').show();
			                     handleImgFileSelect(files);  
							handleFileUpload(files, objDragAndDrop);
						});

						$(document).on('dragenter', function(e) {
							e.stopPropagation();
							e.preventDefault();
						});
						$(document).on('dragover', function(e) {
							e.stopPropagation();
							e.preventDefault();
							objDragAndDrop.css('border', '2px dotted #0B85A1');
						});
						$(document).on('drop', function(e) {
							e.stopPropagation();
							e.preventDefault();
						});
			              function handleImgFileSelect(files){
			                     
			                     var filesArr=Array.prototype.slice.call(files);
			                     
			                     filesArr.forEach(function(f){
			                        if(!f.type.match("image.*")){
			                           
			                           aler("이미지만 가능");
			                           return;
			                        }
			                        sel_file=f;
			                        
			                        var reader=new FileReader();
			                        reader.onload=function(e){
			                           $("#img").attr("src", e.target.result);
			                           
			                        }
			                        reader.readAsDataURL(f);
			                     });
			                  }
						function handleFileUpload(files, obj) {
							for (var i = 0; i < files.length; i++) {
								var fd = new FormData();
								fd.append('file', files[i]);

								var status = new createStatusbar(obj); //Using this we can set progress.
								status.setFileNameSize(files[i].name,
										files[i].size);
								$('#two .content .inner .statusbar').hide();
								sendFileToServer(fd, status);

							}
						}
					
						var rowCount = 0;
						function createStatusbar(obj) {

							rowCount++;
							var row = "odd";
							if (rowCount % 2 == 0)
								row = "even";
							this.statusbar = $("<div class='statusbar "+row+"'></div>");
							this.filename = $("<div class='filename'></div>")
									.appendTo(this.statusbar);
							this.size = $("<div class='filesize'></div>")
									.appendTo(this.statusbar);
							this.progressBar = $(
									"<div class='progressBar'><div></div></div>")
									.appendTo(this.statusbar);
							this.abort = $("<div class='abort'>중지</div>")
									.appendTo(this.statusbar);

							obj.after(this.statusbar);

							this.setFileNameSize = function(name, size) {
								var sizeStr = "";
								var sizeKB = size / 1024;
								if (parseInt(sizeKB) > 1024) {
									var sizeMB = sizeKB / 1024;
									sizeStr = sizeMB.toFixed(2) + " MB";
								} else {
									sizeStr = sizeKB.toFixed(2) + " KB";
								}

								this.filename.html(name);
								this.size.html(sizeStr);
								
							}

							this.setProgress = function(progress) {
								var progressBarWidth = progress
										* this.progressBar.width() / 100;
								this.progressBar.find('div').animate({
									width : progressBarWidth
								}, 10).html(progress + "% ");
								if (parseInt(progress) >= 100) {
									this.abort.hide();
								}
							}

							this.setAbort = function(jqxhr) {
								var sb = this.statusbar;
								this.abort.click(function() {
									jqxhr.abort();
									sb.hide();
								});
							}
						}

						function sendFileToServer(formData, status) {
							
							var uploadURL = "drag.do"; //Upload URL
							var extraData = {}; //Extra Data.
							var jqXHR = $
									.ajax({
										xhr : function() {
											var xhrobj = $.ajaxSettings.xhr();
											if (xhrobj.upload) {
												xhrobj.upload
														.addEventListener(
																'progress',
																function(event) {
																	var percent = 0;
																	var position = event.loaded
																			|| event.position;
																	var total = event.total;
																	if (event.lengthComputable) {
																		percent = Math
																				.ceil(position
																						/ total
																						* 100);
																	}
																	//Set progress
																	status
																			.setProgress(percent);
																}, false);
											}
											return xhrobj;
										},
										url : uploadURL,
										type : "POST",
										contentType : false,
										processData : false,
										cache : false,
										data : formData,
										success : function(data) {
																	
											status.setProgress(100);
											$('.loader').hide();
											$('#label').html(data.label);
											$('#demo-message').html(data.tt);
											var anger = 0;var joy = 0;var sorrow = 0;var surprise = 0;
											if(data.anger=="VERY_UNLIKELY"){
												anger =0 ;
											}else if(data.anger=="UNLIKELY"){
												anger =30 ;
											}else if(data.anger=="POSSIBLE"){
												anger =50 ;
											}else if(data.anger=="LIKELY"){
												anger =70 ;
											}else if(data.anger=="VERY_LIKELY"){
												anger =100 ;
											}
											
											if(data.joy=="VERY_UNLIKELY"){
												joy = 0 ;
											}else if(data.joy=="UNLIKELY"){
												joy =30 ;
											}else if(data.joy=="POSSIBLE"){
												joy =50 ;
											}else if(data.joy=="LIKELY"){
												joy =70 ;
											}else if(data.joy=="VERY_LIKELY"){
												joy =100 ;
											}
											if(data.sorrow=="VERY_UNLIKELY"){
												sorrow = 0 ;
											}else if(data.sorrow=="UNLIKELY"){
												sorrow =30 ;
											}else if(data.sorrow=="POSSIBLE"){
												sorrow =50 ;
											}else if(data.sorrow=="LIKELY"){
												sorrow =70 ;
											}else if(data.sorrow=="VERY_LIKELY"){
												sorrow =100 ;
											}
											if(data.surprise=="VERY_UNLIKELY"){
												surprise = 0 ;
											}else if(data.surprise=="UNLIKELY"){
												surprise =30 ;
											}else if(data.surprise=="POSSIBLE"){
												surprise =50 ;
											}else if(data.surprise=="LIKELY"){
												surprise =70 ;
											}else if(data.surprise=="VERY_LIKELY"){
												surprise =100 ;
											}
											
											$('#container').show();
											$('#container #bar-1  .bar-percentage').attr('data-percentage',anger.toString());
											$('#container #bar-2  .bar-percentage').attr('data-percentage', joy.toString());
											$('#container #bar-3  .bar-percentage').attr('data-percentage', sorrow.toString());
											$('#container #bar-4  .bar-percentage').attr('data-percentage', surprise.toString());
											
											$('.bar-percentage').each(function () {
												  var progress = $(this);
												  var percentage = Math.ceil($(this).attr('data-percentage'));
												  $({countNum: 0}).animate({countNum: percentage}, {
												    duration: 2000,
												    easing:'linear',
												    step: function() {
												      // What todo on every count
												      var pct =  Math.floor(this.countNum) + '%';
												      progress.text(pct) && progress.siblings().children().css('width',pct);
												    }
												  });
												});
										
											$('#demo-message').html(data.tt);
											console.log(data.korean1);
											
											var total_score = data.Score1+data.Score2+data.Score3+data.Score4+data.Score5;
						
											var first_score =  data.Score1/total_score * 100;
											var two_score =  data.Score2/total_score * 100;
											var three_score =  data.Score3/total_score * 100;
											var four_score =  data.Score4/total_score * 100;
											var five_score =  data.Score5/total_score * 100;
											console.log( Math.floor(first_score)+" - " + total_score);
											if(data.korean1!=null){
												$('#first_title').append( '<span>'+data.korean1+'</span>' );
												$('#first_skill').attr('data-percent',Math.floor(first_score).toString()+"%");
												$('#first_skill .skill-bar-percent').html(Math.floor(first_score).toString()+"%");
												
											}else{
												$('#first_skill').hide();
											}
											if(data.korean2!=null){
												$('#two_title').append( '<span>'+data.korean2+'</span>' );
												$('#second_skill').attr('data-percent',Math.floor(two_score).toString()+"%");
												$('#second_skill .skill-bar-percent').html(Math.floor(two_score).toString()+"%");
												
											}else{
												$('#second_skill').hide();
											}
											if(data.korean3!=null){
												$('#third_title').append( '<span>'+data.korean3+'</span>' );
												$('#third_skill').attr('data-percent',Math.floor(three_score).toString()+"%");
												$('#third_skill .skill-bar-percent').html(Math.floor(three_score).toString()+"%");
												
											}else{
												$('#third_skill').hide();
											}
											if(data.korean4!=null){
												$('#fourth_title').append( '<span>'+data.korean4+'</span>' );
												$('#fourth_skill').attr('data-percent',Math.floor(four_score).toString()+"%");
												$('#fourth_skill .skill-bar-percent').html(Math.floor(four_score).toString()+"%");
												
											}else{
												$('#fourth_skill').hide();
											}
											if(data.korean5!=null){
												$('#five_title').append( '<span>'+data.korean5+'</span>' );
												$('#five_skill').attr('data-percent',Math.floor(five_score).toString()+"%");
												$('#five_skill .skill-bar-percent').html(Math.floor(five_score).toString()+"%");
												
											}else{
												$('#five_skill').hide();
											}
											
											
											
											
											
											$('.skillbar').each(function(){
												$(this).find('.skillbar-bar').animate({
													width:$(this).attr('data-percent')
												},6000);
											});
											$('.music_download').show();
											//$("#status1").append("File upload Done<br>");          
										}
									});

							status.setAbort(jqXHR);
						}

					});

</script>


<section id="banner" class="style1">
	<div class="inner">
		<span class="image"> <img src="images/pic07.jpg" alt="" />
		</span>
		<header class="major">
			<h1>이미지로 검색</h1>
		</header>
		<div class="content">
			<p>이미지 파일을 드래그 하시면 이미지 정보들을 받아 볼수 있습니다.!</p>
		</div>
	</div>
</section>

<!-- Main -->
<div id="main">

	<!-- One -->


	<!-- Two -->
	<section id="two" class="spotlights">
		<section>
			<div class="content" style="width: 100%">
				<div class="inner">
					<div id="fileUpload" class="dragAndDropDiv">
					
						파일을 드래그해주세요.
					</div>
				<div class="loader">이미지 분석중...</div>
				</div>
				
			</div>
			

		</section>

	</section>

	

	
	<div id="container" class="row">
		<div  id="list" class="img_wrap 4u 12u$(medium)">  <img id="img" width="300px"/>
		
		
		</div>
		<div class="4u 12u$(medium)" id="web" >
												<h3 id="label"></h3>
<div id="first_skill" class="skillbar clearfix " data-percent="100%">
	<div id="first_title" class="skillbar-title" ></div>
	<div class="skillbar-bar" style="background: #e67e22;"></div>
	<div class="skill-bar-percent"></div>
</div> <!-- End Skill Bar -->

<div class="skillbar clearfix " data-percent="25%" id="second_skill">
	<div id="two_title" class="skillbar-title"></div>
	<div class="skillbar-bar" style="background: #3498db;"></div>
	<div class="skill-bar-percent">25%</div>
</div> <!-- End Skill Bar -->



<div class="skillbar clearfix " data-percent="40%" id="third_skill">
	<div id="third_title" class="skillbar-title" ></div>
	<div class="skillbar-bar" style="background: #5a68a5;"></div>
	<div class="skill-bar-percent">40%</div>
</div> <!-- End Skill Bar -->

<div class="skillbar clearfix " data-percent="75%" id="fourth_skill">
	<div id="fourth_title" class="skillbar-title"></div>
	<div class="skillbar-bar" style="background: #525252;"></div>
	<div class="skill-bar-percent">75%</div>
</div> <!-- End Skill Bar -->

<div class="skillbar clearfix " data-percent="100%" id="five_skill">
	<div id="five_title" class="skillbar-title" ></div>
	<div class="skillbar-bar" style="background: #2ecc71;"></div>
	<div class="skill-bar-percent">100%</div>
</div> <!-- End Skill Bar -->

												
											</div>
		<div id ="face" class="4u 12u$(medium)">
						<h4>
							<strong>표정 분석</strong>
						</h4>

						<div id="bar-1" class="bar-main-container azure">
						<h2 class="progress-title" style="margin:0px">화난 표정</h2>
							
								<div class="bar-percentage"  data-percentage="" ></div>
								<div class="bar-container">
									<div class="bar"></div>
								</div>
							
						</div>

						<div id="bar-2" class="bar-main-container emerald">
							<h2 class="progress-title" style="margin:0px">기쁜 표정</h2>
								
								<div class="bar-percentage" data-percentage=""></div>
								<div class="bar-container">
									<div class="bar"></div>
								</div>
							
						</div>
						<div id="bar-3" class="bar-main-container yellow">
							<h2 class="progress-title" style="margin:0px">슬픈 표정</h2>
								<div class="bar-percentage" data-percentage="85"></div>
								<div class="bar-container">
									<div class="bar"></div>
								</div>
							
						</div>

						<div id="bar-4" class="bar-main-container red">
							<h2 class="progress-title" style="margin:0px">놀란 표정</h2>
								<div class="bar-percentage" data-percentage="33"></div>
								<div class="bar-container">
									<div class="bar"></div>
								</div>
							
						</div>
					</div>
					
					<div class="12u$">
						<textarea name="demo-message" id="demo-message"
							placeholder="내용 없음" rows="6"></textarea>
					</div>
					</div>



</div>
<section id = "two" class="music_download">
<div class="inner" >
            <ul class="actions" >
				<li><a href="download.do?path=C:/test&fileName=test.wav">음성 파일 다운로드</a></li>
			</ul>
			</div>
</section>




