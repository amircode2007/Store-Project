package data;
import user.User;
import user.AuthException;


import java.io.*;
import java.util.ArrayList;


public class UserStore {
    private static final String FILE = "users.csv";


    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                users.add(new User(d[0], d[1]));
            }
        } catch (IOException ignored) {}
        return users;
    }


    public static void signUp(String username, String password) throws AuthException {
        if (username.isEmpty() || password.isEmpty())
            throw new AuthException("Fields cannot be empty");


        ArrayList<User> users = loadUsers();
        for (User u : users)
            if (u.getUsername().equals(username))
                throw new AuthException("Username already exists");


        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(username + "," + password);
        } catch (IOException e) {
            throw new AuthException("File error");
        }
    }
    public static User login(String username, String password) throws AuthException {
        ArrayList<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        throw new AuthException("Invalid username or password");
    }
}
