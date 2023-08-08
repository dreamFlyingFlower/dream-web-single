package com.dream.web.service;

import org.springframework.web.multipart.MultipartFile;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.UserEntity;
import com.dream.web.query.UserQuery;
import com.dream.web.query.UserRoleQuery;
import com.dream.web.vo.AccountVO;
import com.dream.web.vo.LoginAccountVO;
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

	void getUserDetails(UserVO usesrVo);

	AccountVO login(LoginAccountVO loginAccountVO);

	/**
	 * 修改密码
	 *
	 * @param id 用户ID
	 * @param newPassword 新密码
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * 分配角色，用户列表
	 */
	Result<UserVO> roleUserPage(UserRoleQuery query);

	/**
	 * 批量导入用户
	 *
	 * @param file excel文件
	 * @param password 密码
	 */
	void importByExcel(MultipartFile file, String password);

	/**
	 * 导出用户信息表格
	 */
	void export();
}