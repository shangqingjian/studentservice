package com.huawei.StudentService.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncrypUtils {
	
	// 加密秘钥
	private final static String ENCRY_KEY = "ASDF1234QWER5678ASDF1234QWER5678";
	
	private EncrypUtils() {

	}

	// 使用AES对字符串加密
	public static String aesEncrypt(String target) throws Exception {
		if (target == null) {
			return null;
		}
		// 分别的意思为：AES是加密算法，ECB是工作模式，PKCS5Padding是填充方式，加密和解密时必须相同
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		// 加密模式
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(ENCRY_KEY.getBytes("utf-8"), "AES"));
		// 执行加密
		byte[] bytes = cipher.doFinal(target.getBytes("utf-8"));
		String res = Base64.getEncoder().encodeToString(bytes);
		return res;
	}

	// 使用AES对数据解密
	public static String aesDecrypt(String source) throws Exception {
		if (source == null) {
			return null;
		}
		// 分别的意思为：AES是加密算法，ECB是工作模式，PKCS5Padding是填充方式，加密和解密时必须相同
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		// 解密模式
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(ENCRY_KEY.getBytes("utf-8"), "AES"));
		// 执行解密
		byte[] src = Base64.getDecoder().decode(source);
		byte[] res = cipher.doFinal(src);
		return new String(res, "utf-8");
	}

}
