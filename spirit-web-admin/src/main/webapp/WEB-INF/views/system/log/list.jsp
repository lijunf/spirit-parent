<c:import url="/WEB-INF/template/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="系统参数管理" />
	<c:param name="permission" value="system:manage" /><%-- 当前页面资源上级资源权限 --%>
	<c:param name="url" value="/system/log/list" /><%-- 当前页面所属资源url地址，list和form一致 --%>
	<c:param name="navigation" value="系统日志查询" /><%-- 当前页面导航名称 --%>
	<c:param name="body">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a role="button" class="pull-right" data-toggle="collapse" href="#searchForm" aria-expanded="true" 
					aria-controls="collapseSearch" style="margin-top: -10px;">
					<span class="caret-lg"></span>
				</a>
			</div>
			<div class="panel-body sb-search">
				<form class="form-inline collapse in" id="searchForm" aria-expanded="true">
					<div class="form-group">
						<label class="" for="loginIdSearch">用户名</label>
						<input type="text" class="form-control input-sm" name="loginId" id="loginIdSearch" value="${log.loginId }" placeholder="用户名">
					</div>
					<div class="form-group">
						<label class="" for="msgSearch">日志</label>
						<input type="text" class="form-control input-sm" name="msg" id="msgSearch" value="${log.msg }" placeholder="日志">
					</div>
					<div class="form-group">
						<label class="" for="ipAddrSearch">IP</label>
						<input type="text" class="form-control input-sm" name="ipAddr" id="ipAddrSearch" value="${log.ipAddr }" placeholder="IP">
					</div>
					<div class="form-group">
						<label class="" for="beginDateSearch">开始时间</label>
						<input type="date" class="form-control input-sm" name="beginDate" id="beginDateSearch" value="${beginDate }" placeholder="开始时间">
					</div>
					<div class="form-group">
						<label class="" for="endDateSearch">结束时间</label>
						<input type="date" class="form-control input-sm" name="endDate" id="endDateSearch" value="${endDate }" placeholder="结束时间">
					</div>
					<div class="form-group">
						<label class="" for="prioritySearch">级别</label>
						<!-- TODO 需要改为通过字典渲染下拉框 -->
						<select class="form-control input-sm" name="priority" id="prioritySearch">
                            <option value="">ALL</option>
                            <option <c:if test="${log.priority eq 'TRACE'}">selected</c:if>>TRACE</option>
                            <option <c:if test="${log.priority eq 'DEBUG'}">selected</c:if>>DEBUG</option>
                            <option <c:if test="${log.priority eq 'INFO'}">selected</c:if>>INFO</option>
                            <option <c:if test="${log.priority eq 'WARN'}">selected</c:if>>WARN</option>
                            <option <c:if test="${log.priority eq 'ERROR'}">selected</c:if>>ERROR</option>
                        </select>
					</div>
					<input type="hidden" name="page" value="${paging.number }">
					<button type="submit" class="btn btn-primary btn-sm">Search</button>
				</form>
			</div>
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-bordered" style="margin-bottom: 0px;">
					<tr>
						<th>级别</th>
						<th>用户</th>
						<th>日志</th>
						<th>时间</th>
						<th>IP</th>
					</tr>
					<c:forEach items="${paging.content}" var="log">
						<tr>
							<td>${log.priority}</td>
							<td>${log.loginId}</td>
							<td>${log.msg}</td>
							<td>${log.logDate}</td>
							<td>${log.ipAddr}</td>
						<tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="pagination-search-wrapper">
			<jsp:include page="/WEB-INF/template/pagination-search.jsp"/>
		</div>
	</c:param>
</c:import>