package com.zjh.recording.system.oauth.service;


import com.zjh.recording.system.user.vo.UserDetailsVO;

public interface AuthorizationService {

	/**
	 * 根据用户名/用户手机号码（唯一标识）获取指定用户信息
	 *
	 * @param uniqueId
	 * @return
	 */
	UserDetailsVO getByUniqueId(String uniqueId);

	/**
	 * 用户登出
	 * @param token
	 * @return
	 */
	boolean logout(String token);

	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	String parseToken(String token);
}
