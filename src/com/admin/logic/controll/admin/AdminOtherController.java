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
import com.admin.db.bean.Cfgs;
import com.admin.db.bean.Msg;
import com.admin.db.entity.CfgsEntity;
import com.admin.db.entity.MsgEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageMsg;
import com.admin.logic.model.PageOrder;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-其他(帮助,订购管理,消息管理)
 * 
 * @author Canyon
 * 
 */
@Controller
@RequestMapping("/doAdmin")
@SuppressWarnings("rawtypes")
public class AdminOtherController {

	/*** 帮助中心 **/
	@RequestMapping("/helpCenter")
	public String helpCenter(HttpServletRequest request, ModelMap modelMap) {
		Map parsMap = Utls.getMapAllParams(request);
		int type = MapEx.getInt(parsMap, "type");
		int gid = 1;
		switch (type) {
		case 1:
			break;
		case 2:
			gid = 2;
			break;
		case 3:
			gid = 3;
			break;
		case 4:
			gid = 4;
			break;
		default:
			break;
		}
		Cfgs enGuid = CfgsEntity.getByKey(gid);
		modelMap.addAttribute("guid", enGuid);
		modelMap.addAttribute("type", gid);
		return "admin/helpCenter";
	}

	/*** 修改帮助中心 **/
	@RequestMapping("/doHelp")
	public void doHelp(HttpServletRequest request, HttpServletResponse response) {
		Map mapPars = Utls.getMapAllParams(request);
		int cfgid = MapEx.getInt(mapPars, "cfgid");
		String strval = MapEx.getString(mapPars, "strval");
		Cfgs enGuid = CfgsEntity.getByKey(cfgid);
		Map result = new HashMap();
		if (enGuid == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,对象为空!");
		} else {
			enGuid.setValStr(strval);
			enGuid.update();
			result = Utls.tipMap(result, Utls.Status_Success, "修改成功!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 订购管理 **/
	@RequestMapping("/orderManage")
	public String orderManage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageOrder pageOrder = new PageOrder();
		Map map = Svc.getMapAllParams(request);
		params.clear();
		params.put("status", 0);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		PageEnt<Map> pageEnt = pageOrder.getPage(params, page, pageSize);
		pageOrder.resetList(pageEnt.getListPages());
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/orderManage";
	}

	// 1 学生
	// 2 代理商
	// 3 学习中心
	// 12 学生和代理商
	// 13 学生和学习中心
	// 23 代理商和学习中心
	// 123 学生和代理商和学习中心 target 消息类型id

	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 消息管理 **/
	@RequestMapping("/newsManage")
	public String newsManage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		params.clear();
		params.put("status", 0);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		PageMsg pageMsg = new PageMsg();
		PageEnt<Msg> pageEnt = pageMsg.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/newsManage";
	}

	/*** 添加可修改消息 **/
	@RequestMapping("/addmodmsg")
	public void addmodmsg(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		String description = MapEx.getString(map, "description");
		String target = MapEx.getString(map, "target");
		int msgid = MapEx.getInt(map, "msgid");
		if (msgid == 0) {
			Msg msg = Msg.newMsg(0, target, description, 0, 0, new Date());
			MsgEntity.insert(msg);
		} else {
			Msg msg = MsgEntity.getByKey(msgid);
			msg.setDescription(description);
			MsgEntity.update(msg);
		}
		Map result = new HashMap();
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 删除改消息 **/
	@RequestMapping("/delmsg")
	public void delmsg(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int msgid = MapEx.getInt(map, "msgid");
		Msg msg = MsgEntity.getByKey(msgid);
		MsgEntity.delete(msg);
		Map result = new HashMap();
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

}
