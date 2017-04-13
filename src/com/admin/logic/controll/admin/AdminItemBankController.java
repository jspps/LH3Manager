package com.admin.logic.controll.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.content.Svc;
import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Adqdepartment;
import com.admin.db.bean.Appraise;
import com.admin.db.bean.Cfgs;
import com.admin.db.bean.Product;
import com.admin.db.entity.AdcoursesEntity;
import com.admin.db.entity.AdqdepartmentEntity;
import com.admin.db.entity.CfgsEntity;
import com.admin.db.entity.ProductEntity;
import com.admin.logic.Utls;
import com.admin.logic.model.PageAdcourses;
import com.admin.logic.model.PageAppraise;
import com.admin.logic.model.PageProduct;
import com.alibaba.fastjson.JSON;
import com.bowlong.lang.NumEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-题库管理
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/doAdmin")
public class AdminItemBankController {

	// 不用随机图片依次增加改变
	static public Map<Integer, Integer> mapMajorImg = new HashMap<Integer, Integer>();

	protected Map<String, Object> params4Search = new HashMap<String, Object>();

	/*** 题库管理--课程设置 **/
	@RequestMapping("/questionManage_set")
	public String questionManage_set(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		int pageSize = MapEx.getInt(map, "pageSize");
		boolean isPage = map.containsKey("inp_fm_page");
		if (!isPage) {
			params4Search.clear();

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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status = ", 0);
		if (MapEx.getInt(params4Search, "departid") > 0) {
			String vStr = "";
			for (Entry<String, Object> entry : params4Search.entrySet()) {
				vStr = entry.getValue().toString();
				if (StrEx.isEmptyTrim(vStr) || "-1".equals(vStr))
					continue;
				if (entry.getValue() instanceof String) {
					params.put(entry.getKey() + " = '", vStr + "'");
					continue;
				}
				params.put(entry.getKey() + " = ", vStr);
			}
		}

		PageAdcourses pageWrap = new PageAdcourses();
		PageEnt<Adcourses> pageEnt = pageWrap.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);

		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();
		modelMap.addAttribute("adqdepartments", adqdepartments);
		return "admin/questionManage_set";
	}

	String getMajoryImg(int departid) {
		// 财经类-5- finance
		// 电脑类 -6- computer
		// 公招类 -8- recruit
		// 建筑类 -3- build
		// 特色类 -9- special
		// 外贸类 -7- foreigntrade
		// 学历类 -1- education
		// 医学类 -4- medicine
		// 职业类 -2- vocation
		Cfgs cfg = CfgsEntity.getByKey(5);
		if (MapEx.isEmpty(mapMajorImg)) {
			if (cfg == null) {
				mapMajorImg.put(1, 0);
				mapMajorImg.put(2, 0);
				mapMajorImg.put(3, 0);
				mapMajorImg.put(4, 0);
				mapMajorImg.put(5, 0);
				mapMajorImg.put(6, 0);
				mapMajorImg.put(7, 0);
				mapMajorImg.put(8, 0);
				mapMajorImg.put(9, 0);
				String json = MapEx.toStr4Json(mapMajorImg);
				cfg = Cfgs.newCfgs(5, "课程图片顺序", json, 0);
				cfg.insert();
			} else {
				Map val = (Map) JSON.parse(cfg.getValStr());
				Map<String, String> valKV = MapEx.toMapKV(val);
				for (Entry<String, String> entry : valKV.entrySet()) {
					mapMajorImg.put(NumEx.stringToInt(entry.getKey()),
							NumEx.stringToInt(entry.getValue()));
				}
			}
		}
		StringBuffer buff = new StringBuffer();
		buff.append("jsp/imgs/couses/");
		switch (departid) {
		case 1:
			buff.append("education/");
			break;
		case 2:
			buff.append("vocation/");
			break;
		case 3:
			buff.append("build/");
			break;
		case 4:
			buff.append("medicine/");
			break;
		case 5:
			buff.append("finance/");
			break;
		case 6:
			buff.append("computer/");
			break;
		case 7:
			buff.append("foreigntrade/");
			break;
		case 8:
			buff.append("recruit/");
			break;
		default:
			buff.append("special/");
			break;
		}

		int index = MapEx.getInt(mapMajorImg, departid);// RndEx.nextInt(1, 9);
		index++;
		index %= 9;
		index = index <= 0 ? 1 : index;
		buff.append("0").append(index).append(".jpg");

		mapMajorImg.put(departid, index);
		String json = MapEx.toStr4Json(mapMajorImg);
		cfg.setValStr(json);
		cfg.update();

		return buff.toString();
	}

	/*** 题库管理--添加题库 **/
	@RequestMapping("/addquestionManage")
	public void addquestionManage(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int departid = MapEx.getInt(map, "adqdepartmentId"); // 分类
			String nmMajor = MapEx.getString(map, "adqmajor");// 专业
			if (StrEx.isEmptyTrim(nmMajor)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败,专业为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			String nmLevel = MapEx.getString(map, "adqlevel");// 层次
			if (StrEx.isEmptyTrim(nmLevel)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败,层次为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			String nmSub = MapEx.getString(map, "adqsubject");// 科目
			if (StrEx.isEmptyTrim(nmSub)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "科目,层次为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			String nmArea = MapEx.getString(map, "adqarea");// 考试范围
			if (StrEx.isEmptyTrim(nmArea)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "科目,考试范围为空!");
				Utls.writeAndClose(response, result);
				return;
			}

			boolean isHas = AdcoursesEntity.isHasCourses(departid, nmMajor,
					nmLevel, nmSub, nmArea);
			if (isHas) {
				result = Utls.tipMap(result, Utls.Status_Erro, "该课程已经存在了!");
				Utls.writeAndClose(response, result);
				return;
			}

			// String imgurl4major = AdcoursesEntity.getImg4NmMajor(departid,
			// nmMajor);
			// if (StrEx.isEmpty(imgurl4major)) {
			// imgurl4major = getMajoryImg(departid);
			// }

			int profitAgent = MapEx.getInt(map, "profitAgent"); // '代理商利润百分百',
			int profitOwner = MapEx.getInt(map, "profitOwner"); // '题库拥有者利润百分比',
			int deposit = MapEx.getInt(map, "deposit"); // '质量押金百分比',
			int bonus = MapEx.getInt(map, "bonus"); // '代理商奖金百分比',
			int wrong = MapEx.getInt(map, "wrong"); // '错误',
			int program = MapEx.getInt(map, "program"); // '程序提成(RMB:元)',
			int art = MapEx.getInt(map, "art"); // '美工提成(RMB:元)'
			Date createtime = DateEx.nowDate();

			Adcourses adcs = Adcourses.newAdcourses(0, departid, nmMajor,
					nmLevel, nmSub, nmArea, profitAgent, profitOwner, deposit,
					bonus, wrong, program, art, 0, createtime);
			adcs = AdcoursesEntity.insert(adcs);
			if (adcs != null && adcs.getCid() != 0) {
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "此条数据已经存在 失败!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 题库管理--修改题库 **/
	@RequestMapping("/modquestionManage")
	public void modquestionManage(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);

			int cid = MapEx.getInt(map, "cid"); // 题库
			Adcourses adcourses = AdcoursesEntity.getByKey(cid);
			if (adcourses == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "此条数据已经不存在 失败!");
			} else {
				int departid = MapEx.getInt(map, "adqdepartmentId"); // 分类
				String nmMajor = MapEx.getString(map, "adqmajor");// 专业
				if (StrEx.isEmptyTrim(nmMajor)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败,专业为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				String nmLevel = MapEx.getString(map, "adqlevel");// 层次
				if (StrEx.isEmptyTrim(nmLevel)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败,层次为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				String nmSub = MapEx.getString(map, "adqsubject");// 科目
				if (StrEx.isEmptyTrim(nmSub)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "科目,层次为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				String nmArea = MapEx.getString(map, "adqarea");// 考试范围
				if (StrEx.isEmptyTrim(nmArea)) {
					result = Utls
							.tipMap(result, Utls.Status_Erro, "科目,考试范围为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				int profitAgent = MapEx.getInt(map, "profitAgent"); // '代理商利润百分百',
				int profitOwner = MapEx.getInt(map, "profitOwner"); // '题库拥有者利润百分比',
				int deposit = MapEx.getInt(map, "deposit"); // '质量押金百分比',
				int bonus = MapEx.getInt(map, "bonus"); // '代理商奖金百分比',
				int wrong = MapEx.getInt(map, "wrong"); // '错误',
				int program = MapEx.getInt(map, "program"); // '程序提成(RMB:元)',
				int art = MapEx.getInt(map, "art"); // '美工提成(RMB:元)'

				adcourses.setDepartid(departid);

				adcourses.setProfitAgent(profitAgent);
				adcourses.setProfitOwner(profitOwner);
				adcourses.setDeposit(deposit);
				adcourses.setBonus(bonus);
				adcourses.setWrong(wrong);
				adcourses.setProgram(program);
				adcourses.setArt(art);

				adcourses.setNmArea(nmArea);
				adcourses.setNmLevel(nmLevel);
				adcourses.setNmMajor(nmMajor);
				adcourses.setNmSub(nmSub);

				adcourses = adcourses.update();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 题库管理--删除题库 **/
	@RequestMapping("/delquestionManage")
	public void delquestionManage(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int cid = MapEx.getInt(map, "cid"); // 题库
			Adcourses adcourses = AdcoursesEntity.getByKey(cid);
			if (adcourses == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "此条数据已经不存在 失败!");
			} else {
				if (ProductEntity.countByCoursesid(cid) > 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"此条数据已经被学习中心添加到了课程不能删除！");
				} else {
					if (AdcoursesEntity.delete(adcourses) > 0) {
						result = Utls
								.tipMap(result, Utls.Status_Success, "成功!");
					} else {
						result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
					}
				}

			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}

		Utls.writeAndClose(response, result);
	}

	/*** 题库管理--课程点评 **/
	@RequestMapping("/questionManage_comment")
	public String questionManage_comment(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		page = page == 0 ? 1 : page;
		int pageSize = MapEx.getInt(map, "pageSize");
		pageSize = pageSize == 0 ? 10 : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		String name = MapEx.getString(map, "name");
		if (name != null && !"".equals(name)) {
			params.put("appraisetext", name);
			modelMap.addAttribute("name", name);
		}
		params.put("status", 0);
		PageAppraise pageWrap = new PageAppraise();
		PageEnt<Appraise> pageEnt = pageWrap.getPage(params, page, pageSize);
		modelMap.addAttribute("pageEnt", pageEnt);
		return "admin/questionManage_comment";
	}

	/*** 题库管理--课程列表 **/
	@RequestMapping("/questionManage_list")
	public String questionManage_list(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		PageProduct pages = new PageProduct();
		Map map = Svc.getMapAllParams(request);
		int page = MapEx.getInt(map, "inp_fm_page");
		int pageSize = MapEx.getInt(map, "pageSize");
		boolean isPage = map.containsKey("inp_fm_page");
		if (!isPage) {
			params4Search.clear();

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

		// modelMap.addAttribute("params4Search", params4Search);

		page = page == 0 ? 1 : page;
		pageSize = pageSize == 0 ? 10 : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status = ", 0);

		if (MapEx.getInt(params4Search, "departid") > 0) {
			List<Integer> idsCourses = Utls.getCourseIds(params4Search);
			if (!ListEx.isEmpty(idsCourses)) {
				int lens = idsCourses.size();
				PStr pStr = PStr.b(" IN (");
				for (int i = 0; i < lens; i++) {
					pStr.a(idsCourses.get(i));
					if (i < lens - 1) {
						pStr.a(",");
					}
				}
				params.put("coursesid", pStr.e(")"));
			}
		}

		// params.put("status = ", 0);

		PageEnt<Map> pageEnt = pages.getPage(params, page, pageSize);

		pages.resetList(pageEnt.getListPages());

		// listPages
		modelMap.addAttribute("pageEnt", pageEnt);

		List<Adqdepartment> adqdepartments = AdqdepartmentEntity.getAll();// 分类
		modelMap.addAttribute("adqdepartments", adqdepartments);

		return "admin/questionManage_list";
	}

	/*** 题库管理--审核题库 **/
	@RequestMapping("/statusProduct")
	public void statusProduct(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int pid = MapEx.getInt(map, "pid");
			int examineStatus = MapEx.getInt(map, "examineStatus");
			String examineDes = MapEx.getString(map, "examineDes");
			Product product = ProductEntity.getByKey(pid);
			product.setExamineStatus(examineStatus);
			product.setExamineDes(examineDes);
			ProductEntity.update(product);
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 题库管理--删除题库 **/
	@RequestMapping("/deleteProduct")
	public void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int pid = MapEx.getInt(map, "pid");
			Product product = ProductEntity.getByKey(pid);
			product.setStatus(1);
			ProductEntity.update(product);
			result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 题库管理--推荐产品 **/
	@RequestMapping("/recommendProduct")
	public void recommendProduct(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);
		int pid = MapEx.getInt(map, "productid");
		Product product = ProductEntity.getByKey(pid);
		if (product == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败！该课程为空,productid=" + pid);
			Utls.writeAndClose(response, result);
			return;
		}

		if (product.getExamineStatus() != 3) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败！该课程为通过审核,productid=" + pid);
			Utls.writeAndClose(response, result);
			return;
		}

		product.setIsRecommend(true);
		product.setLastTime4Recommend(DateEx.nowDate());
		product.update();
		result = Utls.tipMap(result, Utls.Status_Success, "成功!");
		Utls.writeAndClose(response, result);
	}
}
