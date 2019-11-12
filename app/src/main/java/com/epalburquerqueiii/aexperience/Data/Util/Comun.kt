package com.epalburquerqueiii.aexperience.Data.Util


import java.text.SimpleDateFormat;
import java.util.Date;

public class Comun {

    companion object {

        fun CodetoDate(day: Int, month: Int, year: Int): Date {

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            try {
                return sdf.parse(String.format("%04d-%02d-%02d", year, month, day))
            } catch (e1: Exception) {
                return sdf.parse(String.format(""))
            }

        }

        fun DatetoStringsql(date: Date): String {

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
            if (fecha != "") {
                val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(fecha)
                return DatetoString(date)
            } else {
                return ""
            }
        }

        fun DatetoString(date: Date): String {

            val sdf = SimpleDateFormat("dd-MM-yyyy")
            try {
                return sdf.format(date)
            } catch (e1: Exception) {
                // TODO: handle exception
                return "Nan"
            }

        }

        fun validarEmail(Value: String): Boolean {

            //Math.abs da el valor absoluto de una operacion, por ej. el v. abs de (-1) es 1.
            //Value.length te devuelve la longitud de la cadena.
            //Value.indexOf te devuelve la posicion de un caracter en una cadena.
            return (Value.indexOf("@") != -1 && Value.indexOf(".") != -1
                    && Math.abs(Value.indexOf("@") - Value.indexOf(".")) > 1
                    && Value.length - Value.indexOf(".") > 2)

        }

    }
}
