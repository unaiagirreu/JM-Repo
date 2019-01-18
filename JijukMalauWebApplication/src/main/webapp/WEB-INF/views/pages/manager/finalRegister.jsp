<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="my-form">
  <div class="cotainer">
    <div class="row justify-content-center">
      <div class="col-8 align-items-center">
        <div class="card">
          <div class="card-header text-info text-center"><a> Register</a></div>
          <div class="card-body">
            <form:form action="${pageContext.request.contextPath}/Register/register" method="GET" modelAttribute="register" name="my-form" onsubmit="return validform()">
		      <div class="form-row col-auto justify-content-around">
                <div class="form-group col-12 col-md-6 srow align-items-center">
                    <a>Name:</a>
                    <form:input path="name" required="required" type="text" id="name" class="form-control" name="name" />
                </div>
                <div class="form-group col-12 col-md-6 srow align-items-center">
                  <a>Surname:</a>
                  <form:input path="surname" required="required" type="text" id="lastName" class="form-control" name="surname"/>
                </div>
		      </div>
		      <div class="form-group row justify-content-start">
                  <a>E-Mail Address:</a>
                  <form:input path="email" type="email" required="required" id="email" class="form-control" name="email-address"/>
              </div>
              <div class="form-row col-auto justify-content-around">
              	<div class="form-group col-12 col-md-6 row align-items-center">
                  <a>Username:</a>
                  <form:input path="username" required="required" type="text" id="username" class="form-control" name="username"/>
              	</div>
                <div class="form-group col-12 col-md-6 row align-items-center">
                  <a>Password:</a>
                  <form:input path="password" required="required" type="password" id="password" class="form-control" name="password"/>
              	</div>
              </div>
		      <a>Direction:</a>
              <div class="form-row col-auto justify-content-around">
                <div class="form-group col-12 col-md-6 row align-items-center">
                  <a>Country:</a>
                  <form:input path="country" required="required" type="text" id="country" class="form-control" name="full-name"/>
                </div>
                <div class="form-group col-12 col-md-6 row align-items-center">
                  <a>City:</a>
                  <form:input path="city" required="required" type="text" id="city" class="form-control" name="full-name"/>
                </div>
              </div>
		      <div class="form-row col-auto justify-content-around">
                <div class="form-group col-12 col-md-6 row align-items-center">
                  <a>Postal Code:</a>
                  <form:input path="postalCode" required="required" type="text" id="postalCode" class="form-control" name="postalCode"/>
                </div>
              <div class="form-group col-12 col-md-6 row align-items-center">
              	<a>Street:</a>
              	<form:input path="street" required="required" type="text" id="street" class="form-control" name="street"/>
		      </div>
		    </div>
            <div class="form-row col-auto justify-content-around">
              <div class="form-group col-12 col-md-4 row align-items-center">
                <a>Portal:</a>
                <form:input path="portalNumber" required="required" type="text" id="number" class="form-control" name="portal"/>
              </div>
              <div class="form-group col-12 col-md-4 row align-items-center">
                <a>Floor:</a>
                <form:input path="floor" required="required" type="text" id="number" class="form-control" name="floor"/>
              </div>
              <div class="form-group col-12 col-md-4 row align-items-center">
                <a>Letter:</a>
                <form:input path="letter" required="required" type="text" id="postalCode" class="form-control" name="letter"/>
              </div>
		    </div>
            <div class="col-md-6 offset-md-4">
              <button type="submit" class="btn  btn-lg float-right" style="color:#358E9E" id="btnRegister" value="Register"><a>Register</a></button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>