<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="角色管理" />
	<c:param name="permission" value="permission:manage" />
	<c:param name="url" value="/security/role/list" />
	<c:param name="navigation" value="角色管理" />
	<c:param name="body">
		<shiro:hasPermission name="role:add">
		<p>
			<a href='${pageContext.request.contextPath}/security/role/create' class="btn btn-info">CREATE</a>
		</p>
		</shiro:hasPermission>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>名称</th>
					<th>操作</th>
					<th>资源</th>
				</tr>
				<c:forEach items="${roleList}" var="role">
					<tr>
						<td>${role.name}</td>
						<td nowrap>
							<shiro:hasPermission name="role:edit">
								<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/role/edit/${role.id}'>edit</a>&nbsp; 
							</shiro:hasPermission>
							<shiro:hasPermission name="role:delete">
								<button onclick="deleteRole('${role.id}', '${role.name}')" class="btn btn-primary btn-xs">delete</button>
							</shiro:hasPermission>
						</td>
						<td>${role.viewResources}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		
		<script type="text/javascript">
			function deleteRole(id, name) {
				bootbox.confirm("确定删除角色<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/security/role/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>

