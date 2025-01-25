package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.controller.Dao.UserDao;
import com.controller.model.User;


public class UserDaoImpl implements UserDao {
    
   
	private static final String INSERTQUERY = "INSERT INTO user(username, password, email, address) VALUES(?, ?, ?, ?)";
    private static final String FETCHALL = "SELECT * FROM user";
    private static final String FETCHONE = "SELECT * FROM user WHERE userid=?";
    private static final String UPDATE = "UPDATE user SET password=? WHERE userid=?";
    private static final String DELETE = "DELETE FROM user WHERE userid=?";
    private static final String FETCHBYEMAIL = "SELECT * FROM user WHERE email=?";
	private ResultSet resultSet;
	private Object userList;
	private Connection con;
	private PreparedStatement pstmt;
	
	private User u;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "root");
    }

    @Override
    public int insert(User u) throws Exception {
        String query = "INSERT INTO user (username, email, password, address) VALUES (?, ?, ?, ?)";
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "root");

            // Create a PreparedStatement with generated keys
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters for the PreparedStatement
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getAddress());

            // Execute the update and check affected rows
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Creating user failed, no rows affected.");
            }

            // Retrieve the generated key (user ID)
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new Exception("Creating user failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    
    @Override
    public ArrayList<User> fetchAll() {
        ArrayList<User> userList = new ArrayList<>();
        try (Connection con = getConnection(); 
             PreparedStatement stmt = con.prepareStatement(FETCHALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                userList.add(new User(
//                	resultSet.getInt("userId"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("address")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList; // Return the locally initialized list
    }
    
    @Override
    public User getUserById(int userId) throws Exception {
        String query = "SELECT * FROM user WHERE userid = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "root");
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
         System.out.println("Executing SQL: " + pstmt.toString());
            
            int affectedRows = pstmt.executeUpdate();
            
            System.out.println("Affected rows: " + affectedRows);
            
            if (affectedRows == 0) {
                System.out.println("Creating order failed, no rows affected.");
                
            }
            
            try (ResultSet res = pstmt.getGeneratedKeys()) {
                if (res.next()) {
                    int id = res.getInt(1);
                    System.out.println("Order created successfully with ID: " + id);
                   
                } 
                else {
                    System.out.println("Creating order failed, no ID obtained.");
                    
                }
            }
        }
		return u;
    }

	
   ArrayList<User> extractUserListFromResultSet(ResultSet resultSet)
   {
	   try {
	    while(resultSet.next())
	    {
		   ((ArrayList<User>) userList).add(
			new User(
					resultSet.getInt("userId"),
					resultSet.getString("username"),
					resultSet.getString("password"),
					resultSet.getString("email"),
					resultSet.getString("address")
					)
			);
		   }   
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
		 
	   }
	   return (ArrayList<User>) userList;
   }


@Override
public User fetchOne(int userId)
{
	try
	{
		pstmt = con.prepareStatement(FETCHONE);
		
		resultSet = pstmt.executeQuery();
		userList = extractUserListFromResultSet(resultSet);
		u=((ArrayList<User>) userList).get(0);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return u;
	
}
@Override
public User fetchUserByEmail(String email)
{
	try
	{
		pstmt = con.prepareStatement(FETCHBYEMAIL);
		pstmt.setString(4,email);
		resultSet = pstmt.executeQuery();
		userList = extractUserListFromResultSet(resultSet);
		u=((ArrayList<User>) userList).get(0);
		u.setUserId(u.getUserId());
		u.setUsername(u.getUsername());
        u.setPassword(u.getPassword());
        u.setEmail(u.getEmail());
        u.setAddress(u.getAddress());
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return u;
	
}
    

    @Override
    public int update(int userid, String password) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setString(1, password);
            pstmt.setInt(2, userid);
            return pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int userid) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, userid);
            return pstmt.executeUpdate();
        } 
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
   
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 
	
        	
}

