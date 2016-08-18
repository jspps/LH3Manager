package com.admin.logic.controll.center.itembackmanager;

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

import com.admin.content.Svc;
import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Adqdepartment;
import com.admin.db.bean.Boughtkinds;
import com.admin.db.bean.Exam;
import com.admin.db.bean.Examtype;
import com.admin.db.bean.Kind;
import com.admin.db.bean.Kindclass;
import com.admin.db.bean.Learnhub;
import com.admin.db.bean.Product;
import com.admin.db.bean.Product0examtype;
import com.admin.db.entity.BoughtkindsEntity;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.ExamtypeEntity;
import com.admin.db.entity.KindEntity;
import com.admin.db.entity.KindclassEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.db.entity.Product0examtypeEntity;
import com.admin.db.entity.ProductEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;

/**
 * 学习中心--套餐设置(5)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doCenter")
public class IBM4KindController {
	/*** 学习中心管理后台_套餐设置 **/
	@RequestMapping("/makeKind")
	public String makeKind(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		int lhubid = CenterController.getLhubid(session);
		if (lhubid <= 0)
			return "center/userlogin";

		Map map = Svc.getMapAllParams(request);
		int productid = MapEx.getInt(map, "productid");
		int kindclassId = MapEx.getInt(map, "kindclassId");
		if (productid <= 0 || (kindclassId <= 0))
			return "center/userlogin";

		Product product = ProductEntity.getByKey(productid);
		if (product == null)
			return "center/userlogin";

		// kindclass:大套餐分类（vip，精品，超值）
		List<Kindclass> kindclass = KindclassEntity.getAll();
		modelMap.addAttribute("kindclass", kindclass);

		Kind kind = KindEntity.getByKclassidProductidLhubid(kindclassId,
				productid, lhubid);
		modelMap.addAttribute("kind", kind);
		String nameKind = "";
		List<Integer> examids = null;
		List<Integer> examtypes = null;
		if (kind != null) {
			examids = ListEx.toListInt(kind.getExamids());
			examtypes = ListEx.toListInt(kind.getExamtypes());

			Adcourses course = kind.getAdcoursesFkCoursid();
			if (course != null) {
				nameKind = course.getAdqdepartmentFkDepartid().getName()
						+ course.getNmMajor() + course.getNmLevel()
						+ course.getNmSub();
			}
		}

		int coursesid = product.getCoursesid();
		Adcourses couses = product.getAdcoursesFkCoursesid();
		if (couses != null) {
			modelMap.addAttribute("areaname", couses.getNmArea());

			if (StrEx.isEmpty(nameKind)) {
				Adqdepartment depart = couses.getAdqdepartmentFkDepartid();

				nameKind = depart.getName() + couses.getNmMajor()
						+ couses.getNmLevel() + couses.getNmSub();
			}
		}

		modelMap.addAttribute("kindname", nameKind);

		// examtype:试卷类型表（章节练习，历年真题，全真模拟，考前押题，知识要点，ITM辅助）
		List<Product0examtype> catalog4Product = Product0examtypeEntity
				.getByProductid(productid);
		List<Map> lm4ProCatalog = new ArrayList<Map>();
		if (!ListEx.isEmpty(catalog4Product)) {
			int lens = catalog4Product.size();
			for (int i = 0; i < lens; i++) {
				Product0examtype catalog = catalog4Product.get(i);
				int pro0etpid = catalog.getId();
				Examtype etype = ExamtypeEntity.getByKey(catalog
						.getExamtypeid());
				Map mapEn = etype.toBasicMap();

				// 取得题数量
				int num = OptquestionEntity.getCountByCatalog4Procut(pro0etpid);
				mapEn.put("num", num);

				// 取得试卷
				List<Exam> list = ExamEntity.getByPro0etpid(pro0etpid);
				if (!ListEx.isEmpty(list)) {
					List<Map> lm4Exam = new ArrayList<Map>();
					int lens4Exmas = list.size();
					for (int j = 0; j < lens4Exmas; j++) {
						Exam examEn = list.get(j);
						boolean isHas = ListEx.have(examids, examEn.getId());
						Map mapExam = examEn.toBasicMap();
						mapExam.put("isHas", isHas);
						lm4Exam.add(mapExam);
					}
					mapEn.put("exams", lm4Exam);
				}

				lm4ProCatalog.add(mapEn);
			}
		}

		// 知识要点
		Examtype knowlege = ExamtypeEntity.getByKey(5);
		Map kmap = knowlege.toBasicMap();
		kmap.put("isHas", ListEx.have(examtypes, 5));
		lm4ProCatalog.add(kmap);

		// itms辅助
		Examtype itms = ExamtypeEntity.getByKey(6);
		Map itmsmap = itms.toBasicMap();
		itmsmap.put("isHas", ListEx.have(examtypes, 6));
		lm4ProCatalog.add(itmsmap);

		modelMap.addAttribute("curCursorType", 5);
		modelMap.addAttribute("list", lm4ProCatalog);
		modelMap.addAttribute("coursesid", coursesid);
		modelMap.addAttribute("kindclassId", kindclassId);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("productid", productid);
		return "center/kind/makeKind";
	}

	/*** 添加修改大套餐 **/
	@RequestMapping("/modifyKind")
	public void modifyKind(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Learnhub lhub = CenterController.getLhub(session);
		if (lhub == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,Session失效,请重新登录!");
			Utls.writeAndClose(response, result);
			return;
		}

		int lhubid = lhub.getLhid();

		Map map = Svc.getMapAllParams(request);
		int kindmaxId = MapEx.getInt(map, "kindid"); // 大套餐id',
		int productid = MapEx.getInt(map, "productid"); // 产品 id',
		int kclassid = MapEx.getInt(map, "kclassid"); // '套餐分类标识',
		double price = MapEx.getDouble(map, "price");// '标价',
		double discount = MapEx.getDouble(map, "discount");// '优惠后价格',
		int kbi = MapEx.getInt(map, "kbi"); // '考币价格',
		int validity = MapEx.getInt(map, "validity"); // '有效期,天',
		String examtypes = MapEx.getString(map, "examtypes"); // 选择了那些分类
		String examids = MapEx.getString(map, "examids"); // 套餐拥有的试卷ids
		String descr = MapEx.getString(map, "descr"); // 产品描述
		String imgurl = MapEx.getString(map, "imgurl"); // 产品图片
		String protection = MapEx.getString(map, "txt4cust"); // 消费者保障

		if (price < 5) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,原价不能低于5元!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (discount < 5) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,优惠价不能低于5元!");
			Utls.writeAndClose(response, result);
			return;
		}

		if (discount > price) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,优惠价不能高于原价!");
			Utls.writeAndClose(response, result);
			return;
		}

		Kind kind = KindEntity.getByKey(kindmaxId);
		if (kind != null) {
			if (kind.getLhubid() != lhubid) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败,该套餐不属于您，不能修改!");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		// 查询产品
		Product product = ProductEntity.getByKey(productid);
		if (product == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败,产品为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		product.setDescr(descr);
		product.setImgurl(imgurl);
		product.setProtection(protection);
		product.update();

		int numExercises = MapEx.getInt(map, "exercisesnum");// 数量(1, '章节练习')
		int numZhenti = MapEx.getInt(map, "ahentinum");// 数量(2, '历年真题')
		int numSimulation = MapEx.getInt(map, "simulationnum");// 数量(3, '全真模拟')
		int numVast = MapEx.getInt(map, "vastnum");// 数量(4, '考前押题')
		// (5, '知识要点'),
		// (6, 'ITM辅助');

		Kindclass kclass = KindclassEntity.getByKey(kclassid);
		if (kclass == null) {
			result = Utls.tipMap(result, Utls.Status_Erro,
					"失败,套餐分类kindclass为空!");
			Utls.writeAndClose(response, result);
			return;
		}

		String nmKClass = kclass.getName();
		Adcourses adcourses = product.getAdcoursesFkCoursesid();
		int coursid = adcourses.getCid();
		String nmProduct = product.getName();
		String nmLhub = lhub.getName();

		boolean isHasITMS = false;
		if (null != examtypes && examtypes.indexOf("6") != -1) {
			isHasITMS = true;
		}

		// 添加
		if (kind == null) {
			kind = Kind.newKind(0, kclassid, nmKClass, coursid, productid,
					nmProduct, lhubid, nmLhub, price, discount, kbi, validity,
					imgurl, 0, 0, 0, examtypes, 0, numExercises, numZhenti,
					numSimulation, numVast, new Date(), isHasITMS, examids);

			kind = kind.insert();
			if (kind != null && kind.getId() != 0) {
				result = Utls.tipMap(result, Utls.Status_Success, "成功!", null);
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
			}

		} else {
			kind.setPrice(price);
			kind.setDiscount(discount);
			kind.setKbi(kbi);
			kind.setValidity(validity);
			kind.setExamtypes(examtypes);
			kind.setExamids(examids);
			kind.setNumExercises(numExercises);
			kind.setNumSimulation(numSimulation);
			kind.setNumVast(numVast);
			kind.setNumZhenti(numZhenti);

			kind.setCoursid(coursid);
			kind.setProductid(productid);
			kind.setLhubid(lhubid);

			kind.setNmProduct(nmProduct);
			kind.setNmLhub(nmLhub);

			kind.setIsHasITMS(isHasITMS);
			kind = kind.update();
			result = Utls.tipMap(result, Utls.Status_Success, "成功!", null);
		}

		// 修改购买套餐的信息
		List<Boughtkinds> list = BoughtkindsEntity.getByKindid(kind.getId());
		for (Boughtkinds item : list) {
			item.setName(nmProduct);
			item.setPrice(price);
			item.setKbi(kbi);
			item.update();
		}

		Utls.writeAndClose(response, result);
	}
}
