<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<ul class="pagination">
	<!-- 总页数 -->
	<li><a href="#">${(paging.number + 1)}/${paging.totalPages}</a></li>
	<!-- 首页 -->
	<li><c:if test="${!paging.isFirst()}">
			<a href='<c:url value="?page=0" />'>&laquo;&laquo; </a>
		</c:if></li>
	<li class="disabled"><c:if test="${paging.isFirst()}">
			<a href='#'>&laquo;&laquo; </a>
		</c:if></li>
	<!-- 上一页 -->
	<li><c:if test="${!paging.isFirst()}">
			<a href='<c:url value="?page=${f:h(paging.number - 1)}" />'>&laquo; </a>
		</c:if></li>
	<li class="disabled"><c:if test="${paging.isFirst()}">
			<a href='#'>&laquo;</a>
		</c:if></li>
	<!-- 中间五个 -->
	<li><c:if test="${paging.number-1>0}">
			<a href='<c:url value="?page=${f:h(paging.number-2)}" />'>${f:h(paging.number-1)}</a>
		</c:if></li>
	<li><c:if test="${paging.hasPrevious()}">
			<a href='<c:url value="?page=${f:h(paging.number-1)}" />'>${f:h(paging.number)}</a>
		</c:if></li>

	<li class="active"><a href='<c:url value="?page=${f:h(paging.number)}" />'>${f:h(paging.number+1)}</a></li>

	<li><c:if test="${paging.hasNext()}">
			<a href='<c:url value="?page=${f:h(paging.number+1)}" />'>${f:h(paging.number+2)}</a>
		</c:if></li>
	<li><c:if test="${paging.number+2<paging.totalPages}">
			<a href='<c:url value="?page=${f:h(paging.number+2)}" />'>${f:h(paging.number+3)}</a>
		</c:if></li>
	<!-- 下一页 -->
	<li><c:if test="${!paging.isLast()}">
			<a href='<c:url value="?page=${f:h(paging.number + 1)}" />'> &raquo;</a>
		</c:if></li>
	<li class="disabled"><c:if test="${paging.isLast()}">
			<a href='#'>&raquo;</a>
		</c:if></li>
	<!-- 尾页 -->
	<li
		<c:if test="${paging.isLast()}">
			class="disabled"		
		</c:if>		
	>
		<c:if test="${!paging.isLast()}">
			<a href='<c:url value="?page=${paging.totalPages-1}" />'> &raquo;&raquo;</a>
		</c:if>
		<c:if test="${paging.isLast()}">
			<a href='#'> &raquo;&raquo;</a>
		</c:if>
	</li>
</ul>
