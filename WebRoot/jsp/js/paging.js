/*** 单击关闭 **/
function goPaging(form,page){
		$("#"+form+" :input[name='page']").val(page);
		$("#"+form).submit();
}