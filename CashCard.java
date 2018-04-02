package HW1;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */

/*
 * Class for CashCard specs
 */
public class CashCard 
{
	private String bankID;
	private int cardNum;
	private Calendar expirationDate;
	private ArrayList<Account> accounts;
	
	/*
	 * Constructs a CashCard with three parameters
	 * @param bankID the ID assigned to the card
	 * @param cardNum the number assigned to the card
	 * @ param expirationDate the last date the CashCard is valid
	 */
	public CashCard(String bankID, int cardNum, Calendar expirationDate)
	{
		this.bankID = bankID;
		this.cardNum = cardNum;
		this.expirationDate = expirationDate;
		accounts = new ArrayList<Account>();
	}
	
	/*
	 * Returns the list of accounts on the card
	 * @return the list of accounts on the card
	 */
	public ArrayList<Account> getAccount()
	{
		return accounts; 
	}
	
	 /*
	  * Returns the Bank ID tied to the card
	  * @return the Bank ID tied to the card
	  */
	 public String getBankID()
	 {
		 return bankID;
	 }
	
	 /*
	  * Returns the card number
	  * @return the card number
	  */
	 public int getCardNum()
	 {
		 return cardNum;
	 }
	 
	/*
	 * Returns the expiration date of the card
	 * @return the expiration date of the card
	 */
	 public Calendar getExpirationDate()
	 {
		 return expirationDate;
	 }
	 
	 /*Adds an account to the list of accounts
	  * @param acc the account to be added
	  * 
	  */
	 public void addAccount(Account acc)
	 {
		 accounts.add(acc);
	 }
	 
	 /*
	  * 
	  */
	 public String toString()
	 {
		 String print = "\nCash Card (BankID: " + bankID +", Card Number: #"
		 		+ cardNum +") accesses ";
		for(int i=0; i < accounts.size(); i++)
		{
			print = print +"Account (#"
		+ accounts.get(i).getAccountNum() +")";
		if ( i != accounts.size()-1)
			print = print +" and ";
		else
			print = print+", ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
		return print + "expires on: " + sdf.format(expirationDate.getTime());
		
	 }
}

