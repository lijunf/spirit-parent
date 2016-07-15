<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="资源管理" />
	<c:param name="permission" value="permission:manage" />
	<c:param name="url" value="/security/resource/list" />
	<c:param name="navigation" value="资源管理" />
	<c:param name="body">
		<spring:hasBindErrors name="person">
			<script type="text/javascript">
				$(document).ready(function() {
					$("div.form-group").addClass("has-error");
				});
			</script>
		</spring:hasBindErrors>
		<h3 class="page-header sb-form-header">
			<c:if test="${resource.id eq null}">添加资源</c:if>
			<c:if test="${resource.id ne null}">修改资源</c:if>
		</h3>
		<div class="sb-form">
			<form:form action="" method="post" modelAttribute="resource" cssClass="form-horizontal">
				<c:if test="${parent.id ne null}">
					<div class="form-group">
						<label for="resType" class="col-sm-2 control-label">上级</label>
						<div class="col-sm-10">
							<label class="control-label">${parent.name}（${parent.permission}）</label>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label for="resType" class="col-sm-2 control-label">资源类型</label>
					<div class="col-sm-10">
						<form:select path="resType" disabled="true" cssClass="form-control">
							<form:option value="1">菜单级别</form:option>
							<form:option value="2">按钮级别</form:option>
						</form:select>
						<form:errors path="resType" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">资源名称</label>
					<div class="col-sm-10">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="permission" class="col-sm-2 control-label">权限编码</label>
					<div class="col-sm-10">
						<form:input path="permission" cssClass="form-control" />
						<form:errors path="permission" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="href" class="col-sm-2 control-label">资源URL</label>
					<div class="col-sm-10">
						<form:input path="href" cssClass="form-control" />
						<form:errors path="href" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="orderNo" class="col-sm-2 control-label">排序编号</label>
					<div class="col-sm-10">
						<form:input path="orderNo" cssClass="form-control" type="number"/>
						<form:errors path="orderNo" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="iconCls" class="col-sm-2 control-label">ICON</label>
					<div class="col-sm-10">
						<form:input path="iconCls" cssClass="form-control" />
						<form:errors path="iconCls" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">资源描述</label>
					<div class="col-sm-10">
						<form:textarea path="description" cssClass="form-control" />
						<form:errors path="description" element="span" cssStyle="color:red;" />
					</div>
				</div>
				<form:hidden path="id" />
				<input type="hidden" name="pId" value="${parent.id}">
				<input type="hidden" name="resType" value="${resource.resType}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">提交</button>&nbsp;
						<button type="reset" class="btn">重置</button>&nbsp;
						<a href='${pageContext.request.contextPath}/security/resource/list' class="btn">返回</a>
					</div>
				</div>
			</form:form>

		</div>
	</c:param>
</c:import>

