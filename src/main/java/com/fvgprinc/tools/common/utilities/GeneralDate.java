/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.common.utilities;

import com.fvgprinc.tools.common.string.MyCommonString;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fvargas
 */
public class GeneralDate {

    public static final double COMERCIALYEARDAYS = 360.0;
    public static final double NATURALYEARDAYS = 365.0;
    public static final int MESCOMERNUMDIAS = 30;

    public static enum ArithmeticalDaysMethods {

        z30_360ArithDays,
        z30_360USArithDays,
        z30_360EuroArithDays,
        z365ArithDays
    }
    static final int JANUARY = 1;
    static final int FEBRUARY = 2;
    static final int MARCH = 3;
    static final int APRIL = 4;
    static final int MAY = 5;
    static final int JUNE = 6;
    static final int JULY = 7;
    static final int AUGUST = 8;
    static final int SEPTEMBER = 9;
    static final int OCTOBER = 10;
    static final int NOVEMBER = 11;
    static final int DECEMBER = 12;
    static double[] daysOfMonth365 = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] nameOfMonthSp = {"Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Setiembre", "Octubre", "Noviembre", "Diciembre"};
    static String[] nameOfMonthEng = {"JANUARY", "FEBRUARY", "MARCH", "APRIL",
        "MAY", "JUNE", "JULY", "AUGUST",
        "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
    public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS * 60;
    public final static long HOUR_MILLIS = MINUTE_MILLIS * 60;
    public final static long DAY_MILLIS = HOUR_MILLIS * 24;
    public final static long YEAR_MILLIS = DAY_MILLIS * 365;
    public static final String DATEFORMATCRC = "dd/MM/yyyy";    // formato estandar

    /**
     *
     * @param year
     * @return true if parameter "year" is leap
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * Get the number day of date
     *
     * @param pDate
     * @return
     */
    public static int getNumberDay(java.sql.Date pDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int day = Integer.parseInt(sdf.format(pDate));
        return day;
    }

    public static int getNumberMonth(java.sql.Date pDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        int day = Integer.parseInt(sdf.format(pDate));
        return day;
    }

    public static int getNumberYear(java.sql.Date pDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        int day = Integer.parseInt(sdf.format(pDate));
        return day;
    }

    /**
     *
     * @param pmonth
     * @param pyear
     * @return days of Month. (ie. 1 (January) => 31, 2 (february) => 28, etc.)
     */
    public static double daysOfMonth(int pmonth, int pyear) {
        double res;
        int index;

        index = pmonth - 1; // adjunts access to array dayOfMonth365
        res = daysOfMonth365[index];
        if (pmonth == FEBRUARY) { // february
            res = (isLeapYear(pyear) ? 29 : 28);
        }

        return res;
    }

     public static int dayOfWeek(int day, int month, int year) {

        int res = 0;
        /*
            int[] t = { 0, 3, 2, 5, 0, 3, 5, 
                      1, 4, 6, 2, 4 };
            
            year -= ((month < 3) ? 1 : 0);

            res = (year + year / 4 - year / 100 + year / 400
                             + t[month - 1] + day) % 7;
        */
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month , day + 1);
         System.out.println(cal.getTime());
        res = cal.get(Calendar.DAY_OF_WEEK)  - 1;
        
        return res;
    }

    // Function that returns the name of the 
    // month for the given month Number 
    // January - 0, February - 1 and so on 
    // language = 1 Spanish  = 2 = English
    public static String getMonthName(int monthNumber, int language) {
        String month;

        if (language ==1) {
            month = nameOfMonthSp[monthNumber];
        } else {
            month = nameOfMonthEng[monthNumber];
        }

        return month;
    }
    
            // Function to return the number of days 
        // in a month 
        public static int numberOfDays(int monthNumber, int year)
        {
            // January 
            if (monthNumber == 0)
                return (31);

            // February 
            if (monthNumber == 1)
            {
                // If the year is leap then Feb 
                // has 29 days 
                if (year % 400 == 0
                    || (year % 4 == 0
                        && year % 100 != 0))
                    return (29);
                else
                    return (28);
            }

            // March 
            if (monthNumber == 2)
                return (31);

            // April 
            if (monthNumber == 3)
                return (30);

            // May 
            if (monthNumber == 4)
                return (31);

            // June 
            if (monthNumber == 5)
                return (30);

            // July 
            if (monthNumber == 6)
                return (31);

            // August 
            if (monthNumber == 7)
                return (31);

            // September 
            if (monthNumber == 8)
                return (30);

            // October 
            if (monthNumber == 9)
                return (31);

            // November 
            if (monthNumber == 10)
                return (30);

            // December 
            if (monthNumber == 11)
                return (31);

            return -1;
        }

    /**
     *
     * @param d
     * @return convert a date
     */
    public static double date2DayOfYear(Date d) {
        double res = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("mm");

        int month = Integer.parseInt(sdf.format(d));
        sdf.applyLocalizedPattern("dd");
        int day = Integer.parseInt(sdf.format(d));
        sdf.applyLocalizedPattern("yyyy");
        int year = Integer.parseInt(sdf.format(d));

        if (month == JANUARY) {  // january
            res = day;
        } else {
            for (int i = JANUARY; i < month; i++) {
                res += daysOfMonth(i, year);
            }
            res += day;  // adjust final day
        }

        return res;
    }

    /**
     * Get the current datetime in java.sql.Timestamp type
     *
     * @return the current java.sql.Timestamp
     */
    public static java.sql.Timestamp getCurrentSqlTimeStamp() {
        java.util.Date dateRef = new java.util.Date();
        java.sql.Timestamp currDate = new java.sql.Timestamp(dateRef.getTime());
        return currDate;
    }

    /**
     * Returns the current time in java.sql.Timestamp type
     *
     * @return
     * @throws ParseException
     * @deprecated this type is deprecated use getCurrentSqlTimeStamp instead
     */
    public static java.sql.Timestamp convertCurrDate2Timestamp() throws ParseException {
        Calendar curDate = Calendar.getInstance();
        java.sql.Timestamp res;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String strDate = formatter.format(curDate.getTime());
        res = convertStrDate2SqlTimeStamp(strDate);
        return res;
    }

    /*
     * Requiere pUtilDate created
     */
    public static String convertJavaUtilDate2Str(java.util.Date pDate) {
        DateFormat df = new SimpleDateFormat(DATEFORMATCRC);
        String res;

        res = df.format(pDate);

        return res;
    }

    public static java.util.Date convertStr2JavaUtilDate(String pStrDate) throws ParseException {
        java.util.Date res;
        SimpleDateFormat df = new SimpleDateFormat(DATEFORMATCRC);

        res = df.parse(pStrDate);
        return res;
    }

    public static java.sql.Date convertJavaUtilDate2SqlDate(java.util.Date pUtilDate) {
        java.sql.Date res = new java.sql.Date(pUtilDate.getTime());

        return res;
    }

    /**
     * Convert current dateTime to sql.Date
     *
     * @return
     * @deprecated do not use this method, use getCurrentSqlDate
     */
    public static java.sql.Date convertCurrDateTime2SqlDate() {
        java.util.Date tmpDate = Calendar.getInstance().getTime();
        java.sql.Date res = convertJavaUtilDate2SqlDate(tmpDate);

        return res;
    }

    /**
     * Convert currentDate (only Date) to sql.Date
     *
     * @return
     * @throws ParseException
     */
    public static java.sql.Date getCurrentSqlDate() throws ParseException {
        java.util.Date tmpDate = Calendar.getInstance().getTime();
        java.sql.Date res = convertJavaUtilDate2SqlDate(tmpDate);

        return res;
    }

    public static String convertSqlDate2String(java.sql.Date pDate) {
        String res = MyCommonString.EMPTYSTR;

        if (pDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMATCRC);//Date format
            java.util.Date utilDate = new java.util.Date(pDate.getTime());// your Sql date value
            res = formatter.format(utilDate);
        }

        return res;
    }

    /**
     * Returns the java.sql.date convertion from pStrDate
     *
     * @param pStrDate String date in format "dd/MM/yyyy"
     * @return conversion of pStrDate to java.sql.Date
     * @throws ParseException
     */
    public static java.sql.Date convertStr2SqlDate(String pStrDate) throws ParseException {
        java.util.Date dateUtl = convertStr2JavaUtilDate(pStrDate);
        java.sql.Date res = convertJavaUtilDate2SqlDate(dateUtl);

        return res;

    }

    /**
     * Convert a stringDate in java.sql.Timestamp.
     *
     * @param pstrDate
     * @return sqlTimestamp valor
     * @throws ParseException
     */
    public static java.sql.Timestamp convertStrDate2SqlTimeStamp(String pstrDate) throws ParseException {
        DateFormat formatter;
        Date date;
        java.sql.Timestamp timeStampDate;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = (Date) formatter.parse(pstrDate);
        timeStampDate = new java.sql.Timestamp(date.getTime());

        return timeStampDate;
    }

    /**
     * Convert
     *
     * @param pTimeStamp
     * @return
     */
    public static String convertTimeStamp2StrDate(java.sql.Timestamp pTimeStamp) {
        DateFormat formatter;
        String res;

        if (pTimeStamp == null) {
            pTimeStamp = getCurrentSqlTimeStamp();
        }
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        res = formatter.format(pTimeStamp);
        return res;
    }

    /**
     * public static java.sql.Timestamp convertStrDate2SqlTimeStamp(String
     * pstrTimeSatmp) throws ParseException { SimpleDateFormat dateFormat = new
     * SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); java.util.Date parsedDate =
     * dateFormat.parse("2006-05-22 14:04:59:612"); java.sql.Timestamp
     * timeStampRes = new java.sql.Timestamp(parsedDate.getTime()); return
     * timeStampRes; }
     *
     */
    public static java.sql.Date getFirstDayMonth(java.sql.Date pCurDate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pCurDate);
        String strDate = "01/" + Integer.toString(cal.get(Calendar.MONTH) + 1)
                + "/" + Integer.toString(cal.get(Calendar.YEAR));
        return GeneralDate.convertStr2SqlDate(strDate);
    }

    /**
     * Get the last day of month
     *
     * @param pDateRef
     * @return
     * @throws ParseException
     */
    public static java.sql.Date getLastDateOfMonth(java.sql.Date pDateRef) throws ParseException {
        java.sql.Date res;

        Calendar cal = Calendar.getInstance();
        cal.setTime(pDateRef);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String strDate = Integer.toString(day) + "/"
                + Integer.toString(month) + "/"
                + Integer.toString(year);

        res = GeneralDate.convertStr2SqlDate(strDate);

        return res;
    }

    public static java.sql.Date addDays365(java.sql.Date refDate, int pNumDays) {
        java.sql.Date res = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(refDate);
        cal.add(Calendar.DAY_OF_YEAR, pNumDays);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        res = new java.sql.Date(cal.getTimeInMillis());

        return res;
    }

    public static java.sql.Date addOneDay360(java.sql.Date pDate) throws ParseException {
        int day, month, year;
        java.sql.Date res = null;

        day = getNumberDay(pDate);
        month = getNumberMonth(pDate);
        year = getNumberYear(pDate);

        if (month == JANUARY
                || month == MARCH
                || month == MAY
                || month == JULY
                || month == AUGUST
                || month == OCTOBER
                || month == DECEMBER) {
            if (day == 30
                    || day == 31) {
                day = 1;
                if (month == DECEMBER) {
                    ++year;
                    month = JANUARY;
                } else {
                    ++month;
                }
            } else {
                ++day;
            }
        } else if (month == FEBRUARY) {
            if (isLeapYear(year)) { // año bisciesto
                if (day == 29) {
                    day = 1;
                    ++month;
                } else {
                    ++day;
                }
            } else { // febrero con 28 días
                if (day == 28) {
                    day = 1;
                    ++month;
                } else {
                    ++day;
                }
            }
        } else {  // cualquiera del resto de los meses
            if (day == 30) {
                day = 1;
                ++month;
            } else {
                ++day;
            }
        }

        String scrap = Integer.toString(day) + "/"
                + Integer.toString(month) + "/"
                + Integer.toString(year);
        res = convertStr2SqlDate(scrap);
        return res;

    }

    public static java.sql.Date addDays360(java.sql.Date pRefDate, int pNumDays) throws ParseException {
        java.sql.Date res, date;
        int year, month, day;
        int contExcept = 0;

        date = pRefDate;
        year = getNumberYear(pRefDate);
        month = getNumberMonth(pRefDate);
        day = getNumberDay(pRefDate);
        if (isLeapYear(year)) {
            if (day == 29 && month == FEBRUARY) {
                date = convertStr2SqlDate("28/02/" + Integer.toString(year));
            }
        }
        res = date;
        for (int i = 0; i < pNumDays; i++) {
            year = getNumberYear(res);
            month = getNumberMonth(res);
            day = getNumberDay(res);
            if ((day == 28 && month == FEBRUARY)
                    || (contExcept > 0 && contExcept < 4)) {
                ++contExcept;
                res = convertStr2SqlDate("01/03/" + Integer.toString(year));
            } else {
                res = addOneDay360(res);
            }
            contExcept = (contExcept >= 3 ? 0 : contExcept);
        }
        return res;
    }

    public static java.sql.Date addDays(java.sql.Date pDate, int pNumDays, ArithmeticalDaysMethods arithmeticalDaysMethods) throws ParseException {
        java.sql.Date res = null;

        switch (arithmeticalDaysMethods) {
            case z30_360ArithDays:
                throw new UnsupportedOperationException("Not supported yet.");
            //break;
            case z30_360EuroArithDays:
                res = addDays360(pDate, pNumDays);
                break;
            case z30_360USArithDays:
                throw new UnsupportedOperationException("Not supported yet.");
            case z365ArithDays:
                res = addDays365(pDate, pNumDays);
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }

        return res;
    }

    public static java.sql.Date getLastDayMonth(int pMonth, int pYear) throws ParseException {
        java.sql.Date res = null;

        String scrap = MyCommonString.EMPTYSTR;

        switch (pMonth) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                scrap = "31";
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                scrap = "30";
                break;
            case FEBRUARY:
                if (isLeapYear(pYear)) {
                    scrap = "29";
                } else {
                    scrap = "28";
                }
        }
        convertStr2SqlDate(scrap + "/" + Integer.toString(pMonth)
                + "/" + Integer.toString(pYear));

        return res;
    }

    /**
     * Returns the next day of refDate (for sql porposal)
     *
     * @param pRefDate
     * @return next natural day of refdate
     */
    public static java.sql.Date nextDay(java.sql.Date pRefDate) {
        java.sql.Date res;

        res = addDays365(pRefDate, 1);
        return res;
    }

    /**
     * function that subtracts days with a base calendar of 360 days
     *
     * @param d1
     * @param m1
     * @param y1
     * @param d2
     * @param m2
     * @param y2
     * @return
     */
    public static double diffDays360(int d1, int m1, int y1,
            int d2, int m2, int y2) {
        double res;

        res = (360 * (y2 - y1) + 30 * (m2 - m1) + (d2 - d1));
        return res;
    }

    public static double diffDays30_360(java.sql.Date pFecInic,
            java.sql.Date pFecFin) {
        double res = 0;

        int d1, m1, y1;
        int d2, m2, y2;

        d1 = getNumberDay(pFecInic);
        m1 = getNumberMonth(pFecInic);
        y1 = getNumberYear(pFecInic);

        d2 = getNumberDay(pFecFin);
        m2 = getNumberMonth(pFecFin);
        y2 = getNumberYear(pFecFin);

        res = diffDays360(d1, m1, y1, d2, m2, y2);
        return res;
    }

    public static double diffDays30_360US(java.sql.Date pFecInic,
            java.sql.Date pFecFin) {
        double res = 0.00;
        int d1, m1, y1;
        int d2, m2, y2;

        d1 = getNumberDay(pFecInic);
        m1 = getNumberMonth(pFecInic);
        y1 = getNumberYear(pFecInic);

        d2 = getNumberDay(pFecFin);
        m2 = getNumberMonth(pFecFin);
        y2 = getNumberYear(pFecFin);

        if (m1 == FEBRUARY) {
            if (isLeapYear(y1)) {
                if (d1 == 29 && d2 == 29) {
                    d2 = 30;
                } else if (d1 == 29) {
                    d1 = 30;
                }
            } else {
                if (d1 == 28 && d2 == 28) {
                    d2 = 30;
                } else if (d1 == 28) {
                    d1 = 30;
                }
            }
        } else { // otros meses que no es febrero
            if (d2 == 31 && (d1 == 30 && d1 == 31)) {
                d2 = 30;
            } else if (d1 == 31) {
                d1 = 30;
            }
        }

        res = diffDays360(d1, m1, y1, d2, m2, y2);

        return res;
    }

    public static double diffDays30_360Euro(java.sql.Date pFecInic,
            java.sql.Date pFecFin) {
        double res;
        int d1, m1, y1;
        int d2, m2, y2;

        d1 = getNumberDay(pFecInic);
        m1 = getNumberMonth(pFecInic);
        y1 = getNumberYear(pFecInic);

        d2 = getNumberDay(pFecFin);
        m2 = getNumberMonth(pFecFin);
        y2 = getNumberYear(pFecFin);

        d1 = (d1 == 31 ? 30 : d1);
        d2 = (d2 == 31 ? 30 : d2);

        res = diffDays360(d1, m1, y1, d2, m2, y2);
        return res;
    }

    public static int diffDays365(java.sql.Date pFecInic,
            java.sql.Date pFecFin) {
        int res;
        if (pFecInic == null || pFecFin == null) {
            return 0;
        }

        res = (int) ((pFecFin.getTime() / DAY_MILLIS) - (pFecInic.getTime() / DAY_MILLIS));

        return res;
    }

    /**
     * Rutina que calcula la diferencia de dias entre pfecInic y pfecFin
     * utilizando alguno de los metodos financieros z30_360 .- Todos los meses
     * de 30 dias a/no de 360 dias z30_360US .- Todos los meses de 30 dias a/no
     * de 360 dias, con ajustes para el mes de febrero (US Nasdaq) z30_360EU .-
     * Todos los meses de 30 dias a/no de 360 dias, con ajustes para el mes de
     * febrero (Mercado Europeo) z365 .- A/no Juliano (los meses con los dias
     * correspondientes, considera febrero 28 dias y el febrero del a/no
     * bisiciesto (leap year) de 29 dias
     *
     * @param pFecInic
     * @param pFecFin
     * @param pMethod
     * @return
     */
    public static double diffDays(java.sql.Date pFecInic,
            java.sql.Date pFecFin,
            ArithmeticalDaysMethods diffDaysMethods) {
        double res = 0;

        switch (diffDaysMethods) {
            case z30_360ArithDays:
                res = diffDays30_360(pFecInic, pFecFin);
                break;
            case z30_360USArithDays:
                res = diffDays30_360US(pFecInic, pFecFin);
                break;
            case z30_360EuroArithDays:
                res = diffDays30_360Euro(pFecInic, pFecFin);
                break;
            case z365ArithDays:
                res = diffDays365(pFecInic, pFecFin);
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");

        }
        return res;
    }

    /**
     * public static int numeroCupones(java.sql.Date pfecUltPago, java.sql.Date
     * pfecProxPago, java.sql.Date pfecVenc, int pPeriodic, FlowCouponTypes
     * pFlowCouponTypes, CountFinantialMethods pCountFinantialMethods) { int res
     * = 0; double diasPeriod = 0; double tmpNumber; boolean primCupNormal =
     * true;
     *
     * diasPeriod = (12 / pPeriodic) * 30.0; tmpNumber = diffDays(pfecUltPago,
     * pfecProxPago, pCountFinantialMethods) / diasPeriod;
     *
     * if (tmpNumber != diasPeriod) { tmpNumber = diffDays(pfecProxPago,
     * pfecVenc, pCountFinantialMethods) / diasPeriod; }
     *
     * switch (pFlowCouponTypes) { case zNormalFlujo: res = (int) tmpNumber;
     * break; case zDesigFlujo: res = (int) tmpNumber; break; case zFracciFlujo:
     * res = (int) tmpNumber + 1; break; }
     *
     * return res; }
     *
     * public static double factorCalcCupon(java.sql.Date pfecUltPago,
     * java.sql.Date pfecProxPago, java.sql.Date pFecProxProxPago, double
     * pPeriodic, CountFinantialMethods pCountFinantialMethods) { double res =
     * 0.00;
     *
     * switch (pCountFinantialMethods) { case z30_360: case z30_360EU: case
     * z30_360US: res = diffDays(pfecUltPago, pfecProxPago,
     * pCountFinantialMethods) / 360.00; break; case zActualActual: res =
     * diffDays(pfecUltPago, pfecProxPago, CountFinantialMethods.z365) /
     * (pPeriodic * diffDays(pfecUltPago, pFecProxProxPago,
     * CountFinantialMethods.zActual365)); break; case z365: case zActual365:
     * res = diffDays(pfecUltPago, pfecProxPago, CountFinantialMethods.z365) /
     * 365; break; case zActual360: res = diffDays(pfecUltPago, pfecProxPago,
     * CountFinantialMethods.z365) / 360; break; }
     *
     * return res; }
     */
    public static void main(String[] args) {
        try {
            java.sql.Date fecUltPago = convertStr2SqlDate("28/02/2013");
            java.sql.Date fecProxPago = convertStr2SqlDate("31/03/2013");
            java.sql.Date fecVenc = convertStr2SqlDate("02/12/2014");

            double res = diffDays(fecUltPago, fecProxPago, ArithmeticalDaysMethods.z30_360EuroArithDays);
            System.out.println("diffDays " + Double.toString(res));
            /*
            java.sql.Date fecFinal = addDays365(fecProxPago, -2);
            System.out.println("addDays365: " + fecFinal.toString());

             */
 /*
           java.sql.Date fecInic = convertStr2SqlDate("28/02/2013");
            java.sql.Date fecFinal = addDays360(fecInic, 2);
            System.out.println("addDays360: " + fecFinal.toString());
            * */
            /**
             * int numberDay = getNumberDay(sqlDate); System.out.println("dia "
             * + numberDay);
             *
             * int numberMonth = getNumberMonth(sqlDate);
             * System.out.println("mes " + numberMonth);
             *
             * int numberYear = getNumberYear(sqlDate); System.out.println("mes
             * " + numberYear);
             *
             * java.sql.Date d = addDays365(sqlDate, 29); SimpleDateFormat sdf =
             * new SimpleDateFormat(DATEFORMATCRC); System.out.println("fecha: "
             * + sdf.format(d));
             *
             *
             */
        } catch (ParseException ex) {
            System.out.println("com.fvgprinc.tools.common.utilities.GeneralDate.main()");
        }
    }

}
