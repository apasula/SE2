package com.consultancy.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.consultancy.company.service.Util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(value = Parameterized.class)

public class DateValidationTest {

	private String date;
	private boolean expectedRestult;

	public DateValidationTest(String date, boolean expectedRestult) {
		this.date = date;
		this.expectedRestult = expectedRestult;

	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "08/09/2017", true },
				{ "datetest", false },
				{ "18/09/2017", true },
				{ "2017/09/08", false },
				{ "xx/xx/xxxx", false },
				

		});
	}

	@Test
	public void dateValidationTest() {

		boolean result = Util.validDate(date);
		assertEquals(result, expectedRestult);

	}

}
