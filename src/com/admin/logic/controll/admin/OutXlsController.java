package com.admin.logic.controll.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.db.bean.Adcourses;
import com.admin.db.bean.Agent;
import com.admin.db.bean.Learnhub;
import com.admin.logic.model.ExportXlsVo;
import com.admin.logic.model.PageAdcourses;
import com.admin.logic.model.PageAgent;
import com.admin.logic.model.PageErrorfeedback;
import com.admin.logic.model.PageLearnhub;
import com.admin.logic.model.PageOrder;
import com.admin.util.DateUtil;
import com.admin.util.PoiHelper;
import com.bowlong.util.page.PageEnt;

/**
 * 管理后台-代理商管理
 * 
 * @author Canyon
 * 
 */
@Controller
@RequestMapping("/doAdmin")
public class OutXlsController {
	Map<String, Object> params = new HashMap<String, Object>();
	int page = 1;
	int pageSize = 10000;
	
	/***代理管理导出xls***/
	@RequestMapping("/adminAgentManageOutXls")
	public void adminAgentManageOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="代理管理";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			titles.addAll(Arrays.asList((new String[]{"提交时间","代理人员","代理编号","所在地","手机号码","成交量","实在佣金","代理截止时间设置","代理状态"})));
			params.clear();
			params.put("status", 0);
			PageAgent pageAgent = new PageAgent();
			PageEnt<Agent> pageEnt = pageAgent.getPage(params, page, pageSize);
			if(pageEnt!=null){
				/**<td>${curAgent.agtime}</td>
				<td>${curAgent.uname}</td>
			    <td>${curAgent.code}</td>  
				<td>${curAgent.seat }</td>
				 <td>${curAgent.accountFkAccountid.phone}</td>  
				<td>${curAgent.volume }</td>
				<td>${curAgent.curmoney }</td>
				<td><input style="width: 200px;" id = "setTime_${curAgent.agid}"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
				  class="Wdate" value="${curAgent.endtime}"></td>
				<td>
				 <c:if test="${curAgent.examineStatus == '1'}">审核中</c:if>
				 <c:if test="${curAgent.examineStatus == '2'}">审核不通过</c:if> 
				 <c:if test="${curAgent.examineStatus == '3'}">审核通过</c:if>
				 </td>**/
				List<Agent> ent =  pageEnt.getListPages();
				if(ent!=null && ent.size()>0){
					for (Agent en : ent) {
						String examineStatus= "审核中";
						if(en.getExamineStatus()==2){  examineStatus= "审核不通过";}
						if(en.getExamineStatus()==3){  examineStatus= "审核通过";}
						rows.add(Arrays.asList(new String[]{
								DateUtil.formatLong(en.getCreatetime()),
								en.getUname(),
								en.getCode(),
								en.getSeat(),
								en.getAccountFkAccountid().getPhone(),
								en.getVolume()+"",
								en.getCurmoney()+"",
								DateUtil.formatLong(en.getEndtime()),
								examineStatus
						}));
					}
				}
			}
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
	
	
	
	/***学习中心管理导出xls***/
	@RequestMapping("/learnCenterManageOutXls")
	public void learnCenterManageOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="学习中心管理";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//入住时间 	学习中心 	联系人 	类型 	QQ号码 	所在地 	联系电话 	成交量 	成交金额 	题库提成 	质量提成 	错误率 	设置截止时间 	自主管理权限 	学习中心状态
			titles.addAll(Arrays.asList((new String[]{"入住时间","学习中心","联系人","类型","QQ号码","所在地","联系电话","成交量","成交金额","题库提成","质量提成","错误率","设置截止时间","自主管理权限","学习中心状态"})));
			params.clear();
			params.put("status", 0);
			PageLearnhub pageLearnhub = new PageLearnhub();
			PageEnt<Learnhub> pageEnt = pageLearnhub.getPage(params, page, pageSize);
			if(pageEnt!=null){
				List<Learnhub> ent =  pageEnt.getListPages();
				/**<td><p:date2 parttern="yyyy-MM-dd" value="${learnhub.createtime}"></p:date2></td>
				<td>${learnhub.name}</td>
				<td>${learnhub.uname}</td>
		 		<td>
			 		 <c:if test="${learnhub.type=='1'}">
			 		 个人
			 		 </c:if>
			 		 <c:if test="${learnhub.type=='2'}">
			 		   机构
			 		 </c:if>
		 	    </td>
		 		<td>${learnhub.qq}</td>
		 		<td>${learnhub.seat}</td> 
		 		<td>${learnhub.accountFkAccountid.phone}</td>
		 		<td>${learnhub.volume}</td>
		 		<td>${learnhub.allmoney}</td>
		 		<td></td>
		 		<td></td>
		 		<td></td>
		 		<td><p:date  parttern="yyyy-MM-dd" value="${learnhub.validitytime}"></p:date></td>
		 		<td>
		 			<input type="radio" name="isselfadmin_${learnhub.lhid}" onclick="selfadmin('${learnhub.lhid}','false');"  <c:if test="${learnhub.isselfadmin=='false'}"> checked="true"<s </c:if>>关
		 			 &nbsp;
		 			<input type="radio"  name="isselfadmin_${learnhub.lhid}" onclick="selfadmin('${learnhub.lhid}','true');" <c:if test="${learnhub.isselfadmin=='true'}"> checked="true" </c:if>>开 
		 		</td>
		 		<td>
			     <c:if test="${learnhub.examineStatus == '1'}">审核中</c:if>
				 <c:if test="${learnhub.examineStatus == '2'}">审核不通过</c:if> 
				 <c:if test="${learnhub.examineStatus == '3'}">审核通过</c:if>
		 		</td>**/
				if(ent!=null && ent.size()>0){
					for (Learnhub en : ent) {
						String examineStatus= "审核中";
						if(en.getExamineStatus()==2){  examineStatus= "审核不通过";}
						if(en.getExamineStatus()==3){  examineStatus= "审核通过";}
						int t = en.getType();
						 String type ="机构";
						if(t==1){
							type="个人";
						}
						String isselfadmin="关";
						if(en.getIsselfadmin()){
							 isselfadmin="开";
						}
						rows.add(Arrays.asList(new String[]{
 								DateUtil.formatLong(en.getCreatetime()),
 								en.getName(),
 								en.getUname(),
 								type,
 								en.getQq(),
 								en.getSeat(),
 								en.getAccountFkAccountid().getPhone(),
 								en.getVolume()+"",
 								en.getMoneyAll()+"",
 								//en.get
 								//en.get
 								//en.get
 								"","","","",
 								//DateUtil.formatLong(en.getValiditytime());
 								isselfadmin,
 								examineStatus
						}));
					}
				}
			}
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
 
	
	/***错误反馈导出xls***/
	@RequestMapping("/errorBackOutXls")
	public void errorBackOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="错误反馈";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//时间 	提交人 	课程名称 	所属信息中心 	主要问题
			titles.addAll(Arrays.asList((new String[]{"时间","提交人","课程名称","所属信息中心","主要问题"})));
			params.clear();
			params.put("status", 0);
			PageErrorfeedback pageErrorfeedback = new PageErrorfeedback();
			PageEnt<Map> pageEnt = pageErrorfeedback.getPage(params, page, pageSize);
			if(pageEnt!=null){
				List<Map> ent =  pageEnt.getListPages();
				/**
				 * 
				 * <td>${ent.creationtime}</td>
		                    	<td>${ent.customer.name}</td>
		                    	<td>${ent.exam.name}</td>
		                    	<td>${ent.learnhubFkLhubid.name}</td>
		                    	<td>${ent.description}</td>
		                    	 
				 * **/
				if(ent!=null && ent.size()>0){
					// for (Errorfeedback en : ent) {
					// rows.add(Arrays.asList(new String[]{
					// DateUtil.formatLong(en.getCreatetime()),
					// en.getCustomerFkCustomerid().getName(),
					// en.getExamFkExamid().getName(),
					// en.getLearnhubFkLhubid().getName(),
					// en.getDescription(),
					// }));
					// }
				}
			}
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
	
	
	/***课程设置导出xls***/
	@RequestMapping("/questionManage_setOutXls")
	public void questionManage_setOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="课程设置";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//类别 	专业 	层次 	科目 	考试范围 	代理佣金 	题库提成 	质量押金 	错误率 	代理奖金 	1考币价值 	程序员 	美工
			titles.addAll(Arrays.asList((new String[]{"类别","专业","层次","科目","考试范围","代理佣金","题库提成","质量押金","错误率","代理奖金","1考币价值","程序员","美工"})));
			params.clear();
			params.put("status = ", 0);
			PageAdcourses pages = new PageAdcourses();
			PageEnt<Adcourses> pageEnt = pages.getPage(params, page, pageSize);
			if(pageEnt!=null){
				/**
				<td>${ent.adqdepartmentFkDepartid.name} </td>
	                    	<td>${ent.adqmajorFkMajorid.name}</td>
	                    	<td>${ent.adqlevelFkLevelid.name}</td>
	                    	<td>${ent.adqsubjectFkSubid.name}</td>
	                    	<td>${ent.adqareaFkAreaid.name}</td>
	                    	<td>${ent.profitAgent}%</td>
	                    	<td>${ent.profitOwner}%</td>
	                    	<td>${ent.deposit}%</td>
	                    	<td>${ent.wrong}%</td>
	                    	<td>${ent.bonus}%</td>
	                    	<td>0.01元 </td>
	                    	<td>${ent.program}元/套餐</td>
	                    	<td>${ent.art}元/套餐</td>
				 * **/
				List<Adcourses> ent =  pageEnt.getListPages();
				if(ent!=null && ent.size()>0){
					for (Adcourses en : ent) {
						rows.add(Arrays.asList(new String[]{
								en.getAdqdepartmentFkDepartid().getName(),
								en.getNmMajor(),
								en.getNmLevel(),
								en.getNmSub(),
								en.getNmArea(),
								en.getProfitAgent()+"%",
								en.getProfitOwner()+"%",
								en.getDeposit()+"%",
								en.getWrong()+"%",
								en.getBonus()+"%",
								"0.01元",
								en.getProgram()+"元/套餐",
								en.getArt()+"元/套餐",
								
						}));
					}
				}
			}
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
	
	
	/***订购管理导出xls***/
	@RequestMapping("/orderManageOutXls")
	public void orderManageOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="订购管理";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//全部成交时间 	学生手机号 	全部类别 	全部专业 	层次 	科目 	套餐类型 	购买类型 	单价 	数量 	代理人员 	学习中心 	成交价 	代理佣金 	题库提成 	质量押金 	代理商奖金 	开发提成 	美工提成 	净收入
			titles.addAll(Arrays.asList((new String[]{"全部成交时间","学生手机号","全部类别","全部专业","层次","科目","套餐类型","购买类型","单价","数量","代理人员","学习中心","成交价","代理佣金","题库提成","质量押金","代理商奖金","开发提成","美工提成","净收入"})));
			params.clear();
			params.put("status", 0);
			PageOrder pageOrder = new PageOrder();
			PageEnt<Map> pageEnt = pageOrder.getPage(params, page, pageSize);
			if(pageEnt!=null){
				List<Map> ents =  pageEnt.getListPages();
				/**
				 
				        <td>${ent.createtime}</td>
                    	<td>${ent.customerFkCustomerid.accountFkAccountid.phone}</td>
                    	<td>职业类</td>
                    	<td>人力资源管理师</td>
                    	<td>一级</td>
                    	<td>理论</td>
                    	<td>VIP绝胜套餐</td>
                    	<td>在线模考</td>
                    	<td>${ent.price}</td>
                    	<td>1</td>
                        <td>${ent.agentFkAgentid.uname}</td>
                        <td>${ent.learnhubFkLhubid.name}</td>
                    	<td>${ent.price}</td>
                    	<td>${ent.mnAgent}</td>
                    	<td>${ent.mnQBank}</td>
                    	<td>${ent.mnDeposit}</td>
                    	<td>${ent.bonusAgent}</td>
                        <td>${ent.mnDevelop}</td>
                        <td>${ent.mnArt}</td>
				 **/
				if(ents!=null && ents.size()>0){
//					for (Orders ent : ents) {
//						rows.add(Arrays.asList(new String[]{
//								DateUtil.formatLong(ent.getCreatetime()),
//     	                    	ent.getCustomerFkCustomerid().getAccountFkAccountid().getPhone(),
//		                    	"职业类",
//		                    	"人力资源管理师",
//		                    	"一级",
//		                    	"理论",
//		                    	"VIP绝胜套餐",
//		                    	"在线模考",
//		                    	ent.getPrice()+"",
//		                    	"1",
//		                    	ent.getPrice()+"",
//		                    	ent.getMnAgent()+"",
//		                    	ent.getMnQBank()+"",
//		                    	ent.getMnDeposit()+"",
//		                    	ent.getBonusAgent()+"",
//		                       ent.getMnDevelop()+"",
//		                       ent.getMnArt()+"",
//						}));
//					}
				}
			}
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
	
	/***体验账户导出xls***/
	@RequestMapping("/companyUsersOutXls")
	public void companyUsersOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="体验账户";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//学习中心 	课程类别 	专业名称 	层次 	科目 	考试范围 	套餐 	姓名 	手机电话 	登录密码 	学币数 	有效期 	备注信息
			titles.addAll(Arrays.asList((new String[]{"学习中心","课程类别","专业名称","层次","科目","考试范围","套餐","姓名","手机电话","登录密码","学币数","有效期","备注信息"})));
			params.clear();
			
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
	
	
	/***批量设置导出xls***/
	@RequestMapping("/companyUsers_setListOutXls")
	public void companyUsers_setListOutXls(HttpServletResponse response){
		response.reset();		
		response.setContentType("application/x-download");		
		response.addHeader("Content-Disposition","attachment;filename=export_"+DateUtil.fromatShortNoSign(new Date())+".xls");		
		try {
			OutputStream out = response.getOutputStream();
			ExportXlsVo vo=new ExportXlsVo();
			String sheetName  ="批量设置";
			List<String> titles=new ArrayList<String>();
			List<List<String>> rows=new ArrayList<List<String>>();
			//学习中心 	课程类别 	专业名称 	层次 	科目 	考试范围 	套餐 	账户数 	入账金额 	所属集团客户 	联系人 	联系电话 	时间 	
			titles.addAll(Arrays.asList((new String[]{"学习中心","课程类别","专业名称","层次","科目","考试范围","套餐","账户数","入账金额","所属集团客户","联系人","联系电话","时间"})));
			params.clear();
			params.put("status", 0);
			
			vo.setTitles(titles);
			vo.setSheetName(sheetName);
			vo.setRows(rows);
			PoiHelper.createXls(out,vo);
		} catch (IOException e) {
		}
	}
}
