package com.demo.rest.webservices.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.rest.webservices.model.User;

@Component
public class UserService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {
		users.add(new User(1, "vijay"));
		users.add(new User(2, "Ashok"));
		users.add(new User(3, "Jack"));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {

		if (user.getId() == null)
			user.setId(++usersCount);

		users.add(user);
		return user;

	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {

			}
		}
		return users.get(id);
	}

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
