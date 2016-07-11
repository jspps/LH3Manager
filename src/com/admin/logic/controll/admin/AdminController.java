package com.admin.logic.controll.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.content.Svc;
import com.admin.db.bean.Account;
import com.admin.db.bean.Adprivilege;
import com.admin.db.bean.Aduser;
import com.admin.db.bean.Agent;
import com.admin.db.bean.Learnhub;
import com.admin.db.entity.AccountEntity;
import com.admin.db.entity.AdprivilegeEntity;
import com.admin.db.entity.AduserEntity;
import com.admin.logic.Utls;
import com.admin.util.DateUtil;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.security.Encrypt;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;

/**
 * 管理后台
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doAdmin")
public class AdminController {
	/*** 默认验证 **/
	static public String verify_Login(Account account, HttpSession session) {
		if (account == null) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "帐号不正确！");
			return "tip";
		}

		// 类型[1管理员,2学习中心,3代理商,4学生,5程序,6美工]
		int type = account.getType();
		String base = "admin/adminAgentManage";
		switch (type) {
		case 2:
			Learnhub lhub = account.getLearnhubFkAccountid();
			session.setAttribute(Utls.KeyTipUrl, "loginLhub");
			if (lhub == null) {
				session.setAttribute(Utls.KeyTip, "学习中心对象为空！");
				return "tip";
			}
			if (lhub.getStatus() == 1) {
				session.setAttribute(Utls.KeyTip, "此帐号被禁用！");
				return "tip";
			}
			if (lhub.getExamineStatus() != 3) {
				session.setAttribute(Utls.KeyTip, "此帐号尚未审核通过！");
				return "tip";
			}
			session.setAttribute(Utls.KeyEnCenter, lhub);
			base = "center/basicInformation";
			break;
		case 3:
			Agent agent = account.getAgentFkAccountid();
			session.setAttribute(Utls.KeyTipUrl, "loginAgent");
			if (agent == null) {
				session.setAttribute(Utls.KeyTip, "代理商对象为空！");
				return "tip";
			}
			if (agent.getStatus() == 1) {
				session.setAttribute(Utls.KeyTip, "此帐号被禁用！");
				return "tip";
			}
			if (agent.getExamineStatus() != 3) {
				session.setAttribute(Utls.KeyTip, "此帐号尚未审核通过！");
				return "tip";
			}
			Date date = agent.getEndtime();
			if (date == null || DateUtil.daysBetween(date, new Date()) > 0) {
				session.setAttribute(Utls.KeyTip, "此帐号代理时间已经到期！");
				return "tip";
			}
			session.setAttribute(Utls.KeyEnAgent, agent);
			base = "agent/agentInfo";
			break;
		default:
			session.setAttribute(Utls.KeyEnAdmin, account);
			break;
		}

		account.setLasttime(new Date());
		account.update();
		
		// 单位:秒
		session.setMaxInactiveInterval(3600);
		return base;
	}

	/*** 管理者连接验证 **/
	static public String verify_Login_4_admin(Account account,
			HttpSession session) {
		if (account == null) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "帐号不正确！");
			return "tip";
		}

		// 类型[1管理员,2学习中心,3代理商,4学生,5程序,6美工]
		int type = account.getType();
		String base = "admin/adminAgentManage";
		switch (type) {
		case 2:
			Learnhub lhub = account.getLearnhubFkAccountid();
			session.setAttribute(Utls.KeyTipUrl, "loginLhub");
			if (lhub == null) {
				session.setAttribute(Utls.KeyTip, "学习中心对象为空！");
				return "tip";
			}
			session.setAttribute(Utls.KeyEnCenter, lhub);
			base = "center/basicInformation";
			break;
		case 3:
			Agent agent = account.getAgentFkAccountid();
			session.setAttribute(Utls.KeyTipUrl, "loginAgent");
			if (agent == null) {
				session.setAttribute(Utls.KeyTip, "代理商对象为空！");
				return "tip";
			}
			session.setAttribute(Utls.KeyEnAgent, agent);
			base = "agent/agentInfo";
			break;
		default:
			session.setAttribute(Utls.KeyEnAdmin, account);
			break;
		}

		int second = (int) (DateEx.TIME_MINUTE * 20);
		session.setMaxInactiveInterval(second);
		return base;
	}

	/*** 取得登录界面:管理者 **/
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.removeAttribute(Utls.KeyEnAdmin);
		return "login";
	}

	/*** 处理登录 **/
	@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// /只要到登录页面清空所有的 session
		// session.invalidate();

		Map map = Svc.getMapAllParams(request);
		String lgcode = MapEx.getString(map, "lgcode");
		String sessionCode = (String) session.getAttribute(Utls.KeyCodeAdminLg);
		lgcode = lgcode.trim();
		if (!lgcode.equalsIgnoreCase(sessionCode)) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "验证码不正确！");
			return "tip";
		}

		String lgid = MapEx.getString(map, "lgid");
		String lgpwd = MapEx.getString(map, "lgpwd");
		lgid = lgid.trim();
		lgpwd = lgpwd.trim();
		if (StrEx.isEmpty(lgid) || StrEx.isEmpty(lgpwd)) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "帐号或密码不正确！");
			return "tip";
		}

		Account account = AccountEntity.getByLgid(lgid);
		if (account == null) {
			account = AccountEntity.getByPhone(lgid);
		}

		if (account == null) {
			account = AccountEntity.getByEmail(lgid);
		}

		if (account == null) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "帐号不正确！");
			return "tip";
		}

		if (!lgpwd.equalsIgnoreCase(account.getLgpwd())) {
			session.setAttribute(Utls.KeyTipUrl, "admin/login");
			session.setAttribute(Utls.KeyTip, "密码不正确！");
			return "tip";
		}
		String url = verify_Login(account, session);
		return "redirect:/" + url;
	}

	/*** 管理用户 **/
	@RequestMapping("/manageUsers")
	public String manageUsers(HttpSession session, ModelMap modelMap) {
		List<Adprivilege> list = AdprivilegeEntity.getByParentid(0);
		modelMap.addAttribute("powerParents", list);
		if (!ListEx.isEmpty(list)) {
			int len = list.size();
			Map<String, List<Adprivilege>> map = new HashMap<String, List<Adprivilege>>();
			for (int i = 0; i < len; i++) {
				Adprivilege en = list.get(i);
				int prid = en.getPrid();
				List<Adprivilege> listTmp = AdprivilegeEntity
						.getByParentid(prid);
				String key = "id_" + prid;
				map.put(key, listTmp);
			}
			modelMap.addAttribute("subMap", map);
		}
		return "admin/manageUsers";
	}

	/*** jquery post提交 **/
	@RequestMapping("/submitNewUser")
	public void submitNewUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		String lgid = MapEx.getString(map, "lgid");
		Account entity = AccountEntity.getByLgid(lgid);
		if (entity != null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "登录帐号已经存在!");
		} else {
			String phone = MapEx.getString(map, "phone");
			entity = AccountEntity.getByPhone(phone);
			if (entity == null) {
				String lgpwd = MapEx.getString(map, "lgpwd");
				String email = MapEx.getString(map, "email");
				if (StrEx.isEmpty(email)) {
					email = PStr.str(phone, "@admin");
				}
				Date nowDate = new Date();
				entity = Account.newAccount(0, lgid, phone, email, lgpwd, 1, 0,
						nowDate, nowDate);
				entity = entity.insert();
				if (entity == null) {
					result = Utls.tipMap(result, Utls.Status_Erro, "插入用户数据异常!");
				} else {
					String uname = MapEx.getString(map, "uname");
					String powers = MapEx.getString(map, "powers");
					Aduser user = Aduser.newAduser(0, entity.getId(), uname,
							powers, "");
					user.insert();
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				}
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "此手机号码已注册!");
			}
		}
		Utls.writeAndClose(response, result);
	}

	/*** 查询取得管理者用户的map对象(aduser所有字段+Account所有字段) **/
	static Map getMap4MUser(Aduser aduser) {
		if (aduser == null)
			return null;

		Map result = aduser.toBasicMap();
		Account account = aduser.getAccountFkAccountid();
		if (account != null) {
			result.putAll(account.toBasicMap());
		}

		// 判断 获得权限
		String powers = aduser.getPowerids();
		if ("0".equals(powers)) {
			powers = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42";
		}
		List<Integer> listIds = ListEx.toListInt(powers);
		List<Map> list = new ArrayList<Map>();
		List<Adprivilege> listParant = AdprivilegeEntity.getByParentid(0);
		int len = listParant.size();
		for (int i = 0; i < len; i++) {
			Adprivilege parant = listParant.get(i);
			int pid = parant.getPrid();
			Map mapRet = parant.toBasicMap();
			boolean isHas = ListEx.haveInt(listIds, pid);
			if (isHas) {
				mapRet.put("isHas", isHas);
			}

			// childs
			List<Adprivilege> listChilds = AdprivilegeEntity.getByParentid(pid);
			List<Map> listMapChilds = new ArrayList<Map>();
			int lenchild = listChilds.size();
			for (int j = 0; j < lenchild; j++) {
				Adprivilege child = listChilds.get(j);
				Map mapChild = child.toBasicMap();
				boolean isHasC = ListEx.haveInt(listIds, child.getPrid());
				if (isHas) {
					mapChild.put("isHas", isHasC);
				}
				listMapChilds.add(mapChild);
			}
			mapRet.put("childs", listMapChilds);
			list.add(mapRet);
		}
		result.put("list", list);
		return result;
	}

	/*** 查询管理者用户 **/
	@RequestMapping("/queryUserBy")
	public void queryUserBy(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		String uname = MapEx.getString(map, "uname");
		List<Aduser> list = AduserEntity.getListByUname(uname);
		Map result = new HashMap();
		List<Map> list4Uses = new ArrayList<Map>();

		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map tmpMap = getMap4MUser(list.get(i));
				if (tmpMap == null)
					continue;
				list4Uses.add(tmpMap);
			}
		}
		result.put("list4Uses", list4Uses);
		result = Utls.tipMap(result, Utls.Status_Success, uname + "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 删除管理者用户 **/
	@RequestMapping("/delUserBy")
	public void delUserBy(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int uid = MapEx.getInt(map, "uid");
		Aduser user = AduserEntity.getByKey(uid);
		if (user == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该用户不存在!");
		} else {
			// 判断是不是自己对象
			// session.getAttribute(Utls.KeyEnAdmin);
			Account account = user.getAccountFkAccountid();
			account.setStatus(1);
			account.update();
			result = Utls.tipMap(result, Utls.Status_Success, "操作成功!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 更新管理者用户权限 **/
	@RequestMapping("/upUserPowerBy")
	public void upUserPowerBy(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int uid = MapEx.getInt(map, "uid");
		Aduser user = AduserEntity.getByKey(uid);
		if (user == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "帐号不存在!");
		} else {
			String powers = MapEx.getString(map, "powers");
			user.setPowerids(powers);
			user.update();
			result = Utls.tipMap(result, Utls.Status_Success, "权限更新成功!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 管理者-控制:学习中心 **/
	@RequestMapping("/skip2Lhub")
	public String skip2Lhub(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attributes)
			throws Exception {
		Map mapPars = Svc.getMapAllParams(request);
		int lhubid = MapEx.getInt(mapPars, "id");
		String data = Encrypt.encodeInt(lhubid);
		attributes.addAttribute("data", data);
		attributes.addAttribute("isAdmin", true);
		return "redirect:/go2Lhub";
	}

	/*** 管理者-控制:代理商 **/
	@RequestMapping("/skip2Agent")
	public String skip2Agent(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attributes)
			throws Exception {
		Map mapPars = Svc.getMapAllParams(request);
		int lhubid = MapEx.getInt(mapPars, "id");
		String data = Encrypt.encodeInt(lhubid);
		attributes.addAttribute("data", data);
		attributes.addAttribute("isAdmin", true);
		return "redirect:/go2Agent";
	}
}
