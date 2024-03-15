package com.example.mongatest;

import com.example.mongatest.model.ApplicationUser;
import com.example.mongatest.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UnitTests {

	@Test
	public void testUserUpdatePermission() {
		String testPermission = "ADMIN";
		ApplicationUser user = new ApplicationUser();
		user.addPermission(testPermission);
		Assertions.assertTrue(user.getPermissions().contains(testPermission));
		user.removePermission(testPermission);
		Assertions.assertFalse(user.getPermissions().contains(testPermission));
	}

	@Test
	public void testClientConstructor() {
		String id = "1";
		String name = "2";
		String email = "3";
		String phone = "4";
		Client client = new Client(id, name, email, phone);
		Assertions.assertArrayEquals(new String[]{id, name, email, phone}, new String[]{client.getId(), client.getName(), client.getEmail(), client.getPhone()});
	}

}
