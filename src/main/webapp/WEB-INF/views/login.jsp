<%@include file="./common/header.jsp" %>	

	<main class="main-section">
	<section id="login">
		<div class="form-login-wrapper">
			<header class="form-login-head">
				<h2>Login</h2>
			</header>
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="POST">


				<c:if test="${param.error != null}">
					<p style="color: red; text-align: center;">Invalid username and password.</p>
				</c:if>
				<c:if test="${param.logout != null}">
					<p style="color: green;text-align: center;">You have been logged out successfully.</p>
				</c:if>

				<div class="name-login-form">
					<label for="name">Username:</label> 
					<input id="name" type="text" name="ssoId" placeholder="Enter Username"required />
				</div>
				<div class="password-login-form">
					<label for="password">Password:</label> 
					<input id="password" type="password"  name="password" placeholder="Enter Password"/>
				</div>
				
				<div class="remember-login-form">
					<label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
				</div>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
				<button type="submit">Log in</button>
			</form>
		</div>
	</section>
	</main>
	
	<%@include file="./common/footer.jsp" %>	