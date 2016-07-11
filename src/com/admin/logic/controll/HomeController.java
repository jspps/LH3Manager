package com.admin.logic.controll;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.admin.content.Svc;
import com.admin.db.bean.Account;
import com.admin.db.bean.Agent;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Orders;
import com.admin.db.entity.AccountEntity;
import com.admin.db.entity.AdcoursesEntity;
import com.admin.db.entity.AgentEntity;
import com.admin.db.entity.LearnhubEntity;
import com.admin.db.entity.OrdersEntity;
import com.admin.logic.Utls;
import com.admin.logic.chn.alipayapi.handle.Handle4Alipay;
import com.admin.logic.controll.admin.AdminController;
import com.admin.logic.json.Json4Upload;
import com.bowlong.image.ImgEx;
import com.bowlong.io.FileRw;
import com.bowlong.lang.StrEx;
import com.bowlong.security.Encrypt;
import com.bowlong.tool.TkitJsp;
import com.bowlong.util.MapEx;

/**
 * 后台管理控制器
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
public class HomeController {
	/*** 取得图片，并记录图片字 **/
	@RequestMapping("/checkCode")
	public void checkCode(HttpServletResponse response, HttpSession session) {
		try {
			OutputStream out = response.getOutputStream();
			String code = ImgEx.outImgBy(out, 90, 33, 4);
			session.setAttribute(Utls.KeyCodeAdminLg, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/change4Load")
	public void change4Load(HttpServletRequest request, ModelMap modelMap) {
		Map map = Utls.getMapAllParams(request);
		boolean isWindows = MapEx.getBoolean(map, "isWindows");
		Json4Upload.isWindows = isWindows;
		StringBuffer buff = new StringBuffer();
		buff.append(TkitJsp.getUrlIPProject(request));
		if (map.containsKey("isInitMap")) {
			Json4Upload.isInitMap = MapEx.getBoolean(map, "isInitMap");
			buff.append(",初始化Map = ").append(Json4Upload.isInitMap);
		}
		buff.append(",windows = ").append(isWindows);
		buff.append(",getServletPath = ").append(request.getServletPath());
		modelMap.addAttribute("msg", buff.toString());
	}

	/*** 取得专业名字列表 **/
	@RequestMapping("/getNmMajors")
	public void getNmMajors(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		List<Map> list = AdcoursesEntity.getNmmajors(departid);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得层级列表 **/
	@RequestMapping("/getNmLevels")
	public void getNmLevels(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		List<String> list = AdcoursesEntity.getNmlevels(departid, nmmajor);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得专业列表 **/
	@RequestMapping("/getNmSubs")
	public void getNmSubs(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		String nmLevel = MapEx.getString(map, "nmLevel");
		List<String> list = AdcoursesEntity.getNmsubs(departid, nmmajor,
				nmLevel);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 取得考试范围列表 **/
	@RequestMapping("/getNmAreas")
	public void getNmAreas(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid");
		String nmmajor = MapEx.getString(map, "nmMajor");
		String nmLevel = MapEx.getString(map, "nmLevel");
		String nmSub = MapEx.getString(map, "nmSub");
		List<String> list = AdcoursesEntity.getNmAreas(departid, nmmajor,
				nmLevel, nmSub);
		result = Utls.tipMap(result, Utls.Status_Success, "成功", list);
		Utls.writeAndClose(response, result);
	}

	/*** 上传地址 **/
	@RequestMapping("/getXmlFile")
	public String getXmlFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("action", "handleXmlFile");
		return "xlsfile";
	}

	/*** 上传地址 **/
	@RequestMapping("/handleXmlFile")
	public void handleXmlFile(HttpServletRequest request,
			MultipartHttpServletRequest fileRequest,
			HttpServletResponse response) {
		String pathDir = "/xml";
		String rootPath = request.getSession().getServletContext()
				.getRealPath("");
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = rootPath + pathDir;
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();

		/** 页面控件的文件流 **/
		MultipartFile multipartFile = fileRequest.getFile("fileField");
		/** 获取文件的后缀 **/
		System.out.println(multipartFile.getOriginalFilename());
		String suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));

		/** 拼成完整的文件保存路径加文件 **/
		String name = +System.currentTimeMillis() + suffix;
		String fileName = logoRealPathDir + File.separator + name;
		File file = FileRw.getFile(fileName);

		String result = "";
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			result = Utls.e2s(e);
		}
		Utls.writeAndClose(response, result, "UTF-8");
	}

	/*** 支付宝充(测试) **/
	@RequestMapping("/testPay4Alipay")
	public String testPay4Alipay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Utls.getMapAllParams(request);
		// type[1:lhub,2:agent]
		int type = MapEx.getInt(mapPars, "type");
		// 对应表learnhub,agent的标识ID
		int ident = MapEx.getInt(mapPars, "id");
		String name = MapEx.getString(mapPars, "name");
		double money = MapEx.getDouble(mapPars, "money");
		money = money <= 0 ? 1 : money;
		String v = Handle4Alipay.handle4OneTest(type, ident, name, money);
		modelMap.addAttribute("body", v);
		return "alipayapi";
	}

	/*** 支付宝充值回调(测试)-LH3处理完毕后,再发过来的请求 **/
	@RequestMapping("/notify_url_test")
	public void notify_url_test(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request, "", "");
		int orderid = MapEx.getInt(mapPars, "orderid");
		String buyemail = MapEx.getString(mapPars, "buyemail");
		Orders order = OrdersEntity.getByKey(orderid);
		if (order != null && !StrEx.isEmptyTrim(buyemail)) {
			int type = order.getType();
			int makerid = order.getMakerid();
			if (makerid > 0) {
				if (type == 1) {
					Learnhub lhub = LearnhubEntity.getByKey(makerid);
					lhub.setAlipay(buyemail);
					lhub.setIsVerifyAlipay(true);
					lhub.update();
				} else {
					Agent agent = AgentEntity.getByKey(makerid);
					agent.setAlipay(buyemail);
					agent.setIsVerifyAlipay(true);
					agent.update();
				}
				Utls.writeAndClose(response, "success", "");
				return;
			}
		}
		Utls.writeAndClose(response, "error", "");
	}

	/*** 支付宝充值 跳转界面 **/
	@RequestMapping("/return_url_test")
	public String return_url_test(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "redirect:/admin/login";
	}

	/*** 学习中心,代理商 **/
	void go2LhubAgent(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, boolean isLhub) throws Exception {
		Map<String, String> mapPars = Utls.getMapAllParamsBy(request, "", "");
		String dataStr = MapEx.getString(mapPars, "data");
		int accountid = 0;
		try {
			accountid = Encrypt.decodeInt(dataStr);
		} catch (Exception e) {
		}

		String base = "tip";
		if (isLhub) {
			session.setAttribute(Utls.KeyTipUrl, "loginLhub");
		} else {
			session.setAttribute(Utls.KeyTipUrl, "loginAgent");
		}
		if (accountid <= 0) {
			session.setAttribute(Utls.KeyTip, "帐号错误！");
		} else {
			Account account = AccountEntity.getByKey(accountid);
			if (account == null || (!isLhub && account.getType() != 3)
					|| (isLhub && account.getType() != 2)) {
				session.setAttribute(Utls.KeyTip, "帐号异常！");
			} else {
				boolean isAdmin = MapEx.getBoolean(mapPars, "isAdmin");
				if (isAdmin) {
					base = AdminController.verify_Login_4_admin(account,
							session);
				} else {
					base = AdminController.verify_Login(account, session);
				}
			}
		}
		String path = request.getContextPath();
		response.sendRedirect(path + "/" + base);
	}

	/*** 跳转到代理商 **/
	@RequestMapping("/go2Agent")
	public void go2Agent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		session.removeAttribute(Utls.KeyEnAgent);
		go2LhubAgent(request, response, session, false);
	}

	/*** 跳转到学习中心 **/
	@RequestMapping("/go2Lhub")
	public void go2Lhub(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		session.removeAttribute(Utls.KeyEnCenter);
		go2LhubAgent(request, response, session, true);
	}

	/*** 提示信息 **/
	@RequestMapping("/tip")
	public String tip(HttpSession session) {
		return "tip";
	}

	/*** 取得登录界面:代理商 **/
	@RequestMapping("/loginAgent")
	public String loginAgent(HttpSession session) {
		session.removeAttribute(Utls.KeyEnAgent);
		return "loginAgent";
	}

	/*** 取得登录界面:学习中心 **/
	@RequestMapping("/loginLhub")
	public String loginLhub(HttpSession session) {
		session.removeAttribute(Utls.KeyEnCenter);
		return "loginLhub";
	}
}
