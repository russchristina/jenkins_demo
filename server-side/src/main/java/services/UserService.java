package services;

import java.util.Map;

import models.User;

public interface UserService {
	Map<Integer, User> getAll();
	User getOne(Integer key);
	void createOne(User user);
	void deleteOne(Integer key);
}
