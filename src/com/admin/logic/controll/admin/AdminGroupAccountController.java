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
import com.admin.db.bean.Account;
import com.admin.db.bean.Customer;
import com.admin.db.bean.Kind;
import com.admin.db.bean.Openkind4customer;
import com.admin.db.bean.Openkind4third;
import com.admin.db.entity.AccountEntity;
import com.admin.db.entity.KindEntity;
import com.admin.db.entity.Openkind4customerEntity;
import com.admin.db.entity.Openkind4thirdEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageOpenKind4Cust;
import com.admin.logic.model.PageOpenKind4Third;
import com.admin.logic.model.PageProduct;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-集团用户管理
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doAdmin")
public class AdminGroupAccountController {
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 集团账户 **/
	@RequestMapping("/companyUsers")
	public String companyUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		String userName = MapEx.getString(map, "userName");
		params.put("lhubid = ", 0);
		params.put("agentid = ", 0);
		if (userName != null && !"".equals(userName)) {
			params.put("userName like ", "%" + userName + "%");
			modelMap.addAttribute("userName", userName);
		}

		PageOpenKind4Cust pageCust = new PageOpenKind4Cust();
		PageEnt<Map> pageEnt = pageCust.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/companyUsers";
	}

	/*** [添加,修改]开通套餐给学员 ***/
	@RequestMapping("/modifyOpenKind4Cust")
	public void modifyOpenKind4Cust(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		openKind4Cust(0, 0, request, response, session, modelMap);
	}

	static public void openKind4Cust(int lhubid, int agentid,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int okcustid = MapEx.getInt(map, "okcustid");
			int kbi = MapEx.getInt(map, "kbi");
			int kindid = MapEx.getInt(map, "kindid");
			String remarks = MapEx.getString(map, "remarks");
			String uname = MapEx.getString(map, "uname");
			String upass = MapEx.getString(map, "upass");
			String uphone = MapEx.getString(map, "uphone");
			int validity = MapEx.getInt(map, "validity");

			Kind kind = KindEntity.getByKey(kindid);
			if (kind == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败，该套餐不存在!");
				Utls.writeAndClose(response, result);
				return;
			}

			Openkind4customer en4OpenCourses = null;
			Account acc = null;
			Customer cust = null;

			if (okcustid == 0) {
				acc = AccountEntity.getByPhone(uphone);
				String email = "";
				Date createtime = DateEx.nowDate();
				int status = 0;
				int custId = 0;

				if (acc == null) {
					String lgid = MD5.MD5UUIDStime();
					int type = 4;
					email = MapEx.getString(map, "email");
					if (StrEx.isEmpty(email)) {
						email = PStr.str(uphone, "@admin_group");
					}
					acc = Account.newAccount(0, lgid, uphone, email, upass,
							type, status, createtime, createtime);
					acc = acc.insert();

					String province = "请选择";
					String city = "请选择";
					String seat = "请修改数据";
					String headIcon = "jsp/imgs/onesigle/usermanange/bg.jpg";
					String descr = "";
					double money = 0;
					String remCode = MD5.MD5UUIDStime();

					cust = Customer.newCustomer(0, acc.getId(), uname, kbi,
							kbi, email, province, city, seat, headIcon, descr,
							money, money, remCode, "", "", false, "", "");
					cust = cust.insert();
					custId = cust.getId();
				} else {
					email = acc.getEmail();
					cust = acc.getCustomerFkAccountid();
					custId = cust.getId();
				}

				en4OpenCourses = Openkind4customerEntity.getByCustomeridKindid(
						custId, kindid);
				if (en4OpenCourses == null) {
					en4OpenCourses = Openkind4customer.newOpenkind4customer(0,
							custId, uname, kindid, lhubid, agentid, kbi, 0,
							validity, createtime, createtime, remarks, status);

					en4OpenCourses = en4OpenCourses.insert();
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
					Utls.writeAndClose(response, result);
					return;
				}

			} else {
				en4OpenCourses = Openkind4customerEntity.getByKey(okcustid);
			}

			if (en4OpenCourses == null) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败,体验套餐数据为空!");
				Utls.writeAndClose(response, result);
				return;
			}

//			if (en4OpenCourses.getStatus() == 2) {
//				result = Utls.tipMap(result, Utls.Status_Erro,
//						"失败,该套餐已被该用户使用!");
//				Utls.writeAndClose(response, result);
//				return;
//			}

			if (cust == null) {
				cust = en4OpenCourses.getCustomerFkCustomerid();
			}

			if (cust == null) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败,学员为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			if (acc == null) {
				acc = cust.getAccountFkAccountid();
			}
			
			if (acc == null) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败,登录帐号为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			Account en = AccountEntity.getByPhone(uphone);
			if (en != null && en.getId() != acc.getId()) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败,该手机号码已经存在!");
				Utls.writeAndClose(response, result);
				return;
			}

			long last = acc.getLasttime().getTime();
			long create = acc.getCreatetime().getTime();
			if (last <= create) {
				acc.setPhone(uphone);
				acc.setLgpwd(upass);
				acc.update();

				cust.setName(uname);
				cust.setKbiAll(kbi);
				cust.setKbiUse(kbi);
				cust.update();
			}

			int _lhubid = en4OpenCourses.getLhubid();
			int _agentid = en4OpenCourses.getAgentid();
			if (!(_agentid == agentid && _lhubid == lhubid)) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败，该用户也被其他人员分配了套餐!");
				Utls.writeAndClose(response, result);
				return;
			}

			en4OpenCourses.setCustname(cust.getName());
			en4OpenCourses.setKbi(kbi);
			en4OpenCourses.setKindid(kindid);
			en4OpenCourses.setRemarks(remarks);
			en4OpenCourses.setValidity(validity);
			if(en4OpenCourses.getCreatetime().getTime() != en4OpenCourses.getValidtime().getTime()){
				en4OpenCourses.setValidtime(DateEx.addDay(DateEx.nowDate(), validity));
			}
			en4OpenCourses.update();

			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 集团账户 **/
	@RequestMapping("/companyUsers_setList")
	public String companyUsers_setList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		String phone = MapEx.getString(map, "phone");
		params.clear();
		params.put("status=", 0);
		if (phone != null && !"".equals(phone)) {
			params.put("phone=", "'" + phone + "'");
			modelMap.addAttribute("phone", phone);
		}

		PageOpenKind4Third pageThird = new PageOpenKind4Third();
		PageEnt<Map> pageEnt = pageThird.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/companyUsers_setList";
	}

	/*** [添加,修改]开通套餐给学员 ***/
	@RequestMapping("/modifyOpenKind4Third")
	public void modifyOpenKind4Third(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int okthirdid = MapEx.getInt(map, "okthirdid");
			int num = MapEx.getInt(map, "num");
			int kindid = MapEx.getInt(map, "kindid");
			double money = MapEx.getDouble(map, "money");
			String jtname = MapEx.getString(map, "jtname");
			String lxname = MapEx.getString(map, "lxname");
			String lxphone = MapEx.getString(map, "lxphone");

			Kind kind = KindEntity.getByKey(kindid);
			if (kind == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败，该套餐不存在!");
				Utls.writeAndClose(response, result);
				return;
			}

			Account en = AccountEntity.getByPhone(lxphone);
			if (en == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败，所属集团为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			// 学习中心2,代理上3
			int accType = en.getType();
			if (!(accType == 2 || accType == 3)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败，所属集团类型不对!");
				Utls.writeAndClose(response, result);
				return;
			}

			int lhubid = 0;
			int agentid = 0;
			switch (accType) {
			case 2:
				lhubid = en.getLearnhubFkAccountid().getLhid();
				break;

			default:
				agentid = en.getAgentFkAccountid().getAgid();
				break;
			}

			Openkind4third enThird = null;
			boolean isUp = okthirdid > 0;
			if (okthirdid == 0) {
				enThird = Openkind4thirdEntity.getByKindidLhubidAgentid(kindid,
						lhubid, agentid);
				isUp = enThird != null;
				if (!isUp) {
					enThird = Openkind4third.newOpenkind4third(0, kindid,
							lhubid, agentid, num, money, jtname, lxname,
							lxphone, DateEx.nowDate(), 0);
					enThird = enThird.insert();
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				}
			} else {
				enThird = Openkind4thirdEntity.getByKey(okthirdid);
				if (enThird == null) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败,集团帐号为空!");
					Utls.writeAndClose(response, result);
					return;
				}
			}

			if (isUp) {
				enThird.setAgentid(agentid);
				enThird.setKindid(kindid);
				enThird.setLhubid(lhubid);
				enThird.setMoney(money);
				enThird.setNmContact(lxname);
				enThird.setNmThird(jtname);
				enThird.setNum(num);
				enThird.setPhone(lxphone);
				enThird.update();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 删除开通套餐给学员 ***/
	@RequestMapping("/delOc4Cust")
	public void delOc4Cust(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int okcustid = MapEx.getInt(map, "okcustid");
			Openkind4customer enCust = Openkind4customerEntity
					.getByKey(okcustid);
			if (enCust == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败,该开通用户为空!");
			}
//			else if (enCust.getStatus() != 0) {
//				result = Utls
//						.tipMap(result, Utls.Status_Erro, "失败,该开通用户状态不正常!");
//			}
			else {
				enCust.delete();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 集团账户 **/
	@RequestMapping("/productList")
	public String productList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		page = MapEx.getInt(map, "page");
		page = page == 0 ? 1 : page;
		pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		params.clear();
		params.put("status = ", 0);
		params.put("examineStatus = ", 3);
		params.put("complete = ", 1);
		PageProduct pages = new PageProduct();
		PageEnt<Map> pageEnt = pages.getPage(params, page, pageSize);

		pages.resetList(pageEnt.getListPages());

		// listPages
		modelMap.addAttribute("pageEnt", pageEnt);
		modelMap.addAttribute("uuid", MapEx.getString(map, "uuid"));
		return "admin/productList";
	}
}
