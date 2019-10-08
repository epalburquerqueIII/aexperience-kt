package com.epalburquerqueiii.aexperience.Data.util



import java.text.SimpleDateFormat
import java.util.Date

class Comun {

    companion object {

        fun codetoDate(day: Int, month: Int, year: Int): Date {

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            try {
                return sdf.parse(String.format("%04d-%02d-%02d", year, month, day))
            } catch (e1: Exception) {
                 return sdf.parse(String.format(""))
            }

        }

        fun datetoStringsql(date: Date): String {

            val sdf = SimpleDateFormat("yyyyMMdd")
            try {
                return sdf.format(date)
            } catch (e1: Exception) {
                // TODO: handle exception
                return "Nan"
            }

        }

//2019-01-01
        fun StringYMDtoDMY(fecha: String?): String {
            val date:Date = SimpleDateFormat("yyyy-MM-dd").parse(fecha)
            return datetoString(date)
        }


        fun datetoString(date: Date): String {

            val sdf = SimpleDateFormat("dd-MM-yyyy")
            try {
                return sdf.format(date)
            } catch (e1: Exception) {
                // TODO: handle exception
                return "Nan"
            }

        }
        }
    }
