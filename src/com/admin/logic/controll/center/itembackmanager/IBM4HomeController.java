package com.admin.logic.controll.center.itembackmanager;

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

import com.admin.content.Svc;
import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Adqdepartment;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Product;
import com.admin.db.entity.AdcoursesEntity;
import com.admin.db.entity.AdqdepartmentEntity;
import com.admin.db.entity.ProductEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.PageProduct;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 学习中心--题库管理[产品](1)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class IBM4HomeController {
	
	protected Map<String, Object> params4Search = new HashMap<String, Object>();
	
	
	/*** 学习中心管理后台_题库管理 **/
	@RequestMapping("/go2ManagerProducts")
	public String go2ManagerProducts(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		int lhubid = CenterController.getLhubid(session);
		if (lhubid <= 0) {
			return "center/userlogin";
		}
		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();// 分类
		modelMap.addAttribute("adqdepartments", adqdepartments);

		Map map = Svc.getMapAllParams(request);

		Map<String, Object> params = new HashMap<String, Object>();
		int page = MapEx.getInt(map, "inp_fm_page");
		boolean isPage = map.containsKey("inp_fm_page");
		int pageSize = MapEx.getInt(map, "pageSize");
		
		if (!isPage) {
			int departid = MapEx.getInt(map, "departid");
			String nmMajor = MapEx.getString(map, "nmMajor");
			String nmLevel = MapEx.getString(map, "nmLevel");
			String nmSub = MapEx.getString(map, "nmSub");
			String nmArea = MapEx.getString(map, "nmArea");

			params4Search.put("departid", departid);
			params4Search.put("nmMajor", nmMajor);
			params4Search.put("nmLevel", nmLevel);
			params4Search.put("nmSub", nmSub);
			params4Search.put("nmArea", nmArea);
		}
		
		page = page == 0 ? 1 : page;
		pageSize = pageSize == 0 ? 10 : pageSize;

		params.put("status = ", 0);
		params.put("lhubid = ", lhubid);
		if (MapEx.getInt(params4Search, "departid") > 0) {
			List<Integer> idsCourses = Utls.getCourseIds(params4Search);
			if (!ListEx.isEmpty(idsCourses)) {
				int lens = idsCourses.size();
				PStr pStr = PStr.b(" IN (");
				for (int i = 0; i < lens; i++) {
					pStr.a(idsCourses.get(i));
					if(i < lens - 1){
						pStr.a(",");
					}
				}
				params.put("coursesid", pStr.e(")"));
			}
		}

		PageProduct pages = new PageProduct();
		PageEnt<Map> pageEnt = pages.getPage(params, page, pageSize);
		pages.resetList4Coures(pageEnt.getListPages());

		modelMap.addAttribute("pageEnt", pageEnt);

		modelMap.addAttribute("curCursorType", 1);

		return "center/product/managerProducts";
	}

	/*** 学习中心管理后台_删除课程(对学习中心而言是一个产品) **/
	@RequestMapping("/delProduct")
	public void delProduct(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		Map result = new HashMap();
		try {
			Learnhub lhub = CenterController.getLhub(session);
			if (lhub == null) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"删除试卷失败:session失效");
				Utls.writeAndClose(response, result);
				return;
			}

			if (!lhub.getIsselfadmin()) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"删除试卷失败:没有自主管理权限");
				Utls.writeAndClose(response, result);
				return;
			}

			Map map = Svc.getMapAllParams(request);
			int productid = MapEx.getInt(map, "productid");
			Product product = ProductEntity.getByKey(productid);
			// if (product.getExamineStatus() != 3) {
			product.setStatus(1);
			product.setLasttime(DateEx.nowDate());
			product.update();
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			// } else {
			// result = Utls.tipMap(result, Utls.Status_Erro, "失败,通过审核,不能删除!");
			// }

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 添加产品 **/
	@RequestMapping("/addProduct")
	public void addProduct(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Map result = new HashMap();
		int lhubid = CenterController.getLhubid(session);
		Map map = Svc.getMapAllParams(request);
		int departid = MapEx.getInt(map, "departid"); // 分类
		String nmMajor = MapEx.getString(map, "nmMajor"); //
		String nmLevel = MapEx.getString(map, "nmLevel"); //
		String nmSub = MapEx.getString(map, "nmSub"); //
		String nmArea = MapEx.getString(map, "nmArea"); //

		if (departid == 0 || StrEx.isEmptyTrim(nmArea)
				|| StrEx.isEmptyTrim(nmMajor) || StrEx.isEmptyTrim(nmLevel)
				|| StrEx.isEmptyTrim(nmSub)) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败，选择的参数有为零值!deaprt=" + departid + ",major=" + nmMajor
							+ ",level=" + nmLevel + ",sub=" + nmSub + ",area="
							+ nmSub);
			Utls.writeAndClose(response, result);
			return;
		}

		if (nmMajor.equals("-1")) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，请选择专业！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (nmLevel.equals("-1")) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，请选择层级！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (nmSub.equals("-1")) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，请选择科目！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (nmArea.equals("-1")) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，请选择考试范围！");
			Utls.writeAndClose(response, result);
			return;
		}

		Adcourses adcours = AdcoursesEntity.getCourse(departid, nmMajor,
				nmLevel, nmSub, nmArea);
		if (adcours == null || adcours.getCid() <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，数据出错，没取得对象!");
		} else {
			int coursesid = adcours.getCid();
			// Product 添加字段
			// int examineStatus 审核状态 0 初始化 1审核中 2 审核不通过 3审核通过
			// String examineDes 审核内容
			// int complete 是否完成 0 未完成 1完成
			String name = PStr.str(adcours.getNmMajor(), adcours.getNmLevel(),
					adcours.getNmSub());
			String imgurl = "jsp/imgs/client/51.jpg";
			String descr = "";
			int status = 0;
			int examineStatus = 0;
			String examineDes = "";
			int complete = 0;
			Date createtime = new Date();
			String cruces = "";
			String protection = "";
			Product product = Product.newProduct(0, coursesid, name, imgurl,
					descr, lhubid, status, examineStatus, examineDes, complete,
					createtime, cruces, false, createtime, protection,
					createtime);
			product = product.insert();
			if (product != null && product.getId() != 0) {
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
			}
		}

		Utls.writeAndClose(response, result);
	}

	/*** 题库管理-- 完成未完成题库 **/
	@RequestMapping("/completeproduct")
	public void completeproduct(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int pid = MapEx.getInt(map, "productid");
		int complete = MapEx.getInt(map, "complete");
		// int complete 是否完成 0 未完成 1完成
		Product product = ProductEntity.getByKey(pid);
		if (product == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,课程不存在!productid=" + pid);
			Utls.writeAndClose(response, result);
			return;
		}

		product.setComplete(complete);
		product.setLasttime(DateEx.nowDate());
		ProductEntity.update(product);
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}

	/*** 题库管理-- 提交审核题库 **/
	@RequestMapping("/examineStatusProduct")
	public void examineStatusProduct(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int pid = MapEx.getInt(map, "productid");
			// int examineStatus 审核状态 0 初始化 1审核中 2 审核不通过 3审核通过
			Product product = ProductEntity.getByKey(pid);
			if (product != null) {
				if (product.getComplete() == 1) {
					product.setExamineStatus(1);
					product.setLasttime(DateEx.nowDate());
					product.update();
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				} else {
					result = Utls.tipMap(result, Utls.Status_Erro, "题库状态还未完成!");
				}
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "题库为空!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}
}
