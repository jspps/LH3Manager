package com.admin.logic;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bowlong.util.page.PageEnt;

/**
 * 分页标签
 * 
 * @ClassName: PagingTag
 * @author hxw
 * 
 */
public class PagingTag extends TagSupport {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2771143709830017519L;
	private String style;
	private String formName;
	@SuppressWarnings("rawtypes")
	private PageEnt pe;

	@SuppressWarnings("rawtypes")
	public void setName(String name) {
		Object result = this.pageContext.findAttribute(name);
		if (result != null && result instanceof PageEnt) {
			this.pe = (PageEnt) result;
		}
	}

	public void setStyle(String style) {
		this.style = style;
	}
 

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Override
	public int doEndTag() throws JspException {
		if (this.pe == null) {
			return SKIP_BODY;
		}
	 
		StringBuilder str = new StringBuilder(800);
		str.append("<div class='"+style+"'>");
		int pageCount = pe.getTotalPages();
		if(pageCount!=0){
			if(pe.getHavePrePage()){
				str.append("<a href='javascript:void(0);'   onclick='goPaging("+"\""+formName+"\","+(pe.getPage()-1)+")'  class='fy'>&lt;</a>");
			}else{
				str.append("<a href='javascript:void(0);' class='fy'>&lt;</a>");
			}
		}
		
		
		int a = 9;
		long i =0; 
		long cou = pageCount+1;
		if(cou>a){
			if(pe.getPage()>a-1){
				if(cou-pe.getPage()>-1){
					
					if(pageCount-pe.getPage()<1){
						i = pageCount-a+1;
					}if(pageCount==pe.getPage()){
						i = pageCount-a;
					}else{
						i = pe.getPage()+1-a;
					}
					if(pageCount-pe.getPage()>=1){
						cou = pe.getPage()+1;
					}else{
						cou = pe.getPage();
					}
				}else{
					i= cou-a-1;
					cou = a;
				}
			}else{
				cou = a;
			}
		}else{
			cou = pageCount;
		}
		for (; i < cou; i++) {
			if(pe.getPage()==i+1){
				str.append("<span>"+(i+1)+"</span>");
			}else{
				str.append("<a href='javascript:void(0);'   onclick='goPaging("+"\""+formName+"\","+(i+1)+")'>"+(i+1)+"</a>");
			}
		}
		if(cou>=9 && pe.getHaveNextPage()){
			str.append("<a href='javascript:void(0);'  onclick='goPaging("+"\""+formName+"\","+(pe.getPage()+1)+")'>...</a>");
		}
		if(pageCount!=0){
			if(pe.getHaveNextPage()){
				str.append("<a href='javascript:void(0);'  onclick='goPaging("+"\""+formName+"\","+(pe.getPage()+1)+")'  class='fy'>&gt;</a>");
			}else{
				str.append("<a href='javascript:void(0);' class='fy'>&gt;</a>");
			}
		}else{
			str.append("<p>没有任何数据</p>");
		}
		
		
		try {

			JspWriter writer = this.pageContext.getOut();
			writer.print(str.toString());
			writer.flush();
			writer.clearBuffer();

		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}
