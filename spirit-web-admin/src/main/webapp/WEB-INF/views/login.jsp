<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
	body {
		/* background-color: #eee; */
	}
	
	img {
		cursor: pointer;
	}
	
	.navbar {
		border-radius: 0;
	}
	.col-sm-4 {
	    background: #fff;
    	border-radius: 10px;
    	box-shadow: 0 5px 15px rgba(0,0,0,.5);
    }
	.login {
		max-width: 320px;
    	margin: 0 auto;
	}
	
</style>
<script type="text/javascript">
	function refreshImage() {
		document.getElementById("captchaImage").setAttribute("src",	"Kaptcha.jpg?" + Math.round(Math.random() * 10000));
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Welcome To Spirit Authentication</a>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="container-fluid login">
					<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">
						<h2 class="form-signin-heading">Please sign in</h2>
						<div class="form-group">
							<input class="form-control" type="text" name="username" value="<shiro:principal property="name"/>" placeholder="Login UserName" required autofocus> 
						</div>
						<div class="form-group">
							<input class="form-control" type="password" name="password" placeholder="Password" autocomplete="off" required> 
						</div>
						<div class="form-group">
							<input class="form-control" type="text" name="code"	autocomplete="off" placeholder="Verification code" required>
						</div>
						<div class="form-group">
							<div style="float: left; margin-top: 5px;">
								<label class="checkbox" style="margin-left: 20px;"> 
									<input type="checkbox" name="rememberMe" value="true"> Remember me
								</label>
							</div>
							<div style="float: right">
								<img src="${pageContext.request.contextPath}/Kaptcha.jpg" id="captchaImage" onclick="javascript:refreshImage()" />
							</div>
						</div>
						<div class="form-group">
							<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
						</div>
						<div class="form-group">
							<c:if test="${message!=null}">
								<div class="alert alert-danger alert-dismissible" role="alert">
									<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>	
									${message}
								</div>
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${message!=null}">
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	</c:if>
</body>
</html>
