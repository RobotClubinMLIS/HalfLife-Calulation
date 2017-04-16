package com.xuzhu.HalfLifeCalculator;

/**
 * Created by changfeng on 2016/10/9 18:36.
 */


import android.text.TextUtils;

import java.math.BigDecimal;


/**
 * 浮点数精确计算工具类
 */
public class ArithmeticUtil {

    /**
     * 对一个数字取精度
     *
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            scale = 0;
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(v1));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(v2));
        return bigDecimal1.add(bigDecimal2).doubleValue();
    }

    /**
     * 精确加法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double addWithScale(double v1, double v2, int scale) {
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(v1));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(v2));
        return bigDecimal1.add(bigDecimal2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }


    /**
     * 精确乘法，保留scale位小数
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double mulWithScale(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            scale = 0;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 取余数
     *
     * @param v1
     * @param v2
     * @return
     */
    public static int remainder(float v1, float v2) {
        return Math.round(v1 * 100) % Math.round(v2 * 100);
    }

    /**
     * 比较大小 如果v1 大于v2 则 返回true 否则false
     *
     * @param v1
     * @param v2
     * @return
     */
    public static boolean strCompareTo(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj > 0)
            res = true;
        else
            res = false;
        return res;
    }

    public static String Fen2Yuan(int fen){
        String s = String.valueOf(fen);
        int length = s.length();
        String substring = s.substring(0, length - 2);
        String substring1 = s.substring(length - 2);
        String finalS = substring + "." + substring1;
        return finalS;
    }


    /**
     * 根据手输入的金额（元），转化int的分
     *
     * @param inputNumber
     * @return
     */
    public static int getInputNumber(String inputNumber) {
        if (TextUtils.isEmpty(inputNumber)) {
            return 0;
        }
        if (inputNumber.contains(".")) {
            int i = inputNumber.indexOf(".");
            int length = inputNumber.length() - i - 1;
            if (length == 1) {
                return Integer.parseInt(inputNumber.replace(".", "") + 0);
            } else {
                return Integer.parseInt(inputNumber.replace(".", ""));
            }
        } else {
            int i = Integer.parseInt(inputNumber);
            if (i < 0) {
                return 0;
            }
            return Integer.parseInt(inputNumber + "00");
        }

    }

    /**
     * 根据输入的int分获取string的元
     *
     * @param fen
     * @return
     */
    public static String fen2Yuan(int fen) {

        if (fen < 0) {
            return "-" + fen2Yuan1(Math.abs(fen));
        } else {
            return fen2Yuan1(fen);
        }

    }


    public static String fen2Yuan(String fenS) {
        if (TextUtils.isEmpty(fenS)) {
            return "";
        }
        try {
            int fen = Integer.parseInt(fenS);
            return fen2Yuan(fen);
        } catch (Exception e) {
            return "";
        }
    }

    public static String fen2Yuan1(int fen) {
        if (fen == 0) {
            return "0.00";
        } else if (fen < 9) {
            return "0.0" + fen;
        } else if (fen < 99) {
            return "0." + fen;
        } else {
            String s = String.valueOf(fen);
            int length = s.length();
            String substring = s.substring(0, length - 2);
            String substring1 = s.substring(length - 2);
            String finalS = substring + "." + substring1;
            return finalS;
        }
    }




}

