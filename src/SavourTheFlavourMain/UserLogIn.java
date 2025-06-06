package SavourTheFlavourMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserLogIn{
    public static boolean logIn(User user) {
        return checkLoginStatus(user).equals("success");
    }
    public static User getUserInputForLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        return new User(username, password, email);
    }

    public static String checkLoginStatus(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader("User_info.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    String fileEmail = parts[2].trim();

                    if (user.getUserName().equals(fileUsername)) {
                        if (!user.getPassword().equals(filePassword)) {
                            return "Incorrect password.";
                        }
                        if (!user.getEmail().equals(fileEmail)) {
                            return "Incorrect email.";
                        }
                        return "success";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Username not found.";
    }


}
