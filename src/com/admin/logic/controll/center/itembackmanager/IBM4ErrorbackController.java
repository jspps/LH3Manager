package com.admin.logic.controll.center.itembackmanager;

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
import com.admin.db.bean.Appraise;
import com.admin.db.bean.Optquestion;
import com.admin.db.entity.AppraiseEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.PageAppraise;
import com.admin.logic.model.PageErrorfeedback;
import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 学习中心-[错误反馈,最新点评,同学有问]
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class IBM4ErrorbackController {

	/*** 学习中心管理后台_错误反馈 **/
	@RequestMapping("/errorBack")
	public String errorBack(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int lhubid = CenterController.getLhubid(session);
		int page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 0);
		params.put("lhubid", lhubid);

		PageErrorfeedback pgErrorBack = new PageErrorfeedback();
		PageEnt<Map> pageEnt = pgErrorBack.getPage(params, page, pageSize);

		int count = OptquestionEntity.getCountByLhubid(lhubid);
		int trs = pageEnt.getTotalRecords();
		int rate = 0;
		if (count > 0) {
			rate = (int) ((double) trs * 100 / count);
		}

		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("count", count);
		modelMap.addAttribute("countError", trs);
		modelMap.addAttribute("rate", rate);
		return "center/errorBack";
	}

	static public String getUrl4Opt(int jugdeType, int examid, int optid,
			RedirectAttributes attr) {
		if (jugdeType <= 0 || jugdeType >= 7) {
			return "redirect:errorBack";
		}
		StringBuffer buff = new StringBuffer("redirect:");
		switch (jugdeType) {
		case 1:
			buff.append("/center/singleEntry");
			break;
		case 2:
			buff.append("/center/doubleEntry");
			break;
		case 3:
			buff.append("/center/judgeEntry");
			break;
		case 4:
			buff.append("/center/fillBlankentry");
			break;
		case 5:
			buff.append("/center/shortAnswerentry");
			break;
		case 6:
			buff.append("/center/discussEntry");
			break;
		default:
			break;
		}
		attr.addAttribute("examid", examid);
		attr.addAttribute("optquestion", optid);
		return buff.toString();
	}

	@RequestMapping("/errback4Redirect")
	public String errback4Redirect(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			RedirectAttributes attr) {
		Map map = Svc.getMapAllParams(request);
		int optid = MapEx.getInt(map, "optid");
		if (optid == 0) {
			return "redirect:errorFeedback";
		}
		Optquestion opt = OptquestionEntity.getByKey(optid);
		if (opt == null) {
			return "redirect:errorFeedback";
		}

		int type = opt.getType();
		int gid = opt.getGid();
		int examid = opt.getExamid();

		int jugdeType = type;
		if (type == 7) {
			jugdeType = gid;
		}
		return getUrl4Opt(jugdeType, examid, optid,attr);
	}

	/*** 学习中心管理后台_最新点评 **/
	@RequestMapping("/latestReviews")
	public String latestReviews(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {

		int lhubid = CenterController.getLhubid(session);
		if (lhubid <= 0)
			return "center/userlogin";

		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 0);
		params.put("lhubid", lhubid);
		PageAppraise pageAppraise = new PageAppraise();
		PageEnt<Appraise> pageEnt = pageAppraise
				.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "center/latestReviews";
	}

	/*** 回复点评信息 **/
	@RequestMapping("/reback4Appraise")
	public void reback4Appraise(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int appraiseid = MapEx.getInt(map, "id");
		String reback = MapEx.getString(map, "reback");
		if (StrEx.isEmptyTrim(reback)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,回复内容不能为空!");
			Utls.writeAndClose(response, result);
		}

		Appraise appraise = AppraiseEntity.getByKey(appraiseid);
		if (appraise == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,该点评不存在!");
			Utls.writeAndClose(response, result);
			return;
		}
		appraise.setReback(reback);
		appraise.update();
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 模考分析_同学有问 **/
	@RequestMapping("/feedback")
	public String feedback(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "center/feedback";
	}
}
