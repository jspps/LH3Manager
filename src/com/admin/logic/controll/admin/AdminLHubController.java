package com.admin.logic.controll.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.content.Svc;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Optquestion;
import com.admin.db.entity.LearnhubEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.itembackmanager.IBM4ErrorbackController;
import com.admin.logic.model.PageErrorfeedback;
import com.admin.logic.model.PageLearnhub;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-学习中心管理
 * 
 * @author Canyon
 * 
 */
@Controller
@RequestMapping("/doAdmin")
@SuppressWarnings("rawtypes")
public class AdminLHubController {

	PageLearnhub pageLearnhub = new PageLearnhub();
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 学习中心管理 **/
	@RequestMapping("/learnCenterManage")
	public String learnCenterManage(HttpServletRequest request,
			ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status", 0);
		String name = MapEx.getString(map, "name");
		if (name != null && !"".equals(name)) {
			params.put("name", "%" + name + "%");
			modelMap.addAttribute("name", name);
		}
		PageEnt<Learnhub> pageEnt = pageLearnhub
				.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/learnCenterManage";
	}

	/*** 学习中心管理 删除 **/
	public void deleteLearnhub(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int lhid = MapEx.getInt(map, "lhid");
		Learnhub entity = LearnhubEntity.getByKey(lhid);
		if (entity == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "当前数据不存在!");
		} else {
			entity.setStatus(1);
			LearnhubEntity.update(entity);
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 学习中心管理 修改 **/
	@RequestMapping("/updateLearnhub")
	public void updateLearnhub(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int lhid = MapEx.getInt(map, "lhid");
		// 学习中心状态
		String examineStatus = MapEx.getString(map, "examineStatus");
		// 自主管理权限
		String isselfadmin = MapEx.getString(map, "isselfadmin");
		Learnhub entity = LearnhubEntity.getByKey(lhid);
		if (examineStatus != null && !"".equals(examineStatus)) {
			// status = true 审核通过 状态正常否则不通过
			entity.setExamineStatus(Integer.parseInt(examineStatus));
			LearnhubEntity.update(entity);
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		}

		if (isselfadmin != null && !"".equals(isselfadmin)) {
			// isselfadmin = true 开启
			if ("true".equals(isselfadmin)) {
				entity.setIsselfadmin(true);
			} else {
				entity.setIsselfadmin(false);
			}
			LearnhubEntity.update(entity);
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 错误反馈 **/
	@RequestMapping("/errorBack")
	public String errorBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageErrorfeedback pageWrap = new PageErrorfeedback();
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status", 0);
		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		int count = pageWrap.countAll(params);
		int trs = pageEnt.getTotalRecords();
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("trs", trs);
		double trscount = 0;
		if (count > 0) {
			trscount = trs * 100 / count;
		}
		modelMap.addAttribute("trscount", trscount);
		return "admin/errorBack";
	}

	@RequestMapping("/errback4Redirect")
	public String errback4Redirect(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map map = Svc.getMapAllParams(request);
		int optid = MapEx.getInt(map, "optid");
		if (optid == 0) {
			return "redirect:errorBack";
		}
		Optquestion opt = OptquestionEntity.getByKey(optid);
		if (opt == null) {
			return "redirect:errorBack";
		}

		int type = opt.getType();
		int gid = opt.getGid();
		int examid = opt.getExamid();

		int jugdeType = type;
		if (type == 7) {
			jugdeType = gid;
		}

		return IBM4ErrorbackController.getUrl4Opt(jugdeType, examid, optid,
				attr);
	}
}
