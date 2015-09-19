package name.walnut.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro 数据接库鉴权
 * 
 * @author drpc
 * 
 */
public class AuthDbRealm extends AuthorizingRealm {

	

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("*****************鉴权*********************");
		// AuthAccount account = (AuthAccount) principals.getPrimaryPrincipal();
		// // 取得用户所有权限
		// List<VPermissionAccount> permissionList = permissionService
		// .getUserAllPermission(account.getId());
		// List<String> pList = new ArrayList<String>();
		// if (permissionList != null && permissionList.size() > 0) {
		// for (int i = 0; i < permissionList.size(); i++) {
		// VPermissionAccount p = (VPermissionAccount) permissionList
		// .get(i);
		// pList.add(p.getResUrl());
		//
		// }
		// }
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> pList = new ArrayList<String>();
		info.addStringPermissions(pList);

		return info;
	}

	/**
	 * 登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		return null;

	}



}
