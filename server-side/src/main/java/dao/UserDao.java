package dao;

import java.util.Map;

import models.User;

public interface UserDao {
	Map<Integer, User> getAll();
	User getOne(Integer key);
	void createOne(User user);
	void deleteOne(Integer key);
}
