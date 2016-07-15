<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="系统参数管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/config/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="系统参数管理" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<h3 class="page-header sb-form-header">
			<c:if test="${config.id eq null}">添加系统参数</c:if>
			<c:if test="${config.id ne null}">修改系统参数</c:if>
		</h3>
		<div class="sb-form">
			<form:form action="" method="post" modelAttribute="config" cssClass="form-horizontal">
				<c:if test="${message!=null}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${message}
					</div>
				</c:if>
				<div class="form-group">
					<label for="key" class="col-sm-2 control-label">参数名</label>
					<div class="col-sm-10">
						<form:input path="key" cssClass="form-control" />
						<form:errors path="key" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="value" class="col-sm-2 control-label">参数值</label>
					<div class="col-sm-10">
						<form:input path="value" cssClass="form-control" />
						<form:errors path="value" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="creator" class="col-sm-2 control-label">创建者</label>
					<div class="col-sm-10">
						<form:input path="creator" cssClass="form-control" />
						<form:errors path="creator" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">参数描述</label>
					<div class="col-sm-10">
						<form:textarea path="description" cssClass="form-control" />
						<form:errors path="description" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<form:hidden path="id" />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">提交</button>&nbsp;
						<button type="reset" class="btn">重置</button>&nbsp;
						<a href='${pageContext.request.contextPath}/system/config/list' class="btn">返回</a>
					</div>
				</div>
			</form:form>
		</div>
	</c:param>
</c:import>

