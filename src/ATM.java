import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ATM {
	
	private HashMap <Integer, Double> account = new HashMap<Integer, Double>(); 

	public static void main(String[] args) {
		ATM chase = new ATM();
		// Open accounts
		chase.openAccount(00001);
		chase.openAccount(00002, 2500.0);
		// Test Basic Functionality
		chase.depositMoney(00001, 433.45);
		chase.depositMoney(00001, 434.77);
		chase.depositMoney(00001, 424.91);
		chase.depositMoney(00001, 474.10);
		chase.depositMoney(00002, 154.30);
		System.out.println(chase.checkBalance(00001)); // Should be 1767.23
		System.out.println(chase.checkBalance(00002)); // Should be 2654.30
		chase.withdrawMoney(00001, 100.00);
		chase.withdrawMoney(00001, 1000.00);
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
		// Test Invalid Deposits
		chase.depositMoney(00003, -433.45);
		chase.depositMoney(00004, 32434.77);
		chase.depositMoney(01337, 128537424.91);
		chase.depositMoney(21504, 2343474.10);
		System.out.println(chase.checkBalance(00003)); // Should be 0.0
		System.out.println(chase.checkBalance(00004)); // Should be 0.0
		System.out.println(chase.checkBalance(01337)); // Should be 0.0
		System.out.println(chase.checkBalance(21504)); // Should be 0.0
		// Test Invalid Withdrawals
		chase.withdrawMoney(00001, -433.45);
		chase.withdrawMoney(00001, 32434.77);
		chase.withdrawMoney(01337, 128537424.91);
		chase.withdrawMoney(21504, -2343474.10);
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
		System.out.println(chase.checkBalance(01337)); // Should be 0.0
		System.out.println(chase.checkBalance(21504)); // Should be 0.0
		// Test other issues
		chase.withdrawMoney(00002, 2020.2);
		System.out.println(chase.checkBalance(00002)); // Should be 634.1 and not a fraction more!

		chase.closeAccount(00002);
		System.out.println(chase.checkBalance(00002));
		System.out.println(chase.depositMoney(00002, 200));
		System.out.println(chase.withdrawMoney(00002, 100));
		chase.withdrawMoney(00002, 200);
		System.out.println(chase.checkBalance(00002));
		System.out.println(chase.depositMoney(00002, -100));
	}

	
	public void openAccount (int accountNum) {
		account.put(accountNum, 0.0);
	}
	
	public void openAccount (int accountNum, double deposit) {
		account.put(accountNum, deposit);
	}
	
	public void closeAccount(int accountNum) {
		if(account.containsKey(accountNum)){
			account.remove(accountNum);
		}
	}
	
	public double checkBalance(int accountNum) {
		if (account.containsKey(accountNum)){
			BigDecimal balance = new BigDecimal(account.get(accountNum)).setScale(2, RoundingMode.HALF_UP);
			return balance.doubleValue();
		}
		return 0.0;
	}
	
	public boolean depositMoney(int accountNum, double depositAmount) {
		if(account.containsKey(accountNum) && depositAmount>=0){
			account.put(accountNum, account.get(accountNum) + depositAmount);
			return true;
		}
		return false;
	}

	public boolean withdrawMoney (int accountNum, double withdrawAmount) {
		if(account.containsKey(accountNum) && account.get(accountNum)>= withdrawAmount && withdrawAmount >= 0){
			account.put(accountNum, account.get(accountNum)- withdrawAmount);
			return true;
		}
		return false;
	}
	
	
}
