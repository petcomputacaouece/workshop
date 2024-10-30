import java.util.ArrayList;
import java.util.List;

public class BankSystem {

    private List<Account> accs = new ArrayList<>(); // armazena contas

    public void addAccount(String owner, int accNum, double balance) {
        if (!validBalance(balance)) {
            System.out.println("Cannot add account with negative balance");
            return;
        }

        if (!duplicateAcc(accNum)) {
            System.out.println("Duplicate account. Die.");
            return;
        }

        if (!validAccNum(accNum)) {
            System.out.println("Invalid account number. Perish.");
            return;
        }

        accs.add(new Account(owner, accNum, balance));
        System.out.println("Account added for " + owner);
    }

    public void removeAccount(int num) {
        boolean accFound = false;
        for (int i = 0; i < accs.size(); i++) {
            if (accs.get(i).accNum == num) {
                accs.remove(i);
                accFound = true;
                System.out.println("Account removed.");
            }
        }

        if (!accFound) {
            System.out.println("Account not found.");
        }

    }

    public void displayAccountInfo(int num) {
        for (Account acc : accs) {
            if (acc.accNum == num) {
                System.out.println("Owner: " + acc.owner + ", Balance: $" + acc.balance);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void deposit(int accNum, double amt) {
        if (amt <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        for (Account acc : accs) {
            if (acc.accNum == accNum) {
                acc.balance += amt;
                System.out.println("Deposited $" + amt + " to account " + accNum);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(int accNum, double amt) {
        for (Account acc : accs) {
            if (acc.accNum == accNum) {
                if (amt > acc.balance) {
                    System.out.println("Insufficient funds.");
                    return;
                }
                acc.balance -= amt;
                System.out.println("Withdrew $" + amt + " from account " + accNum);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void transferFunds(int fromAccNum, int toAccNum, double amt) {
        if (amt <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        Account fromAcc = null;
        Account toAcc = null;

        for (Account acc : accs) {
            if (acc.accNum == fromAccNum) {
                fromAcc = acc;
            } else if (acc.accNum == toAccNum) {
                toAcc = acc;
            }
        }

        if (fromAcc == null || toAcc == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        if (fromAcc.balance < amt) {
            System.out.println("Insufficient funds for transfer.");
            return;
        }

        fromAcc.balance -= amt;
        toAcc.balance += amt;
        System.out.println("Transferred $" + amt + " from account " + fromAccNum + " to account " + toAccNum);
    }

    public void showAllAccounts() {
        for (Account acc : accs) {
            System.out
                    .println("Owner: " + acc.owner + ", Account Number: " + acc.accNum + ", Balance: $" + acc.balance);
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

    public boolean duplicateAcc(int num) {
        for (Account acc : accs) {
            if (acc.accNum == num) {
                return true;
            }
        }

        return false;
    }

    public boolean validAccNum(int num) {
        if (num >= 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        BankSystem bs = new BankSystem();
        bs.addAccount("Alice", 1001, 500);
        bs.addAccount("Bob", 1002, -100); // Erro permitido no sistema
        bs.deposit(1001, 200);
        bs.withdraw(1001, 100);
        bs.transferFunds(1001, 1002, 300);
        bs.showAllAccounts();
    }
}

class Account {
    String owner;
    int accNum;
    double balance;

    public Account(String owner, int accNum, double balance) {
        this.owner = owner;
        this.accNum = accNum;
        this.balance = balance;
    }
}
