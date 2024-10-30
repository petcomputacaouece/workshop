import java.util.ArrayList;
import java.util.List;
import Exceptions.*;

public class BankSystem {
   
    private List<Account> accounts = new ArrayList<>(); // armazena contas


    public void addAccount(String owner, int accountNum, double balance) throws createNegativegBalanceAccountErr{
        if (balance < 0) {
            throw new createNegativegBalanceAccountErr();
        }
        accounts.add(new Account(owner, accountNum, balance));
        System.out.println("Account added for " + owner);
    }

    public Account getAccount(int accountNum) throws accountDontExistsErr{
        for (Account account : accounts) {
            if (account.getAccountNum() == accountNum) {
                return account;
            }
        }
        throw new accountDontExistsErr();
    }


    public void removeAccount(int num) throws accountDontExistsErr{
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNum() == num) {
                accounts.remove(i);
                System.out.println("Account removed.");
                return;
            }
        }
        throw new accountDontExistsErr();
    }



    public void displayAccountInfo(int num) throws accountDontExistsErr{
        for (Account account : accounts) {
            if (account.getAccountNum() == num) {
                System.out.println(account.toString());
                return;
            }
        }
        throw new accountDontExistsErr();
    }


    public void deposit(int accountNum, double amt) throws accountDontExistsErr, negativeDepositeErr{
        if (amt <= 0) {
            throw new negativeDepositeErr();
        }
        for (Account account : accounts) {
            if (account.getAccountNum() == accountNum) {
                account.setBalance(account.getBalance()-amt);
                System.out.println("Deposited $" + amt + " to account " + accountNum);
                return;
            }
        }
        throw new accountDontExistsErr();
    }


    public void withdraw(int accountNum, double amt) throws InsufficientFundsErr,accountDontExistsErr{
        for (Account account : accounts) {
            if (account.getAccountNum() == accountNum) {
                if (amt > account.getBalance()) {
                    throw new InsufficientFundsErr();
                }
                account.setBalance(account.getBalance()-amt);
                System.out.println("Withdrew $" + amt + " from account " + accountNum);
                return;
            }
        }
        throw new accountDontExistsErr();
    }


    public void transferFunds(int fromAccountNum, int toAccountNum, double amt) throws negativeDepositeErr, accountDontExistsErr, InsufficientFundsErr {
        if (amt <= 0) {
            throw new negativeDepositeErr();
        }


        Account fromAccount = getAccount(fromAccountNum);
        Account toAccount = getAccount(toAccountNum);

        if (fromAccount.getBalance() < amt) {
            throw new InsufficientFundsErr();
        }


        fromAccount.setBalance(fromAccount.getBalance()-amt);
        toAccount.setBalance(toAccount.getBalance()+amt);
        System.out.println("Transferred $" + amt + " from account " + fromAccountNum + " to account " + toAccountNum);
    }


    public void showAccounts() {
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }


    public boolean validBalance(double balance) {
       return balance >= 0;
    }


    public boolean validAccountNumber(int accNum) {
        return accNum > 0;
    }


    public boolean validAmount(double amt) {
        return amt > 0;
    }


    public boolean isBalanceValid(double balance) { // Função duplicada
        return balance >= 0;
    }


    public static void main(String[] args) throws createNegativegBalanceAccountErr, accountDontExistsErr, negativeDepositeErr, InsufficientFundsErr {
        BankSystem bs = new BankSystem();
        try {
        bs.addAccount("Alice", 1001, 500);
        bs.addAccount("Bob", 1002, -100);
        bs.deposit(1001, 200);
        bs.withdraw(1001, 100);
        bs.transferFunds(1001, 1002, 300);
        bs.showAccounts();
    }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}


class Account {


    private String owner;
    private int accountNum;
    private double balance;


    public Account(String owner, int accountNum, double balance) {
        this.owner = owner;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public String  toString(){
        return  "Owner: " + owner + ", Account Number: " + accountNum + ", Balance: $" + balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}



