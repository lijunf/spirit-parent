<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="用户管理" />
	<c:param name="permission" value="permission:manage" />
	<c:param name="url" value="/security/user/list" />
	<c:param name="navigation" value="用户管理" />
	<c:param name="body">
		<link href="<c:url value="/resources/bootstrap/css/bootstrapValidator.min.css" />" rel="stylesheet" />
		<c:if test="${message!=null}">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<shiro:hasPermission name="user:add">
					<a href='#createModal' data-toggle="modal" class="glyphicon glyphicon-plus"></a>
				</shiro:hasPermission>
				<a role="button" class="pull-right" data-toggle="collapse" href="#searchForm" aria-expanded="true" aria-controls="collapseSearch">
					<span class="caret-lg"></span>
				</a>
			</div>
			<div class="panel-body sb-search">
				<form class="form-inline collapse in" id="searchForm">
					<div class="form-group">
						<label class="sr-only" for="nameSearch">账号</label>
						<input type="text" class="form-control input-sm" name="name" id="nameSearch" value="${user.name }" placeholder="账号">
					</div>
					<div class="form-group">
						<label class="sr-only" for="realNameSearch">姓名</label>
						<input type="text" class="form-control input-sm" name="realName" id="realNameSearch" value="${user.realName }" placeholder="姓名">
					</div>
					<div class="form-group">
						<label class="sr-only" for="mobileSearch">手机</label>
						<input type="text" class="form-control input-sm" name="mobile" id="mobileSearch" value="${user.mobile }" placeholder="手机">
					</div>
					<div class="form-group">
						<label class="sr-only" for="emailSearch">电子邮箱</label>
						<input type="email" class="form-control input-sm" name="email" id="emailSearch" value="${user.email }" placeholder="电子邮箱">
					</div>
					<input type="hidden" name="page" value="${paging.number }">
					<button type="submit" class="btn btn-primary btn-sm">Search</button>
				</form>
			</div>
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
					<tr>
						<th>帐号</th>
						<th>操作</th>
						<th>姓名</th>
						<th>手机</th>
						<th>最后登录时间</th>
					</tr>
					<c:forEach items="${paging.content}" var="user">
						<tr>
							<td>${user.name}</td>
							<td nowrap>
								<shiro:hasPermission name="user:edit">
									<button onclick="editUser('${pageContext.request.contextPath}/security/user/edit/${user.id}')" 
										class="btn btn-primary btn-xs" data-toggle="modal" data-target="#editModal">edit</button>&nbsp; 
									<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/user/grant/${user.id}'>grant</a>&nbsp;
								</shiro:hasPermission>
								<shiro:hasPermission name="user:delete">
									<button onclick="deleteUser('${user.id}', '${user.name}')" class="btn btn-primary btn-xs">delete</button>
								</shiro:hasPermission>
							</td>
							<td>${user.realName}</td>
							<td>${user.mobile}</td>
							<td>${user.lastLogin}</td>
						<tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="pagination-search-wrapper">
			<jsp:include page="/WEB-INF/template/pagination-search.jsp"/>
		</div>
		
		<script type="text/javascript">
			function deleteUser(id, name) {
				bootbox.confirm("确定删除用户<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/security/user/delete/' + id;
					}
				});
			}
		
			$(document).ready(function() {
				var setting = {
			        message: 'This value is not valid',
			        feedbackIcons: {
			            valid: 'glyphicon glyphicon-ok',
			            invalid: 'glyphicon glyphicon-remove',
			            validating: 'glyphicon glyphicon-refresh'
			        },
			        fields: {
			        	name: {
			                validators: {
			                    notEmpty: {
			                        message: '用户账户不能为空'
			                    }
			                }
			            },
			            employeeId: {
			            	validators: {
			            		notEmpty: {
			                        message: '员工ID不能为空'
			                    }
			                }
			            },
			            realName: {
			                validators: {
			                    notEmpty: {
			                        message: '真实姓名不能为空'
			                    }
			                }
			            },
			            email: {
			                validators: {
			                	notEmpty: {
			                        message: '电子邮箱不能为空'
			                    },
			                    emailAddress: {
			                        message: '请填写有效的邮箱地址'
			                    }
			                }
			            },
			            password: {
			                validators: {
			                    notEmpty: {
			                        message: '密码不能为空'
			                    }
			                }
			            },
			            mobile: {
			                validators: {
			                    notEmpty: {
			                        message: '手机不能为空'
			                    },
			                    regexp: {
			                        regexp: /^(13[0-9]{9})|(15[89][0-9]{8})$/,
			                        message: '请填写有效的手机号码'
			                    }
			                }
			            }
			        }
			    };
				$('#createForm').bootstrapValidator(setting);
				$('#editForm').bootstrapValidator(setting);
			});
			
			// 编辑用户
			function editUser(path) {
				$.ajax({
					url : path,
					type : 'get',
					async : true,
					dataType: "json",
					success: function(user) {
						$('#id').val(user.id);
                        $('#name').val(user.name);
                        $('#employeeId').val(user.employeeId);
                        $('#realName').val(user.realName);
                        $('#email').val(user.email);
                        $('#mobile').val(user.mobile);
                     }
				});
			}
		</script>

		<!-- Create Modal -->
		<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<form role="form" id="createForm" action="${pageContext.request.contextPath}/security/user/create" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<b>创建新用户</b>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input class="form-control" type="text" name="name" placeholder="用户账户"	autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="password" name="password" placeholder="密码" autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="employeeId" placeholder="员工ID">
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="realName" placeholder="真实姓名" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="email" name="email" placeholder="电子邮箱" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="number" name="mobile" placeholder="手机" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">确定</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- Edit Modal -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<form:form id="editForm" action="${pageContext.request.contextPath}/security/user/edit" method="post" modelAttribute="user">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<b>编辑用户信息</b>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input id="id" name="id" type="hidden">
								<input class="form-control" id="name" type="text" name="name" placeholder="用户账户" autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="password" name="password" placeholder="密码" autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" id="employeeId" type="text" name="employeeId" placeholder="员工ID" required>
							</div>
							<div class="form-group">
								<input class="form-control" id="realName" type="text" name="realName" placeholder="真实姓名" required>
							</div>
							<div class="form-group">
								<input class="form-control" id="email" type="email" name="email" placeholder="Email" required>
							</div>
							<div class="form-group">
								<input class="form-control" id="mobile" type="number" name="mobile" placeholder="手机" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>

		<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrapValidator.min.js" />"></script>
	</c:param>
</c:import>

