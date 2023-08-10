package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dream.basic.core.constant.ConstCore;
import com.dream.basic.core.excel.EasyExcelHelper;
import com.dream.basic.core.excel.callback.ExcelFinishCallBack;
import com.dream.framework.enums.SuperAdminEnum;
import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.convert.UserConvert;
import com.dream.system.entity.UserEntity;
import com.dream.system.mapper.UserMapper;
import com.dream.system.query.UserQuery;
import com.dream.system.query.UserRoleQuery;
import com.dream.system.service.UserPostService;
import com.dream.system.service.UserRoleService;
import com.dream.system.service.UserService;
import com.dream.system.vo.UserExcelVO;
import com.dream.system.vo.UserPasswordVO;
import com.dream.system.vo.UserVO;
import com.fhs.trans.service.impl.TransService;
import com.wy.result.Result;
import com.wy.result.ResultException;
import com.wy.util.DateTimeTool;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 用户管理
 * 
 *
 * @author 飞花梦影
 * @date 2023-08-09 10:57:14
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends AbstractScopeServiceImpl<UserEntity, UserVO, UserQuery, UserConvert, UserMapper>
		implements UserService {

	private final UserRoleService userRoleService;

	private final UserPostService userPostService;

	private final TransService transService;

	private final PasswordEncoder passwordEncoder;

	@Override
	public Result<List<UserVO>> page(UserQuery query) {
		// 查询参数
		Map<String, Object> params = getParams(query);
		// 分页查询
		IPage<UserEntity> page = getPage(query);
		params.put(ConstCore.PAGE, page);
		// 数据列表
		List<UserEntity> list = baseMapper.getList(params);
		return Result.page(baseConvert.convertt(list), page.getCurrent(), page.getSize(), page.getTotal());
	}

	private Map<String, Object> getParams(UserQuery query) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", query.getUsername());
		params.put("mobile", query.getMobile());
		params.put("gender", query.getGender());
		params.put("status", query.getStatus());
		params.put("userCert", query.getUserCert());
		params.put("orgId", query.getOrgId());
		params.put("realName", query.getRealName());

		// 数据权限
		params.put(ConstCore.DATA_SCOPE, getDataScope("t1", null));

		return params;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserEntity add(UserVO vo) {
		UserEntity entity = baseConvert.convert(vo);
		entity.setSuperAdmin(SuperAdminEnum.NO.getCode());

		entity.setPassword(passwordEncoder.encode(vo.getPassword()));

		// 判断用户名是否存在
		UserVO user = baseMapper.getByUsername(entity.getUsername());
		if (user != null) {
			throw new ResultException("用户名已经存在");
		}

		// 判断手机号是否存在
		user = baseMapper.getByMobile(entity.getMobile());
		if (user != null) {
			throw new ResultException("手机号已经存在");
		}

		// 保存用户
		baseMapper.insert(entity);

		// 保存用户角色关系
		userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIds());

		// 更新用户岗位关系
		userPostService.saveOrUpdate(entity.getId(), vo.getPostIds());

		entity.setPassword(null);
		return entity;
	}

	@Override
	public Boolean edit(UserVO vo) {
		vo.setPassword(null);
		// 判断用户名是否存在
		UserVO userVO = baseMapper.getByUsername(vo.getUsername());
		if (userVO != null && !userVO.getId().equals(vo.getId())) {
			throw new ResultException("用户名已经存在");
		}

		// 判断手机号是否存在
		userVO = baseMapper.getByMobile(vo.getMobile());
		if (userVO != null && !userVO.getId().equals(vo.getId())) {
			throw new ResultException("手机号已经存在");
		}

		updateById(baseConvert.convert(vo));

		// 更新用户角色关系
		userRoleService.saveOrUpdate(vo.getId(), vo.getRoleIds());

		// 更新用户岗位关系
		userPostService.saveOrUpdate(vo.getId(), vo.getPostIds());

		return true;
	}

	@Override
	public Boolean deleteByIds(List<Serializable> ids) {
		Long userId = SecurityHelper.getUserId();
		if (ids.contains(userId)) {
			throw new ResultException("不能删除当前登录用户");
		}
		// 删除用户
		removeByIds(ids);
		// 删除用户角色关系
		userRoleService.deleteByUserIdList(ids);
		// 删除用户岗位关系
		userPostService.deleteByUserIdList(ids);
		return true;
	}

	@Override
	public UserVO getByMobile(String mobile) {
		return baseMapper.getByMobile(mobile);
	}

	@Override
	public UserVO getByUsername(String username) {
		return baseMapper.getByUsername(username);
	}

	@Override
	public void updatePassword(UserPasswordVO userPasswordVO) {
		String newPassword = passwordEncoder.encode(userPasswordVO.getNewPassword());
		// 原密码不正确
		SecurityUserDetails securityUserDetails = SecurityHelper.getUser();
		if (!passwordEncoder.matches(newPassword, securityUserDetails.getPassword())) {
			throw new ResultException("原密码不正确");
		}
		updateById(UserEntity.builder().id(securityUserDetails.getId()).password(newPassword).build());
	}

	@Override
	public Result<List<UserVO>> roleUserPage(UserRoleQuery query) {
		// 分页查询
		IPage<UserEntity> page = getPage(query);

		// 数据列表
		List<UserEntity> list = baseMapper.getRoleUserList(query);

		return Result.page(baseConvert.convertt(list), page.getCurrent(), page.getSize(), page.getTotal());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importByExcel(MultipartFile file) {
		String password = passwordEncoder.encode("123456");
		EasyExcelHelper.readAnalysis(file, UserExcelVO.class, new ExcelFinishCallBack<UserExcelVO>() {

			@Override
			public void doAfterAllAnalysed(List<UserExcelVO> result) {
				saveUser(result);
			}

			@Override
			public void doSaveBatch(List<UserExcelVO> result) {
				saveUser(result);
			}

			private void saveUser(List<UserExcelVO> result) {
				EasyExcelHelper.parseDict(result);
				List<UserEntity> sysUserEntities = baseConvert.convertExcel(result);
				sysUserEntities.forEach(user -> user.setPassword(password));
				saveBatch(sysUserEntities);
			}
		});
	}

	@Override
	@SneakyThrows
	public void export() {
		List<UserEntity> list =
				list(Wrappers.lambdaQuery(UserEntity.class).eq(UserEntity::getSuperAdmin, SuperAdminEnum.NO.getCode()));
		List<UserExcelVO> userExcelVOS = baseConvert.convert2Excel(list);
		transService.transBatch(userExcelVOS);
		// 写到浏览器打开
		EasyExcelHelper.excelExport(UserExcelVO.class, "system_user_excel" + DateTimeTool.formatDateTime(), null,
				userExcelVOS);
	}

	@Override
	public UserVO getDetail(Serializable id) {
		UserVO userVO = super.getDetail(id);
		// 用户角色列表
		List<Long> roleIdList = userRoleService.getRoleIdList(id);
		userVO.setRoleIds(roleIdList);
		// 用户岗位列表
		List<Long> postIdList = userPostService.getPostIdList(id);
		userVO.setPostIds(postIdList);
		return userVO;
	}
}