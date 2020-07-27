/*
*CommonUtility.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Part;

public class CommonUtility {

	//Convert List<String to String array
	public static String[] convertListToStringArray(List<String> list) {
		String str[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			str[i] = list.get(i);
		}
		return str;

	}
	//Get Months
	public static Map<String,String> getMonth()
	{
		Map<String,String> monthsMap=new HashMap<String, String>();
		monthsMap.put("01","Jan");
		monthsMap.put("02","Feb");
		monthsMap.put("03","Mar");
		monthsMap.put("04","Apr");
		monthsMap.put("05","May");
		monthsMap.put("06","Jun");
		monthsMap.put("07","Jul");
		monthsMap.put("08","Aug");
		monthsMap.put("09","Sep");
		monthsMap.put("10","Oct");
		monthsMap.put("11","Nov");
		monthsMap.put("12","Dec");

		return monthsMap;
	}
	
	//Get Months
		public static Integer getMonthId(String strMonth)
		{
			Map<String,Integer> monthsMap=new HashMap<String, Integer>();
			monthsMap.put("Jan",0);
			monthsMap.put("Feb",1);
			monthsMap.put("Mar",2);
			monthsMap.put("Apr",3);
			monthsMap.put("May",4);
			monthsMap.put("Jun",5);
			monthsMap.put("Jul",6);
			monthsMap.put("Aug",7);
			monthsMap.put("Sep",8);
			monthsMap.put("Oct",9);
			monthsMap.put("Nov",10);
			monthsMap.put("Dec",11);
			return monthsMap.get(strMonth);

		}
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	

public static String convertListToString(ArrayList list) {

	// TODO Auto-generated method stub

	if (null == list || list.size() < 1) {

	return null;

	}

	StringBuffer singleRow = new StringBuffer("");

	for (int j = 0; j < list.size(); j++) {

	String content = list.get(j).toString();

	StringTokenizer st = new StringTokenizer(content, ",");

	if (st.countTokens() > 1) {

	content = "";

	while (st.hasMoreTokens()) {

	content = content + st.nextToken() + ",";

	}

	content = "\"" + content.substring(0, content.lastIndexOf(",")) + "\"";

	} else if (content.indexOf(',') != -1) {

	content = "\"" + content.substring(0, content.length() - 1) + "\"";

	}

	singleRow.append(content + ",");

	}

	singleRow = new StringBuffer(singleRow.toString().substring(0, singleRow.length() - 1));

	return singleRow.toString();

	}

	public static List<String> getMonthList() {
		List<String> monthList=new ArrayList<String>();
		monthList.add("Jan");
		monthList.add("Feb");
		monthList.add("Mar");
		monthList.add("Apr");
		monthList.add("May");
		monthList.add("Jun");
		monthList.add("Jul");
		monthList.add("Aug");
		monthList.add("Sep");
		monthList.add("Oct");
		monthList.add("Nov");
		monthList.add("Dec");
		return monthList;
	}
	public static Map<Integer,String> getMonthMap()
	{
		Map<Integer,String> monthsMap=new HashMap<Integer, String>();
		monthsMap.put(0,"Jan");
		monthsMap.put(1,"Feb");
		monthsMap.put(2,"Mar");
		monthsMap.put(3,"Apr");
		monthsMap.put(4,"May");
		monthsMap.put(5,"Jun");
		monthsMap.put(6,"Jul");
		monthsMap.put(7,"Aug");
		monthsMap.put(8,"Sep");
		monthsMap.put(9,"Oct");
		monthsMap.put(10,"Nov");
		monthsMap.put(11,"Dec");

		return monthsMap;
	}
	
	
	public static List<Integer> getYearList() {
		List<Integer> yearList=new ArrayList<Integer>();
		Date date = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(simpleDateformat.format(date));
        yearList.add(year-1);
        yearList.add(year);
		return yearList;
	}
	public static boolean isValidFormat(String format, String value, Locale locale) {
	    LocalDateTime ldt = null;
	    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

	    try {
	        ldt = LocalDateTime.parse(value, fomatter);
	        String result = ldt.format(fomatter);
	        return result.equals(value);
	    } catch (DateTimeParseException e) {
	        try {
	            LocalDate ld = LocalDate.parse(value, fomatter);
	            String result = ld.format(fomatter);
	            return result.equals(value);
	        } catch (DateTimeParseException exp) {
	            try {
	                LocalTime lt = LocalTime.parse(value, fomatter);
	                String result = lt.format(fomatter);
	                return result.equals(value);
	            } catch (DateTimeParseException e2) {
	                // Debugging purposes
	                //e2.printStackTrace();
	            }
	        }
	    }

	    return false;
	}

}
