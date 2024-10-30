import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<String> users = new ArrayList<>();

    // Funcao para adicionar usuario
    public void addUser(String name, int age, String email) {
        // Se for menor que 18
        if (age < 18) {
            System.out.println("User is not old enough to register.");
            return;
        }
        users.add(name + ":" + age + ":" + email);
        System.out.println("User added successfully!");
    }

    public String getUser(String name) {
        for (String user : users) {
            if (user.split(":")[0].equals(name)) {
                return user;
            }
        }
        System.out.println("User not found.");
        return null;
    }

    public void deleteUser(String name) {
        boolean foundUser = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).split(":")[0].equals(name)) {
                users.remove(i);
                System.out.println("User deleted.");
                foundUser = true;
                break;
            }
        }

        if (!foundUser) {
            System.out.println("User not found.");
        }

    }

    public void printUser() {
        for (String user : users) {
            String[] details = user.split(":");
            System.out.println("Name: " + details[0] + ", Age: " + details[1] + ", Email: " + details[2]);
        }
    }

    // Funcao para checar email e senha
    public boolean checkEmail(String email) {
        return email.contains("@");
    }

    public void updateEmail(String name, String newEmail) {

        if (!checkEmail(newEmail)) {
            System.out.println("Invalid email.");
            return;
        }

        for (int i = 0; i < users.size(); i++) {
            String[] details = users.get(i).split(":");
            if (details[0].equals(name)) {
                users.set(i, details[0] + ":" + details[1] + ":" + newEmail);
                System.out.println("Email updated.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void validate(String name, int age, String email) {
        // Adiciona usuario
        addUser(name, age, email);

        // Verifica se o email é válido
        if (!checkEmail(email)) {
            System.out.println("Invalid email. Removing user.");
            // Remove o usuário se o email for inválido
            deleteUser(name);
        } else {
            String user = getUser(name);

            if (user != null) {
                System.out.println("User validated");
            } else {
                System.out.println("User not found");
            }
        }
    }

}