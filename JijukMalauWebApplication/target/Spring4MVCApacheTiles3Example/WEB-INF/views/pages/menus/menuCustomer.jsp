<div class="col-md-12">
  <nav class="navbar navbar-expand-md navbar-dark secondary-color mb-5 no-content">
<!-- Breadcrumb-->
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item "><a href="${pageContext.request.contextPath}/Shop/load">Shop</a></li>
      <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
      <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/Profile/load">Profile</a></li>
    </ol> 
  </nav>
  <ul class="navbar-nav ml-auto nav-flex-icons">
    <!-- ICONO DE USER -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user"></i>
      </a>
      <div class="dropdown-menu dropdown-menu-right dropdown-unique" aria-labelledby="navbarDropdownMenuLink">
        <a class="dropdown-item waves-effect waves-light" href="${pageContext.request.contextPath}/Login/Logout">Log Out</a>
      </div>
    </li>
  </ul>
  </nav>
</div>