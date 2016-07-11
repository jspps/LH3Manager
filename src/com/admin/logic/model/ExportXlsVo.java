package com.admin.logic.model;
import java.util.List;

public class ExportXlsVo {
	String sheetName;
    List<String> titles;
    List<List<String>> rows;

    public List<String> getTitles() {
        return titles;
    }

    public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}

		public void setTitles(List<String> titles) {
			this.titles = titles;
		}

		public void setRows(List<List<String>> rows) {
			this.rows = rows;
		}

		public  List<List<String>> getRows() {
        return rows;
    }

    public String getSheetName() {
        return sheetName;
    }
}
