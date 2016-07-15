<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="系统参数管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/config/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="系统参数管理" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<shiro:hasPermission name="config:add">
		<p>
			<a href='${pageContext.request.contextPath}/system/config/create' class="btn btn-info">添加</a>
			<a href="javascript:get('${pageContext.request.contextPath}/system/cache/refresh/config')" class="btn btn-info">刷新缓存</a>
		</p>
		</shiro:hasPermission>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>操作</th>
					<th>参数名</th>
					<th>参数值</th>
					<th>描述</th>
					<th>创建者</th>
				</tr>
				<c:forEach items="${configList}" var="config">
					<tr>
						<td nowrap>
							<shiro:hasPermission name="config:edit">
								<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/system/config/edit/${config.id}'>edit</a>&nbsp; 
							</shiro:hasPermission>
							<shiro:hasPermission name="config:delete">
								<button onclick="deleteconfig('${config.id}', '${config.key}')" class="btn btn-primary btn-xs">delete</button>
							</shiro:hasPermission>
						</td>
						<td>${config.key}</td>
						<td>${config.value}</td>
						<td>${config.description}</td>
						<td>${config.creator}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		
		<script type="text/javascript">
			function deleteconfig(id, name) {
				bootbox.confirm("确定删除系统参数<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/system/config/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>