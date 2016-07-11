package com.admin.logic.controll.center.itembackmanager;

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
import com.admin.db.bean.Exam;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Product;
import com.admin.db.bean.Product0examtype;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.db.entity.Product0examtypeEntity;
import com.admin.db.entity.ProductEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.PageExam2;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 学习中心--设置试题类别[产品目录](2)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doCenter")
public class IBM4ProductCatalogController {
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 学习中心管理后台_去产品目录编辑界面(procute2examtype) **/
	@RequestMapping("/go2ProExamType")
	public String go2ProExamType(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		Map map = Svc.getMapAllParams(request);
		int productid = MapEx.getInt(map, "productid");
		Product product = ProductEntity.getByKey(productid);
		if (product == null) {
			return "center/userlogin";
		}

		modelMap.addAttribute("product", product);
		modelMap.addAttribute("catalog4ProductType", 5);
		modelMap.addAttribute("curCursorType", 2);
		return "center/product/productCatalog";
	}

	/*** 学习中心管理后台_去产品目录视图界面(catalog4Product) **/
	@RequestMapping("/catalog4Product")
	public String catalog4Product(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map map = Svc.getMapAllParams(request);
		int productid = MapEx.getInt(map, "productid");
		int catalog4ProductType = MapEx.getInt(map, "ctype");
		Product product = ProductEntity.getByKey(productid);
		if (product == null) {
			return "redirect:/admin/login";
		}

		int lhubid = product.getLhubid();
		int examtypeid = catalog4ProductType;
		Product0examtype productCatalog = Product0examtypeEntity
				.getByLhubidProductidExamtypeid(lhubid, productid, examtypeid);
		int pro0etpid = -1;
		if (productCatalog != null) {
			pro0etpid = productCatalog.getId();
		}

		PageExam2 pageExam = new PageExam2();
		page = MapEx.getInt(map, "inp_fm_page");
		pageSize = MapEx.getInt(map, "pageSize");
		page = page <= 0 ? 1 : page;
		pageSize = pageSize <= 0 ? 10 : pageSize;

		params.clear();
		params.put("pro0etpid", "=" + pro0etpid);
		params.put("status", "!= 1");

		PageEnt<Map> pageEnt = pageExam.getPage(params, page, pageSize);

		modelMap.addAttribute("product", product);
		modelMap.addAttribute("productCatalog", productCatalog);
		modelMap.addAttribute("catalog4ProductType", catalog4ProductType);
		modelMap.addAttribute("pageEnt", pageEnt);

		int numQues = OptquestionEntity.getCountByCatalog4Procut(pro0etpid);
		modelMap.addAttribute("numQues", numQues);
		return "center/product/catalog4Product";
	}

	/*** 修改知识要点 **/
	@RequestMapping("/changeCruces")
	public void changeCruces(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int pid = MapEx.getInt(map, "productid");
			// int examineStatus 审核状态 0 初始化 1审核中 2 审核不通过 3审核通过
			Product product = ProductEntity.getByKey(pid);
			if (product != null) {
				String cruces = MapEx.getString(map, "cruces");
				product.setCruces(cruces);
				product.update();
				result = Utls.tipMap(result, Utls.Status_Success, "修改知识要点,成功!");
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "题库为空!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "修改知识要点,失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 修改产品目录的信息product0examtype **/
	@RequestMapping("/changeProCatalog")
	public void changeProCatalog(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int catalogid = MapEx.getInt(map, "catalogid");
			Product0examtype catalog = null;
			if (catalogid != 0) {
				catalog = Product0examtypeEntity.getByKey(catalogid);
			}

			int buymoney = MapEx.getInt(map, "buymoney");
			int friend = MapEx.getInt(map, "friend");
			int kbi = MapEx.getInt(map, "kbi");

			if (catalog == null) {
				int pid = MapEx.getInt(map, "productid");
				// int examineStatus 审核状态 0 初始化 1审核中 2 审核不通过 3审核通过
				Product product = ProductEntity.getByKey(pid);
				if (product != null) {
					int lhubid = product.getLhubid();
					int extypeid = MapEx.getInt(map, "extypeid");
					catalog = Product0examtypeEntity
							.getByLhubidProductidExamtypeid(lhubid, pid,
									extypeid);
					if (catalog == null) {
						int status = 0;
						Date createtime = DateEx.nowDate();
						catalog = Product0examtype.newProduct0examtype(0,
								lhubid, pid, extypeid, (double) buymoney,
								friend, kbi, status, createtime);
						catalog.insert();
					} else {
						catalog.setBuymoney(buymoney);
						catalog.setFriend(friend);
						catalog.setKbi(kbi);
						catalog.update();
					}

					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				} else {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败,题库为空!");
				}
			} else {
				catalog.setBuymoney(buymoney);
				catalog.setFriend(friend);
				catalog.setKbi(kbi);
				catalog.update();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,出现错误!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 添加新的试卷 **/
	@RequestMapping("/newExam")
	public void newExam(HttpServletRequest request, HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int catalogid = MapEx.getInt(map, "catalogid");
			Product0examtype catalog = null;
			if (catalogid != 0) {
				catalog = Product0examtypeEntity.getByKey(catalogid);
			}
			if (catalog == null) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"添加新试卷失败，目录catalogid=" + catalogid + ",不存在!");
			} else {
				String newname = MapEx.getString(map, "newname");
				if (StrEx.isEmptyTrim(newname)) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"添加新试卷失败，试卷名字为空!");
				} else {
					newname = newname.trim();
					int examtypeid = catalog.getExamtypeid();
					int score = 0;
					int lhubid = catalog.getLhubid();
					int examtime = 0;
					int status = 0;
					Date createtime = DateEx.nowDate();
					int productid = catalog.getProductid();
					String desc = "";
					Exam exam = Exam.newExam(0, examtypeid, newname, score,
							lhubid, examtime, status, createtime, productid,
							catalogid, desc);
					exam.insert();
					result = Utls.tipMap(result, Utls.Status_Success,
							"添加新试卷成功!");
				}
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "添加新试卷失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 删除试卷 **/
	@RequestMapping("/delExam")
	public void delExam(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
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
			int examid = MapEx.getInt(map, "examid");
			Exam exam = null;
			if (examid != 0) {
				exam = ExamEntity.getByKey(examid);
			}
			if (exam == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "删除试卷失败:examid="
						+ examid + ",不存在!");
			} else {
				Product product = ProductEntity.getByKey(exam.getProductid());
				if (product == null) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败,课程为空,不能删除!");
				} else {
					// product.getExamineStatus() == 3
					// result = Utls.tipMap(result,
					// Utls.Status_Erro,"失败,通过审核,不能删除!");
					exam.setStatus(1);
					exam.update();
					result = Utls.tipMap(result, Utls.Status_Success, "删除成功!");
				}
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "删除失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 修改试卷名字 **/
	@RequestMapping("/changeExamName")
	public void changeExamName(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int examid = MapEx.getInt(map, "examid");
			Exam exam = null;
			if (examid != 0) {
				exam = ExamEntity.getByKey(examid);
			}
			if (exam == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "删除试卷失败:examid="
						+ examid + ",不存在!");
			} else {
				String examname = MapEx.getString(map, "examname");
				if (StrEx.isEmptyTrim(examname)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败，名字为空!");
				} else {
					examname = examname.trim();
					if (examname.equals(exam.getName())) {
						result = Utls.tipMap(result, Utls.Status_Erro,
								"失败，名字相同!");
					} else {
						exam.setName(examname);
						exam.update();
						result = Utls
								.tipMap(result, Utls.Status_Success, "成功!");
					}
				}
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}
}
