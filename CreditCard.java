/*
 * David Hernandez
 *	Date: 12/21/2021
 *	Credit Card Number
 * 
 * 
 */


import java.util.*;
import java.io.*;

public class CreditCard
{

	public static void main(String[] args) throws IOException
	{
		//Declare a File object, then open the file.
		 File file = new File("100CardNumbers.txt");   

		//Check if the file got opened. 
		//Your program will terminate if it cannot find the file
		 if (!file.exists())
		 {
		      System.out.println("The file cannot be found.");
		      System.exit(0);
		 }

		//Scanner to read file data
		 Scanner inputFile = new Scanner(file);
		 
		//while there is still data in the file, read it 
		 while(inputFile.hasNext())
		 {
			 //grab the data from the file and put it into your String:
		     String inputCard = inputFile.next();
		      
		     //if the length of the string is not 16, returns an error
		     if(inputCard.length() != 16)
		     {
		    	 //prints message for inputed number that doesnt have 16 digits
		    	 System.out.printf("The credit card number is: %s\n", inputCard);
		    	 System.out.println("ERROR! Number MUST have exactly 16 digits");
		    	 
		    	 //invoking method
		    	 convertBase18(inputCard);
		     }
		     //else the methods isValid and convertBase18 are invoked 
		     else
		     {
		    	 isValid(inputCard);
		    	 convertBase18(inputCard);
		     }
		          
		 }
		 
		//file close
		inputFile.close();
		
	}//END MAIN


		


//METHODS START HERE
	
	//method converts String of numbers to a long, then returns whether the check digit is what it should be (whether credit card is valid or not)
	public static void isValid(String cardNumInput)
	{
		//string to store inputed string argument
		String numbers = cardNumInput;
		
		//long will store cardNumber
		long cardNumber = 0;
		
		//will store stripped digit
		long remainder = 0;
	
		//will totalSum of all numbers after they've been properly adjusted
		long sumOfNums = 0;
		
		//counter to count times loop has ran(will decrement) 
		int counter = 15;
		
		//will store the last digit of the addition of numbers
		long lastDigitOfSum = 0;
		
		//will store the check digit of the inputed credit card
		long checkDigitCard = 0;
		
		//will store the what check digit SHOULD be
		long computedCheckDigit = 0;


		//for loop used to change creditCard number from String to long 
		for(int i = 0 ; i  < numbers.length() ; i++)
		{
			cardNumber += (long)Character.getNumericValue(numbers.charAt(i)) * Math.pow(10, 15 - i);
		}
		
		//stores the first number(check digit) of the inputed credit card
		checkDigitCard = cardNumber % 10;
		
		while(cardNumber > 0)
		{
			//remainder equals the number being stripped from current credit card number
			remainder = cardNumber % 10;
			//cardNumber is divided by 10 to remove the stripped number
			cardNumber = cardNumber / 10;
			
			//if loop is on an even occurrence and the remainder times 2 is less than 10, the product 
			//of remainder * 2 is added to sumOfNums
			if(counter % 2 ==0 && remainder * 2 < 10)
			{
				sumOfNums += remainder * 2;
			}
			//else if the loop is on an even occurrence but the remainder * 9 is greater than 9
			//the product of the remainder times 2 is subtracted 9 and added to sum of numbers
			else if(counter % 2 == 0 && remainder * 2 > 9)
			{
				sumOfNums += (remainder * 2) - 9;
			}
			//else if the loop is on an odd occurrence and not on the first occurrence(to avoid the first digit)
			//the remainder is added to the sum of numbers
			else if(counter % 2 != 0 && counter != 15)
			{
				sumOfNums += remainder;
			}
			
			//counter decreases
			counter--;
		}
		
		//after the numbers have been added, the last number of the sum is stripped
		lastDigitOfSum = sumOfNums % 10;
		
		
		//the computed check digit equals 10 minus the stripped number of the sum
		computedCheckDigit = 10 - lastDigitOfSum;
		
		//if the computed check digit is 10, that means the last digit of the card is 0(10 - 0)
		if(computedCheckDigit == 10)
		{
			computedCheckDigit = 0;
		}
			
		
		
	
		//prints credit card number, computed check digit, and the cards check digit
		System.out.printf("The credit card number is: %s\n", numbers);
		System.out.printf("Check digit should be: %d\n",computedCheckDigit);
		System.out.printf("Check digit is: %d\n", checkDigitCard);
		
		if(checkDigitCard == computedCheckDigit)
		{
			System.out.println("This is a valid Credit Card number.");
		}
		else
		{
			System.out.println("This is not a valid Credit Card Number.");
		}

	
	}//end method
	
	//takes a String of numbers and converts it to base 18
	public static void convertBase18(String creditCard) 
	{
		
		//FIELDS
		//holds the method's String argument
		String inputedCard = creditCard;
			
		//will store the String as a long once converted
		long cardNumber = 0;
			
		//remainder will hold the value of the stripped digit
		long remainder = 0;
			
		//counter will be used to identify the how many times the number can be divided by 18
		//in order to set the array of converted decimals to log base 18 without the array having extra spaces
		//if it were to be set to the length of the string
		int counter = 0;
			
		//this long will be used to store the creditCard number in order for it to be divided by 18 without changing the value of the
		//credit card number 
		long placeHolder;
			
		//declares a long array, the length will be set once the counter is incremented
		long[] base18;
		
		//values used in base18
		String valBase18 = "0123456789ABCDEFGH";
		
		
		
		//END FIELDS
			
			
		//for loop will move from the first integer in the String to the last,
		//the Character.getNumericValue method will get the value of the character at the last index of the string, 
		//the number will then be multiplied by 10 raised to the length of the string array -1 - i
		//example: array length is 16, first number will be multiplied by 10^ 16 - 1 - 15   
		//as the loop gets values from right to left, each number in multiplied by 10 to the power of its numerical position
		for(int i = inputedCard.length() - 1; i >= 0; i--)
		{
			cardNumber += (long)(Character.getNumericValue(inputedCard.charAt(i)) * Math.pow(10, inputedCard.length() -1 -i));
		}
			
		//placeholder value holds the same value as the computed card number
		placeHolder = cardNumber;
			
		//the place holder is divided by 18, counter increments,
		while(placeHolder > 0)
		{
			placeHolder = placeHolder / 18;
			counter++; 
		}
			
		//sets the length of the array to counter
		base18 = new long[counter];
			
		//loop divides card number by 18, stores the remainders in an array
		for(int i = 0; i < base18.length;i++)
		{
			remainder = cardNumber % 18;
			cardNumber = cardNumber/ 18;
			base18[i] = remainder;
				
				
		}
			
		//loop reads the array of remainders in reverse order, converts the decimal values to the letter associated with its number in log base 18
		System.out.print("The credit card number in base 18 is: ");
		for(int i = base18.length - 1; i >= 0 ;i--)
		{
			
			//the prints the char at the index of valBase18 that corresponds to the value of base18[i]
			System.out.printf("%c", valBase18.charAt((int)base18[i]));

		}
			
			//creates spacing for readability  
			System.out.println();
			System.out.println();
		
		}//end method
			

}
