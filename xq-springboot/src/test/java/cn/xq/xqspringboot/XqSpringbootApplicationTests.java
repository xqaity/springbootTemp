package cn.xq.xqspringboot;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
class XqSpringbootApplicationTests {

	/**
	 * 注入加密方法
	 */
	@Autowired
	private StringEncryptor encryptor;
	@Test
	void contextLoads() {
		String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:13306/xq_mode?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
		String name = encryptor.encrypt("root");
		String password = encryptor.encrypt("root");
		System.out.println("database url: " + url);
		System.out.println("database name: " + name);
		System.out.println("database password: " + password);
//		Assert assertTrue(url.length() > 0);
//		Assert.assertTrue(name.length() > 0);
//		Assert.assertTrue(password.length() > 0);
	}

}
