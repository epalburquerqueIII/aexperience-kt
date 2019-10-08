package com.epalburquerqueiii.aexperience.Data.Util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

class Comun {

    companion object {

        fun StringdmytoDate(fecha:String):Date? {
            //'4/11/2019'


            try {
                return SimpleDateFormat("dd-MM-yyyy").parse(fecha)
            } catch (e1: Exception) {
                return null
            }
            // necesita api26  descartamos      var date = LocalDate.parse(fecha, formatter)
        }

        fun CodetoDate(year:Int, month:Int, day:Int):Date?
        {

            val sdf:SimpleDateFormat = SimpleDateFormat("yyyy/mm/dd");
            try {
                return sdf.parse(String.format("%02d/%02d/%04d", year, month, day))
            } catch (e1:Exception ) {
                return null
            }

        }

        fun DatetoStringsql(date: Date?): String {
            val sdf: SimpleDateFormat = SimpleDateFormat("yyyy/mm/dd");
            try {
                return sdf.format(date)
            } catch (e1: Exception) {
                // TODO: handle exception
                return "Nan"
            }
        }

    }


}