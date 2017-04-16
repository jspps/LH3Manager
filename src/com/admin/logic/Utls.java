package com.admin.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.db.entity.AdcoursesEntity;
import com.bowlong.tool.TkitJsp;
import com.bowlong.util.MapEx;

/*** 工程工具类 **/
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Utls extends TkitJsp {
	// ================ 常量 ================
	static public final String KeyCodeAdminLg = "Code4AdminLogin";

	static public final String KeyTip = "tip";
	static public final String KeyTipUrl = "tipUrl";

	static public final int Status_Success = 1;
	static public final int Status_Erro = -1;

	/*** 管理者登录后的实体对象的键值对 **/
	static public final String KeyEnAdmin = "enAdmin";

	/*** 学习中心登录后的实体对象的键值对 **/
	static public final String KeyEnCenter = "enCenter";

	/*** 代理商登录后的实体对象的键值对 **/
	static public final String KeyEnAgent = "enAgent";

	// ================ 方法 ================
	static public final Map tipMap(Map map, int result, String msg, Object obj) {
		if (MapEx.isEmpty(map))
			map = new HashMap();
		map.put("msg", msg);
		map.put("status", result);
		map.put("data", obj);
		return map;
	}

	static public final List<Integer> getCourseIds(Map map) {
		int departid = MapEx.getInt(map, "departid");
		String nmMajor = MapEx.getString(map, "nmMajor");
		String nmLevel = MapEx.getString(map, "nmLevel");
		String nmSub = MapEx.getString(map, "nmSub");
		String nmArea = MapEx.getString(map, "nmArea");
		return AdcoursesEntity.getCourseIds(departid, nmMajor, nmLevel, nmSub,
				nmArea);
	};

	static public final String getContent(int index, String content) {
		int indP = content.indexOf("<p>");
		if (indP == 0)
			return content.substring(0, indP + 3) + index + "."
					+ content.substring(indP + 3);
		return index + "." + content;
	}
}
