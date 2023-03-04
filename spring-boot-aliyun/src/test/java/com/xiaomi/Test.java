package com.xiaomi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

        private static List<Date> getDates(int year,int month){
            List<Date> dates = new ArrayList<Date>();

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH,  month - 1);
            cal.set(Calendar.DATE, 1);


            while(cal.get(Calendar.YEAR) == year &&
                    cal.get(Calendar.MONTH) < month){
                int day = cal.get(Calendar.DAY_OF_WEEK);

                if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
                    dates.add((Date)cal.getTime().clone());
                }
                cal.add(Calendar.DATE, 1);
            }
            return dates;

        }
        public static void main(String[] args) {
            List<Date> dates = getDates(2013,12);
            for(Date date : dates){
                System.out.printf(date.toString());
                //System.out.println(DateUtils.getDate2String(date));
            }

        }

}
