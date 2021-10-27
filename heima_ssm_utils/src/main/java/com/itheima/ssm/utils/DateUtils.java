package com.itheima.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zero
 */
public class DateUtils {


    /**
     * 日期转字符串
     * @param date
     * @param patt
     * @return
     */
    public static String dateToString(Date date, String patt){
        SimpleDateFormat sd = new SimpleDateFormat(patt);
        String format = sd.format(date);
        return format;
    }

    /**
     * 字符串转日期
     * @param str
     * @param patt
     * @return
     * @throws Exception
     */
    public static Date stringToDate(String str, String patt) throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat(patt);
        Date date = sd.parse(str);
        return date;
    }
}
