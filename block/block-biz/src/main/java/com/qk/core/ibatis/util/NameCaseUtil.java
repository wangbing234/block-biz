package com.qk.core.ibatis.util;

public class NameCaseUtil {

	/**
	 * 下划线分隔符
	 */
	private static final char SEPARATOR = '_';

	/**
	 * 驼峰命名
	 * @param s
	 * @return
	 */
	public static String toCamelCase(String s) {
		// 防错控制
		if (s.equals("")) {
			return s;
		}

		// 不包含下划线，且首字母小写，判定为已经是驼峰命名，不再做转换
		if (-1 == s.indexOf(SEPARATOR) && Character.isLowerCase(s.charAt(0))) {
			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}

		return sb.toString();
	}

	/**
	 * 帕斯卡命名
	 * @param s
	 * @return
	 */
	public static String toPascalCase(String s) {
		s = toCamelCase(s);
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	/**
	 * 下划线命名，大写
	 * @param s
	 * @return
	 */
	public static String toUnderlineUpperCase(String s) {
		if (-1 != s.indexOf(SEPARATOR)) {
			return s.toUpperCase();
		}

		if (s.equals(s.toUpperCase())) {
			return s;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (i > 0 && Character.isUpperCase(c)) {
				sb.append(SEPARATOR);
			}

			sb.append(Character.toUpperCase(c));
		}

		return sb.toString();
	}

	/**
	 * 下划线命名，小写
	 * @param s
	 * @return
	 */
	public static String toUnderlineLowerCase(String s) {
		return toUnderlineUpperCase(s).toLowerCase();
	}

	public static void main(String[] args) {
		System.out.println(toCamelCase("RID"));
		System.out.println(toUnderlineUpperCase("rid"));
		System.out.println(toCamelCase("hello_WoRld"));
		System.out.println(toUnderlineUpperCase("hello_WoRld"));
	}
}
