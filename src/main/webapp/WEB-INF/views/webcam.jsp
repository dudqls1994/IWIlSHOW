
<head>
  <meta charset="UTF-8">
  <title>HTML5 Media Device Access</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    video, img {
      max-width:100%;
      width: 800px;
      
    }
    audio {
    	width: 0px;
    	background:transparent;
    	
    	
    }
    #noticeTable{
    	width:90%;
      border:0;
      margin: 0 auto;
      text-align:center;
      padding-bottom: 30px;
      margin-top: 20px;
    }
  </style>
</head>
<body>
	
	<div id="noticeTable"><video autoplay ></video><div id="video"></div></div>
	
	
	<br><br>
	<audio id="audio" controls autoplay>
   		<source src="PlayFile.do" type="audio/mpeg">
   		<source src="PlayFile.do" type="audio/ogg">
	</audio>
</body>
<script>
  (function() {
	 
    'use strict';
    var video = document.querySelector('video')
      , canvas;
   
    /**
     *  generates a still frame image from the stream in the <video>
     *  appends the image to the <body>
     */
    function takeSnapshot() {
      var img = document.querySelector('img') || document.createElement('img');
      var context;
      var width = video.offsetWidth
        , height = video.offsetHeight;
        //alert("dd");
      canvas = canvas || document.createElement('canvas');
      canvas.width = width;
      canvas.height = height;

      context = canvas.getContext('2d');
      context.drawImage(video, 0, 0, width, height);

      img.src = canvas.toDataURL('image/png');
      //location.href = canvas.toDataURL('image/png').replace(/^data:image\/png/, 'data:application/octet-stream');
      document.body.appendChild(img);
      //window.location.href=image.src;
      var imageData=canvas.toDataURL();
     // alert(canvas.toDataURL());
      $.ajax({
          url:'capture.do',
          data:{imageBase64: imageData},
          type: "POST",
          dataType: 'json',
          timeout: 10000,
          async: false,
          error: function(){
              console.log("WOOPS");
          },
          success: function(data){
              console.log(data.joy)
              location.reload();
              
              $('#video').empty;
              
          }
      });
    }

    // use MediaDevices API
    // docs: https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
    if (navigator.mediaDevices) {
      // access the web cam

      navigator.mediaDevices.getUserMedia({video: true})

      // permission granted:
        .then(function(stream) {
          video.src = window.URL.createObjectURL(stream);
          video.addEventListener('click', takeSnapshot);
        })
        // permission denied:
        
    }
  })();

</script>
