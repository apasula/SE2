package com.consultancy.company.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static boolean validDate(String s) {

		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		sdf.setLenient(false);

		try {

			Date date = sdf.parse(s);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
