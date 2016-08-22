package com.consultancy.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.consultancy.company.service.Util;

@RunWith(value = Parameterized.class)

public class NumberValidationTest {

	private String number;
	private boolean expectedRestult;

	public NumberValidationTest(String number, boolean expectedRestult) {
		this.number = number;
		this.expectedRestult = expectedRestult;

	}
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "08/09/2017", false },
				{ "32", true },
				{ "", false },
				{ "0", true },
				{ "test", false },
				

		});
	}
	
	@Test
	public void numberValidationTest() {

		boolean result = Util.isInteger(number);
		assertEquals(result, expectedRestult);

	}
}
