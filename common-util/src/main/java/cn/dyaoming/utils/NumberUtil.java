package cn.dyaoming.utils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NumberUtil {

	/**
	 * 单位进位，中文默认为4位即（万、亿）
	 */
	private static final int			UNIT_STEP	= 4;

	/**
	 * 单位
	 */
	private static final String[]		CN_UNITS	= new String[] { "", "十", "百", "千", "万", "十",
			"百", "千", "亿", "十", "百", "千",
			"万" };

	/**
	 * 汉字
	 */
	private static final String[]		CN_CHARS	= new String[] { "零", "一", "二", "三", "四", "五",
			"六", "七", "八", "九" };

	/**
	 * 繁体金额单位
	 */
	private static final String[]		CNFT_UNITS	= new String[] { "分", "角", "元", "拾", "佰", "仟",
			"万", "拾", "佰", "仟", "亿",
			"拾", "佰", "仟", "万" };

	/**
	 * 繁体汉字
	 */
	private static final String[]		CNFT_CHARS	= new String[] { "零", "壹", "贰", "叁", "肆", "伍",
			"陆", "柒", "捌", "玖" };

	private static Map<String, Integer>	template	= new HashMap<String, Integer>();
	static {
		template.put("I", 1);
		template.put("V", 5);
		template.put("X", 10);
		template.put("L", 50);
		template.put("C", 100);
		template.put("D", 500);
		template.put("M", 1000);
	}



	public static Integer romanToArab(String param) throws ArithmeticException {
		Integer arab = 0;
		String[] s_param = param.split("");
		for(int i = 0; i < s_param.length; i++) {
			if (i < s_param.length - 1 && template.get(s_param[i]) < template.get(s_param[i + 1])) {
				arab -= template.get(s_param[i]);
			} else {
				arab += template.get(s_param[i]);
			}
		}

		return arab;
	}



	public static String arabToRoman(Integer arab) throws ArithmeticException {
		if (arab < 0 || arab > 3999) { throw new ArithmeticException("超出运算范围0~3999"); }

		String roman = "";
		String[][] list = { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "", "M", "MM", "MMM", "", "", "", "", "", "" } };
		roman += list[3][arab / 1000 % 10];
		roman += list[2][arab / 100 % 10];
		roman += list[1][arab / 10 % 10];
		roman += list[0][arab % 10];
		return roman;
	}



	/**
	 * 数值转换为中文字符串(普通)
	 * 
	 * @param num 需要转换的数值
	 * @return String类型 转换后中文字符串
	 */
	public static String arabToCn(long num) {
		return arabToCn(num, false, false);
	}



	/**
	 * 数值转换为中文字符串(口语化)
	 * 
	 * @param num 需要转换的数值
	 * @return  String类型 转换后中文字符串
	 */
	public static String arabToCnForColloquialism(long num) {
		return arabToCn(num, true, false);
	}



	/**
	 * 金额转换为中文金额大写
	 * 
	 * @param amount BigDecimal类型 需要转换的数值
	 * @return String类型 转换后中文字符串
	 */
	public static String arabToCnForMoney(BigDecimal amount) {
		long num = Long.parseLong(
				amount.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
						.toBigInteger().toString());
		return arabToCn(num, false, true);
	}



	/**
	 * 将数值转换为中文
	 * 
	 * @param num 需要转换的数值
	 * @param isColloquial 是否口语化。例如12转换为'十二'而不是'一十二'。
	 * @param isMoney 是否是金额。
	 * @return String类型 转换后中文字符串
	 */
	private static String arabToCn(long num, boolean isColloquial, boolean isMoney)
			throws ArithmeticException {
		String[] char_dw = CN_UNITS;
		String[] char_gw = CN_CHARS;
		if (isMoney) {
			char_dw = CNFT_UNITS;
			char_gw = CNFT_CHARS;
		}

		if (num < 10) {// 10以下直接返回对应汉字
			return new String(char_gw[(int) num] + char_dw[0]);// ASCII2int
		}

		char[] chars = String.valueOf(num).toCharArray();
		if (chars.length > char_dw.length) {// 超过单位表示范围的返回空
			throw new ArithmeticException("超过单位表示范围");
		}

		boolean isLastUnitStep = false;// 记录上次单位进位
		ArrayList<String> cnchars = new ArrayList<String>(chars.length * 2);// 创建数组，将数字填入单位对应的位置
		for(int pos = chars.length - 1; pos >= 0; pos--) {// 从低位向高位循环
			char ch = chars[pos];
			String cnChar = char_gw[ch - '0'];// ascii2int 汉字
			int unitPos = chars.length - pos - 1;// 对应的单位坐标
			String cnUnit = char_dw[unitPos];// 单位

			boolean isZero = (ch == '0');// 是否为0
			boolean isZeroLow = (pos + 1 < chars.length && chars[pos + 1] == '0');// 是否低位为0

			boolean isUnitStep = false;
			if (isMoney) {
				isUnitStep = (unitPos < 3 || ((unitPos - 2) % UNIT_STEP == 0));// 当前位是否需要单位进位
			} else {

				isUnitStep =  (unitPos % UNIT_STEP == 0);// 当前位是否需要单位进位

			}

			if (isUnitStep && isLastUnitStep) {// 去除相邻的上一个单位进位
				int size = cnchars.size();
				cnchars.remove(size - 1);
				if (size >= 2) {
					if (!char_gw[0].equals(cnchars.get(size - 2))) {// 补0
						cnchars.add(char_gw[0]);
					}
				}
			}

			if (isUnitStep || !isZero) {// 单位进位(万、亿)，或者非0时加上单位
				cnchars.add(cnUnit);
				isLastUnitStep = isUnitStep;
			}
			if (isZero && (isZeroLow || isUnitStep)) {// 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
				continue;
			}
			// }

			cnchars.add(cnChar);
			isLastUnitStep = false;
		}

		Collections.reverse(cnchars);

		// 清除最后一位的0
		int chSize = cnchars.size();
		String chEnd = cnchars.get(chSize - 1);
		if (char_gw[0].equals(chEnd) || (char_dw[0].equals(chEnd) && !isMoney)) {
			cnchars.remove(chSize - 1);
		}
		// 整数金额处理
		if (isMoney) {
			if ("元".equals(chEnd)) {// 是否以'元'结尾，补充“整”
				cnchars.add("整");
			}
		}

		// 口语化处理
		if (isColloquial) {
			String chFirst = cnchars.get(0);
			String chSecond = cnchars.get(1);
			if (chFirst.equals(char_gw[1]) && chSecond.startsWith(char_dw[1])) {// 是否以'一'开头，紧跟'十'
				cnchars.remove(0);
			}
		}

		StringBuffer strs = new StringBuffer(32);
		for(String str : cnchars.toArray(new String[] {})) {
			strs.append(str);
		}
		return strs.toString();
	}



	/**
	 * 将中文转换为数字
	 * 
	 * @param cn 需要转换的数值
	 * @return long类型 转换后阿拉伯数字
	 */

	public static long cnToArab(String cn) throws ArithmeticException {
		List<Long> jg = new ArrayList<Long>();

		if (cn.indexOf("亿") != -1) {
			String[] spy = cn.split("亿");
			if (spy.length == 2) {
				jg.add(cnToArab(spy[0]) * 10000 * 10000);
				cn = spy[1];
			} else if (spy.length == 1) {
				jg.add(cnToArab(spy[0]) * 10000 * 10000);
				cn = "";
			} else {
				throw new ArithmeticException("格式错误");
			}
		}
		if (cn.indexOf("万") != -1) {
			String[] spwq = cn.split("万");
			if (spwq.length == 2) {
				jg.add(cnToArab(spwq[0]) * 10000);
				cn = spwq[1];
			} else if (spwq.length == 1) {
				jg.add(cnToArab(spwq[0]) * 10000);
				cn = "";
			} else {
				throw new ArithmeticException("格式错误");
			}
		}

		if (!"".equals(cn)) {

			List<String> l_cn = Arrays.asList(CN_CHARS);

			int[] sum = { 0, 0, 0, 0 };
			String[] cnt = cn.split("");
			for(int i = 0; i < cnt.length; i++) {
				if ("千".equals(cnt[i])) {
					sum[0] = l_cn.indexOf(cnt[i - 1]);
				} else if ("百".equals(cnt[i])) {
					sum[1] = l_cn.indexOf(cnt[i - 1]);
				} else if ("十".equals(cnt[i])) {
					if ("十".equals(cnt[0]) || ("零".equals(cnt[0]) && l_cn.indexOf(cnt[1]) == -1)
							|| "零".equals(cnt[i - 1])) {
						sum[2] = 1;
					} else {
						sum[2] = l_cn.indexOf(cnt[i - 1]);
					}
				}
			}

			if (l_cn.indexOf(cnt[cnt.length - 1]) != -1) {
				sum[3] = l_cn.indexOf(cnt[cnt.length - 1]);
			} else {
				sum[3] = 0;
			}

			jg.add((long) (sum[0] * 1000 + sum[1] * 100 + sum[2] * 10 + sum[3]));
		}
		return jg.stream().mapToLong(Long::valueOf).sum();
	}

}
