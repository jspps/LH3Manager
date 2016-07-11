package com.admin.logic;

import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.bowlong.util.DateEx;

/**
 * 
 * 
 * @author hxw
 * 
 */
public class DateTag extends TagSupport {
	private static final long serialVersionUID = -3354015192721342312L;
	private Date value;

	public void setValue(Date value) {
		this.value = value;
	}

	private String parttern;

	public void setParttern(String parttern) {
		this.parttern = parttern;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(DateEx.format(value, parttern));
		} catch (Exception e) {
		}
		return super.doStartTag();
	}
}
