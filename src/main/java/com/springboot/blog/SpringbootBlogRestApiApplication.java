package com.springboot.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.*;

@SpringBootApplication
public class SpringbootBlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
		String url = "jdbc:mysql://localhost:3306/myblog";
		String username = "root";
		String password = "Myschool3";
		Map<Integer, List<String>> days=new HashMap<>();
		int day=1;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM posts");

			while (rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				String description = rs.getString("description");
				String title = rs.getString("title");
				String []arr=new String[]{Integer.toString(id),content,description,title};
			//	System.out.println("ID:" +id+" , content "+content);
				days.put(day, Arrays.stream(arr).toList());
				// Process the retrieved data as per your requirements
				day++;
			}

			System.out.println("There is menu for a week: ");
			for(int i:days.keySet()){
				System.out.println("Day number  " +i);
				System.out.println(days.get(i));
			}
			Scanner scanner=new Scanner(System.in);
			System.out.println("\nWrite a number of a day to see menu(1- Monday, etc):");
			int number=scanner.nextInt();
			System.out.println(days.get(number));
			System.out.println("Write a dish you want to choose: ");
			String dish=scanner.next();


				if(days.get(number).contains(dish))
					System.out.println("Here is your order! Have a nice day!");
				else
					System.out.println("There is no dish. Bye!");



			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
