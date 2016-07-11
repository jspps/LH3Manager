package com.admin.logic.controll.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Agent;
import com.admin.db.bean.Cfgs;
import com.admin.db.bean.Customer;
import com.admin.db.bean.Exchangermb;
import com.admin.db.bean.Learnhub;
import com.admin.db.entity.AgentEntity;
import com.admin.db.entity.CfgsEntity;
import com.admin.db.entity.CustomerEntity;
import com.admin.db.entity.ExchangermbEntity;
import com.admin.db.entity.LearnhubEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageExchangeRmb;
import com.bowlong.lang.PStr;
import com.bowlong.net.http.uri.HttpUriPostEx;
import com.bowlong.text.EncodingEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台 - 兑换管理
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes" })
@Controller
@RequestMapping("/doAdmin")
public class AdminExchangeController {

	/*** RMB兑换考币 **/
	@RequestMapping("/rmb2Kbi")
	public String rmb2Kbi(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Cfgs cfg = CfgsEntity.getByKey(6);
		if (cfg == null) {
			cfg = Cfgs.newCfgs(0, "人民币2考币", "", 100);
			cfg.insert();
		}
		modelMap.addAttribute("rateKbi", cfg.getValInt());
		return "admin/rmb2kbi";
	}

	/*** 修改RMB兑换考币 **/
	@RequestMapping("/submitRmb2Kbi")
	public void submitRmb2Kbi(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = Svc.getMapAllParams(request);
		Map result = new HashMap();
		int kbi = MapEx.getInt(map, "kbi");
		if (kbi < 10) {
			result = Utls.tipMap(result, Utls.Status_Erro, "修改RMB兑换考币,失败!");
			Utls.writeAndClose(response, result);
			return;
		}
		Cfgs cfg = CfgsEntity.getByKey(6);
		if (cfg == null) {
			cfg = Cfgs.newCfgs(0, "人民币2考币", "", kbi);
			cfg.insert();
		} else {
			cfg.setValInt(kbi);
			cfg.update();
		}
		result = Utls.tipMap(result, Utls.Status_Success, "修改RMB兑换考币,成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 申请提现管理 **/
	@RequestMapping("/exchangeRmb")
	public String exchangeRmb(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Map mapPars = Svc.getMapAllParams(request);

		PageExchangeRmb pageWrap = new PageExchangeRmb();
		int page = MapEx.getInt(mapPars, "inp_fm_page");
		page = page < 1 ? 1 : page;

		Map<String, Object> params = new HashMap<String, Object>();

		PageEnt<Exchangermb> pageEnt = pageWrap.getPage(params, page, 10);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/exchangeRmb";
	}

	/*** 拒绝 **/
	@RequestMapping("/cancelExchangeRmb")
	public void cancelExchangeRmb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map mapPars = Svc.getMapAllParams(request);

		int pars_id = MapEx.getInt(mapPars, "pars_id");
		if (pars_id <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "拒绝操作失败，参数小于0了！");
			Utls.writeAndClose(response, result);
			return;
		}

		Exchangermb exchangermb = ExchangermbEntity.getByKey(pars_id);
		if (exchangermb == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "拒绝操作失败,对象为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		int optStatus = exchangermb.getStatusOpt();
		if (optStatus != 0 && optStatus != 1) {
			result = Utls.tipMap(result, Utls.Status_Erro, "拒绝操作失败,对象状态不正常!");
			Utls.writeAndClose(response, result);
			return;
		}

		String desc = exchangermb.getReason();
		if (optStatus == 0) {
			desc = PStr.str("拒绝_提现:", desc);
		} else {
			desc = PStr.str("拒绝_取消提现:", desc);
		}

		exchangermb.setReason(desc);
		exchangermb.setStatusOpt(2);
		exchangermb.update();

		result = Utls.tipMap(result, Utls.Status_Success, "拒绝操作成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 同意 : 取消退款 **/
	private void handle4CancelApplay(Exchangermb exchangermb) {
		if (exchangermb == null || exchangermb.getStatusOpt() != 1) {
			// 状态1:表示取消中
			return;
		}

		String desc = exchangermb.getReason();
		desc = PStr.str("同意_取消退款:", desc);
		exchangermb.setReason(desc);

		exchangermb.setStatusOpt(5);
		exchangermb.setLasttime(DateEx.nowDate());
		exchangermb.update();

		// 退还金额 type : 0学员,1lhub,2agent
		int type = exchangermb.getType();
		int makerid = exchangermb.getMakerid();
		double money = exchangermb.getMonyeApply();
		switch (type) {
		case 0:
			Customer cust = CustomerEntity.getByKey(makerid);
			if (cust != null) {
				cust.changeMoneyAll(money);
				cust.changeMoneyCur(money);
				cust.update();
			}
			break;
		case 1:
			Learnhub lhub = LearnhubEntity.getByKey(makerid);
			if (lhub != null) {
				lhub.changeMoneyAll(money);
				lhub.changeMoneyCur(money);
				lhub.update();
			}
			break;
		case 2:
			Agent agent = AgentEntity.getByKey(makerid);
			if (agent != null) {
				agent.changeAllmoney(money);
				agent.changeCurmoney(money);
				agent.update();
			}
			break;
		default:
			break;
		}
	}

	/*** 同意 : 转账 **/
	private String handle4ApplayExchange(Exchangermb exchangermb) {
		if (exchangermb == null || exchangermb.getStatusOpt() != 0) {
			// 状态1:表示取消中
			return "状态异常";
		}

		int pars_id = exchangermb.getId();
		String url = "";
		// url = "http://127.0.0.1:8080/LH3/getHtml4ExchangeRmb";
		url = "http://1010xue.com/getHtml4ExchangeRmb";
		Map<String, String> map = new HashMap<String, String>();
		map.put("pars_id", String.valueOf(pars_id));
		HttpResponse response = HttpUriPostEx.postMap(url, map,
				EncodingEx.UTF_8);
		String result = HttpUriPostEx.readStr(response, EncodingEx.UTF_8);

		boolean isOkey = result.indexOf("<form id=") >= 0;

		if (isOkey) {
			String desc = exchangermb.getReason();
			desc = PStr.str("同意_转账:", desc);
			exchangermb.setReason(desc);

			exchangermb.setStatusOpt(4);
			exchangermb.setLasttime(DateEx.nowDate());
			exchangermb.update();
		}
		return result;
	}

	/*** 同意 **/
	@RequestMapping("/sureExchangeRmb")
	public void sureExchangeRmb(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map result = new HashMap();
		Map mapPars = Svc.getMapAllParams(request);

		int pars_id = MapEx.getInt(mapPars, "pars_id");
		if (pars_id <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "同意操作失败，参数小于0了！");
			Utls.writeAndClose(response, result);
			return;
		}

		Exchangermb exchangermb = ExchangermbEntity.getByKey(pars_id);
		if (exchangermb == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "同意操作失败,对象为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		int optStatus = exchangermb.getStatusOpt();
		if (optStatus != 0 && optStatus != 1) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					PStr.str("同意操作失败,对象状态不正常,状态值为:", optStatus, "!"));
			Utls.writeAndClose(response, result);
			return;
		}

		if (optStatus == 1) {
			handle4CancelApplay(exchangermb);
			result = Utls.tipMap(result, Utls.Status_Success, "同意取消申请提现成功!");
		} else {
			String v = handle4ApplayExchange(exchangermb);
			boolean isOkey = v.indexOf("<form id=") >= 0;
			if (isOkey) {
				result = Utls.tipMap(result, Utls.Status_Success,
						"同意提现成功,数据处理中!", v);
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, v, v);
			}

		}
		Utls.writeAndClose(response, result);
	}
}
