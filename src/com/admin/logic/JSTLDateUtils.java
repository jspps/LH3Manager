package com.admin.logic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.bowlong.lang.NumEx;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;

/**
 * 用于页面jstl时间格式化
 * 
 * @author zhangwen
 * 
 */
public class JSTLDateUtils extends TagSupport {
	private static final long serialVersionUID = -3354015192721342312L;
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	private String parttern;

	public void setParttern(String parttern) {
		this.parttern = parttern;
	}

	@Override
	public int doStartTag() throws JspException {
		long time = NumEx.stringToLong(value);
		String v = "";
		if (time > 0) {
			if (StrEx.isEmpty(parttern))
				parttern = DateEx.fmt_yyyy_MM_dd_HH_mm_ss;

			v = DateEx.format(DateEx.parse2Cal(time), parttern);
		}
		try {
			pageContext.getOut().write(v);
		} catch (Exception e) {
		}
		return super.doStartTag();
	}
}
