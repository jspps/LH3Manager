<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印界面</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<style type="text/css" media=print>
		.noprint{display : none }
	</style>
	
	<link rel="stylesheet" type="text/css" href="jsp/css/base_print.css" />

  </head>
  
  <body>
    <div id="noprint_menu" class="noprint">
    	<object classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" id="WebBrowser"></object>
    	<div>  
			<input type="button" value="预览" onclick="pagePreview()"/>  
			<input type="button" value="打印" onclick="pagePrint()"/>  
			<input type="button" value="页面设置" onclick="pageSetup()"/>  
		</div> 
		
		<!-- 打印预览 -->
		<div class="print-box">
			<span class="print-title">
				打印预览
			</span>
			<span class="select-list">
				<span class="mechanism">
					<input type="checkbox" id="mechanism-logo" class="regular-checkbox" name="">
					<label for="mechanism-logo">
						<i>机构LOGO</i>
					</label>
				
				</span>
	
				<span>
					<input type="checkbox" id="contain-answer" class="regular-checkbox" name="">
					<label for="contain-answer">
						<i>包含答案</i>
					</label>
				</span>
				
				
			</span>
	
			<span class="page-info t-center">共4页</span>
	
			<span class="print-button-box t-center">
				<input type="button" class="print-button" value="打印" name="">
			</span>
		</div>
    </div>
	<div>
		<div class="p-container persen-index">
			<div class="logo-box">
				<a href="javascript:void(0)">
					<img src="./images/logo.png">
				</a>
			</div>

			<div class="persen-content">
				<h1 class="test-title t-center">2017年会计基础真题</h1>
				<p class="test-dec t-center">
					<span class="time">考试时间：120分钟</span>
					<span class="mark">总分：100分</span>
				</p>

				<!-- 考生信息 -->
				<div class="examinee-info">
					<span class="name">
						<label>姓名：</label>
						<input type="text" class="examinee-input" name="">
					</span>
					<span class="school">
						<label>学校：</label>
						<input type="text" class="examinee-input" name="">
					</span>
					<span class="class-name">
						<label>班级：</label>
						<input type="text" class="examinee-input" name="">
					</span>
				</div>

				<!-- 试题说明 -->
				<dl class="question-explain">
					<dt>试题说明：</dt>
					<dd>1、看拼音，写词语。</dd>
					<dd>2、将正确的序号填入</dd>
					<dd>3、选择合适的词语填空，把序号写在括号里。</dd>
					<dd>4、按课文内容填空。</dd>
				</dl>

				<!-- 题号分值 -->
				<table class="score">
					<col width="100px" />
					<col width="136px" />
					<col width="136px" />
					<col width="136px" />
					<col width="136px" />
					<thead>
						<tr>
							<th>题号</th>
							<th>一（30分）</th>
							<th>二（20分）</th>
							<th>三（30分）</th>
							<th>四（20分）</th>
							<th>总分（100分）</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>得分</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>

				<!-- 测试题列表	 -->
				<div class="question-box">

					<div class="question-type">
						<h3 class="question-type-title">一、单项选择题（本题 型共20小题，每小题 1分，共20分。多选、错远、不选均不得分）</h3>
						<div class="question-list">
							<h4 class="question-title">
								1、企业工资、资金等现金的支取，只能通过（    ）账户办理。
							</h4>
							<ul class="options">
								<li>A、基本存款</li>
								<li>B、一般存款</li>
								<li>C、临时存款</li>
								<li>D、专用存款</li>
							</ul>

							<div class="answer-box">
								<span class="l">
									<i class="">正确答案：</i>
									<em class="right">【B】</em>
								</span>
								<span>
									<i class="">教材位置：</i>
									<em></em>
								</span>
							</div>

							<div class="result-box">
								<span class="l">
									<i class="">答题结果：</i>
									<em class="error">A 【错误】</em>
								</span>
								<span>
									<i class="">得分：</i>
									<em class="error">【0分】</em>
								</span>
							</div>

							<p class="analysis">
								<em>本题解析：</em>中华人民共和国、中华人民共和国中华人民共和国只从加价中华人民共和国中华人民共和国中华人民共和国只从加价国中华人民共和国中华人民共和国。
							</p>	
						</div>
					</div>

					<div class="question-type">
						<h3 class="question-type-title">二、多项选择题（本题 型共20小题，每小题 2分，共40分。多选、错远、不选均不得分）</h3>
						<div class="question-list">
							<h4 class="question-title">
								1、企业工资、资金等现金的支取，只能通过（    ）账户办理。
							</h4>
							<ul class="options">
								<li>A、基本存款</li>
								<li>B、一般存款</li>
								<li>C、临时存款</li>
								<li>D、专用存款</li>
							</ul>

							<div class="answer-box">
								<span class="l">
									<i class="">正确答案：</i>
									<em class="right">【B】</em>
								</span>
								<span>
									<i class="">教材位置：</i>
									<em></em>
								</span>
							</div>

							<div class="result-box">
								<span class="l">
									<i class="">答题结果：</i>
									<em class="error">A 【错误】</em>
								</span>
								<span>
									<i class="">得分：</i>
									<em class="error">【0分】</em>
								</span>
							</div>

							<p class="analysis">
								<em>本题解析：</em>中华人民共和国、中华人民共和国中华人民共和国只从加价中华人民共和国中华人民共和国中华人民共和国只从加价国中华人民共和国中华人民共和国。
							</p>	
						</div>
					</div>


					<div class="question-type">
						<h3 class="question-type-title">三、判断题（本题 型共10小题，每小题 2分，共20分）</h3>
						<div class="question-list">
							<h4 class="question-title">
								1、企业工资、资金等现金的支取，只能通过（    ）账户办理。
							</h4>
							<ul class="options">
								<li>A、基本存款</li>
								<li>B、一般存款</li>
								<li>C、临时存款</li>
								<li>D、专用存款</li>
							</ul>

							<div class="answer-box">
								<span class="l">
									<i class="">正确答案：</i>
									<em class="right">【B】</em>
								</span>
								<span>
									<i class="">教材位置：</i>
									<em></em>
								</span>
							</div>

							<div class="result-box">
								<span class="l">
									<i class="">答题结果：</i>
									<em class="error">A 【错误】</em>
								</span>
								<span>
									<i class="">得分：</i>
									<em class="error">【0分】</em>
								</span>
							</div>

							<p class="analysis">
								<em>本题解析：</em>中华人民共和国、中华人民共和国中华人民共和国只从加价中华人民共和国中华人民共和国中华人民共和国只从加价国中华人民共和国中华人民共和国。
							</p>	
						</div>
					</div>
					
				</div>

			</div>

		</div>
	</div>
  </body>
</html>
