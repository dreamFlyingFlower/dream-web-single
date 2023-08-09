package com.dream.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.UserEntity;
import com.dream.web.query.UserQuery;
import com.dream.web.query.UserRoleQuery;
import com.dream.web.vo.UserPasswordVO;
import com.dream.web.vo.UserVO;
import com.wy.result.Result;

/**
 * 用户表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface UserService extends BaseService<UserEntity, UserVO, UserQuery> {

	UserVO getByMobile(String mobile);

	UserVO getByUsername(String username);

	/**
	 * 修改密码
	 *
	 * @param userPasswordVO 新密码
	 */
	void updatePassword(UserPasswordVO userPasswordVO);

	/**
	 * 分配角色，用户列表
	 */
	Result<List<UserVO>> roleUserPage(UserRoleQuery query);

	/**
	 * 批量导入用户
	 *
	 * @param file excel文件
	 * @param password 密码
	 */
	void importByExcel(MultipartFile file);

	/**
	 * 导出用户信息表格
	 */
	void export();
}