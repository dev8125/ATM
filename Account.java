package HW1;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */

/*
 * A checking account
 */

public class Account 
{
	private int accountNum;
	private int balance; 
	
	/*
	 * Constructs the account
	 * @param accountNum is the account number for the account
	 * @param balance is the amount in the account
	 */
	public Account(String bankType, int accountNum)
	{
		this.accountNum= accountNum;
		balance = 40; 
	}
	
	/*
	 * Returns the account number
	 * @return the account number
	 */
	public int getAccountNum()
	{
		return accountNum;
	}
	
	/*
	 * Returns the balance amount in the account
	 * @return the balance amount
	 */
	public int getBalance()
	{
		return balance;
	}

	/*
	 * Adjusts the balance to a new balance
	 * @param balance amount to be subtracted 
	 * Precondition: Need more money in balance than being withdrawn
	 * Postcondition: Subtracts money
	 */
	public void withdraw(int amount)
	{
		if(amount > balance)
		{
			System.out.println("Not enough money in account");
		}
		else
		{
		balance = balance - amount; 
		}
	}
}