package models;

/*
 * user model that we will be utilizing
 * 
 * POJO
 */
public class User {

		private String firstName;
		private String lastName;
		private int age;
		
		/*
		 * generating constructors
		 */
		public User() {
			super();
		}

		public User(String firstName, String lastName, int age) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
		}

		
		/*
		 * generating getters and setters
		 */
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
		}
		
}
