
/**
 * 分页跳转
 * @param number
 */
function toPage(number) {
	var searchForm = $("#searchForm");
	if (searchForm && searchForm.length > 0) {
		searchForm.find("input[name='page']").val(number);
		searchForm.submit();
	} else {
		window.location.href = "?page=" + number;
	}
}

/**
 * 发送get请求
 * @param url
 */
function get(url) {
	$.get(url, function(data) {
		bootbox.alert(data);
	});
}