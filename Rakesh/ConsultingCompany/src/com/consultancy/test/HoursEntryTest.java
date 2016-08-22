package com.consultancy.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.consultancy.company.db.HoursDB;
import com.consultancy.company.model.Hours;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class HoursEntryTest {

	Hours hours;

	@Parameters
	public static Collection<Object[]> testdata() {
		return Arrays.asList(new Object[][] {
				{ new Hours(1, 1001, "9/1/2016", 8) },
				{ new Hours(2, 1002, "9/1/2016", 8) },
				{ new Hours(3, 1002, "9/1/2016", 8) },
				{ new Hours(4, 1003, "9/1/2016", 8) },
				{ new Hours(5, 1004, "9/1/2016", 8) }

		});
	}

	public HoursEntryTest(Hours hours) {

		this.hours = hours;
	}

	@Test
	public void testHours() {

		HoursDB hoursDB = new HoursDB();
		int countbefore = hoursDB.allHourss().size();
		hoursDB.insert(hours);
		int countafter = hoursDB.allHourss().size();
		assertEquals(countafter, countbefore + 1);

	}

}
