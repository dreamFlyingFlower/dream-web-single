package com.dream.web.agent;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dream.basic.core.enums.CommonErrorCode;
import com.dream.web.vo.AccountDTO;
import com.dream.web.vo.LoginAccountVO;
import com.wy.result.Result;

import lombok.extern.slf4j.Slf4j;

@FeignClient(value = "account-service", fallbackFactory = AccountFallbackFactory.class)
public interface AccountApiAgent {

	@PostMapping(value = "/account/l/accounts/session")
	Result<AccountDTO> login(@RequestBody LoginAccountVO loginAccountVO);
}

@Component
@Slf4j
class AccountFallbackFactory implements FallbackFactory<AccountApiAgent> {

	@Override
	public AccountApiAgent create(Throwable cause) {
		return new AccountApiAgent() {

			@Override
			public Result<AccountDTO> login(LoginAccountVO loginAccountVO) {
				// 记录错误原因
				log.info("fallback; reason was:", cause);
				// 降级回退
				return Result.error(CommonErrorCode.E_999995);
			}
		};
	}
}