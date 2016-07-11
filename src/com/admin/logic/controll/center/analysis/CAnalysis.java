package com.admin.logic.controll.center.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Adqdepartment;
import com.admin.db.entity.AdqdepartmentEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.PageRecordAnser;
import com.bowlong.lang.PStr;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class CAnalysis {

	protected Map<String, Object> params4Search = new HashMap<String, Object>();

	/*** 模考分析_综合分析 **/
	@RequestMapping("/complexAnalysis")
	public String complexAnalysis(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "center/analysis/complexAnalysis";
	}

	/*** 模考分析 **/
	@RequestMapping("/examAnalysis")
	public String examAnalysis(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		int lhubid = CenterController.getLhubid(session);
		if (lhubid <= 0)
			return "center/userlogin";

		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		int pageSize = MapEx.getInt(map, "pageSize");
		boolean isPage = map.containsKey("inp_fm_page");
		if (!isPage) {
			params4Search.clear();

			int departid = MapEx.getInt(map, "departid");
			String nmMajor = MapEx.getString(map, "nmMajor");
			String nmLevel = MapEx.getString(map, "nmLevel");
			String nmSub = MapEx.getString(map, "nmSub");
			String nmArea = MapEx.getString(map, "nmArea");

			params4Search.put("departid", departid);
			params4Search.put("nmMajor", nmMajor);
			params4Search.put("nmLevel", nmLevel);
			params4Search.put("nmSub", nmSub);
			params4Search.put("nmArea", nmArea);
		}
		page = page == 0 ? 1 : page;
		pageSize = pageSize == 0 ? 10 : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lhubid = ", lhubid);
		if (MapEx.getInt(params4Search, "departid") > 0) {
			List<Integer> idsCourses = Utls.getCourseIds(params4Search);
			if (!ListEx.isEmpty(idsCourses)) {
				int lens = idsCourses.size();
				PStr pStr = PStr.b(" IN (");
				for (int i = 0; i < lens; i++) {
					pStr.a(idsCourses.get(i));
					if(i < lens - 1){
						pStr.a(",");
					}
				}
				params.put("courseid ", pStr.e(")"));
			}
		}
		PageRecordAnser pages = new PageRecordAnser();
		PageEnt<Map> pageEnt = pages.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);

		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();// 分类
		modelMap.addAttribute("adqdepartments", adqdepartments);
		return "center/analysis/examAnalysis";
	}
}
