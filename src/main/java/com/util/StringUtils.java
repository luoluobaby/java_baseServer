package com.util;

/**
 * 字符串通用操作
 * @author 阳
 *
 */
public class StringUtils {
	/**
	 * equipment_code填充长度
	 */
	public final static int PADDING_LEGTH_EQUIPMENT_CODE=5;
	/**
	 * 判断字符串是否为空，或者内容为""
	 * @param input
	 * @return
	 */
	public static boolean IsNullOrEmpty(String input) {
		return null==input||"".equals(input);
		
	}
	/**
	 * 字符串填充
	 * @param src 源字符串
	 * @param length 填充后长度
	 * @param left 若该值未true，则左填充；否则右填充
	 * @param paddingChar 填充字符
	 * @return 填充后返回的值
	 */
	public static String Pad(String src,int length,boolean left,char paddingChar) {
		if(null==src||length<=src.length()||""==String.valueOf(paddingChar))
			return src;
		String paddingStr="";
		for(int i=0;i<length;i++)
			paddingStr+=paddingChar;
		if(true==left)
		{
			src=paddingStr+src;
			src=src.substring(src.length()-length, src.length());
		}
		else
		{
			src=src+paddingStr;
			src=src.substring(0, length);
		}
		return src;
	}
	/**
	 * 字符格式化填充，默认填充'0'
	 * @param src 源字符串
	 * @param length 填充后长度
	 * @param left 若该值未true，则左填充；否则右填充
	 * @return 填充后返回的值
	 */
	public static String Pad(String src,int length,boolean left) {
		return Pad(src, length, left, '0');
	}
}
