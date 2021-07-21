package controllers;



import io.javalin.http.Context;
import models.User;
import services.UserService;
import services.UserServiceImpl;

public class UserController {
	static UserService userService = new UserServiceImpl();
	
	public static void getAllUsers(Context context) {
		context.json(userService.getAll());
	}
	
	public static void getOneUser(Context context) {
		Integer key = Integer.parseInt(context.pathParam("id"));
		context.json(userService.getOne(key));
	}
	
	public static void createOneUser(Context context) {
		User user = context.bodyAsClass(User.class);
		userService.createOne(user);
	}
	
	public static void deleteOneUser(Context context) {
		Integer key = Integer.parseInt(context.pathParam("id"));
		userService.deleteOne(key);
	}
}
