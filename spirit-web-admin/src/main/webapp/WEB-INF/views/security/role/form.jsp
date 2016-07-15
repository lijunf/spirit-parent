<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="角色管理" />
	<c:param name="permission" value="permission:manage" />
	<c:param name="url" value="/security/role/list" />
	<c:param name="navigation" value="角色管理" />
	<c:param name="body">

		<script type="text/javascript">
			var $checkableTree;
			var chk_value = "${role.viewPermissions}".split(",");
			$(document).ready(function() {
				var defaultData = [
				<c:forEach items="${applicationScope.topResourceList}" var="resource">
	              {
	            	id: '${resource.id}',
	            	nodeId: '${resource.id}',
	                text: '${resource.name}',
	                href: '${resource.href}',
	                nodes: [
					<c:forEach items="${resource.subResource}" var="subRes">
	                  {
	                	id: '${subRes.id}',
	                	nodeId: '${subRes.id}',
	                  	text: '${subRes.name}',
	                  	href: '${subRes.href}',
	                  	nodes: [
	    				<c:forEach items="${subRes.subResource}" var="subRes2">
	       					{
	       						id: '${subRes2.id}',
	       						nodeId: '${subRes2.id}',
	                          	text: '${subRes2.name}',
	                          	href: '${subRes2.href}'
	       					},
	    				</c:forEach>
	    				]
	                  },
	                </c:forEach>
	                ]
	              },
	            </c:forEach>
	            ];
				
				initTreeData(defaultData);
				
				$checkableTree = $('#treeview-checkable').treeview({
		          data: defaultData,
		          showIcon: false,
		          showCheckbox: true,
		          onNodeChecked: function(event, node) {
		        	if (node.parentId != undefined) {
			          $checkableTree.treeview('checkNode', [ node.parentId, { silent: true } ]);
		        	}
		          },
		          onNodeUnchecked: function (event, node) {
		        	if (node.nodes) {
		        	  node.nodes.forEach(function(item) {
						$checkableTree.treeview('uncheckNode', [ item.nodeId, { silent: true } ]);
					  })
		        	}
		          }
		        });
				
				// Check/uncheck all
		        $('#btn-check-all').on('click', function (e) {
		          $checkableTree.treeview('checkAll', { silent: true });
		        });
	
		        $('#btn-uncheck-all').on('click', function (e) {
		          $checkableTree.treeview('uncheckAll', { silent: true });
		        });
		        
		        $('#input-search').on('keyup', function(e) {
		            var pattern = $('#input-search').val();
		            var options = {
		              ignoreCase: true,
		              exactMatch: false,
		              revealResults: true
		            };
		            var results = $checkableTree.treeview('search', [ pattern, options ]);
					console.log(results);
	          	});
		        
			});
			
			// 初始化资源树
			function initTreeData(data) {
				if (chk_value && chk_value.length > 0) {
					data.forEach(function(item) {
						var id = item.id;
						if (chk_value.indexOf(id) > -1) {
							item.state = {
								checked: true
							};
						}
						if (item.nodes) {
							initTreeData(item.nodes)
						}
					})
				}
			}
			
			function generatePermissionString() {
				var chk_value = [];
				$checkableTree.treeview('getChecked').forEach(function(item) {
					chk_value.push(item.id);
				});
				$('input[name="resourceStr"]').val(chk_value);
			}
		</script>
		<h3 class="page-header sb-form-header">
			<c:if test="${role.id eq null}">添加角色</c:if>
			<c:if test="${role.id ne null}">修改角色</c:if>
		</h3>
		<form class="form-horizontal" role="form" action="" method="post">
			<div class="sb-form">
				<div class="form-group">
					<label for="roleName" class="col-sm-2 control-label">角色名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="roleName" required value="${role.name}">
					</div>
				</div>
				<div class="form-group">
					<label for="roleDesc" class="col-sm-2 control-label">角色说明</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="roleDesc" required value="${role.description}">
					</div>
				</div>
				<div class="form-group">
					<label for="roleDesc" class="col-sm-2 control-label">角色权限</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input-search" placeholder="search..." value="">
						<div id="treeview-checkable" class=""></div>
						<button type="button" class="btn btn-success" id="btn-check-all">Check All</button>
						<button type="button" class="btn btn-danger" id="btn-uncheck-all">Uncheck All</button>
					</div>
				</div>
				<input type="text" name="resourceStr" hidden="true" />
				<input type="text" name="id" hidden="true" value="${role.id}"/>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" value="提交" onclick="generatePermissionString();" class="btn btn-primary" /> &nbsp;
						<input type="reset" value="重置"	class="btn btn-default" />&nbsp;
						<a href='${pageContext.request.contextPath}/security/role/list' class="btn">返回</a>
					</div>
				</div>
			</div>
		</form>
		
		<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-treeview.js" />"></script>
	</c:param>
</c:import>

