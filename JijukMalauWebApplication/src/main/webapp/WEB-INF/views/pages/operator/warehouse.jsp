<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:form action="${pageContext.request.contextPath}/Warehouse/Warehouse" method="POST" modelAttribute="warehouse" >
<link href="/Spring4MVCApacheTiles3Example/static/css/warehouse.css?v=<?php echo time(); ?>" rel="stylesheet" ></link>
    <div class="container bg-overlay">
      <div class="row" id="warehouseContainer">
        <!--FIRST ROW-->        
        <div class="Empty" class="card col-2">
          <figure class="figure">
            <img src="/Spring4MVCApacheTiles3Example/static/img/coxe_vacio.png"> 
          </figure>
        </div>

        <div  class="card col-2 row align-items-start">
          <figure class="figure-img " id="Workstation3_image">
            <img class="images" id="w3" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div  class="card col-2  row ">
          <figure class="figure-img " id="Parking2_image">
            <img class="images" id="p2" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div  class="card col-2 ">
          <figure class="figure-img" id="Workstation2_image">
            <img class="images" id="w2" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div  class="card col-2 ">
          <figure class="figure-img" id="Parking1_image">
            <img class="images" id="p1" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div  class="card col-2 ">
          <figure class="figure-img" id="Workstation1_image">
            <img class="images" id="w1" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

      <!--SECOND ROW-->
       <div class="Empty" class="card col-2">
          <figure class="figure">
            <img class="images" src="/Spring4MVCApacheTiles3Example/static/img/coxe_vacio.png"> 
          </figure>
        </div>

        <div class="card col-2 row align-items-start">
          <figure class="figure-img " id="Line5_image">
             <img class="images" id="l5" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div class="card col-2 row">
          <figure class="figure-img " id="Line4_image">
            <img class="images" id="l4" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line3" class="card col-2 ">
          <figure class="figure-img " id="Line3_image">
            <img class="images" id="l3" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line2" class="card col-2">
          <figure class="figure-img " id="Line2_image">
            <img class="images" id="l2" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line1" class="card col-2">
          <figure class="figure-img " id="Line1_image">
            <img class="images" id="l1" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <!--THIRD ROW-->
        <div id="line6" class="card col-2">
          <figure class="figure-img  align-self-end" id="Line6_image">
            <img class="images" id="l6" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line16" class="card col-2">
          <figure class="figure-img " id="Line16_image">
            <img class="images" id="l16" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line15" class="card col-2">
          <figure class="figure-img " id="Line15_image">
            <img class="images" id="l15" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line14" class="card col-2">
          <figure class="figure-img" id="Line14_image" >
            <img class="images" id="l14" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Line13" class="card col-2">
          <figure class="figure-img " id="Line13_image">
            <img class="images" id="l13" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line12" class="card col-2 ">
          <figure class="figure-img align-self-start" id="Line12_image">
             <img class="images" id="l12" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>
        <!--FOURTH ROW-->
       <div class="Empty" class="card col-2">
          <figure class="figure">
            <img class="images" src="/Spring4MVCApacheTiles3Example/static/img/coxe_vacio.png"> 
          </figure>
        </div>

        <div id="line7" class="card col-2  row jusitfy-content-start">
          <figure class="figure-img  align-self-start" id="Line7_image">
             <img class="images" id="l7" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="line8" class="card col-2 row">
          <figure class="figure-img " id="Line8_image" >
            <img class="images" id="l8" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line9" class="card col-2 ">
          <figure class="figure-img " id="Line9_image" >
            <img class="images" id="l9" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line10" class="card col-2 ">
          <figure class="figure-img " id="Line10_image" >
            <img class="images" id="l10" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="line11" class="card col-2 ">
          <figure class="figure-img " id="Line11_image" >
            <img class="images" id="l11" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
        <!-- FIFTH ROW -->
       <div class="Empty" class="card col-2">
          <figure class="figure">
            <img class="images"  src="/Spring4MVCApacheTiles3Example/static/img/coxe_vacio.png"> 
          </figure>
        </div>

        <div id="Workstation4" class="card col-2  row jusitfy-content-start">
          <figure class="figure-img  align-self-start" id="Workstation4_image">
             <img class="images" id="w4" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png"> 
          </figure>
        </div>

        <div id="Parking3" class="card col-2 row">
          <figure class="figure-img " id="Parking3_image">
            <img class="images" id="p3" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Workstation5" class="card col-2 ">
          <figure class="figure-img " id="Workstation5_image" >
            <img class="images" id="w3" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>

        <div id="Parking4" class="card col-2 ">
          <figure class="figure-img " id="Parking4_image" >
            <img class="images" id="p4" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
        <div id="Workstation6" class="card col-2 ">
          <figure class="figure-img " id="Workstation6_image" >
            <img class="images" id="w6" src="/Spring4MVCApacheTiles3Example/static/img/coxe.png">
          </figure>
        </div>
      </div>
    </div>
    <div id="content">
    <script src="/Spring4MVCApacheTiles3Example/static/scripts/warehouse.js"></script>
    </div>
</form:form>