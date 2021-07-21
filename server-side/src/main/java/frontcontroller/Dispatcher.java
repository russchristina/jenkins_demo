package frontcontroller;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import controllers.UserController;
import io.javalin.Javalin;

public class Dispatcher {
	
	public Dispatcher(Javalin app) {
		/*
		 * This is where we are going to delegate where the requests go
		 * 
		 *
		 */
		
		//get all users /api/user GET
		//app.get("/api/user", UserController::getAllUsers);
		
		//get one user /api/user/:id GET
		//app.get("/api/user/:id", UserController::getOneUser);
		
		//create one user /api/user POST
		//app.post("/api/user", UserController::createOneUser);
		
		//delete one user /api/user/:id DELETE
		//app.delete("/api/user/:id", UserController::deleteOneUser);
		
		app.routes(() ->{
			path("/api/user", () -> {
				get(UserController::getAllUsers);
				post(UserController::createOneUser);
				
				path(":id", () -> {
					get(UserController::getOneUser);
					delete(UserController::deleteOneUser);
				});
			});
		});
		
		
	}

}
