package com.admin.logic.controll.center.itembackmanager;

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
import com.admin.db.bean.Exam;
import com.admin.db.bean.Examcatalog;
import com.admin.db.bean.Learnhub;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.ExamcatalogEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;

/**
 * 学习中心--题型设置[试卷的目录](3)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doCenter")
public class IBM4ExamCatalogController {
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10;

	/*** 学习中心管理后台_题型设置(procute2examtype) **/
	@RequestMapping("/go2ExamCatalog")
	public String go2ExamCatalog(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid");
		Exam en = ExamEntity.getByKey(examid);
		if (en == null) {
			return "center/userlogin";
		}

		modelMap.addAttribute("exam", en);
		modelMap.addAttribute("curCursorType", 3);
		
		getExamCatalogs(en, modelMap);
		
		// examCatalogAll
		return "center/product/examCatalogAll";
	}

	public String go2ExamCatalog_back(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid");
		Exam en = ExamEntity.getByKey(examid);
		if (en == null) {
			return "center/userlogin";
		}

		modelMap.addAttribute("exam", en);
		modelMap.addAttribute("curCursorType", 3);
		return "center/product/examCatalog";
	}

	/*** 修改试卷 **/
	@RequestMapping("/changeExam")
	public void changeExam(HttpServletRequest request,
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
				result = Utls.tipMap(result, Utls.Status_Erro, "失败:examid="
						+ examid + ",不存在!");
			} else {
				String name = MapEx.getString(map, "name");
				String desc = MapEx.getString(map, "desc");
				int examtime = MapEx.getInt(map, "examtime");
				int score = MapEx.getInt(map, "score");
				exam.setName(name);
				exam.setDescstr(desc);
				exam.setScore(score);
				exam.setExamtime(examtime);
				exam.update();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 添加试卷目录 **/
	@RequestMapping("/newExamCatalog")
	public void newExamCatalog(HttpServletRequest request,
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
				result = Utls.tipMap(result, Utls.Status_Erro, "失败:examid="
						+ examid + ",不存在!");
				Utls.writeAndClose(response, result);
				return;
			}

			String serial = MapEx.getString(map, "serial");
			String bigtypes = MapEx.getString(map, "bigtypes");
			boolean isSubjective = MapEx.getBoolean(map, "isSubjective");
			int num = MapEx.getInt(map, "num");
			int everyScore = MapEx.getInt(map, "everyScore");
			String title = MapEx.getString(map, "title");
			int catalogType = getExamCatalogIntByBigtypes(bigtypes);
			// catalogType为7时，小分类[1单2多3判4填空5简答6论述]
			int gid = MapEx.getInt(map, "gid");

			if (!(catalogType == 7 && gid == 0)) {
				if (num <= 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败，题目总数应该为非零整数!");
					Utls.writeAndClose(response, result);
					return;
				}
				if (everyScore <= 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败，每题分数应该为非零整数!");
					Utls.writeAndClose(response, result);
					return;
				}
			}

			if (catalogType == 0) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败，大题序号或者大题类型没选择!");
				Utls.writeAndClose(response, result);
				return;
			}

			// catalogType为7时，他所属的父级的目录的id
			int parentid = MapEx.getInt(map, "parentid");
			if (parentid > 0) {
				if (gid <= 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败，案例分析子类别没选择题型!");
					Utls.writeAndClose(response, result);
					return;
				}
			}

			if (StrEx.isEmptyTrim(title)) {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败，标题为空!");
			} else {
				Examcatalog ecatalog = Examcatalog.newExamcatalog(0, examid,
						serial, catalogType, gid, bigtypes, isSubjective, num,
						everyScore, title, 0, DateEx.nowDate(), parentid);
				ecatalog.insert();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	// 取得试卷目录结构的map对象,并取得该试卷目录下面的案例分析对象
	private List<Map> getLM4ExamCatalog(List<Examcatalog> list,
			Ref<List<Integer>> ref) {
		List<Map> result = new ArrayList<Map>();
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();

			for (int i = 0; i < lens; i++) {
				Examcatalog en = list.get(i);
				if (en == null)
					continue;
				Map map = en.toBasicMap();
				int numQues = OptquestionEntity.getCountByCatalog4Exam(en
						.getId());
				map.put("numQues", numQues);
				result.add(map);

				if (en.getCatalogType() == 7 && en.getParentid() == 0) {
					if (ref.val == null) {
						ref.val = new ArrayList<Integer>();
					}
					ref.val.add(i);
				}
			}
		}
		return result;
	}

	/*** 取得试卷的目录 **/
	@RequestMapping("/examCatalogs")
	public String examCatalogs(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid");
		Exam en = ExamEntity.getByKey(examid);
		if (en == null) {
			return "center/userlogin";
		}

		params.clear();
		params.put("examid", "=" + examid);
		params.put("status", "!= 1");
		params.put("parentid", "= 0");

		List<Map> result;
		try {
			List<Examcatalog> list = ExamcatalogEntity.getListBy(params, 0, 20);
			// 案例分析题的Index值
			Ref<List<Integer>> ref = new Ref<List<Integer>>(null);
			result = getLM4ExamCatalog(list, ref);

			// 取得案例分析下面的目录结构
			if (ref.val != null) {
				for (Integer index : ref.val) {
					Map mapCase = result.get(index);
					int caseParentid = MapEx.getInt(mapCase, "id");
					List<Examcatalog> lm4Case = ExamcatalogEntity
							.getListByParentid(caseParentid);
					if (!ListEx.isEmpty(lm4Case)) {
						List<Map> listChild = getLM4ExamCatalog(lm4Case, null);
						mapCase.put("listChild", listChild);
					}
				}
			}
		} catch (Exception e) {
			result = new ArrayList<Map>();
		}

		modelMap.addAttribute("exam", en);
		modelMap.addAttribute("list", result);
		return "center/product/catalog4Exam";
	}

	// 取得试卷目录结构
	protected void getExamCatalogs(Exam en, ModelMap modelMap) {

		params.clear();
		params.put("examid", "=" + en.getId());
		params.put("status", "!= 1");
		params.put("parentid", "= 0");

		List<Map> result;
		try {
			List<Examcatalog> list = ExamcatalogEntity.getListBy(params, 0, 20);
			// 案例分析题的Index值
			Ref<List<Integer>> ref = new Ref<List<Integer>>(null);
			result = getLM4ExamCatalog(list, ref);

			// 取得案例分析下面的目录结构
			if (ref.val != null) {
				for (Integer index : ref.val) {
					Map mapCase = result.get(index);
					int caseParentid = MapEx.getInt(mapCase, "id");
					List<Examcatalog> lm4Case = ExamcatalogEntity
							.getListByParentid(caseParentid);
					if (!ListEx.isEmpty(lm4Case)) {
						List<Map> listChild = getLM4ExamCatalog(lm4Case, null);
						mapCase.put("listChild", listChild);
					}
				}
			}
		} catch (Exception e) {
			result = new ArrayList<Map>();
		}
		modelMap.addAttribute("list", result);
	}

	/*** 删除试卷目录 **/
	@RequestMapping("/delExamCatalog")
	public void delExamCatalog(HttpServletRequest request,
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
			int examcatalogid = MapEx.getInt(map, "examcatalogid");
			Examcatalog examCatalog = null;
			if (examcatalogid != 0) {
				examCatalog = ExamcatalogEntity.getByKey(examcatalogid);
			}

			if (examCatalog == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "删除试卷目录失败:id="
						+ examcatalogid + ",不存在!");
			} else {
				boolean isDel = true;

				if (isDel) {
					ExamcatalogEntity
							.delExamCatalogAndOptQuestion(examcatalogid);
					result = Utls.tipMap(result, Utls.Status_Success, "删除成功!");
				}
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "删除失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 修改试卷目录 **/
	@RequestMapping("/modifyExamCatalog")
	public void modifyExamCatalog(HttpServletRequest request,
			HttpServletResponse response) {
		Map result = new HashMap();
		try {
			Map map = Svc.getMapAllParams(request);
			int examcatalogid = MapEx.getInt(map, "examcatalogid");
			Examcatalog exam = null;
			if (examcatalogid != 0) {
				exam = ExamcatalogEntity.getByKey(examcatalogid);
			}
			if (exam == null) {
				result = Utls.tipMap(result, Utls.Status_Erro, "修改试卷目录失败:id="
						+ examcatalogid + ",不存在!");
			} else {
				String serial = MapEx.getString(map, "serial");
				int num = MapEx.getInt(map, "num");
				int everyScore = MapEx.getInt(map, "everyScore");
				String title = MapEx.getString(map, "title");
				if (StrEx.isEmpty(serial) || "0".equals(serial)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "大题序号不能为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				if (num <= 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败，题目总数应该为非零整数!");
					Utls.writeAndClose(response, result);
					return;
				}
				if (everyScore <= 0) {
					result = Utls.tipMap(result, Utls.Status_Erro,
							"失败，每题分数应该为非零整数!");
					Utls.writeAndClose(response, result);
					return;
				}

				if (StrEx.isEmpty(title)) {
					result = Utls.tipMap(result, Utls.Status_Erro, "标题不能为空!");
					Utls.writeAndClose(response, result);
					return;
				}

				exam.setSerial(serial);
				exam.setNum(num);
				exam.setEveryScore(everyScore);
				exam.setTitle(title);
				int subject = MapEx.getInt(map, "isSubjective");
				if (subject == 0 || subject == 1) {
					boolean isSubjective = MapEx
							.getBoolean(map, "isSubjective");
					exam.setIsSubjective(isSubjective);
				}
				exam.update();
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败，出现异常!");
		}
		Utls.writeAndClose(response, result);
	}

	static int getExamCatalogIntByBigtypes(final String bigtypes) {
		if (bigtypes == null)
			return 0;
		switch (bigtypes) {
		case "单选题":
			return 1;
		case "多选题":
			return 2;
		case "判断题":
			return 3;
		case "填空题":
			return 4;
		case "简答题":
			return 5;
		case "论述题":
			return 6;
		case "案例分析题":
			return 7;
		default:
			break;
		}
		return 0;
	}
}
