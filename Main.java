
public  class Main{

    public static void main(String[] args) {
        try {
            // Cria um AccountManager
            AccountManager accountManager = new AccountManager();
            // Adiciona um usuario John Doe, de idade 25, com email john.doe@email.com
            accountManager.addUser("John Doe", 25, "john.doe@email.com");
            // Print all users
            accountManager.printUsersInfo(); // Chamada do método atualizado
            // Atualiza email
            accountManager.updateEmail("John Doe", "john@doe.com"); // Chamada do método atualizado
            // Deleta usuário
            accountManager.removeUser("John Doe"); // Chamada do método atualizado
            // Print all users again to see the result
            accountManager.printUsersInfo(); // Chamada do método atualizado
        }catch (Exception e){
            System.out.println(e.toString());
        }
        }

}
