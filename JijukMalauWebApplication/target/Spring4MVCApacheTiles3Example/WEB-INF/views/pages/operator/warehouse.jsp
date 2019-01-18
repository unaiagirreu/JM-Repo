<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:form action="${pageContext.request.contextPath}/Warehouse/Warehouse" method="POST" modelAttribute="warehouse" >
    <div class="container bg-overlay" style="background: url("${pageContext.request.contextPath}/static/images/warehouse1.jpg")">
      <div class="row" id="warehouseContainer">
        <!--FIRST ROW-->
        <button type="submit" onclick="process()" class="btn  btn-lg float-right " id="btnLogin" value="Log in"><a>Login</a></button>
        
        <div id="Empty1" class="card col-2">
          <figure class="figure d-none d-md-bloc">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="Workstation3" class="card col-2 row align-items-start">
          <figure class="figure-img">
            <img id="Workstation3_image" onclick="changeImage()">
          </figure>
        </div>

        <div id="Parking2" class="card col-2  row ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Workstation2" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Parking1" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Workstation1" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

      <!--SECOND ROW-->
       <div id="Empty2" class="card col-2">
          <figure class="figure d-none d-md-bloc">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line5" class="card col-2 row align-items-start">
          <figure class="figure">
             <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line4" class="card col-2 row">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line3" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line2" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line1" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <!--THIRD ROW-->
        <div id="line6" class="card col-2">
          <figure class="figure-img align-self-end">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line17" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line16" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line15" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Line14" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line13" class="card col-2 ">
          <figure class="figure-img align-self-start">
             <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>
        <!--FOURTH ROW-->
       <div id="Empty3" class="card col-2">
          <figure class="figure d-none d-md-bloc">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line7" class="card col-2  row jusitfy-content-start">
          <figure class="figure img align-self-start">
             <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line8" class="card col-2 row">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line9" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line10" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line11" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
        <!-- FIFTH ROW -->
       <div id="Empty4" class="card col-2">
          <figure class="figure d-none d-md-bloc">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="Workstation4" class="card col-2  row jusitfy-content-start">
          <figure class="figure-img align-self-start">
             <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="Parking3" class="card col-2 row">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Workstation5" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Parking4" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
        <div id="Workstation6" class="card col-2 ">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
        <script src="App/src/main/webapp/static/scripts/warehouse.js"></script>
      </div>
    </div>
</form:form>