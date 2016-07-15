<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="字典管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/dict/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="字典管理" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<shiro:hasPermission name="dict:add">
		<p>
			<a href='${pageContext.request.contextPath}/system/dict/create' class="btn btn-info">添加</a>
			<a href="javascript:get('${pageContext.request.contextPath}/system/cache/refresh/dict')" class="btn btn-info">刷新缓存</a>
		</p>
		</shiro:hasPermission>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>操作</th>
					<th>代码</th>
					<th>名称</th>
					<th>描述</th>
					<th>创建者</th>
				</tr>
				<c:forEach items="${dictTypeList}" var="dict">
					<tr>
						<td nowrap>
							<shiro:hasPermission name="dict:edit">
								<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/system/dict/edit/${dict.dictTypeId}'>edit</a>&nbsp; 
							</shiro:hasPermission>
							<shiro:hasPermission name="dict:delete">
								<button onclick="deleteconfig('${dict.dictTypeId}', '${dict.dictTypeName}')" class="btn btn-primary btn-xs">delete</button>&nbsp;
							</shiro:hasPermission>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/system/dict/entry/list/${dict.dictTypeId}'>常量维护</a>
						</td>
						<td>${dict.dictTypeId}</td>
						<td>${dict.dictTypeName}</td>
						<td>${dict.description}</td>
						<td>${dict.creator}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		
		<script type="text/javascript">
			function deleteconfig(id, name) {
				bootbox.confirm("确定删除字典<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/system/dict/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>