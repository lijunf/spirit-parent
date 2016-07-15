<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="客户管理" />
	<c:param name="permission" value="person:manage" />
	<c:param name="url" value="/person/list" />
	<c:param name="navigation" value="客户管理" />
	<c:param name="body">
		<spring:hasBindErrors name="person">
			<script type="text/javascript">
				$(document).ready(function() {
					$("div.form-group").addClass("has-error");
				});
			</script>
		</spring:hasBindErrors>
		<h3 class="page-header sb-form-header">
			<c:if test="${person.id eq null}">添加客户</c:if>
			<c:if test="${person.id ne null}">修改客户</c:if>
		</h3>
		<div class="sb-form">
			<form:form action="" method="post" modelAttribute="person" cssClass="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="age" class="col-sm-2 control-label">年龄</label>
					<div class="col-sm-10">
						<form:input path="age" cssClass="form-control" type="number"/>
						<form:errors path="age" element="text" cssStyle="color:red;" />
					</div>
				</div>
				<form:hidden path="id" />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">提交</button>&nbsp;
						<button type="reset" class="btn">重置</button>&nbsp;
						<a href='${pageContext.request.contextPath}/person/list' class="btn">返回</a>
					</div>
				</div>
			</form:form>

		</div>
	</c:param>
</c:import>

