package com.edongik.glucoguidetest.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String nvl(String str) {
		if(str == null || str.length() == 0)
			return "";
		else
			return str.trim();
	}

	public static String nvl(String str1, String str2) {
		if(str1 == null || str1.length() == 0)
			return str2.trim();
		else
			return str1.trim();
	}

	public static boolean isNullValueString(String str) {
		boolean result = false;
		if(str == null) {
			result = true;
		}
		return result;
	}
	
	public static String iso_8859ToEuc_Kr(String str) {
		try {
			str = new String(str.getBytes("8859_1"), "EUC-KR");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String convertLineDelimiter(String str, String delimiter) {
		int poscnt = 0;
		while((poscnt = str.indexOf("\n")) != -1) {
			String Left = str.substring(0, poscnt);
			String Right = str.substring(poscnt + 1, str.length());
			str = Left + delimiter + Right;
		}
		return str;
	}
	
	/**
	 * 
	 * @param String
	 *            strHTML
	 * @return String replacedString
	 */
	public static String doUrlFilter(String strHTML, String linkName) {
		String regex = "([\\p{Alnum}]+)://([a-z0-9.\\-&/%=?:@#$(),.+;~\\_]+)";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(removeTagAll(strHTML));
		return m.replaceAll("<a href=\"http://$2\" target=\"_blank\" title=\"http://$2\">" + linkName + "</a>");
	}

	/**
	 * @param strHTML
	 * @return
	 */
	public static String getImgSrc(String strHTML) {
		Pattern image_source_grabber = Pattern.compile("(?i)< *[img][^\\>]*[src] *= *[\"\']{0,1}([^\"\'\\ >]*)");
        Matcher captured = image_source_grabber.matcher(strHTML);
        String src = "";
        while(captured.find()) {
        	src = captured.group(1);
        }
		return src;
	}
	
	/**
	 * 
	 * @param String
	 *            body
	 * @return String cleanString
	 */
	public static String removeTagAll(String body) {
		Pattern p = Pattern.compile("\\<(\\/?)(\\w+)*([^<>]*)>");
		Matcher m = p.matcher(body);
		return m.replaceAll("");
	}

	/**
	 * 
	 * @param text
	 * @return 
	 */
	public static boolean isNumeric(String text) {
		boolean result = true;
		for(int i = 0; i < text.length(); i++) {
			if(!Character.isDigit(text.charAt(i))) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * @param String
	 *            value1, String value2
	 * @return 
	 */
	public static boolean isStringEqual(String value1, String value2) {
		return value1.trim().equals(value2.trim()) ? true : false;
	}

	/**
	 * @param numStr
	 * @return
	 */
	public static String toCommaFormat(String numStr) {
		DecimalFormat df = new DecimalFormat("###,##0");
		String x = df.format(Long.parseLong(numStr)).toString();
		return x;
	}

	/**
	 * @param text
	 * @return
	 */
	public static String escapeXmlReservedChar(String text) {
		StringBuffer sb = new StringBuffer();
		if(text == null) {
			text = "";
		}
		for(int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if(ch == '<') {
				sb.append("&lt;");
			} else if(ch == '>') {
				sb.append("&gt;");
			} else if(ch == '&') {
				sb.append("&amp;");
			} else if(ch == '\'') {
				sb.append("&apos;");
			} else if(ch == '\"') {
				sb.append("&quot;");
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * @param text
	 * @return
	 */
	public static String restoreXmlReservedChar(String text) {
		return text.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&apos;", "'").replaceAll("&quot;", "\"").replaceAll("&amp;", "&");
	}

	/**
	 * @param orgText
	 * @return
	 */
	public static String escapeHtmlTag(String orgText) {
		return orgText.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
	
	public static String escapeSprecialString(String orgText) {

		String pattern = "([\\.,-?!])";
		return orgText.replaceAll(pattern, ""); 
	}	
	
	/**
	 *  change char [ " ] to [ &#34; ]
	 * @param text
	 * @return
	 */
	public static String restoreDoubleQuotation(String text) {
		return text.replaceAll(Pattern.compile("&#34;").toString(), "\"");
	}
	
	/**
	 * change char [ ' ] to [ &#39; ] 
	 * @param text
	 * @return
	 */
	public static String restoreSingleQuotation(String text) {
		return text.replaceAll(Pattern.compile("&#39;").toString(), "\'");
	}
	
	/**
	 * change char [ " ] to [ &#34; ] , char [ ' ] to [ &#39; ] 
	 * @param text
	 * @return
	 */
	public static String restoreQuotation(String text) {
		return restoreDoubleQuotation(restoreSingleQuotation(text));
	}
	
	/**
	 *  change char [ " ] to [ &#34; ]
	 * @param text
	 * @return
	 */
	public static String escapeDoubleQuotation(String text) {
		return text.replaceAll(Pattern.compile("\"").toString(), "&#34;");
	}
	
	/**
	 * change char [ ' ] to [ &#39; ] 
	 * @param text
	 * @return
	 */
	public static String escapeSingleQuotation(String text) {
		return text.replaceAll(Pattern.compile("\'").toString(), "&#39;");
	}
	
	/**
	 * change char [ " ] to [ &#34; ] , char [ ' ] to [ &#39; ] 
	 * @param text
	 * @return
	 */
	public static String escapeQuotation(String text) {
		return escapeDoubleQuotation(escapeSingleQuotation(text));
	}
	
	/**
	 * remove SQL remark [ -- ] 
	 * @param text
	 * @return
	 */
	public static String escapeSQLRemark(String text) {
		return text.replaceAll(Pattern.compile("--").toString(), "");
	}
	
	/**
	 * remove SQL remark and single quotation.
	 * @param text
	 * @return
	 */
	public static String escapeSQLInjectionChar(String text) {
		return escapeDoubleQuotation(escapeSingleQuotation(escapeSQLRemark(text)));
	}
	

	/**
	 * @param orgStr
	 * @param insStr
	 * @param format
	 * @return
	 */
	public static String simpleStringFormat(String orgStr, String insStr, String format, String formatDelimiter) {
		StringBuffer convStr = new StringBuffer();
		if("".equals(formatDelimiter))
			formatDelimiter = "_";
		StringTokenizer st = new StringTokenizer(format, formatDelimiter);
		if(!"".equals(orgStr.trim())) {
			int indexSize = 0;
			int formatSize = 0;
			while(st.hasMoreTokens()) {
				try {
					formatSize = Integer.parseInt(st.nextToken());
					convStr.append(orgStr.substring(indexSize, indexSize + formatSize));
				} catch (NumberFormatException ne) {
					break;
				} catch (StringIndexOutOfBoundsException e) {
					convStr.append(orgStr.substring(indexSize, orgStr.length()));
					break;
				}
				indexSize += formatSize;
				if(st.hasMoreTokens() && indexSize < orgStr.length())
					convStr.append(insStr);
			}
		} else {
			convStr.append(orgStr);
		}
		return convStr.toString();
	}

	public static String simpleStringFormat(String orgStr, String insStr, String format) {
		return simpleStringFormat(orgStr, insStr, format, "_");
	}
	
	/**
	 * @param text
	 * @param delimiter
	 * @return list
	 */
	public static ArrayList<String> stringTokenizerToArrayList(String text, String delimiter) {
		StringTokenizer tokens = new StringTokenizer(text, delimiter);
		ArrayList<String> list = new ArrayList<String>();
		while(tokens.hasMoreTokens()) {
			list.add(tokens.nextToken());
		}
		return list;
	}
	
	public static String stringResize(String string, int limit, String tailString) {
		String result = "";
		try {
			result = string.substring(0, limit) + tailString;
		} catch (StringIndexOutOfBoundsException e) {
			result = string;
		}
		return result;
	}
	
	public static String dateFormat(Date date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String dateFormat(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static String dateTimeFormat(String yyyyMMddHHmmss) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		df.setLenient(false);
		Date date = new Date();
		try {
			date = df.parse(yyyyMMddHHmmss);
			return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			return yyyyMMddHHmmss;
		} catch (IllegalArgumentException ae) {
			return yyyyMMddHHmmss;
		}
	}
	
	public static String dateFormat(String yyyyMMdd) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		df.setLenient(false);
		Date date = new Date();
		try {
			date = df.parse(yyyyMMdd);
			return dateFormat(date, "yyyy-MM-dd");
		} catch (ParseException e) {
			return yyyyMMdd;
		} catch (IllegalArgumentException ae) {
			return yyyyMMdd;
		} catch (NullPointerException ne) {
			return null;
		}
	}
	
	
	public static String replaceAll(String orgStr, String obj, String tgt) {
		return orgStr.replaceAll(Pattern.compile(obj).toString(), tgt);
	}
}
