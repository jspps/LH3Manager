package com.admin.logic.controll.center.institution4info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Account;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Msg;
import com.admin.db.entity.AccountEntity;
import com.admin.db.entity.LearnhubEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.PageMsg;
import com.bowlong.lang.StrEx;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 学习中心-机构信息
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class InstitutionInfoController {
	/*** 学习中心管理后台_基本信息 **/
	@RequestMapping("/basicInformation")
	public String basicInformation(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Learnhub learnhub = CenterController.getLhub(session);
		if (learnhub == null)
			return "center/userlogin";

		modelMap.addAttribute("user", learnhub);
		modelMap.addAttribute("account", learnhub.getAccountFkAccountid());
		String img = learnhub.getImgr4Cover();
		if (!StrEx.isEmptyTrim(img)) {
			String[] imgs = img.split(",");
			List<String> listimg = new ArrayList<String>();
			for (String imgurl : imgs) {
				listimg.add(imgurl);
			}
			modelMap.addAttribute("listimg", listimg);
		}
		String img1 = learnhub.getImg4logo();
		modelMap.addAttribute("img1", img1);
		
		return "center/basicInformation";
	}

	/*** 修改简介 **/
	@RequestMapping("/modDesc")
	public void modifyDecription(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		String desc = MapEx.getString(map, "desc");
		if (StrEx.isEmptyTrim(desc)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,简介为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		Learnhub le = CenterController.getLhub(session);
		if (le == null) {
			result = Utls
					.tipMap(result, Utls.Status_Erro, "失败,session失效,重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}
		le.setDescr(desc);
		le.update();
		result = Utls.tipMap(result, Utls.Status_Success, "修改成功");
		Utls.writeAndClose(response, result);
	}

	/*** 修改学习中心信息 **/
	@RequestMapping("/modLearnhub")
	public void modLearnhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Map result = new HashMap();
		Learnhub learnhub = CenterController.getLhub(session);
		if (learnhub == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,session失效,请重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}

		Map map = Svc.getMapAllParams(request);
		String name = MapEx.getString(map, "name");
		String uname = MapEx.getString(map, "uname");
		String province = MapEx.getString(map, "province");
		String city = MapEx.getString(map, "city");
		String seat = MapEx.getString(map, "seat");

		String account_phone = MapEx.getString(map, "account_phone");
		
		boolean isMobile = TkitValidateCheck.isMobile(account_phone);
		if (!isMobile) {
			result = Utls.tipMap(result, Utls.Status_Erro, "您输入的手机号码有误！");
			Utls.writeAndClose(response, result);
			return;
		}
		

		// String account_alipay = MapEx.getString(map, "account_alipay");
		// if (StrEx.isEmptyTrim(account_alipay)) {
		// result = Utls.tipMap(result, Utls.Status_Erro, "支付宝帐号不能为空!");
		// Utls.writeAndClose(response, result);
		// return;
		// }

		Account account = learnhub.getAccountFkAccountid();
		Account acc = AccountEntity.getByPhone(account_phone);
		if (acc != null && account.getId() != acc.getId()) {
			result = Utls.tipMap(result, Utls.Status_Erro, "电话号码已经存在!");
			Utls.writeAndClose(response, result);
			return;
		}

		account.setPhone(account_phone);
		account.update();

		learnhub.setName(name);
		learnhub.setUname(uname);
		learnhub.setProvince(province);
		learnhub.setCity(city);
		learnhub.setSeat(seat);
		// learnhub.setAlipay(account_alipay);
		learnhub.update();

		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 修改宣传图片 **/
	@RequestMapping("/modimg")
	public void modimg(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Learnhub learnhub = CenterController.getLhub(session);
		if (learnhub == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,Session失效,请重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}
		Map map = Svc.getMapAllParams(request);
		String imgs = MapEx.getString(map, "imgs");
		learnhub.setImgr4Cover(imgs);
		LearnhubEntity.update(learnhub);
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}
	
	/*** 修改Logo图片 **/
	@RequestMapping("/logo")
	public void logo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Learnhub learnhub = CenterController.getLhub(session);
		if (learnhub == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,Session失效,请重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}
		Map map = Svc.getMapAllParams(request);
		String imgs = MapEx.getString(map, "imgurl");
		learnhub.setImg4logo(imgs);
		LearnhubEntity.update(learnhub);
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 学习中心管理后台_最新消息 **/
	@RequestMapping("/latestNews")
	public String latestNews(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 0);
		params.put("target", "%3%");
		PageMsg pageMsg = new PageMsg();
		PageEnt<Msg> pageEnt = pageMsg.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "center/latestNews";
	}

	/*** 学习中心管理后台_密码设置 **/
	@RequestMapping("/passwordSettings")
	public String passwordSettings(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "center/passwordSettings";
	}

	/*** 修改密码 **/
	@RequestMapping("/modpassword")
	public void modpassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		String password = MapEx.getString(map, "password");
		String password2 = MapEx.getString(map, "password2");
		if (StrEx.isEmptyTrim(password)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,密码为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (!password.equals(password2)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,两次密码不同!");
			Utls.writeAndClose(response, result);
			return;
		}

		Learnhub le = CenterController.getLhub(session);
		if (le == null) {
			result = Utls
					.tipMap(result, Utls.Status_Erro, "失败,session失效,重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}

		Account account = le.getAccountFkAccountid();
		if (account == null) {
			result = Utls
					.tipMap(result, Utls.Status_Erro, "失败,数据异常,Account为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		String ypsassword = MapEx.getString(map, "ypsassword");
		if (!account.getLgpwd().equals(ypsassword)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "原始密码错误！");
			Utls.writeAndClose(response, result);
			return;
		}
		account.setLgpwd(password);
		account.update();
		result = Utls.tipMap(result, Utls.Status_Success, "修改成功");
		Utls.writeAndClose(response, result);
	}
}
