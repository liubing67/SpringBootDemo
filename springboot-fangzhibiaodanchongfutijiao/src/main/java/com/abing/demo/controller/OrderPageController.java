/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.abing.demo.controller;

import javax.servlet.http.HttpServletRequest;

import com.abing.demo.entity.OrderEntity;
import com.abing.demo.ext.ExtApiIdempotent;
import com.abing.demo.ext.ExtApiToken;
import com.abing.demo.mapper.OrderMapper;
import com.abing.demo.utils.ConstantUtils;
import com.abing.demo.utils.RedisToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月16日 下午5:48:56<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Controller
public class OrderPageController {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RedisToken redisToken;

	@RequestMapping("/indexPage")
	@ExtApiToken
	public String indexPage(HttpServletRequest req) {
		return "indexPage";
	}

	@RequestMapping("/addOrderPage")
	@ExtApiIdempotent(type = ConstantUtils.EXTAPIFROM)
	public String addOrder(OrderEntity orderEntity) {
		int addOrder = orderMapper.addOrder(orderEntity);
		return addOrder > 0 ? "success" : "fail";
	}

}
