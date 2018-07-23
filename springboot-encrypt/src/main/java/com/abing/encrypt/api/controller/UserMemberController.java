/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.abing.encrypt.api.controller;

import javax.crypto.Cipher;

import com.abing.encrypt.rsa.RSAUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午5:53:37<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class UserMemberController {
	@Value("${private_key}")
	private String privateKey;

	// 使用公钥加密 私钥解密
	@RequestMapping("/getMember")
	public String getMember(String userName) {
		String decUserName;
		try {
			decUserName = RSAUtil.encryptByprivateKey(userName, privateKey, Cipher.DECRYPT_MODE);
		} catch (Exception e) {
			return "解密错误!";
		}
		if (StringUtils.isEmpty(decUserName)) {
			return "解密失败!";
		}

		System.out.println("解密成功 decUserName:" + decUserName);
		return "解密成功!";
	}

}
