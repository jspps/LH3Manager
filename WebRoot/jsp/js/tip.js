/*** 单击关闭 **/
function clickTipClose(){
	console.info("clickTipClose");
	doSubmitAdminTip();
}

function verifyAdminTip() {
	console.info("verifyAdminTip");
	return true;
}

function doSubmitAdminTip() {
	console.info("doSubmitAdminTip");
	$("#admin_tip").submit();
}