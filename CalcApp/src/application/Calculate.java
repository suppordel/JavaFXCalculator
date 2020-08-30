package application;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Calculate {
	double compute(String exp)	{
		try	{
			ArrayList<Double> numList = new ArrayList<>();
			ArrayList<String> opList = new ArrayList<>();
			String num = "";
			
			if(exp.length()==0)
				numList.add((double) 0);
			
			if(exp.charAt(0)=='+' || exp.charAt(0)=='-' || isDigit(exp.charAt(0)))
				num += exp.charAt(0);
			
			for(int i=1;i<exp.length();i++)	{
				if(isDigit(exp.charAt(i)))
					num += exp.charAt(i);
				else if(isOp(exp.charAt(i)))	{
					opList.add(String.valueOf(exp.charAt(i)));
					
					numList.add(Double.valueOf(num));
					num = "";
				}
			}
			
			double result1 = -97;
			double result2 = -97;
			boolean secondOp = false, 
					hasFirstOp = false, hasSecondOp = false;
			
			double in1 = numList.get(0), in2 = 0, in3 = 0;
			if(numList.size()>=2)	{
				in2 = numList.get(1);
				if(numList.size()>2)	{
					in3 = numList.get(2);
				}
			}
			
			String op = "", op2 = "";
			if(opList.size()>=1)	{
				hasFirstOp = true;
				op = opList.get(0);
				if(opList.size()>=2)	{
					hasSecondOp = true;
					op2 = opList.get(1);
				}
			}	

			if(!hasFirstOp)	{
				return in1;
			}
			
			if(!hasSecondOp)	{
				boolean plusOrMinus = op.equals("+") || op.equals("-");
				
				switch(op)	{
				case "+": 
					return in1+in2;
				case "-":
					return in1-in2;
				case "*":
					return in1*in2;
				case "**":
					return Math.pow(in1, in2);
				case "/":
					return in1/in2;
				case "%":
					return in1%in2;
				default:
					return 0;					
				}
			}
			
			if(hasSecondOp)	{
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
						return in1+result2;
					else
						result1 = in1 + in2;
				break;
				case "-":
					if(secondOp)
						return in1-result2;
					else
						result1 = in1 - in2;
				break;
				case "*":
					if(secondOp)
						return in1*result2;
					else
						result1 = in1 * in2;
				break;
				case "**":
					result1 =  Math.pow(in1, in2);
					if(op2.equals("**"))
						return Math.pow(result1, in3);
				break;
				case "/":
					if(secondOp)
						return in1/result2;
					else
						result1 = in1 / in2;
				break;
				case "%":
					if(secondOp)
						return in1%result2;
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
					return result1+in3;
				case "-":
					return result1-in3;
				case "*":
					return result1*in3;
				case "**":
					if(!op.contentEquals("**"))
						return Math.pow(result1, in3);
				break;
				case "/":
					return result1/in3;
				case "%":
					return result1%in3;
				default:
					System.out.println("Second operation not supported.");					
					}
				}
			}
			

			
		}	catch(InputMismatchException e)	{
			System.out.println("Error, the numbers and operators must be space separated.");
		}	
		double patches = 0;
		return patches;
	}
}
