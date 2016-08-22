package com.consultancy.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.consultancy.company.service.UserLogin;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class UserLoginTest {

	private String username;
	private String password;
	private String usertype;
	private boolean expectedresult;

	public UserLoginTest(String username, String password, String usertype,
			boolean expectedresult) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.expectedresult = expectedresult;

	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "account", "account", "accountant",true },
				{ "account1", "account", "accountant",false },
				{ "developer46", "developer46", "developer",true },
				{ "developer47", "developer47", "developer",false },
				{ "manager46", "manager46", "manager",true } ,
				{ "manager47", "manager47", "manager",false }
				
		});
	}

	UserLogin login = new UserLogin();

	@Test
	public void loginTest() {

		
		if (usertype.equals("accountant")) {

			boolean result = login.accountantLogin(username,
					password);
			assertEquals(result, expectedresult);
		} else if (usertype.equals("developer")) {

			boolean result = login.developerLogin(username,
					password);
			assertEquals(result, expectedresult);
		} else if (usertype.equals("manager")) {

			boolean result = login.managerLogin(username,
					password);

			assertEquals(result, expectedresult);
		}
	}

}
