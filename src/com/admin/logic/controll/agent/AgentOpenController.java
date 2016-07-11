package com.admin.logic.controll.agent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Openkind4customer;
import com.admin.db.bean.Openkind4third;
import com.admin.db.entity.Openkind4customerEntity;
import com.admin.db.entity.Openkind4thirdEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.admin.AdminGroupAccountController;
import com.admin.logic.model.PageOpenKind4Cust;
import com.admin.logic.model.PageOpenKind4Third;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 代理商管理--课程开通
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doAgent")
public class AgentOpenController {
	Map<String, Object> params = new HashMap<String, Object>();

	/*** 课程开通 ***/
	@RequestMapping("/courseOpen")
	public String courseOpen(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		int agentid = AgentController.getAgentid(session);
		if (agentid <= 0)
			return "center/userlogin";

		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status = ", 0);
		params.put("agentid = ", agentid);

		PageOpenKind4Cust pageCust = new PageOpenKind4Cust();
		PageEnt<Map> pageEnt = pageCust.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "agent/courseOpen";
	}

	/*** [添加,修改]开通套餐给学员 ***/
	@RequestMapping("/modifyOpenKindCustByAgent")
	public void modifyOpenKindCustByAgent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		int agentid = AgentController.getAgentid(session);
		AdminGroupAccountController.openKind4Cust(0, agentid, request,
				response, session, modelMap);
	}

	/*** 删除开通 ***/
	@RequestMapping("/delCourseopened")
	public void delCourseopened(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int okcustid = MapEx.getInt(map, "okcustid");
			Openkind4customer enCust = Openkind4customerEntity
					.getByKey(okcustid);
			if (enCust == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败,该开通用户为空!");
			} else if (enCust.getStatus() != 0) {
				result = Utls
						.tipMap(result, Utls.Status_Erro, "失败,该开通用户状态不正常!");
			} else {
				int lhubid = 0;
				int agentid = AgentController.getAgentid(session);
				Openkind4third enThird = Openkind4thirdEntity
						.getByKindidLhubidAgentid(enCust.getKindid(), lhubid,
								agentid);
				if (enThird == null) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败,该集团帐号为空!");
				} else {
					enCust.delete();
					enThird.changeNum(1);
					enThird.update();
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				}
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 代理项目 ***/
	@RequestMapping("/actingProject")
	public String actingProject(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		int agentid = AgentController.getAgentid(session);
		if (agentid <= 0)
			return "center/userlogin";

		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status = ", 0);
		params.put("agentid = ", agentid);
		params.put("num > ", 0);
		PageOpenKind4Third pageThird = new PageOpenKind4Third();
		PageEnt<Map> pageEnt = pageThird.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("courseOpen", MapEx.getInt(map, "courseOpen"));
		modelMap.addAttribute("uuid", MapEx.getString(map, "uuid"));
		return "agent/actingProject";
	}
}
