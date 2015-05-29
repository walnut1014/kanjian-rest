package name.walnut.sequence;

import com.daren.common.sequence.impl.Sequence;
import com.daren.common.utils.SpringUtils;

/**
 * 序列枚举
 * 需要在数据库中添加
 * 枚举名和ST_SEQ_STATE表中的SEQ_NAME相同
 * @author walnut
 *
 */

	public enum SequenceEnum {
		TEST,TEST2,//测试使用
		PT_MESSAGE_SEND_TASK,
		T_SITE_MESSAGE,
		PT_USER,
		T_CONTRACT_TEMPLATE,
		T_PARTY_PAYMENT_TXN,
		//合作方
		PARTNER, 
		PT_CHARGE_RULE,
		//鉴权:账号序列
		PT_AUTHACCOUNT,
		//文件组ID
		PT_ATTACHMENT_GROUPID,
		//文件ID
		PT_ATTACHMENT_FILEID,
		T_LOAN_RECEIPT,
		T_REPAYMENT_PLAN,
		T_FLOW_RECORD,
		T_CASH_RECORD,
		//产品
		T_PRODUCT,
		//银行卡
		PT_BANK_CARD,
		//银行信息
		PT_BANK_INFO,
		
		//客户编号

		CustomerNo,
		T_INVESTMENT,
		
		// 项目备案订单号
		ICP_ORDER_NO,
		// 项目流标订单号
		WITHDRAWAL_ORDER_NO,
		// 项目放款订单号字段
		PAYMENT_ORDER_NO,
		// 银行卡绑定订单号
		BINDING_ORDER_NO,
		// 银行卡解绑订单号
		UNBUNDLING_ORDER_NO,
		// 投资订单号
		INVEST_ORDER_NO,
		// 投资放款订单号
		INVEST_PAYMENT_ORDER_NO,
		// 用户注册订单号
		REGISTER_ORDER_NO,
		// 项目还款订单号
		REPAYMENT_ORDER_NO,
		// 用户收益订单号
		INCOME_ORDER_NO,
		// 提现订单号
	    WITHDRAW_ORDER_NO,
	    // 充值订单号
	    CASHIER_ORDER_NO,
	    // 查询用户注册结果订单号
	    QRY_ACCOUNT_RGE_ORDER_NO,
	    // 用户认证信息表序列号
	    PT_USER_CERTIFY_INFO,
	    // 用户认证历史序列号
	    PT_USER_CERTIFY_HISTORY,
	    // 用户认证订单号
	    USER_CERTIFY_ORDER_NO,
	    // 接口合同序列号
	    T_LOAN_CONTRACT,
	    // 附件文件序列号
	    ATTACHMENT_FILE_NO,
	    // 交易流水
	    T_TRADE_RECORD,
		// 查询资金明细订单号
	    QRY_CAPITAL_DETAIL_ORDER_NO,
	    // 信息披露序列号
	    T_DISCLOSE_INFO,
		// 借款序列号
	    T_LOAN_NO,
	    // 媒体动态序列号
	    T_MEDIA_NO,
	    //人工身份认证
	    PT_HUMAN_CERTIFY,
	    // 提前还款序列号
	    T_PRE_REPAYMENT_RECORD,
	    // 第三方交易记录序列号
	    T_PARTY_PAYMENT_RECORD,
	    //营销短信
	    T_MARK_MSG,
	    // 提前还款计划序列号
	    T_PRE_REPAYMENT_PLAN,
		//banner
		T_BANNER_NO,
		T_PRE_INCOME_PLAN,
		// 自动投资订单号
		AUTO_INVEST_ORDER_NO,
		// 取消投资订单号
		CANCEL_AUTO_INVEST_ORDER_NO,
		// 开通投资订单号
		OPEN_AUTO_INVEST_ORDER_NO,
		//赎回记录
		T_REDEMPTION,
		// 赎回订单号
		REDEMPTION_ORDER_NO,
		// 派送订单号
		PROVIDE_REWARD_ORDER_NO,
		// 对账序列号
		T_RECONCILIATION,
		// 双十一晒单互动序列号
		TG_141111,
		// 对账详情序列号
		T_RECONCILIATION_DETAIL,
		// 平台账户交易序列号
		T_PLATFORM_TRADE_RECORD,
		// 平台提现订单号
		PLATFORM_WITHDRAW_ORDER_NO,
	    // 平台充值订单号
		PLATFORM_CASHIER_ORDER_NO,
	    // 推荐奖励序列号
		TG_RECOMMEND_REWARD_INFO,
		// 达币流水序列号
		PT_DCOIN_DETAIL,
		//达币池记录序列号
		T_DCOIN_POOL_RECORD,
		//红包序列号
		PT_REDPACKET_RECORD,
		// 还款批次号
		REPAYMENT_BATCH_NO,
		//快付通验密订单号
		CHECK_PASSWORD_NO,
		// 第三方账户详情订单号
		ACCOUNT_DETAIL_ORDER_NO;
		
	public long next(){
		SequenceManager manager = SpringUtils.getBean("sequenceManager");
		Sequence sequence =  manager.getSequence(this);
		return sequence.next();
	}
	
	public long current(){
		SequenceManager manager = SpringUtils.getBean("sequenceManager");
		return manager.getSequence(this).getCurrent();
	}
}
