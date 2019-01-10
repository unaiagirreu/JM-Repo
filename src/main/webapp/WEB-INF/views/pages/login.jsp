<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form action="${pageContext.request.contextPath}/Login/Login" method="POST" modelAttribute="login" >
<div class="container py-5">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center text-white mb-4"></h2>
            <div class="row">
                <div class="col-md-6 mx-auto">

                    <!-- form card login -->
                    <div class="card rounded-0">
                        <div class="card-header text-center text-info">
                            <a class="text-center">Please, log in to continue</a>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST">
                                <div class="form-group">
                                    <a for="uname1">Username</a>
                                    <form:input type="text" path="username" class="form-control form-control-lg rounded-0" name="uname1" id="uname1" required=""/>
                                    <div class="invalid-feedback">Oops, you missed this one.</div>
                                </div>
                                <div class="form-group">
                                    <a>Password</a>
                                    <form:input type="password" path="password" class="form-control form-control-lg rounded-0" id="pwd1" required="" autocomplete="new-password"/>
                                    <div class="invalid-feedback">Enter your password too!</div>
                                </div>
                                <button type="submit" class="btn  btn-lg float-right " id="btnLogin" value="Log in"><a>Login</a></button>
                            </form>
                        </div>
                        <!--/card-block-->
                        <div>
                        	<span id="log"></span>
                        </div>
                    </div>
                    <!-- /form card login -->
                </div>
            </div>
            <!--/row-->
		</div>
	</div>
</div>
</form:form>
