package com.admin.logic.model;

import java.util.Date;

public class ExamcatalogVo {
	public ExamcatalogVo(){
		super();
	}
    public ExamcatalogVo(int examcatalogid, int examid, String serial,
			String bigtypes, boolean isSubjective, int num, int everyScore,
			String title, int status, Date createtime) {
		super();
		this.examcatalogid = examcatalogid;
		this.examid = examid;
		this.serial = serial;
		this.bigtypes = bigtypes;
		this.isSubjective = isSubjective;
		this.num = num;
		this.everyScore = everyScore;
		this.title = title;
		this.status = status;
		this.createtime = createtime;
	}
    
	private int examcatalogid;
    private int examid;
    private String serial;
    private String bigtypes;
    private boolean isSubjective;
    private int num;
    private int everyScore;
    private String title;
    private int status;
    private java.util.Date createtime;
    
    
	public int getExamcatalogid() {
		return examcatalogid;
	}
	public void setExamcatalogid(int examcatalogid) {
		this.examcatalogid = examcatalogid;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getBigtypes() {
		return bigtypes;
	}
	public void setBigtypes(String bigtypes) {
		this.bigtypes = bigtypes;
	}
	public boolean getIsSubjective() {
		return isSubjective;
	}
	public void setIsSubjective(boolean isSubjective) {
		this.isSubjective = isSubjective;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getEveryScore() {
		return everyScore;
	}
	public void setEveryScore(int everyScore) {
		this.everyScore = everyScore;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
    
    
}
