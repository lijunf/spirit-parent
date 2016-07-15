<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="字典常量管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/dict/entry/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="字典常量管理" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<p>
			<a class="btn btn-info" data-toggle="modal" data-target="#createModal">CREATE</a>
		</p>
		<div class="table-responsive">
			<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
				<tr>
					<th>TYPE</th>
					<th>操作</th>
					<th>代码</th>
					<th>名称</th>
				</tr>
				<c:forEach items="${dictEntryList}" var="dict">
					<tr>
						<td>${dictTypeId}</td>
						<td nowrap>
							<button onclick="editDictEntry('${pageContext.request.contextPath}/system/dict/entry/edit/${dictTypeId}/${dict.dictId}')" 
									class="btn btn-primary btn-xs" data-toggle="modal" data-target="#editModal">edit</button>
							<button onclick="deleteconfig('${dict.id}', '${dict.dictName}')" class="btn btn-primary btn-xs">delete</button>
						</td>
						<td>${dict.dictId}</td>
						<td>${dict.dictName}</td>
					<tr>
				</c:forEach>
			</table>
		</div>
		
		<!-- Create Modal -->
		<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<form role="form" id="createForm" action="${pageContext.request.contextPath}/system/dict/entry/create" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<b>创建新字典常量</b>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input class="form-control" type="text" name="dictTypeId" value="${dictTypeId}" readOnly autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="dictId" placeholder="代码"	autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="dictName" placeholder="名称" autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="number" name="orderNo" placeholder="排序编号">
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
				<form:form id="editForm" action="${pageContext.request.contextPath}/system/dict/entry/edit" method="post" modelAttribute="user">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
								<b>编辑字典常量</b>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" name="id" id="id">
								<input class="form-control" type="text" name="dictTypeId" value="${dictTypeId}" readOnly autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="dictId" id="dictId" placeholder="代码" readOnly autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="text" name="dictName" id="dictName" placeholder="名称" autocomplete="off" required>
							</div>
							<div class="form-group">
								<input class="form-control" type="number" name="orderNo" id="orderNo" placeholder="排序编号">
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
		
		<script type="text/javascript">
			function deleteconfig(id, name) {
				bootbox.confirm("确定删除字典常量<font color='red'>" + name + "</font>?", function(result) {
					if (result == true) {
						window.location.href = '${pageContext.request.contextPath}/system/dict/entry/delete/${dictTypeId}/' + id;
					}
				});
			}
			
			// 编辑字典常量
			function editDictEntry(path) {
				$.ajax({
					url : path,
					type : 'get',
					async : true,
					dataType: "json",
					success: function(entry) {
						$('#id').val(entry.id);
						$('#dictId').val(entry.dictId);
                        $('#dictName').val(entry.dictName);
                        $('#orderNo').val(entry.orderNo);
                     }
				});
			}
		</script>
	</c:param>
</c:import>