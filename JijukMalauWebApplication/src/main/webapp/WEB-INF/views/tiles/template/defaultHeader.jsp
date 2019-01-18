	<script src="${pageContext.request.contextPath}/static/scripts/jquery.min.js"></script>
	<script>
	   	function functionAjax() {
	      	 $.ajax({
	      	     url : 'ajaxtest.html',
	      	     success : function(data) {
	      	         $('#title2_text').html(data);
	      	     }
	     	  });
	  	 }
	</script>
	<script>
	    var intervalId = 0;
	    intervalId = setInterval(functionAjax, 1000);
	</script>
</head>
<body>
<!--HEADER-->
  <div class="container">
    <div class="jumbotron">   
      <div class="page-header">  
<!--LOGO-->
        <div id="logo">
          <img id="jm_logo" class="img" src="${pageContext.request.contextPath}/static/img/jm_logo.jpg" alt="This is our logo">
        </div>
 <!--TITLE-->
        <h1 id="title1_text">Jijuk Malau<span id="title2_text">Stock</span></h1>
        </div>  
    </div>
  </div>
</body>