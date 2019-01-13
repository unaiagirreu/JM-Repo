  <div class="col-md-12">
    <nav class="navbar navbar-expand-md navbar-dark secondary-color mb-5 no-content">
<!-- Breadcrumb-->
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item "><a href="${pageContext.request.contextPath}/register">Shop</a></li>
        <li class="breadcrumb-item" aria-current="page"><a href="shop.html">Contact</a></li>
        <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
        <li class="breadcrumb-item" aria-current="page"><a href="shop.html">Contacts</a></li>
      </ol> 
    </nav>
    <ul class="navbar-nav ml-auto nav-flex-icons">
    <li><a id="numberOfProducts">3</a></li>
    <!-- CARRITO SOLO PARA LA SHOP -->
    <li class="nav-item dropleft">
        <a class="nav-link align-items-center" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-shopping-cart"></i>
        </a>
            <div class="dropdown-menu dropdown-menu-left dropdown-unique row align-items-center " aria-labelledby="navbarDropleftMenuLink">
              
              <a class="dropdown-item waves-effect waves-light" href="#">Lista de productos</a>
              <button type="submit" class="btn  btn-lg col align-self-center " id="btnRegister"><a href="confirmOrder.html">Confirm order</a></button>
        </div>
      </li>
      <!-- ICONO DE USER -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-right dropdown-unique" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item waves-effect waves-light" href="#">Log Out</a>
        </div>
      </li>
    </ul>
    </nav>
  </div>