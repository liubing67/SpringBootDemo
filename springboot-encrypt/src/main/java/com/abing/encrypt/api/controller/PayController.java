/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.abing.encrypt.api.controller;

import java.util.UUID;

import com.abing.encrypt.base.BaseApiService;
import com.abing.encrypt.base.BaseRedisService;
import com.abing.encrypt.base.ResponseBase;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午6:15:05<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class PayController extends BaseApiService {
	@Autowired
	private BaseRedisService baseRedisService;
	private static long timeToken = 15 * 60l;

	@RequestMapping("/pay")
	public ResponseBase pay(String token) {
		// 获取提交参数 数据库保存.,
		if (StringUtils.isEmpty(token)) {
			return setResultError("token 不能为空！");
		}
		String reuslt = (String) baseRedisService.getString(token);
		if (StringUtils.isEmpty(reuslt)) {
			return setResultError("参数不能空!");
		}
		System.out.println("获取提交的参数reuslt：" + reuslt);
		return setResultSuccess("获取提交的参数reuslt：" + reuslt);
	}

	@RequestMapping("/getPayToken")
	public String pay(Long userId, Long money) {
		String payToken = UUID.randomUUID().toString();
		baseRedisService.setString(payToken, userId + "-" + money, timeToken);
		return payToken;
	}

}
