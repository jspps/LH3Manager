package com.admin.logic.controll.center.itembackmanager;

import java.util.ArrayList;
import java.util.Collections;
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
import com.admin.db.bean.Optquestion;
import com.admin.db.bean.Product;
import com.admin.db.entity.ExamEntity;
import com.admin.db.entity.ExamcatalogEntity;
import com.admin.db.entity.OptquestionEntity;
import com.admin.db.entity.ProductEntity;
import com.admin.logic.Utls;
import com.admin.logic.controll.center.CenterController;
import com.admin.logic.model.ComparatorExamcatalog;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;

/**
 * 学习中心--添加试题(4)
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doCenter")
public class IBM4AddController {

	// 是不是案例分析的根目录
	static boolean isAnalysisCatalog(Examcatalog catalog) {
		return (catalog.getCatalogType() == 7 && catalog.getGid() == 0);
	}

	// 是不是案例分析的子目录
	static boolean isAnalysisChildCatalog(Examcatalog catalog) {
		return (catalog.getCatalogType() == 7 && catalog.getGid() != 0);
	}

	// 目录 + list question
	static Map ToMapCatalogAndListQuestion(Examcatalog catalog) {
		Map map = catalog.toBasicMap();

		String elps = StrEx.ellipsis(catalog.getTitle(), 10);
		int index = elps.indexOf("<img");
		if (index != -1) {
			elps = elps.substring(0, index);
			elps += "...";
		}

		index = elps.indexOf("<p");
		if (index != -1) {
			elps = elps.substring(0, index);
			elps += "...";
		}
		map.put("titleEllipsis", elps);

		boolean isAnalysisCatalog = isAnalysisCatalog(catalog);
		if (!isAnalysisCatalog) {
			List<Optquestion> listChild = OptquestionEntity
					.getByExamcatalogid(catalog.getId());
			int lens = 0;
			List<Map> listChildMap = null;
			if (!ListEx.isEmpty(listChild)) {
				lens = listChild.size();
				listChildMap = new ArrayList<Map>();
			}

			Map tmp = null;
			for (int i = 0; i < lens; i++) {
				tmp = listChild.get(i).toBasicMap();
				tmp.put("isOldContent",
						listChild.get(i).getContent().indexOf("<p") == -1);
				listChildMap.add(tmp);
			}
			
			if (listChildMap != null)
				map.put("listChild", listChildMap);
		}
		return map;
	}

	/**
	 * 取得试卷详情(目录+试题)
	 * 
	 * @param examid
	 * @return
	 */
	static public List<Map> getExamDetailsByExamid(int examid) {
		List<Examcatalog> examcatalogs = ExamcatalogEntity.getByExamid(examid);

		List<Map> lmResult = new ArrayList<Map>();
		if (!ListEx.isEmpty(examcatalogs)) {
			int lens = 0;

			// 一级目录
			List<Examcatalog> tmpList = new ArrayList<Examcatalog>();
			lens = examcatalogs.size();
			for (int i = 0; i < lens; i++) {
				Examcatalog item = examcatalogs.get(i);
				if (item.getStatus() != 0) {
					continue;
				}

				if (isAnalysisChildCatalog(item)) {
					continue;
				}
				tmpList.add(item);
			}
			Collections.sort(tmpList, new ComparatorExamcatalog());

			lens = tmpList.size();
			List<Integer> tmp2List = new ArrayList<Integer>();
			for (int i = 0; i < lens; i++) {
				Examcatalog item = tmpList.get(i);
				if (isAnalysisCatalog(item)) {
					tmp2List.add(item.getId());
				}

				Map map = ToMapCatalogAndListQuestion(item);
				lmResult.add(map);
				examcatalogs.remove(item);
			}

			// 二级目录
			lens = tmp2List.size();
			int id = -1;
			int id2 = -1;
			int index = -1;
			Map map = null;
			int parentid = -1;
			int index2 = -1;

			A: for (int i = 0; i < lens; i++) {
				parentid = -1;
				index = -1;

				id = tmp2List.get(i);
				B: for (int j = 0; j < lmResult.size(); j++) {
					map = lmResult.get(j);
					id2 = MapEx.getInt(map, "id");
					if (id2 == id) {
						parentid = id2;
						index = j;
						break B;
					}
				}

				if (parentid == -1) {
					continue A;
				}

				index2 = 0;
				C: for (Examcatalog ec : examcatalogs) {
					if (ec.getStatus() != 0) {
						continue C;
					}

					if (ec.getParentid() == parentid) {
						index2++;
						map = ToMapCatalogAndListQuestion(ec);
						lmResult.add(index + index2, map);
					}
				}
			}
		}
		return lmResult;
	}

	/*** 学习中心管理后台_录入试题_编辑试卷 **/
	@RequestMapping("/editPapers")
	public String editPapers(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		Exam exam = ExamEntity.getByKey(examid);
		if (exam == null) {
			return "redirect:basicInformation";
		}

		Product product = ProductEntity.getByKey(exam.getProductid());

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("exam", exam);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/editPapers";
	}

	/*** 学习中心管理后台_录入试题_单项选择 1 **/
	@RequestMapping("/singleEntry")
	public String singleEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 1 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));

		return "center/make/singleEntry";
	}

	/*** 学习中心管理后台_录入试题_多项选择题录入 2 **/
	@RequestMapping("/doubleEntry")
	public String doubleEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 2 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/doubleEntry";
	}

	/*** 学习中心管理后台_录入试题_判断题录入 3 **/
	@RequestMapping("/judgeEntry")
	public String judgeEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 3 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/judgeEntry";
	}

	/*** 学习中心管理后台_录入试题_填空题录入 4 **/
	@RequestMapping("/fillBlankentry")
	public String fillBlankentry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 4 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/fillBlankentry";
	}

	/*** 学习中心管理后台_录入试题_简答题录入 5 **/
	@RequestMapping("/shortAnswerentry")
	public String shortAnswerentry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 5 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));

		modelMap.addAttribute("examcatalog",
				ExamcatalogEntity.getByKey(MapEx.getInt(map, "examcatalogid")));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/shortAnswerentry";
	}

	/*** 学习中心管理后台_录入试题_论述题录入 6 **/
	@RequestMapping("/discussEntry")
	public String discussEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid"); // 分类
		int type = MapEx.getInt(map, "type"); // 分类
		int optquestion = MapEx.getInt(map, "optquestion"); // 考题id
		if (optquestion != 0) {
			Optquestion en = OptquestionEntity.getByKey(optquestion);
			if (en != null)
				type = en.getType();
			modelMap.addAttribute("optq", en);
		}

		type = type <= 0 ? 6 : type;

		modelMap.addAttribute("curCursorType", 4);
		modelMap.addAttribute("type", type);
		modelMap.addAttribute("examid", examid);
		modelMap.addAttribute("examcatalogs", getExamDetailsByExamid(examid));

		modelMap.addAttribute("examcatalog",
				ExamcatalogEntity.getByKey(MapEx.getInt(map, "examcatalogid")));
		modelMap.addAttribute("examcatalogid",
				MapEx.getInt(map, "examcatalogid"));
		return "center/make/discussEntry";
	}

	/*** 添加和修改考题 **/
	@RequestMapping("/addoptquestion")
	public void addoptquestion(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map result = new HashMap();
		Map map = Svc.getMapAllParams(request);

		int examid = MapEx.getInt(map, "examid"); // '试卷ID',
		int type = MapEx.getInt(map, "type"); // type[1单,2多,3判断,4填空,5简答,6论述,7案例分析]
		int gid = MapEx.getInt(map, "gid");// type=7时gid[1单,2多,3判断,4填空,5简答]
		// String title = MapEx.getString(map, "title");// ' title 标题',
		String content = MapEx.getString(map, "content");// ' text_a 考题内容'
		String right = MapEx.getString(map, "right");// '正确答案',
		String imgPic = MapEx.getString(map, "imgPic");// '图片补充',
		String analyse = MapEx.getString(map, "analyse");// '分析',
		String voiceurl = MapEx.getString(map, "voiceurl");// '音频地址',
		String videourl = MapEx.getString(map, "videourl");// '视频地址',
		String position = MapEx.getString(map, "position");// '教材位置'

		int examcatalogid = MapEx.getInt(map, "examcatalogid");// '
		int answernum = MapEx.getInt(map, "answernum");// '
		int optquestionId = MapEx.getInt(map, "optquestion"); // '考题ID',

		if (examid <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!试卷为空，不能录入题！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (optquestionId == 0) {
			Examcatalog examcatalog = ExamcatalogEntity.getByKey(examcatalogid);
			int num = 0;
			if (examcatalog != null) {
				num = examcatalog.getNum();
			}

			int curNum = OptquestionEntity
					.getCountByCatalog4Exam(examcatalogid);
			if (curNum >= num) {
				result = Utls.tipMap(result, Utls.Status_Erro,
						"失败! 超过了您设置的题目数量");
			} else {
				Optquestion optque = Optquestion
						.newOptquestion(0, examid, type, content.trim(),
								right.trim(), analyse.trim(), voiceurl.trim(),
								videourl.trim(), position.trim(), 0,
								DateEx.nowDate(), examcatalogid, answernum,
								gid, imgPic);
				optque = OptquestionEntity.insert(optque);
				if (optque != null && optque.getOptid() != 0) {
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				} else {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
				}
			}
		} else {
			Optquestion optque = OptquestionEntity.getByKey(optquestionId);
			if (optque != null && optque.getOptid() != 0) {
				optque.setExamid(examid);
				optque.setType(type);
				optque.setContent(content);
				optque.setRight_2(right);
				optque.setAnalyse(analyse);
				optque.setPosition(position);
				optque.setAnswernum(answernum);
				optque.setImgPic(imgPic);
				OptquestionEntity.update(optque);
				if (optque != null && optque.getOptid() != 0) {
					result = Utls.tipMap(result, Utls.Status_Success, "成功!");
				} else {
					result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
				}
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
			}
		}

		Utls.writeAndClose(response, result);
	}

	/*** 删除考题 **/
	@RequestMapping("/deloptquestion")
	public void deloptquestion(HttpServletRequest request,
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
			int optqid = MapEx.getInt(map, "optqid"); // 考题id
			boolean isDel = true;

			// Optquestion enOpt = OptquestionEntity.getByKey(optqid);

			// if (enOpt != null) {
			// int examid = enOpt.getExamid();
			// Exam enExam = ExamEntity.getByKey(examid);
			// if (enExam != null) {
			// Product product = ProductEntity.getByKey(enExam
			// .getProductid());
			// if (product != null && product.getExamineStatus() == 3) {
			// result = Utls.tipMap(result, Utls.Status_Erro,
			// "失败,通过审核,不能删除!");
			// isDel = false;
			// }
			// }
			// }

			if (isDel) {
				OptquestionEntity.delete(optqid);
				result = Utls.tipMap(result, Utls.Status_Success, "成功!");
			}
		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "失败!");
		}
		Utls.writeAndClose(response, result);
	}
}
