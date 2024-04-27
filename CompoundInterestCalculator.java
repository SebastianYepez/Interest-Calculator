/**
 * Created by Sebastian Yepez
 */

import java.util.Scanner;

public class CompoundInterestCalculator {
    private static Scanner userResponse = new Scanner(System.in);
    private static double PrinAmount;
    private static double MonthlyAmount;
    private static double InterestRate;
    private static double CompoundFreq;
    private static double LengTime;
    private static double FinAmount;

    public static void main(String[] args){
        int choice = 0;
        do {
            System.out.println("Hello! What kind of interest are you trying to look into? Please enter a number: " +
                    "\nSavings w/ no contributions[1] Savings w/ monthly contributions[2] Loan with an approx APR[3] Years to double my investment[4] Quit[0]");
            choice = userResponse.nextInt();
            if (choice == 0) continue;
            if (choice == 1) {
                noContributions();
            }
            else if (choice == 2) {
                monthlyContributions();
            }
            else if (choice == 3) {
                loan();
            }
            else if (choice == 4) {
                yearsToDouble();
            }
            else {
                System.out.println("Not a valid input");
            }
        }
        while (choice != 0);
        System.out.println("Thank you for choosing to use my calculator(s)!");
    }

    private static void noContributions() {
        System.out.println("\nSavings with No Contributions: This means that you put an investment into an account and never touch it until you take it out.");
        System.out.println("Principal Amount (Initial Investment): ");
        PrinAmount = userResponse.nextDouble();
        System.out.println("Interest Rate (APR as a Percentage(Ex: 3.5): ");
        InterestRate = userResponse.nextDouble();
        System.out.println("Compound Frequency (Number of Times Compounded Per Year(Ex: Monthly = 12)): ");
        CompoundFreq = userResponse.nextDouble();
        System.out.println("Length of Time (Number of Years): ");
        LengTime = userResponse.nextDouble();
        FinAmount = PrinAmount*Math.pow((1+(InterestRate/100)/CompoundFreq),(CompoundFreq*LengTime));
        System.out.println("FINAL AMOUNT: $" + returnMoneyAmount(FinAmount));
        System.out.println("Principal Amount: $" + returnMoneyAmount(PrinAmount));
        System.out.println("Total Interest: $" + returnMoneyAmount(FinAmount-PrinAmount));
        System.out.println();
    }

    private static void monthlyContributions() {
        System.out.println("\nSavings with Monthly Contributions: This means that you put an investment into an account and actively invest a " +
                "set amount of money each month for a certain time period.");
        System.out.println("Principal Amount (Initial Investment): ");
        PrinAmount = userResponse.nextDouble();
        System.out.println("Monthly Contributions: ");
        MonthlyAmount = userResponse.nextDouble();
        System.out.println("Interest Rate (APR as a Percentage(Ex: 3.5): ");
        InterestRate = userResponse.nextDouble();
        System.out.println("Compound Frequency (Number of Times Compounded Per Year(Ex: Monthly = 12)): ");
        CompoundFreq = userResponse.nextDouble();
        System.out.println("Length of Time (Number of Years): ");
        LengTime = userResponse.nextDouble();
        FinAmount = PrinAmount;
        double totContribution = PrinAmount;
        for (int i = 0; i < LengTime*CompoundFreq; i++){
            FinAmount *= (1+(InterestRate/100)/CompoundFreq);
            FinAmount += MonthlyAmount*(12/CompoundFreq);
            totContribution += MonthlyAmount*(12/CompoundFreq);
        }
        System.out.println("FINAL AMOUNT: $" + returnMoneyAmount(FinAmount));
        System.out.println("Principal Amount: $" + returnMoneyAmount(PrinAmount));
        System.out.println("Total Contributions: $" + returnMoneyAmount(totContribution));
        System.out.println("Total Interest: $" + returnMoneyAmount(FinAmount-totContribution));
        System.out.println();
    }

    private static void loan() {
        System.out.println("\nLoan: This means that you're getting a loan at a set annual interest rate. Ex: Mortgage of $250,000 for 30 years at a 3% APR");
        System.out.println("Loan Amount: ");
        PrinAmount = userResponse.nextDouble();
        System.out.println("Interest Rate (APR as a Percentage(Ex: 3.5)): ");
        InterestRate = userResponse.nextDouble();
        System.out.println("Length of Time (Number of Years): ");
        LengTime = userResponse.nextDouble();
        FinAmount = PrinAmount*Math.pow((1+(InterestRate/100)),(LengTime));
        System.out.println("\nFINAL AMOUNT OF PAYMENTS: $" + returnMoneyAmount(FinAmount));
        System.out.println("Principal Amount: $" + returnMoneyAmount(PrinAmount));
        System.out.println("Interest: $" + returnMoneyAmount(FinAmount-PrinAmount));
        System.out.println("Monthly Payments: $" + returnMoneyAmount(FinAmount/LengTime/12));
        System.out.println("Interest Payment Per Month: $" + returnMoneyAmount((FinAmount/LengTime/12)-(PrinAmount/LengTime/12)));
        System.out.println();
    }

    private static void yearsToDouble() {
        //using formula x(r)^t = 2x
        //r^t = 2 -> log"base"r(2) = log(2)/log(r) = t
        System.out.println("\nThis calculator will tell you how many years it will take to double your investment if you leave it alone");
        System.out.println("What is the interest rate of your investment? (Ex: 7)");
        InterestRate = userResponse.nextDouble()/100 + 1;
        LengTime = Math.log(2)/Math.log(InterestRate);
        System.out.println("It will take you "+LengTime+" years to double your investment.");
        System.out.println();
    }

    private static double returnMoneyAmount(double amount){
        amount *= Math.pow(10, 2);
        amount = Math.floor(amount);
        amount /= Math.pow(10, 2);
        return amount;
    }

}
