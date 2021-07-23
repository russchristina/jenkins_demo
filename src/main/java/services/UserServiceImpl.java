package services;

import java.util.Map;

import dao.UserDao;
import dao.UserDaoImpl;
import models.User;

public class UserServiceImpl implements UserService {
	
	UserDao userDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}

	@Override
	public Map<Integer, User> getAll() {
		return this.userDao.getAll();
	}

	@Override
	public User getOne(Integer key) {
		return this.userDao.getOne(key);
	}

	@Override
	public void createOne(User user) {
		this.userDao.createOne(user);
		
	}

	@Override
	public void deleteOne(Integer key) {
		this.userDao.deleteOne(key);
	}

}
