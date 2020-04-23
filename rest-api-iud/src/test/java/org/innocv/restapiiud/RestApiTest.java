package org.innocv.restapiiud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.innocv.restiud.RestApiIudApplication;
import org.innocv.restiud.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiIudApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestApiTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void findAllUsersTest() {
		final String findAllUserUrl = "http://localhost:8080/users";
		ResponseEntity<String> result = restTemplate.getForEntity(findAllUserUrl, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void findUserByIdTest() {
		final String findUserByIdUrl = "http://localhost:8080/users/1";
		User result = restTemplate.getForObject(findUserByIdUrl, User.class);
		Assert.assertNotNull(result);
	}

	@Test
	public void findUserByIdNotFoundTest() {
		final String findUserByIdNotFoundUrl = "http://localhost:8080/users/0";
		User result = restTemplate.getForObject(findUserByIdNotFoundUrl, User.class);
		Assert.assertNotNull(result);
	}

	@Test
	public void createUserTest() throws ParseException {
		final String createUserUrl = "http://localhost:8080/users";

		String pattern = "dd-MM-YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse("14-02-1966");
		User user = new User("Angelines", date);

		ResponseEntity<User> result = restTemplate.postForEntity(createUserUrl, user, User.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getBody());
	}

	@Test
	public void createUserNotFoundTest() throws ParseException {
		final String createUserNotFoundUrl = "http://localhost:8080/users";

		String pattern = "dd-MM-YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date birthdate = simpleDateFormat.parse("29-08-2018");
		User user = new User("Arturo", birthdate);

		ResponseEntity<User> result = restTemplate.postForEntity(createUserNotFoundUrl, user, User.class);
		Assert.assertEquals(409, result.getStatusCodeValue());
	}

	@Test
	public void updateUserTest() throws ParseException {
		String pattern = "dd-MM-YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date birthdate = simpleDateFormat.parse("01-09-2010");
		final String updateUserUrl = "http://localhost:8080/users/1";
		User user = restTemplate.getForObject(updateUserUrl, User.class);
		user.setName("Manolo");
		user.setBirthdate(birthdate);

		restTemplate.put(updateUserUrl, user);
		User userUpdate = restTemplate.getForObject(updateUserUrl, User.class);
		Assert.assertNotNull(userUpdate);
	}

	@Test
	public void deleteUserTest() {
		final String deleteUserUrl = "http://localhost:8080/users/10";
		final String findUserByIdUrl = "http://localhost:8080/users/10";

		restTemplate.delete(deleteUserUrl);
		try {
			restTemplate.getForObject(findUserByIdUrl, User.class);
		} catch (Exception e) {
			Assert.assertEquals(404, HttpStatus.NOT_FOUND);
		}
	}

}
