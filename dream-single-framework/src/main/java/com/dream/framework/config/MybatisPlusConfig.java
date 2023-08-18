package com.dream.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import dream.framework.web.interceptor.DataScopeInnerInterceptor;

@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		// 数据权限
		mybatisPlusInterceptor.addInnerInterceptor(new DataScopeInnerInterceptor());
		// 分页插件
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		// 乐观锁
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		// 防止全表更新与删除
		mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

		return mybatisPlusInterceptor;
	}
}