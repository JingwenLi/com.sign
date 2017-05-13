/**
 * @Title: UserRealm.java
 * @Description: 
 * @Copyright: Copyright (c) 2017
 * @author LJW831
 * @date  2017年4月30日 下午4:16:13
 */
package com.sign.Util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Title: UserRealm
 * @Description:
 * @author LJW831
 * @date 2017年4月30日 下午4:16:13
 */
public class UserRealm extends AuthorizingRealm {

	/**
	 * 提供用户信息返回权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUsername = (String) super
				.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// // 根据用户名查询当前用户拥有的角色
		// Set<Role> roles = userService.findRoles(username);
		// Set<String> roleNames = new HashSet<String>();
		// for (Role role : roles) {
		// roleNames.add(role.getRole());
		// }
		// // 将角色名称提供给info
		// authorizationInfo.setRoles(roleNames);
		// // 根据用户名查询当前用户权限
		// Set<Permission> permissions = userService.findPermissions(username);
		// Set<String> permissionNames = new HashSet<String>();
		// for (Permission permission : permissions) {
		// permissionNames.add(permission.getPermission());
		// }
		// // 将权限名称提供给info
		// authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}

	/**
	 * 提供账户信息返回认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if (null != username && null != password) {
			return new SimpleAuthenticationInfo(username, password, getName());
		} else {
			return null;
		}
	}
}
