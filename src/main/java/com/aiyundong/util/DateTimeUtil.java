package com.aiyundong.util;


import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * Created by XJY on 2016/1/26.
 */
public class DateTimeUtil {

    public final static String FORMAT_SHORT = "yyyy-MM-dd";
    public final static String FORMAT_SHORT2 = "yyyyMMdd";//后台使用
    public final static String FORMAT_SHORT3 = "yyyy";
    public final static String FORMAT_SHORT4 = "yyyy.MM.dd";
    public final static String FORMAT_SHORT_YM = "yyyyMM";
    public final static String FORMAT_LONG_0 = "yyyy-MM-dd 00:00:00";//后台使用
    public final static String FORMAT_LONG_23 = "yyyy-MM-dd 23:59:59";//后台使用
    public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_LONG2 = "yyyyMMddHHmmss";
    public final static String FORMAT_LONG3 = "yyyyMMddHHmmssSSS";
    public final static String FORMAT_SHORT_CN = "yyyy年MM月dd日";
    public final static String FORMAT_SHORT_ZN = "MM月dd日HH时mm分";


    // public final static String FORMAT_SHORT_Y_M = "yyyy-MM";
    // public final static String FORMAT_SHORTEST = "MM-dd";
    // public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    // public final static String FORMAT_SPECIAL_ZN = "yyyy年MM月dd HH:mm";

    /**
     * 字符串按指定格式转化为date(字符串日期格式一致)
     *
     * @param timeStr
     * @param pattern
     * @return
     */
    public static Date parseStrToDate(String timeStr, String pattern) {
        if (timeStr != null && !"".equals(timeStr)) {
            return DateTime.parse(timeStr, DateTimeFormat.forPattern(pattern)).toDate();
        }
        return null;
    }

    /**
     * date按照指定格式转化为str
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String parseDateToStr(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }


    /**
     * 当前日期加数月
     *
     * @param date
     * @param num
     * @return
     */

    public static Date addMonths(Date date, int num) {
        return new DateTime(date).plusMonths(num).toDate();
    }

    /**
     * 当前日期加数月
     */

    public static String addMonths(String str, int num) {
        DateTime dt = DateTime.parse(str, DateTimeFormat.forPattern(FORMAT_SHORT));
        return dt.plusMonths(num).toString(FORMAT_SHORT);
    }

    /**
     * 当前日期加数日
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDays(Date date, int num) {
        return new DateTime(date).plusDays(num).toDate();
    }


    /**
     * 当前日期加数小时
     */
    public static String addHourStr(int num, String pattern) {

        return  new DateTime().plusHours(num).toString(pattern);
    }

    public static Date addMinutes(Date date, int num) {
        return new DateTime(date).plusMinutes(num).toDate();
    }


    /**
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return parseDateToStr(new Date(), FORMAT_SHORT);
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return parseDateToStr(new Date(), format);
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期时间Time值
     *
     * @return
     */
    public static long getNowTime() {

        return new DateTime().getMillis();
    }


    /**
     * 判断是否是周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        DateTime dt = new DateTime(date);
        int dayOfWeek = dt.getDayOfWeek();
        return (dayOfWeek == DateTimeConstants.SATURDAY || dayOfWeek == DateTimeConstants.SUNDAY);
    }

    /**
     * 只后台用: 获取指定日期是星期几
     *
     * @param date 指定日期
     * @return 返回星期几的描述
     */
    public static String getWeekdayDesc(Date date) {
        final String[] weeks = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        DateTime dt = new DateTime(date);
        int dayOfWeek = dt.getDayOfWeek();
        return weeks[dayOfWeek - 1];
    }


    /**
     * 获得年第一天的日期
     *
     * @param date
     * @return
     */
    public static String getYearFirst(Date date) {
        DateTime dt = new DateTime(date);
        String firstday = dt.dayOfYear().setCopy(1).toString(FORMAT_SHORT);
        return firstday;
    }

    /**
     * 得到本月第一天的日期
     *
     * @return Date
     * @Methods Name getFirstDayOfMonth
     */
    public static Date getFirstDayOfMonth(Date date) {
        DateTime dt = new DateTime(date);
        return dt.dayOfMonth().setCopy(1).toDate();
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String dateZNFormat(Date date) {
        return parseDateToStr(date, FORMAT_SHORT_ZN);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String dataFormat(Date date) {
        return parseDateToStr(date, FORMAT_SHORT_CN);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String formats(Date date) {
        return parseDateToStr(date, FORMAT_SHORT);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return parseDateToStr(date, FORMAT_LONG);
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    // 使用用户格式格式化日期
    public static String format(Date date, String pattern) {
        if (date != null && pattern != null) {
            return parseDateToStr(date, pattern);
        }
        return null;
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, FORMAT_LONG);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        return parseStrToDate(strDate, pattern);
    }


    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return parseDateToStr(date, FORMAT_SHORT3);
    }


    /**
     * 计算两个时间的差值
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDateHHMMSS(Date startDate, Date endDate) {
        Period p = new Period(new DateTime(startDate), new DateTime(endDate));
        return new String(p.getDays() + "天" + p.getHours() + "小时" + p.getMinutes() + "分钟" + p.getSeconds() + "秒");
    }

    /**
     * 计算两个时间的差值
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDateSS(Date startDate, Date endDate) {
        long starttime = startDate.getTime();
        long endtime = endDate.getTime();
        long seconds = endtime - starttime;
        return String.valueOf(seconds/1000);
    }

    /**
     * 把秒变成分秒计算
     *
     * @return
     */
    public static String changeSS2MS(long duration_s) {
        long mm = 0;
        long ss = 0;
        if (duration_s != 0) {
            mm = duration_s / 60;
            ss = duration_s % 60;
        }
        StringBuilder str = new StringBuilder("通话时长");
        if (mm != 0) {
            str.append(mm + "分 ");
        }
        if (ss != 0) {
            str.append(ss + "秒");
        }
        return str.toString();
    }

    /**
     * 计算两个时间的差值
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonth(Date startDate, Date endDate) {
        DateTime sDate = new DateTime(startDate);
        DateTime eDate = new DateTime(endDate);
        return Months.monthsBetween(sDate, eDate).getMonths();
    }

    /**
     * 计算两个时间的差值
     * @param startDate
     * @return
     */
    public static int getMonth(Date startDate) {
        return getMonth(startDate, new Date());
    }

    /**计算两日期间天数差
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDays(Date beginDate, Date endDate) {
        return subDay(beginDate, endDate);
    }


    public static int subDay(Date beginDate, Date endDate) {
        DateTime begin = new DateTime(beginDate);
        DateTime end = new DateTime(endDate);
        return Days.daysBetween(begin, end).getDays();
    }

    public static int subDay(String begin, String end) {
        Date fromDate = parseStrToDate(begin, FORMAT_SHORT);
        Date toDate = parseStrToDate(end, FORMAT_SHORT);
        return subDay(fromDate, toDate);
    }

    /**
     * 按用户格式字符串距离今天的天数
     */
    public static int countDays(String str, String pattern) {
        Date fromDate = parseStrToDate(str, pattern);
        return subDay(fromDate, new Date());
    }


    /**
     * 日期时间比较大小 前一个时间大于后一个时间返回1，小于返回-1，等于返回0
     */
    public static int compare_date(String before, String after) {
        Date fromDate = parseStrToDate(before, FORMAT_LONG);
        Date toDate = parseStrToDate(after, FORMAT_LONG);
        return fromDate.compareTo(toDate);
    }

    /**
     * 判断是否在可投资时间
     */
    public static int CheckDebtInvestIime() {
        DateTime current = new DateTime();
        int year = current.yearOfEra().get();
        int month = current.monthOfYear().get();
        int day = current.dayOfMonth().get();
        DateTime min = new DateTime(year, month, day, 0, 0, 0, 0);
        DateTime max = new DateTime(year, month, day, 1, 0, 0, 0);
        Interval i = new Interval(min, max);
        if (i.contains(current)) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int checkAddRate(Date addRateBegin, Date addRateEnd) {
        if (addRateBegin != null && addRateEnd != null) {
            DateTime begin = new DateTime(addRateBegin);
            DateTime end = new DateTime(addRateEnd);
            DateTime now = new DateTime();
            if (new Interval(begin, end).contains(now)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     *  @Title: diffDate
     *  @Description: 计算2个时间差值，返回毫秒数
     *  @author Yangjingchen
     *  @param addBegin
     *  @param addEnd
     *  @return long
     */
    public static long diffDate(Date addBegin,Date addEnd) {

        long begin = addBegin.getTime();
        long end = addEnd.getTime();
        return end - begin;
    }

    /*
    * 判断两日期是不是同一天
    * */
    public static boolean sameDate(Date date1, Date date2) {
        Boolean same = false;
        String data1_str = format(date1,FORMAT_SHORT);
        String data2_str = format(date2,FORMAT_SHORT);
        if (data1_str.equals(data2_str)) {
            same = true;
        }
        return same;
    }


}
