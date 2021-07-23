package testng;

import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dao.UserDao;
import models.User;
import services.UserServiceImpl;

public class UserServiceTest {

	/*
	 * What kind of setup do we want to do?
	 */
	
	/*
	 * Use Mockito's @Mock annotation to mock an object.
	 */
	@Mock
	public UserDao userDaoImpl;
	
	/*
	 * Inject all of your mocks into your object under test.
	 */
	@InjectMocks
	public UserServiceImpl userServiceImpl;

	
	public HashMap<Integer, User> dummyUsers;
	
	@BeforeClass
	public void beforeClassSetup() {
		
		//Can't inject mocks if you don't have an instance of the object under test yet, now can we?
		userServiceImpl = new UserServiceImpl();
		
		//Initialize my Mockito mocks
		MockitoAnnotations.openMocks(this);
		
		// We've decided that we want mock data. We want to
		// mock the return value of a method in our DAO, which
		// returns a map like so: Map<Integer, User>
		
		dummyUsers = new HashMap<>();
		dummyUsers.put(1, new User("Pikachu", "Ketchum", 305));
		dummyUsers.put(2, new User("Ash", "Ketchum", 13));
		dummyUsers.put(3, new User("Gary", "Will show up to beat up at any moment in the game", 13));
		dummyUsers.put(4, new User("Misty", "Ketchum", 16));
		
		/*
		 * Use the Mockito class's static methods to stub your return value for the getAll method
		 * on the User DAO.
		 */
		Mockito.when(userDaoImpl.getAll()).thenReturn(dummyUsers);
	}
	
	//Now we can actually write our test.
	
	@Test
	public void testGetAllUsers() {
		Map<Integer, User> retrievedUsers = userServiceImpl.getAll();
		//State verification
		Assert.assertEquals(retrievedUsers.get(1).getAge(), 305);
		//Behavior verification
		Mockito.verify(userDaoImpl).getAll();
	}
	
	
}
