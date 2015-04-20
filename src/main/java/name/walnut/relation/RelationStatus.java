package name.walnut.relation;

/**
 * 用户关系转态
 */
public enum RelationStatus {
	
	sendRequest, //发送请求
	waitVerify, //待验证
	accetp; //接受请求
}
