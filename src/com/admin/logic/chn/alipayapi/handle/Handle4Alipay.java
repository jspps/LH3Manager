package com.admin.logic.chn.alipayapi.handle;

import java.util.Date;
import java.util.Map;

import com.admin.db.bean.Orders;
import com.admin.logic.chn.alipayapi.AlipayConfig;
import com.admin.logic.chn.alipayapi.AlipayCore;
import com.admin.logic.chn.alipayapi.AlipaySubmit;
import com.admin.logic.chn.alipayapi.entity.AlipayEn4Ques;
import com.bowlong.lang.PStr;
import com.bowlong.security.MD5;
import com.bowlong.util.DateEx;

public class Handle4Alipay {

	static public final String return_url_test = "http://1010xue.com/LH3Manager/return_url_test";
	static public final String notify_url = "http://1010xue.com/notify_url";

	/*** type[0买套餐,1学测(1元),2代测(1元),3买考币,4买问答] **/
	static public String handle4OneTest(int type, int makerid,
			String makername, double money) {
		// 产生订单
		String name = PStr.str("进行支付宝状态(是否可用)验证!");
		double price = 0;
		int kbi = 0;
		Date createtime = DateEx.nowDate();
		int status = 0;
		int statusProcess = 0;
		double discount = 0;
		Date lasttime = createtime;
		String out_trade_no = MD5.MD5UUIDStime();

		double total_fee = money;

		Orders orders = Orders.newOrders(0, type, name, "", makerid, price,
				kbi, createtime, status, statusProcess, makername, discount,
				"", lasttime, out_trade_no, total_fee);
		orders = orders.insert();

		String royalty_parameters = "";

		AlipayEn4Ques quesEntity = new AlipayEn4Ques(notify_url, out_trade_no,
				name, total_fee, royalty_parameters);
		quesEntity.setPartner(AlipayConfig.partner);
		quesEntity.setSeller_email(AlipayConfig.seller_email);
		quesEntity.setReturn_url(return_url_test);
		// 设置不是扫描支付
		quesEntity.setQr_pay_mode("");

		Map<String, String> mapBuild = AlipayCore.buildRequestPara(quesEntity);
		return AlipaySubmit.buildRequest(mapBuild, "post", "确定");
	}
}