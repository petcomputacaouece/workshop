import java.util.ArrayList;
import java.util.List;
import Exceptions.*;

public class AccountManager {
    private List<User> users = new ArrayList<>();

    public void addUser(String name, int age, String email) throws userNotOldEnoughErr  {
        //Se idade <age> for menor que 18
        if (age < 18) {
            throw new userNotOldEnoughErr();
        }
        users.add(new User(name,age,email));
        System.out.println("User added successfully!");
    }

    public User getUser(String name) throws userDontExists {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new userDontExists();
    }

    public void removeUser(String name) throws userDontExists {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                users.remove(i);
                System.out.println("User deleted.");
                return;
            }
        }
        throw new userDontExists();
    }

    public void printUsersInfo() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    // Funcao para checar email e senha
    public boolean isEmailValid(User user) {
        return user.getEmail().contains("@");
    }

    public void updateEmail(String name, String newEmail) throws userDontExists {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                users.get(i).setEmail(newEmail);
                System.out.println("Email updated.");
                return;
            }
        }
        throw new userDontExists();
    }

    public void validate(String name, int age, String email) throws userNotOldEnoughErr, userDontExists {

        //Usuário a ser Validado
        User unverifyUser = new User(name,age,email);

        if (!isEmailValid(unverifyUser)) {
            System.out.println("Invalid email. Removing user.");
            removeUser(name);
        }
    }

}

