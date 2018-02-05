package com.ciexperts.projectmanagement.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

	public static final String dbDateFormat = "yyyy-MM-dd HH:mm:ss";
	private static String formatD(String strDate, String dateFormat, String stringFormat){
		SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
		SimpleDateFormat sFormat = new SimpleDateFormat(stringFormat);
		String rtn = "";
		try {
			if(strDate != null || strDate != ""){
				Date date = dFormat.parse(strDate);
				rtn = sFormat.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	public static  String formatDate(String strDate, String dateFormat, String stringFormat){
		return formatD(strDate, dateFormat, stringFormat);
	}
	
	
	public static String getAccess(String roleName){
		String access = null;

		switch (roleName.toLowerCase()) {
		case "project manager":
			access = "pm";
			break;
		case "qa manager":
			access = "qa";
			break;
		case "business analyst":
			access = "ba";
			break;
		case "project owner":
			access = "po";
			break;
		case "business user":
			access = "bu";
			break;
		case "developer manager":
			access = "dv";
			break;
		case "it staff":
			access = "is";
			break;
		default:
			break;
		}
		return access;
	}
}
