<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>${param.title}</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/bootstrap/css/sb-admin.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/bootstrap/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootbox.js" />"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Spirit</a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
					<ul class="dropdown-menu message-dropdown">
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong></h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong>
										</h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-preview">
							<a href="#">
								<div class="media">
									<span class="pull-left">
										<img class="media-object" src="" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading"><strong>John Smith</strong>
										</h5>
										<p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
							</a>
						</li>
						<li class="message-footer">
							<a href="#">Read All New Messages</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
					<ul class="dropdown-menu alert-dropdown">
						<li>
							<a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
						</li>
						<li>
							<a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">View All</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-user"></i>
						<shiro:principal property="realName" />
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:viewUserInfo()"><i class="fa fa-fw fa-user"></i> 个人信息</a>
						</li>
						<li>
							<a href="javascript:updatePassWord()"><i class="fa fa-fw fa-gear"></i> 修改密码</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="${pageContext.request.contextPath}/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<div class="nav navbar-nav side-nav">
					<%-- 显示一级菜单 --%>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<c:if test="${param.permission eq null}">
							<div class="panel panel-default">
							    <div class="panel-heading current" role="tab" id="heading-home">
							      <h4 class="panel-title">
							        <a role="button">
							        	<i class="glyphicon glyphicon-home"></i>
							        	首页
							        	<span class="pull-right glyphicon glyphicon-chevron-right"></span>
							        </a>
							      </h4>
							    </div>
							</div>
						</c:if>
						<c:forEach items="${applicationScope.topResourceList}" var="resource">
							<shiro:hasPermission name="${resource.permission}">
								<div class="panel panel-default">
								    <div class="panel-heading <c:if test="${param.permission eq resource.permission}">current</c:if>" role="tab" id="heading${resource.id}">
								      <h4 class="panel-title">
								        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${resource.id}" 
								        	<c:if test="${param.permission ne resource.permission}">aria-expanded="false" class="collapsed"</c:if> 
								        	aria-controls="collapse${resource.id}">
								        	<i class="glyphicon ${resource.iconCls}"></i>
								          	${resource.name}
								          	<span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
								        </a>
								      </h4>
								    </div>
								    <div id="collapse${resource.id}" class="panel-collapse collapse <c:if test="${param.permission eq resource.permission}">in</c:if>" 
								    	role="tabpanel" aria-labelledby="heading${resource.id}">
								      <div class="panel-body">
								      	<ul class="nav nav-stacked">
								      		<c:forEach items="${resource.subResource}" var="subRes">
												<shiro:hasPermission name="${subRes.permission}">
													<li <c:if test="${param.url eq subRes.href}">class="active"</c:if>>
														<a href="${pageContext.request.contextPath}${subRes.href}">
															<i class="glyphicon ${subRes.iconCls}"></i>
															${subRes.name}
														</a>
													</li>
												</shiro:hasPermission>
											</c:forEach>
										</ul>
								      </div>
								    </div>
							  	</div>
							</shiro:hasPermission>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	
		<div id="page-wrapper">
			<div class="container-fluid">
				<c:if test="${param.navigation ne null}">
					<!-- Page Heading -->
			        <div class="row">
			            <div class="col-lg-12">
			                <ol class="breadcrumb" style="margin-top: 5px;">
			                    <li>
			                        <i class="glyphicon glyphicon-home"></i>
			                        <a href="${pageContext.request.contextPath}/home">首页</a>
			                    </li>
			                    <li class="active">
			                        ${param.navigation}
			                    </li>
			                </ol>
			            </div>
			        </div>
				</c:if>
				${param.body}
			</div>
		</div>
		<!-- /#page-wrapper -->
	
		<script type="text/javascript">
		
			/**
			 *	查询用户信息
			 */
			function viewUserInfo() {
				bootbox.dialog({
			        title: "个人信息",
			        message: '<div class="row">  ' +
			          '<div class="col-xs-10 col-xs-offset-1"> ' +
			          '<form class="form-horizontal"> ' +
			          '<div class="form-group"> ' +
			          '用户名：<shiro:principal property="name" />' +
			          '</div> ' +
			          '<div class="form-group"> ' +
			          '真实姓名：<shiro:principal property="realName" />' +
			          '</div> ' +
			          '<div class="form-group"> ' +
			          '手机号码：TODO' +
			          '</div> ' +
			          '<div class="form-group"> ' +
			          '邮箱地址：TODO' +
			          '</div> ' +
			          '</form> </div>  </div>',
			        buttons: {
			          success: {
			            label: "Close",
			            className: "btn-primary",
			            callback: function () {
			            }
			          }
			        }
				});
			}
			
			/**
			 *	修改用户密码
			 */
			function updatePassWord() {
				bootbox.dialog({
			        title: "修改密码",
			        message: '<div class="row">  ' +
			          '<div class="col-xs-10 col-xs-offset-1"> ' +
			          '<form id="updatePassWordForm" class="form-horizontal"> ' +
			          '<div class="form-group"> ' +
			          '<input id="password" name="password" type="password" placeholder="原密码" class="form-control" autocomplete="off" required> ' +
			          '</div> ' +
			          '<div class="form-group"> ' +
			          '<input id="newpassword" name="newpassword" type="password" placeholder="新密码" class="form-control" autocomplete="off" required> ' +
			          '</div> ' +
			          '<div class="form-group"> ' +
			          '<input id="newpassword2" name="newpassword2" type="password" placeholder="重复密码" class="form-control" autocomplete="off" required> ' +
			          '</div> ' +
			          '</form> </div>  </div>',
			        buttons: {
			          success: {
			            label: "Save",
			            className: "btn-primary",
			            callback: function () {
			              // TODO 待完成
			              var password = $('#password').val();
			              var newpassword = $('#newpassword').val();
			              var newpassword2 = $('#newpassword2').val();
			              if (!password) {
			            	  return false;
			              }
			              if (!newpassword || !newpassword2 || newpassword != newpassword2) {
			            	  return false;
			              }
			            }
			          }
			        }
				});
			}
		
		</script>
	</div>
	<!-- /#wrapper -->
	<script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
</body>
</html>
