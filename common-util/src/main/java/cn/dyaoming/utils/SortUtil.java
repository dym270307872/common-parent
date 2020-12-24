package cn.dyaoming.utils;

import java.util.Arrays;

/**
 * <p>
 * 排序工具类
 * </p>
 * 
 * @author DYAOMING
 * @since 2020/12/23
 * @version 0.0.1
 */
public class SortUtil {


    /**
     * 功能描述：字符串排序方法。
     * 
     * @param target String类型 要处理的字符串
     * @param isAsc 是否升序
     * @return String类型 返回字符串
     */
    public static String sortStr(String target, boolean isAsc) {
        if (StringUtil.isBlank(target)) { return ""; }
        char[] chars = target.toCharArray();
        Arrays.sort(chars);
        if (!isAsc) {
            reverse(chars);
        }
        return chars.toString().trim();
    }



    /**
     * <p>
     * 字符数组倒叙方法
     * </p>
     * 
     * @param arr 字符数组
     */
    private static void reverse(char[] arr) {
        int size = arr.length;
        for(int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--) {
            swap(arr, i, j);
        }
    }



    /**
     * <p>
     * 数组内字符交换方法
     * </p>
     * 
     * @param arr 字符数组
     * @param i 交换左下标
     * @param j 小环右下标
     */
    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
