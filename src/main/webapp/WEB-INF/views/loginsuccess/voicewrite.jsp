<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page session="false"%>
<script type="text/javascript"
   src="http://code.jquery.com/jquery-1.11.3.js"></script>
<link href="${pageContext.request.contextPath}/css/dragdrop2.css" rel="stylesheet">
<script type="text/javascript">
            $(document).ready(function(){
                var objDragAndDrop = $(".dragAndDropDiv");
                  
                $(document).on("dragenter",".dragAndDropDiv",function(e){
                    e.stopPropagation();
                    e.preventDefault();
                    $(this).css('border', '2px solid #0B85A1');
                });
                $(document).on("dragover",".dragAndDropDiv",function(e){
                    e.stopPropagation();
                    e.preventDefault();
                });
                $(document).on("drop",".dragAndDropDiv",function(e){
                      
                    $(this).css('border', '2px dotted #0B85A1');
                    e.preventDefault();
                    var files = e.originalEvent.dataTransfer.files;
                  
                    handleFileUpload(files,objDragAndDrop);
                });
                  
                $(document).on('dragenter', function (e){
                   e.stopPropagation();
                    e.preventDefault();
                });
                $(document).on('dragover', function (e){
                 e.stopPropagation();
                 e.preventDefault();
                  objDragAndDrop.css('border', '2px dotted #0B85A1');
                });
                $(document).on('drop', function (e){
                    e.stopPropagation();
                   e.preventDefault();
                });
                  
                function handleFileUpload(files,obj)
                {
                   for (var i = 0; i < files.length; i++)
                   {
                        var fd = new FormData();
                        fd.append('file', files[i]);
                   
                        var status = new createStatusbar(obj); //Using this we can set progress.
                        status.setFileNameSize(files[i].name,files[i].size);
                        sendFileToServer(fd,status);
                   }
                   
                }
                  
                var rowCount=0;
                function createStatusbar(obj){
                          
                    rowCount++;
                    var row="odd";
                    if(rowCount %2 ==0) row ="even";
                    this.statusbar = $("<div class='statusbar "+row+"'></div>");
                    this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);
                    this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
                    this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
                    this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);
                      
                    obj.after(this.statusbar);
                   
                    this.setFileNameSize = function(name,size){
                        var sizeStr="";
                        var sizeKB = size/1024;
                        if(parseInt(sizeKB) > 1024){
                            var sizeMB = sizeKB/1024;
                            sizeStr = sizeMB.toFixed(2)+" MB";
                        }else{
                            sizeStr = sizeKB.toFixed(2)+" KB";
                        }
                      $("#filename").val(name);       
                        this.filename.html(name);
                        this.size.html(sizeStr);
                    }
                      
                    this.setProgress = function(progress){      
                        var progressBarWidth =progress*this.progressBar.width()/ 100; 
                        this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
                        if(parseInt(progress) >= 100)
                        {
                            this.abort.hide();
                        }
                    }
                      
                    this.setAbort = function(jqxhr){
                        var sb = this.statusbar;
                        this.abort.click(function()
                        {
                            jqxhr.abort();
                            sb.hide();
                        });
                    }
                }
                  
                function sendFileToServer(formData,status)
                {   
                    var uploadURL = "writecomplete.do"; //Upload URL
                    var extraData ={}; //Extra Data.
                    var jqXHR=$.ajax({
                            xhr: function() {
                            var xhrobj = $.ajaxSettings.xhr();
                            if (xhrobj.upload) {
                                    xhrobj.upload.addEventListener('progress', function(event) {
                                        var percent = 0;
                                        var position = event.loaded || event.position;
                                        var total = event.total;
                                        if (event.lengthComputable) {
                                            percent = Math.ceil(position / total * 100);
                                        }
                                        //Set progress
                                        status.setProgress(percent);
                                    }, false);
                                    
                                }
                            return xhrobj;
                        },
                        url: uploadURL,
                        type: "POST",
                        contentType:false,
                        processData: false,
                        cache: false,
                        data: formData,
                        success: function(data){
                            status.setProgress(100);
                          
                            //$("#status1").append("File upload Done<br>");          
                        }
                    });
                   
                    status.setAbort(jqXHR);
                }
                  
            });
        </script>


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
   
      <section>
         <header class="major">
               <h2>음성 게시판 글쓰기</h2>
         </header>
         <form method="post" action="writecomplete.do?email=${name}" enctype="multipart/form-data" id="signupForm">
            <div class="field half first">
               <label for="name">제목</label> <input type="text" name="name"
                  id="name" />
            </div>
            <div class="field half">
               <label for="writer">작성자</label> <input type="text" name="writer"
                  id="writer" value="${name}"  disabled="disabled" />
            
            </div>
            <label for="message">첨부파일 </label>
            <div id="fileUpload" class="dragAndDropDiv">Drag And Drop
                  Files Here</div>
            <input id="filename" name ="filename" type="hidden" />
            <div class="field">
               <label for="message">내용 </label>
               <textarea name="contents" id="contents" rows="9"></textarea>
            </div>
         
            <ul class="actions">
               <li><input type="submit" value="글쓰기" class="special" /></li>
               <li><input type="reset" value="취소" /></li>
            </ul>
         </form>
      </section>
   </div>
</section>