package com.aran.utils;

import java.security.MessageDigest;

public class SHAUtils {
	private static final String CODING = "utf-8";

	/**
	 * 传入文本内容，返回 SHA-256 串
	 * 
	 * @param strText 
	 * @return length 64 
	 */
	public static String SHA256(final String content) {
		return SHA(content, "SHA-256");
	}

	/**
	 * 传入文本内容，返回 SHA-512 串
	 * 
	 * @param strText  
	 * @return length 128
	 */
	public static String SHA512(final String content) {
		return SHA(content, "SHA-512");
	}

	/**
	 * 字符串 SHA 加密
	 * 
	 * @param strSourceText
	 * @return
	 */
	private static String SHA(final String strText, final String strType) {
		// 返回值
		String strResult = null;
		// 是否是有效字符串
		if (strText != null && strText.length() > 0) {
			try {
				// SHA 加密开始
				// 创建加密对象 并傳入加密類型
				MessageDigest messageDigest = MessageDigest
						.getInstance(strType);
				// 传入要加密的字符串
				messageDigest.update(strText.getBytes(CODING));
				// 得到 byte 類型结果
				byte byteBuffer[] = messageDigest.digest();

				// 將 byte 轉換爲 string
				StringBuffer strHexString = new StringBuffer();
				// 遍歷 byte buffer
				for (int i = 0; i < byteBuffer.length; i++) {
					String hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				// 得到返回結果
				strResult = strHexString.toString();
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage());
			}
		}
		return strResult;
	}
	/**
	 * 判断是否为sha256加密
	 * 
	 * @param content
	 * @return
	 *2017年6月27日 上午9:59:53
	 *@author lintao.xing
	 */
	public static boolean isSHA256Ciphertext(String content){
		if(null != content && content.matches("^[0-9a-fA-F]{64}$")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为sha512加密
	 * 
	 * @param content
	 * @return
	 *2017年6月27日 上午9:59:53
	 *@author lintao.xing
	 */
	public static boolean isSHA512Ciphertext(String content){
		if(null != content && content.matches("^[0-9a-fA-F]{128}$")){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(isSHA512Ciphertext(SHAUtils.SHA512("我是谁")));
	}
}

