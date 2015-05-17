package name.walnut.auth.service;

import java.util.List;

import name.walnut.auth.dto.UserQueryResult;

public interface UserService {
	/**
	 * 通过手机号查找用户
	 * @param mobilephone
	 * @return
	 */
	List<UserQueryResult> findUserByMobile(String mobilephone);
	
	/**
	 * 通过一堆手机号查询
	 * @param mobilephones
	 * @return
	 */
	List<UserQueryResult> findUserByMobiles(String[] mobilephones);
	
	/**
	 * 修改昵称
	 * @param nickName
	 */
	void modifyNickName(String nickName);
	
	/**
	 * 修改头像路径
	 * @param path
	 */
	void modifyHeadPhoto(String path);
}
