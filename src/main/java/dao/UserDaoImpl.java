package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import dbconfig.ConnectionUtil;
import dbconfig.ResourceClosers;
import models.User;

/*
 * Ideally, we'd like to be pulling the data from a database. This example currently uses
 * dummy data from a HashMap. That said, we'll refactor this DAO to pull data from our
 * existing RDS instance. To do so, we will need to establish a connection to the DB using
 * JDBC. 
 * 
 * JDBC stands for Java Database Connectivity. It allows us to establish a connection to a
 * DB from within a Java application. Yes, this is similar to pyodbc (and the flow of JDBC
 * is even similar to that of pyodbc).
 */
public class UserDaoImpl implements UserDao{
	
	public UserDaoImpl() {
		
	}

	@Override
	public Map<Integer, User> getAll() {
		HashMap<Integer, User> users = new HashMap<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		//Let's establish a connection to our database. We use the Connection
		//interface of JDBC to establish this connection. We use this interface
		//in conjunction with the DriverManager class, which manages your JBCD
		//drivers.
		
		//Of course, we are not hardcoding our credentials into the source code.
		//We will pull the DB connection information from our environment variables
		//as we usually do. We use the System class to do this.
		try {
			 conn = ConnectionUtil.getConnection();
			
			//Now that we've gotten a connection, we want to execute our SQL
			//statement. In order to execute the SQL statement, we will need to use
			//the Statement interface.
			final String SQL = "select user_id, first_name, last_name from users";
			stmt = conn.createStatement();
			
			//Once you've executed your query, you get back a ResultSet. The ResultSet
			//is an object which contains the data returned from executing query.
			set = stmt.executeQuery(SQL);
			
			//At this point, we have the ResultSet. Now we just need to move through
			//the data and place it into the Map that we will return to the caller.
			
			//We'll start by moving through each row in our ResultSet. We can do this by
			//calling the next() method on the ResultSet. This moves the cursor forward to
			//the next row.
			
			while(set.next()) {
				//What is it that we want to do with the data inside of each row?
				//We want to pull out the three values we need (user_id, first_name, and
				//last_name) and construct a user and then throw that user into the map.
				
				User retrievedUser = new User(
						set.getString(2),
						set.getString(3),
						1337);
				
				users.put(set.getInt(1), retrievedUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				//Remember to close your connection and release all of your JDBC resources.
				ResourceClosers.closeConnection(conn);
				ResourceClosers.closeStatement(stmt);
				ResourceClosers.closeResultSet(set);
		}
		
		return users;
	}

	@Override
	public User getOne(Integer key) {
		return null;
	}

	/*
	 * We want to persist a user that the client passes back to the server.
	 */
	@Override
	public void createOne(User user) {
		
		// What is our JDBC workflow?
		// Note that we don't need a ResultSet this time around as we aren't pulling
		// records out of our DB.
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			//Establish the connection to the DB
			conn = ConnectionUtil.getConnection();
	
			//Make a SQL statement
			// Note that we are now refactoring this code to take advantage of 
			//PreparedStatements instead. A PreparedStatement protects against SQL
			//injection and can improve performance if you're intending on executing
			//the same statement multiple times.
			final String SQL = "insert into users values(default, ?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, "hardcodedemail@dontdothis.com");
			stmt.setString(4, "secret_sauce");
			
			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
	}

	@Override
	public void deleteOne(Integer key) {
		return;
	}

}
