package com.admin.proto;

import java.util.*;
import com.bowlong.io.*;
import com.bowlong.net.proto.gen.*;

import static com.bowlong.net.proto.gen.B2G.DATA;
import static com.bowlong.net.proto.gen.B2G.SHEET;
import static com.bowlong.net.proto.gen.B2G.SHEET_ROW;

@B2Class(namespace = "exam")
public class ExamXls {
    public static void main(String[] args) throws Exception {
        Class<?> c = ExamXls.class;
        boolean src = FileEx.exists("src");
        Bio2GJava.b2g(c, src);
    }

    @B2Class(type = DATA, sheetType = SHEET_ROW, remark = "题库分类_Course")
    class Course {
        @B2Field(remark = "ID")
        public int ID;
        @B2Field(remark = "分类")
        public String nmDepart;
        @B2Field(remark = "专业")
        public String nmMajor;
        @B2Field(remark = "层级")
        public String nmLevel;
        @B2Field(remark = "科目")
        public String nmSub;
        @B2Field(remark = "考试范围")
        public String nmArea;
    }

    @B2Class(type = DATA, sheetName="题库分类_Course",sheetCName="Course", sheetType = SHEET, remark = "Courses")
    class Courses {
        public List<Course> datas;
    }


    @B2Class(type = DATA, sheetType = SHEET_ROW, remark = "分类类别_Catalog4Course")
    class Catalog4Course {
        @B2Field(remark = "ID")
        public int ID;
        @B2Field(remark = "题库分类ID")
        public int ID4Course;
        @B2Field(remark = "所属类别")
        public String examtype;
        @B2Field(remark = "购买金额")
        public double price;
        @B2Field(remark = "购买获得考币")
        public int kbi;
        @B2Field(remark = "推荐好友数量")
        public int num4Fri;
    }

    @B2Class(type = DATA, sheetName="分类类别_Catalog4Course",sheetCName="Catalog4Course", sheetType = SHEET, remark = "Catalog4Courses")
    class Catalog4Courses {
        public List<Catalog4Course> datas;
    }


    @B2Class(type = DATA, sheetType = SHEET_ROW, remark = "试卷_Exam")
    class Exam {
        @B2Field(remark = "ID")
        public int ID;
        @B2Field(remark = "分类类别ID")
        public int ID4CourseCatalog;
        @B2Field(remark = "试卷题目")
        public String name;
        @B2Field(remark = "试卷说明")
        public String decription;
        @B2Field(remark = "考试时间")
        public int examTime;
        @B2Field(remark = "总分")
        public int score;
    }

    @B2Class(type = DATA, sheetName="试卷_Exam",sheetCName="Exam", sheetType = SHEET, remark = "Exams")
    class Exams {
        public List<Exam> datas;
    }


    @B2Class(type = DATA, sheetType = SHEET_ROW, remark = "试卷目录_ExamCatalog")
    class ExamCatalog {
        @B2Field(remark = "ID")
        public int ID;
        @B2Field(remark = "试卷ID")
        public int ID4Exam;
        @B2Field(remark = "大题序号")
        public String serial;
        @B2Field(remark = "大题类型")
        public String bigtypes;
        @B2Field(remark = "是否是主观题")
        public boolean isSubject;
        @B2Field(remark = "题目数量")
        public int num;
        @B2Field(remark = "每题分数")
        public int oneScore;
        @B2Field(remark = "所属上级")
        public int parentid;
        @B2Field(remark = "大题提干")
        public String title;
    }

    @B2Class(type = DATA, sheetName="试卷目录_ExamCatalog",sheetCName="ExamCatalog", sheetType = SHEET, remark = "ExamCatalogs")
    class ExamCatalogs {
        public List<ExamCatalog> datas;
    }


    @B2Class(type = DATA, sheetType = SHEET_ROW, remark = "试题_Question")
    class Question {
        @B2Field(remark = "ID")
        public int ID;
        @B2Field(remark = "试卷目录ID")
        public int ID4ExamCatalog;
        @B2Field(remark = "题目")
        public String title;
        @B2Field(remark = "选项内容")
        public String A;
        @B2Field(remark = "正确答案")
        public String right;
        @B2Field(remark = "解析(分析)")
        public String analyse;
    }

    @B2Class(type = DATA, sheetName="试题_Question",sheetCName="Question", sheetType = SHEET, remark = "Questions")
    class Questions {
        public List<Question> datas;
    }

}
