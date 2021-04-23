package dao;
import java.sql.*;

import model.Employee;
public class EmployeeDao {
 
	public int registerEmployee(Employee employee) throws Exception {
		
		String INSERT_USERS_SQL = "INSERT INTO employee VALUES (?,?,?,?,?,?,?);";
		int result=0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false","root","password");
			PreparedStatement ps = connection.prepareStatement("SELECT MAX(id) FROM employee;");
			ResultSet rs=ps.executeQuery();
			int x=0;
			while(rs.next())  x= Integer.parseInt(rs.getString(1));
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1, ++x);
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getUsername());
			preparedStatement.setString(5, employee.getPassword());
			preparedStatement.setString(6, employee.getAddress());
			preparedStatement.setString(7, employee.getContact());
			
			System.out.println(preparedStatement);
			result=preparedStatement.executeUpdate();
			connection.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
