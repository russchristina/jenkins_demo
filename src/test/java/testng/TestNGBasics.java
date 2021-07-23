package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * TestNG, which stands for Test Next Generation, is a popular
 * Java testing framework. TestNG looks very similar to JUnit,
 * but it allows for more custom test suites and more intuitive
 * ordering of our tests. Like JUnit, it is annotation-drive,
 * which makes it easy to use.
 * 
 * TestNG also runs tests in parallel when it is possible to
 * do so, which means that it can perform fairly well.
 */

public class TestNGBasics {

	/*
	 * Of course, we have options for setting up our test bed
	 * in TestNG just as we do in other testing frameworks.
	 */
	
	@BeforeClass
	public static void beforeClass() {
		// This method runs once before all of the tests in
		// the test class. It provides one-time setup.
		System.out.println("Before class");
		
	}
	
	@BeforeTest
	public void beforeEachTest() {
		// This method runs once before each and every test
		// in the testng.xml file. A test per the XML file is
		// anything specified inside the <test> tag.
		System.out.println("Before test");
	}
	
	@BeforeMethod
	public void beforeEachTestMethod() {
		// This method runs once before each and very test method
		// in this test class.
		System.out.println("Before method");
	}
	
	@BeforeGroups(groups = {"group1"})
	public void beforeGroups() {
		/*
		 * In TestNG, you have the option to create custom
		 * groups and group your test together logically using
		 * these groups. This method runs once before specific
		 * groups that the developer specifies.
		 */
		System.out.println("Before groups.");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		/*
		 * In TestNG, you can group your TestNG <test>s under
		 * suites. This method will once before a suite of tests
		 * as defined in your testng.xml file.
		 */
		System.out.println("Before suite");
	}
	
	@Test(groups = {"group1"})
	public void thisIsATest() {
		//Test logic for this test case goes here
		System.out.println("Test in group 1");
	}
	
	@Test(groups = {"group2"})
	public void testInAnotherGroup() {
		System.out.println("This test is in group 2.");
	}
	
	@Test(enabled = false)
	public void disabledTest() {
		//This is a disabled test, so it does not run.
	}
	
	/*
	 * This test will be fed data from the DataProvider we created
	 * below. This test will run once for each set of data provided.
	 * 
	 * In order to isolate the pieces of data within each data set, we
	 * define some parameters for this method that allow us to do so.
	 */
	@Test(dataProvider = "dummyUsers")
	public void dataDrivenTest(String firstName, String lastName, int age) {
		System.out.println(firstName + " " + lastName + " is " + age + " years old");
	}
	
	/*
	 * And of course we have the options for teardown...
	 */
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("After suite");
		
	}
	
	@AfterGroups(groups = {"group2"})
	public void afterGroups() {
		System.out.println("After group 2");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After method");
		
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("After class");
	}
	
	/*
	 * TestNG provides some support for data-driven testing. Data-driven
	 * testing allows us to run a test around a set of data. In other
	 * words, the test runs multiple times - once for each set of
	 * data.
	 * 
	 * And, yes, there is a @DataProvider annotation.
	 * 
	 * A method that is specified as a DataProvider returns a
	 * multidimensional array (an array of arrays). Each array
	 * within our containing array contains a set of data which
	 * a test should be run around.
	 */
	
	@DataProvider(name = "dummyUsers")
	public Object[][] provideData(){
		Object testData[][] = new Object[][] {
			{"Christina", "Russ", 1337},
			{"Nivek", "Childs", 1337},
			{"Kevin", "Childs", 7331}
			};
		
		return testData;
	}
	
}
