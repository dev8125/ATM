package HW1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.GregorianCalendar; 

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */

/*
 * To test the ATM Network 
 */
public class ATMSystem
{
	static Scanner in; 
	
	public static void main (String [] args)
	{
		in = new Scanner(System.in);
		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");
		
		BankComputer compA= new BankComputer(1);
		BankComputer compB= new BankComputer(2);
		bankA.initializeComp(compA);
		bankB.initializeComp(compB);
		
		ATM ATM1_A = new ATM("A2",50, 100);
		ATM ATM2_A = new ATM("A1",50, 100);
		ATM1_A.connectComp(compA);
		ATM2_A.connectComp(compA);
		
		ATM ATM1_B = new ATM("B1", 100, 100);
		ATM ATM2_B = new ATM("B2", 50,100);
		ATM1_B.connectComp(compB);
		ATM2_B.connectComp(compB);
		
		ArrayList<ATM> atmList = new ArrayList<ATM>();
		atmList.add(ATM1_A);
		atmList.add(ATM2_A);
		atmList.add(ATM1_B);
		atmList.add(ATM2_B);
		
		Customer a1 = new Customer("Dev");
		Customer a2 = new Customer ("Omar");
		Customer b1 = new Customer("Robert");
		Customer b2 = new Customer ("Carlos");
		
		CashCard cardA1 = bankA.issueCard(new GregorianCalendar(2021,12,17), "Maluma");
		CashCard cardA2 = bankA.issueCard(new GregorianCalendar(2020,12,19), "Jhoni");
		CashCard cardB1 = bankB.issueCard(new GregorianCalendar(2012,12,21), "Royce");
		CashCard cardB2 = bankB.issueCard(new GregorianCalendar(2010,12,18), "Badbunny");

		ArrayList <CashCard> cardList = new ArrayList<CashCard>();
		cardList.add(cardA1);
		cardList.add(cardA2);
		cardList.add(cardB1);
		cardList.add(cardB2);
		
		Account accA1_1 = new Account("Checkings",111);
		Account accA1_2 = new Account("Checkings",112);
		Account accB1_1 = new Account("Checkings",113);
		Account accB1_2 = new Account("Checkings",114);
		
		a1.setCashCard(cardA1);
		a2.setCashCard(cardA2);
		b1.setCashCard(cardB1);
		b2.setCashCard(cardB2);
		
		cardA1.addAccount(accA1_1);
		cardA2.addAccount(accA1_2);
		cardB1.addAccount(accB1_1);
		cardB2.addAccount(accB1_2);
	
		bankA.addCustomer(a1, accA1_1);
		bankA.addCustomer(a2, accA1_2);
		bankB.addCustomer(b1, accB1_1);
		bankB.addCustomer(b2, accB1_2);
		
		System.out.println("State of the two banks: All accounts have $40");
		System.out.println(bankA.toString() + "\n" + bankB.toString());
		
		System.out.println("States of Bank Computers: 1 per Bank");
		System.out.println(compA.toString() + "\n" + compB.toString());
		
		System.out.println("States of the 4 ATMs (2 ATMs per Bank)");
		System.out.println(ATM1_A.toString() +"\n" + ATM2_A.toString()+ 
				ATM1_B.toString() +"\n" + ATM2_B.toString());
		
		System.out.println("ATM simulation ends when all ATMs have been used");
		
		while(ATM1_A.available() || ATM2_A.available() ||
				ATM1_B.available() || ATM2_B.available())
		{
			System.out.println("Choose a bank: Bank A or Bank B");
			String bankID = "";
			bankID = in.nextLine();
			
			System.out.println("Choose an ATM: ATM 1 or ATM 2");
			String atmCode = "";
			atmCode = in.nextLine();
			atmCode = bankID+ atmCode;
			
			ATM a = null;
			for(int i=0;i< atmList.size() && a == null;i++)
			{
				if(atmCode.equals(atmList.get(i).getATMNum()))
					a = atmList.get(i); // set null ATM to chosen
			}
			
			System.out.println("Enter Cash Card:");
			String cardID = "";
			cardID = in.nextLine();
			
			boolean cardValid = false;
			for(int i =0; i< cardList.size() && !cardValid; i++)
			{
				CashCard c = cardList.get(i);
				if(cardID.equals(c.getBankID() + c.getCardNum()))
				{
					a.setInsertedCard(c);
					cardValid = true; 
				}
			}
		}
		System.out.println("\nThese are the states of the transactions:");
		System.out.println("States of the two banks)");
		System.out.println(bankA.toString() + "\n" + bankB.toString());
		
		System.out.println("States of Bank Computers (1 per Bank)");
		System.out.println(compA.toString()+ "\n" + compB.toString());
		
		System.out.println("\nStates of four ATMs (2 per Bank Computer)");
		System.out.println(ATM1_A.toString() + "\n"+ ATM2_A.toString()  +
				"\n"+ ATM1_B.toString() + "\n"+ ATM2_B.toString() +"\n");
		
		in.close();	
	}	
}
