/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.abing.encrypt.api.controller;

import com.abing.encrypt.base.BaseApiService;
import com.abing.encrypt.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// http协议特殊字符处理
@RestController
public class IndexController extends BaseApiService {

	// 1. 什么是特殊字符处理（rpc远程通讯 实现加密 + ?） 正好和http特殊相同，导致会转成空格
	// 2. 注意事项
	@RequestMapping("/indexPage")
	public ResponseBase indexPage(String userName) {
		System.out.println("userName:" + userName);
		return setResultSuccessData(userName);
	}

}
