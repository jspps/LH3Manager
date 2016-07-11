package com.admin.logic.controll.center;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Learnhub;
import com.admin.logic.Utls;
import com.admin.logic.model.PageOrder4Profit;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 学习中心
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class CenterController {
	static public Learnhub getLhub(HttpSession session) {
		Object obj = session.getAttribute(Utls.KeyEnCenter);
		if (obj == null)
			return null;
		if (obj instanceof Learnhub)
			return (Learnhub) obj;
		return null;
	}

	static public int getLhubid(HttpSession session) {
		Learnhub obj = getLhub(session);
		if (obj == null)
			return 0;
		return obj.getLhid();
	}

	/*** 代理商管理 **/
	@RequestMapping("/test")
	public String agentManage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "center/agentManage";
	}

	/*** 我的支付宝 **/
	@RequestMapping("/mypay")
	public String myplay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "center/mypay";
	}

	
	/*** 成交统计 ***/
	@RequestMapping("/tradeStatistics")
	public String tradeStatistics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		int lhubid = getLhubid(session);
		if (lhubid <= 0)
			return "center/userlogin";

		PageOrder4Profit pageWrap = new PageOrder4Profit();
		Map<String, Object> params = new HashMap<String, Object>();
		int page = 1;
		int pageSize = 10;
		
		params.clear();
		params.put("lhubid", lhubid);
		
		Map mapPars = Svc.getMapAllParams(request);
		page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(mapPars, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, pageSize);
		pageWrap.resetList4Cust(pageEnt.getListPages());
		
		modelMap.addAttribute("pageEnt", pageEnt);
		return "center/tradeStatistics";
	}
	
	
}
