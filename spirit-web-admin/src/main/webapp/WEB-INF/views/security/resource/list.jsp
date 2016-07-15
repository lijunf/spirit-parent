<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="资源管理" />
	<c:param name="permission" value="permission:manage" />
	<c:param name="url" value="/security/resource/list" />
	<c:param name="navigation" value="资源管理" />
	<c:param name="body">
		<shiro:hasPermission name="resource:add">
		<p>
			<a href='${pageContext.request.contextPath}/security/resource/create' class="btn btn-info">CREATE</a>
		</p>
		</shiro:hasPermission>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>名称</th>
					<th>操作</th>
					<th>权限</th>
					<th>地址</th>
				</tr>
				<c:forEach items="${applicationScope.topResourceList}" var="resource">
					<tr class="success">
						<td nowrap>${resource.name}</td>
						<td nowrap>
							<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/edit/${resource.id}'>edit</a>&nbsp;
							<c:if test="${resource.subResource eq null || resource.subResource.size() eq 0}">
								<button onclick="deleteResource('${resource.id}', '${resource.name}')" class="btn btn-primary btn-xs">delete</button>&nbsp;
							</c:if>
							<a class="btn btn-primary btn-xs" title="添加子资源" href='${pageContext.request.contextPath}/security/resource/create?pId=${resource.id}'>add</a>
						</td>
						<td>${resource.permission}</td>
						<td>${resource.href}</td>
					</tr>
					<c:forEach items="${resource.subResource}" var="subRes">
						<tr class="info">
							<td nowrap>——${subRes.name}</td>
							<td nowrap>
								<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/edit/${subRes.id}'>edit</a>&nbsp;
								<c:if test="${subRes.subResource eq null || subRes.subResource.size() eq 0}">
									<button onclick="deleteResource('${subRes.id}', '${subRes.name}')" class="btn btn-primary btn-xs">delete</button>&nbsp;
								</c:if>
								<a class="btn btn-primary btn-xs" title="添加子资源" href='${pageContext.request.contextPath}/security/resource/create?pId=${subRes.id}'>add</a>
							</td>
							<td>${subRes.permission}</td>
							<td>${subRes.href}</td>
						</tr>
						<c:forEach items="${subRes.subResource}" var="subRes2">
							<tr>
								<td nowrap>————${subRes2.name}</td>
								<td nowrap>
									<a class="btn btn-primary btn-xs" href='${pageContext.request.contextPath}/security/resource/edit/${subRes2.id}'>edit</a>&nbsp;
									<button onclick="deleteResource('${subRes2.id}', '${subRes2.name}')" class="btn btn-primary btn-xs">delete</button>
								</td>
								<td>${subRes2.permission}</td>
								<td>${subRes2.href}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
		
		<script type="text/javascript">
			function deleteResource(id, name) {
				bootbox.confirm("确定删除资源<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/security/resource/delete/' + id;
					}
				});
			}
		</script>
	</c:param>
</c:import>

