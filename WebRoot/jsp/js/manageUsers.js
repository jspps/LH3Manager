/** * 管理用户-提交新用户注册 * */
function onsubmitNewUser(that) {
	var jqform = $(that);
	var url = jqform.attr("action");

	var ckvals = new Array();
	jqform.find("input:checkbox:checked").each(function() {
		ckvals.push($(this).val());
	});
	var strCks = ckvals.toString();

	var data = {};
	data.lgid = $("#lgid").val().trim();
	data.lgpwd = $("#lgpwd").val().trim();
	data.uname = $("#uname").val().trim();
	data.phone = $("#phone").val().trim();
	data.powers = strCks;

	if (data.lgid == "") {
		alert("登录帐号不能为空!");
		return false;
	} else {
		var len = data.lgid.length;
		if (len < 4 || len > 18) {
			alert("登录帐号长度不能小于6位,多于18位。");
			return false;
		}
		;
	}
	;

	if (data.lgpwd == "") {
		alert("登录密码不能为空!");
		return false;
	} else {
		var len = data.lgpwd.length;
		if (len < 6 || len > 18) {
			alert("登录密码长度不能少于6位,多于18位。");
			return false;
		}
		;
	}
	;

	if (data.uname == "") {
		alert("用户名不能为空!");
		return false;
	} else {
		var len = data.uname.length;
		if (len > 10) {
			alert("用户名长度多于10位。");
			return false;
		}
		;
	}
	;

	if (data.phone == "") {
		alert("电话号码不能为空!");
		return false;
	} else {
		var isN = isNaN(data.phone);
		if (isN) {
			alert("电话号码格式不对!");
			return false;
		}
	}
	;

	$.post(url, data, function(back) {
		// console.info(back);
		if (back.status) {
			alert(back.msg);
			if (back.status != -1) {
				location.reload();
			}
		}

	}, "json");
	return false;
}

/** * 改变check box值的处理[type:1-8] * */
function clickCBox(that, type, pclass) {
	if (!pclass) {
		pclass = "li";
	}
	var wrap = $(pclass + '[type="' + type + '"]');
	var wrapCk = wrap.find('input:checkbox[value="' + type + '"]');
	var cur = $(that);
	var val = cur.val();
	var isChecked = cur.is(":checked");
	var isWckChecked = false;
	var chirends = wrap.find("input:checkbox");
	if (val == type) {
		isWckChecked = isChecked;
	} else {
		chirends.each(function() {
			var tmp = $(this);
			if (tmp.is(":checked")) {
				var lcval = tmp.val();
				if (lcval != type) {
					isWckChecked = true;
				}
				;
			}
			;
		});
	}
	;

	if (!isWckChecked) {
		chirends.each(function() {
			var tmp = $(this);
			tmp[0].checked = false;
		});
	}
	wrapCk[0].checked = isWckChecked;
}

/** * 管理用户-精准查询用户by名字 * */
function onsubmitQueryUser(isCanNull) {
	var jqform = $("#fm_query_use");
	var url = jqform.attr("action");
	var data = {};
	data.uname = $("#queryName").val().trim();
	if(!isCanNull){
		if (data.uname == "") {
			alert("用户名不能为空!");
			return false;
		};
	};

	$.post(url, data, function(back) {
		console.info(back);
		if (back.status) {
			if (back.status == -1) {
				alert(back.msg);
			} else {
				query4List(back.list4Uses);
			}
		}
	}, "json");
	return false;
}

function query4List(list) {
	var jqCont = $("#query_cont");
	jqCont.empty();
	if (list) {
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			queryCont(list[i]);
		}
	}
}

function queryCont(map) {
	var jqCont = $("#query_cont");
	if (map && map.uid) {
		var list = map.list;
		var len = list.length;
		for ( var i = 0; i < len; i++) {
			var en = list[i];
			var tmptr = $("<tr>").attr("type", en.prid);
			if (i == 0) {
				var tdNM = $('<td rowspan="' + len + '" class="yh">');
				tdNM.html("姓名：" + map.uname + "<br/>帐号：" + map.lgid
						+ "<br/>密码：" + map.lgpwd + "<br/>电话：" + map.phone);
				tmptr.append(tdNM);
			}
			tmptr = showCell(tmptr, en, i);
			jqCont.append(tmptr);

			if (i == 0) {
				var tdOpt = $('<td rowspan="' + len + '" class="cx">');
				tdOpt
						.html('<a href="javascript:void(0);" class="delete" onclick="delUser('
								+ map.uid
								+ ');">删除</a><a href="javascript:void(0);" class="check" onclick="upUserPower('
								+ map.uid + ')">修改</a>');
				tmptr.append(tdOpt);
			}
		}
	}
}

function showCell(tr, power, index) {
	var isChecked = (!!power.isHas);
	var td1 = $('<td class="mb">').html(
			'<input type="checkbox" ' + (isChecked ? 'checked = "true" ' : "")
					+ ' value="' + power.prid + '" onclick="clickCBox(this,'
					+ power.prid + ',&quot;tr&quot;);"> ' + power.name);
	tr.append(td1);

	var td2 = $('<td class="cx">');
	tr.append(td2);

	var listChilds = power.childs;
	var len = listChilds.length;
	var tmp = null;
	for ( var i = 0; i < len; i++) {
		tmp = listChilds[i];
		isChecked = (!!tmp.isHas);
		td2.append($('<span>', {
			"html" : '<input type="checkbox" '
					+ (isChecked ? 'checked = "true" ' : "") + ' value="'
					+ tmp.prid + '" onclick="clickCBox(this,' + power.prid
					+ ',&quot;tr&quot;);"> ' + tmp.name
		}));
	}
	return tr;
}

function delUser(uid) {
	if (confirm("确定删除该角色吗?")) {
		var url = "admin/delUserBy";
		var data = {};
		data.uid = uid;
		$.post(url, data, function(back) {
			if (back.status) {
				alert(back.msg);
				if (back.status != -1) {
					location.reload();
				}
			}
		}, "json");
	}
}

function upUserPower(uid) {
	if (confirm("确定更新该角色的权限吗?")) {
		var url = "admin/upUserPowerBy";
		var ckvals = new Array();
		$("#query_cont").find("input:checkbox:checked").each(function() {
			ckvals.push($(this).val());
		});
		var strCks = ckvals.toString();

		var data = {};
		data.uid = uid;
		data.powers = strCks;

		$.post(url, data, function(back) {
			if (back.status) {
				alert(back.msg);
				if (back.status != -1) {
					location.reload();
				}
			}
		}, "json");
	}
};

window.onload = function() {	
	onsubmitQueryUser(true);
};