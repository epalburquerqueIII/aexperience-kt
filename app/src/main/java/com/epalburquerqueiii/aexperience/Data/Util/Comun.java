package com.epalburquerqueiii.aexperience.Data.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comun {
    public static String datetoStringsql(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        try {
            return sdf.format(date);
        } catch (Exception e1) {
            // TODO: handle exception
            return "Nan";
        }
    }

    public static Date codetoDate(int day,int month,int year) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        try {
            return sdf.parse(String.format("%04d/%02d/%02d", year,month,day));
        } catch (Exception e1) {
            return null;
        }

    }
}
