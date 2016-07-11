package com.admin.logic.controll.agent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Account;
import com.admin.db.bean.Agent;
import com.admin.db.bean.Rnk4profit;
import com.admin.db.entity.AccountEntity;
import com.admin.db.entity.Rnk4profitEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageMsg;
import com.admin.logic.model.PageOrder4Profit;
import com.bowlong.lang.NumFmtEx;
import com.bowlong.lang.StrEx;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.CalendarEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 代理商管理
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doAgent")
public class AgentController {
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	static public Agent getAgent(HttpSession session) {
		Object obj = session.getAttribute(Utls.KeyEnAgent);
		if (obj == null)
			return null;
		if (obj instanceof Agent)
			return (Agent) obj;
		return null;
	}

	static public int getAgentid(HttpSession session) {
		Agent obj = getAgent(session);
		if (obj == null)
			return 0;
		return obj.getAgid();
	}

	/*** 代理商管理 **/
	@RequestMapping("/agentManage")
	public String agentManage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "agent/agentManage";
	}

	/*** 我的支付宝 **/
	@RequestMapping("/mypay")
	public String myplay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "agent/mypay";
	}

	/*** 成交情况 ***/
	@RequestMapping("/turnover")
	public String turnover(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		return "agent/turnover";
	}

	/*** 成交统计 ***/
	@RequestMapping("/tradeStatistics")
	public String tradeStatistics(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		int agentid = getAgentid(session);
		if (agentid <= 0) {
			return "center/userlogin";
		}

		PageOrder4Profit pageWrap = new PageOrder4Profit();
		params.clear();
		params.put("agentid", agentid);

		Map mapPars = Svc.getMapAllParams(request);
		page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(mapPars, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		PageEnt<Map> pageEnt = pageWrap.getPage(params, page, pageSize);
		pageWrap.resetList4Cust(pageEnt.getListPages());

		modelMap.addAttribute("pageEnt", pageEnt);
		return "agent/tradeStatistics";
	}

	/*** 修改 ***/
	@RequestMapping("/modAgentInfo")
	public void modAgentInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {

		Map result = new HashMap();
		Agent agent = getAgent(session);
		if (agent == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
			Utls.writeAndClose(response, result);
			return;
		}

		Map map = Svc.getMapAllParams(request);
		String uname = MapEx.getString(map, "uname");
		String goodness = MapEx.getString(map, "goodness");
		String account_phone = MapEx.getString(map, "account_phone");
		String account_lgid = MapEx.getString(map, "account_lgid");
		String province = MapEx.getString(map, "province");
		String city = MapEx.getString(map, "city");
		String seat = MapEx.getString(map, "seat");
		String qq = MapEx.getString(map, "qq");

		boolean isMobile = TkitValidateCheck.isMobile(account_phone);
		if (!isMobile) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您输入的手机号码有误！");
			Utls.writeAndClose(response, result);
			return;
		}
		
		if (StrEx.isEmptyTrim(account_lgid)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "登录帐号不能为空!");
			Utls.writeAndClose(response, result);
			return;
		}
		
		Account account = AccountEntity.getByKey(agent.getAccountid());
		
		Account acc = AccountEntity.getByPhone(account_phone);
		if (acc != null && account.getId() != acc.getId()) {
			result = Utls.tipMap(result, Utls.Status_Erro, "电话号码已经存在!");
			Utls.writeAndClose(response, result);
			return;
		}
		
		acc = AccountEntity.getByLgid(account_lgid);
		if (acc != null && account.getId() != acc.getId()) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该账户已经存在!");
			Utls.writeAndClose(response, result);
			return;
		}

		acc = AccountEntity.getByEmail(qq);
		if (acc != null && account.getId() != acc.getId()) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该QQ邮箱已经存在!");
			Utls.writeAndClose(response, result);
			return;
		}

		account.setPhone(account_phone);
		account.setLgid(account_lgid);
		account.setEmail(qq);
		account.update();

		agent.setUname(uname);
		agent.setQq(qq);
		agent.setGoodness(goodness);
		agent.setProvince(province);
		agent.setCity(city);
		agent.setSeat(seat);
		agent.setCode(account_phone);
		agent.update();

		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 错误反馈 ***/
	@RequestMapping("/errorFeedback")
	public String errorFeedback(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {

		return "agent/errorFeedback";
	}

	/*** 代理商管理后台信息 ***/
	@RequestMapping("/agentInfo")
	public String agentInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Agent agent = getAgent(session);
		int agentid = getAgentid(session);
		Calendar now_date = CalendarEx.nowCalendar();
		int h = CalendarEx.hour(now_date);
		if (h < 4) {
			now_date = CalendarEx.addDay(now_date, -1);
		}
		String dataStr = DateEx.format(now_date, DateEx.fmt_yyyyMMdd);
		Rnk4profit rnk = Rnk4profitEntity.getEnBy(0, agentid, dataStr);
		boolean isRnk = rnk != null;
		int indexs = 0;
		double money = 0;
		if (isRnk) {
			indexs = rnk.getIndexs();
			money = rnk.getMoney();
		}
		modelMap.put("agent", agent);
		modelMap.put("isRnk", isRnk);
		modelMap.put("indexs", indexs);
		modelMap.put("money", NumFmtEx.formatDouble(money));
		return "agent/agentInfo";
	}

	/*** 密码设置 ***/
	@RequestMapping("/passwordSettings")
	public String passwordSettings(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {

		Agent le = (Agent) session.getAttribute(Utls.KeyEnAgent);
		modelMap.put("code", le.getCode());
		return "agent/passwordSettings";
	}

	/*** 最新消息 ***/
	@RequestMapping("/latestNews")
	public String latestNews(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "page");
		int pageSize = MapEx.getInt(map, "pageSize");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountid", "%2%");
		PageMsg pageMsg = new PageMsg();
		PageEnt<com.admin.db.bean.Msg> pageEnt = pageMsg.getPage(params,
				page == 0 ? 1 : page, pageSize == 0 ? 10 : pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "agent/latestNews";
	}

	/*** 修改密码 **/
	@RequestMapping("/modpassword")
	public void modpassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			String password = MapEx.getString(map, "password");
			String password2 = MapEx.getString(map, "password2");
			if (password.equals(password2)) {
				if (null != session.getAttribute(Utls.KeyEnAgent)) {
					Agent le = (Agent) session.getAttribute(Utls.KeyEnAgent);
					if (le != null && 0 != le.getAgid()) {
						String ypsassword = MapEx.getString(map, "ypsassword");
						Account account = AccountEntity.getByKey(le
								.getAccountid());
						if (account != null && account.getId() != 0
								&& account.getType() == 3) {
							if (account.getLgpwd().equals(ypsassword)) {
								account.setLgpwd(password);
								AccountEntity.update(account);
								result = Utls.tipMap(result,
										Utls.Status_Success, "修改成功");
							} else {
								result = Utls.tipMap(result, Utls.Status_Erro,
										"原始密码错误！");
							}

						} else {
							result = Utls.tipMap(result, Utls.Status_Erro,
									"帐号异常！!");
						}
					} else {
						result = Utls.tipMap(result, Utls.Status_Erro,
								"登录超时失败!");
					}
				} else {
					result = Utls.tipMap(result, Utls.Status_Erro, "登录超时失败!");
				}

			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "两次密码不一直!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

}
