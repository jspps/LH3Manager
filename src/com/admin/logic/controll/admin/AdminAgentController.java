package com.admin.logic.controll.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Agent;
import com.admin.db.entity.AgentEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageAgent;
import com.admin.util.DateUtil;
import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-代理商管理
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
@Controller
@RequestMapping("/doAdmin")
public class AdminAgentController {

	PageAgent pageAgent = new PageAgent();
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 代理商管理 **/
	@RequestMapping("/adminAgentManage")
	public String adminAgentManage(HttpSession session,
			HttpServletRequest request, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status", 0);
		String code = MapEx.getString(map, "code");
		if (!StrEx.isEmptyTrim(code)) {
			params.put("code", "%" + code + "%");
			modelMap.addAttribute("code", code);
		}
		PageEnt<Agent> pageEnt = pageAgent.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/adminAgentManage";
	}

	/*** 代理商管理 删除 **/
	@RequestMapping("/deleteAgent")
	public void deleteAgent(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int agid = MapEx.getInt(map, "agid");
		Agent agent = AgentEntity.getByKey(agid);
		if (agent == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "当前数据不存在! ");
		} else {
			agent.setStatus(1);
			AgentEntity.update(agent);
			result = Utls.tipMap(result, Utls.Status_Success, "成功! ");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 代理商管理 拒绝通过审核 **/
	@RequestMapping("/notPassAuditing")
	public void notPassAuditing(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int agid = MapEx.getInt(map, "agid");
		String examineDes = MapEx.getString(map, "examineDes");
		Agent agentEnt = AgentEntity.getByKey(agid);
		if (agentEnt == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "当前数据不存在! ");
		} else {
			agentEnt.setExamineStatus(2);
			agentEnt.setExamineDes(examineDes);
			AgentEntity.update(agentEnt);
			result = Utls.tipMap(result, Utls.Status_Success, "设置成功! ");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 代理商管理 通过审核 **/
	@RequestMapping("/passAuditing")
	public void passAuditing(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int agid = MapEx.getInt(map, "agid");
		String timerStr = MapEx.getString(map, "time");
		String examineDes = MapEx.getString(map, "examineDes");

		// Date nowDate = new Date(endTime);
		// System.out.println("endTime = " + endTime);
		// System.out.println("timer = " + timerStr);
		Agent agentEnt = AgentEntity.getByKey(agid);
		if (agentEnt == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "当前数据不存在! ");
		} else {
			// <c:if test="${curAgent.examineStatus == '2'}">审核不通过</c:if>
			// <c:if test="${curAgent.examineStatus == '3'}">审核通过</c:if>
			Date tms = DateUtil.parseShort(timerStr);
			if (DateUtil.daysBetween(tms, new Date()) > 0) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"代理截止时间不能小于当前时间! ");
			} else {
				agentEnt.setEndtime(tms);
				agentEnt.setExamineStatus(3);
				agentEnt.setExamineDes(examineDes);
				AgentEntity.update(agentEnt);
				result = Utls.tipMap(result, Utls.Status_Success, "成功! ");
			}
		}

		Utls.writeAndClose(response, result);
	}

	/*** 代理排行榜 **/
	@RequestMapping("/agentTops")
	public String agentTops(HttpSession session, ModelMap modelMap) {
		params.clear();
		params.put("status", 0);
		PageEnt<Agent> pageEnt = pageAgent.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/agentTops";
	}

	/*** 奖金设置 **/
	@RequestMapping("/setBonus")
	public String setBonus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "admin/setBonus";
	}

}
