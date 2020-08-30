package application;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TwoOpsCalculator {

	public static void main(String[] args) {
		try	{
		Scanner sc = new Scanner(System.in);
		System.out.println("input math expression with up to 2 operations:");
		double in1 = sc.nextDouble();
		String op = sc.next();
		double in2 = sc.nextDouble();
		String op2 = sc.next();
		double in3  =  sc.nextDouble();
		
		double result1 = -97;
		double result2 = -97;
		boolean secondOp = false;
		
		boolean plusOrMinus = op.equals("+") || op.equals("-");
		//evaluates second operation first when appropriate
		//stores result in result2
		switch(op2)	{
		case "+": 
		break;
		case "-":
		break;
		case "*":
			if(plusOrMinus)	{
				result2 = in3*in2;
				secondOp = true;
			}
		break;
		case "**":
			if(!op.equals("**"))	{
				result2 = Math.pow(in2, in3);
				secondOp = true;
			}
		break;
		case "/":
			if(plusOrMinus)	{
				result2 = in2/in3;
				secondOp = true;
			}
		break;
		case "%":
			if(plusOrMinus)	{
				result2 = in2%in3;
				secondOp = true;
			}
		break;
		default:
			System.out.println("Second operation not supported.");					
		}

		//if result2 != -97, the second operation took place 
		switch(op)	{
		case "+": 	
			if(secondOp)
				System.out.println("Result is: " + (in1+result2));
			else
				result1 = in1 + in2;
		break;
		case "-":
			if(secondOp)
				System.out.println("Result is: " + (in1-result2));
			else
				result1 = in1 - in2;
		break;
		case "*":
			if(secondOp)
				System.out.println("result is: " + (in1*result2));
			else
				result1 = in1 * in2;
		break;
		case "**":
			result1 =  Math.pow(in1, in2);
			if(op2.equals("**"))
				System.out.println("result is: " + Math.pow(result1, in3));
		break;
		case "/":
			if(secondOp)
				System.out.println("result is: " + (in1/result2));
			else
				result1 = in1 / in2;
		break;
		case "%":
			if(secondOp)
				System.out.println("result is: " + (in1%result2));
			else
				result1 = in1 % in2;
		default:
			System.out.println("First operation not supported.");					
		}
		
		//if result1 != -97, the first operation took place
		//only operate if result2 == -97
		if(!secondOp)	{
		switch(op2)	{
		case "+": 
			System.out.println("result: "  + (result1+in3));
		break;
		case "-":
			System.out.println("result is: "  + (result1-in3));
		break;
		case "*":
			System.out.println("result: "  + (result1*in3));
		break;
		case "**":
			if(!op.contentEquals("**"))
				System.out.println("result is: "  + Math.pow(result1, in3));
		break;
		case "/":
			System.out.println("result is: "  + (result1/in3));
		break;
		case "%":
			System.out.println("result is: "  + (result1%in3));
		break;
		default:
			System.out.println("Second operation not supported.");					
			}
		}
		
		sc.close();
		System.out.println(secondOp);
		}	catch(InputMismatchException e)	{
			System.out.println("Error, the numbers and operators must be space separated.");
		}
	}
}