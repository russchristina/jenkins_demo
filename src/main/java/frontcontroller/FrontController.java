package frontcontroller;



import io.javalin.Javalin;
import io.javalin.http.Context;

/*
 * The front controller is the initial contact point from the Client.
 * 	- the front controller will contain your middleware
 */
public class FrontController {
	Javalin app;
	Dispatcher dispatcher;
	
	public FrontController(Javalin app) {
		this.app = app;
		
		/*
		 * All of your middleware routes would go here
		 * 
		 * - the :: syntax tells the compiler that the 
		 * 	checkAllRequest method will be the implementation for the functional interface method
		 */
		
		this.app.before("/api/*", FrontController::checkAllRequests);
		/*
		 * 
		 * this is equivalent to the syntax above
		 * this.app.before("/api/*", context -> { //do context stuff here });
		 */
		
		
		this.dispatcher = new Dispatcher(app);
	}
	
	
	public static void checkAllRequests(Context context) {
		System.out.println("Middleware has been hit");
	}

}
