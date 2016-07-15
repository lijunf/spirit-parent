<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="客户管理" />
	<c:param name="permission" value="person:manage" />
	<c:param name="url" value="/person/list" />
	<c:param name="navigation" value="客户管理" />
	<c:param name="body">
		<div class="panel panel-default">
			<div class="panel-heading">
				<shiro:hasPermission name="person:add">
					<a href='${pageContext.request.contextPath}/person/create' class="glyphicon glyphicon-plus"></a>
				</shiro:hasPermission>
				<a role="button" class="pull-right" data-toggle="collapse" href="#searchForm" aria-expanded="true" aria-controls="collapseSearch">
					<span class="caret-lg"></span>
				</a>
			</div>
			<div class="panel-body sb-search">
				<form class="form-inline collapse in" id="searchForm" aria-expanded="true">
					<div class="form-group">
						<label class="sr-only" for="nameSearch">NAME</label>
						<input type="text" class="form-control input-sm" name="name" id="nameSearch" value="${person.name }" placeholder="NAME">
					</div>
					<div class="form-group">
						<label class="sr-only" for="ageSearch">AGE</label>
						<input type="number" class="form-control input-sm" name="age" id="ageSearch" value="${person.age }" placeholder="AGE">
					</div>
					<input type="hidden" name="page" value="${paging.number }">
					<button type="submit" class="btn btn-primary btn-sm">Search</button>
				</form>
			</div>
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
					<tr>
						<th>Action</th> 
						<th>NAME</th>
						<th>AGE</th>
					</tr>
					<c:forEach items="${paging.content}" var="person">
						<tr>
							<td>
								<shiro:hasPermission name="person:edit">
									<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/person/edit/${person.id}'>edit</a>&nbsp;&nbsp; 
								</shiro:hasPermission>
								<shiro:hasPermission name="person:delete">
									<a class="btn btn-primary btn-xs" href="javascript:deletePerson('${person.id}', '${person.name}')">delete</a>
								</shiro:hasPermission>
							</td>
							<td>${person.name}</td>
							<td>${person.age}</td>
						<tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="pagination-search-wrapper">
			<jsp:include page="/WEB-INF/template/pagination-search.jsp"/>
		</div>
		
		<script type="text/javascript">
			function deletePerson(id, name) {
				bootbox.confirm("确定删除客户<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/person/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>

