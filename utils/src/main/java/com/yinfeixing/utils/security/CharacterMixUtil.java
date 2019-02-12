package com.yinfeixing.utils.security;

public class CharacterMixUtil {
	/**
	 * 
	* @Function: CharacterMixUtil.java
	* @Description: 字符串混淆
	* @str:6-32位字符串
	* @return：String
	* @author: mazy
	* @date: 2018年8月3日 上午11:12:05 
	*
	 */
	public static String encode(String str){
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			c[i] = (char)(c[i] ^ (i*2));
		}
		return new String(c);
	}
	
	public static String decode(String str){
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			c[i] = (char)(c[i] ^ (i*2));
		}
		return new String(c);
	}

}

