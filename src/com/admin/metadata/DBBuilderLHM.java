package com.admin.metadata;

import com.admin.content.AppContext;
import com.bowlong.sql.freemarker.SK_Generate;

public class DBBuilderLHM {
	public static void main(String[] args) throws Exception {
		buildEntityByDesign();
		// buildEntityByCfg();
		System.exit(1);
	}

	static void buildEntityByDesign() throws Exception {
		String cfgPath = ""; // "src/com/monster/metadata/template/"
		SK_Generate.runByMySql(AppContext.dsDesign(), AppContext.class,
				"src/com/war/db", cfgPath, false);
	}

	static void buildEntityByCfg() throws Exception {
		SK_Generate.runByMySql(AppContext.dsCfg(), AppContext.class,
				"src/com/war/db", "", true);
	}
}
