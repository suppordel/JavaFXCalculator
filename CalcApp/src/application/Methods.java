package application;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class Methods {
	public static void main(String[] args)	{
		
	}
	
	/**
	 * Creates a Button. It will add the specified operation/number to the expression as well as display the updated expression.
	 * @param newInput this is what will be displayed on the button.
	 * @param inputList the ArrayList containing the expression.
	 * @param txt the Text that displays the expression.
	 * @return The Button created.
	 */
	Button createButton(String newInput, ArrayList<String> inputList, Text txt)	{
		Button btn = new Button(newInput);
		btn.setMinSize(30, 30);
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	//btn.setFont(Font.font(22));
	        	inputList.add(newInput);
	        	
		        String exp = "";
		       	for(String s:inputList)
		       		exp += s;
		       	
				txt.setText(exp);
		        }
		    };
		btn.setOnAction(eh);
		return btn;
	}
	
	/**
	 * Creates an operator Button. It will add the specified operation/number to the expression as well as display the updated expression. 
	 * Will also overwrite previous operator button press if pressed consecutively.
	 * @param newInput this is what will be displayed on the button.
	 * @param inputList the ArrayList containing the expression.
	 * @param txt the Text that displays the expression.
	 * @return The Button created.
	 */
	Button createOpButton(String newInput, ArrayList<String> inputList, Text txt)	{
		Button btn = new Button(newInput);
		btn.setMinSize(30, 30);
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	//btn.setFont(Font.font(22));
	        	if(inputList.size()==0)	{
	        		if(newInput.equals("+") || newInput.equals("-"))
	        			inputList.add(newInput);
	        		else
	        			return;
	        	}
	        	else {
	        		if(!isOp(inputList.get(inputList.size()-1).charAt(0)))
	        			inputList.add(newInput);
	        		else
	        			inputList.set(inputList.size()-1, newInput);
	        	}
		        String exp = "";
		       	for(String s:inputList)
		       		exp += s;
		       	
				txt.setText(exp);
		        }
		    };
		btn.setOnAction(eh);
		return btn;
	}
		
	/**
	 * Computes the coordinates of a number between 1 and 9 in a numpad.
	 * @param num the number to find coordinates for.
	 * @return the coordinate as an array (col, row}.
	 */
	int[] getNumpadCoord(int num)	{
		int row = 5 - (num-1)/3, 
				col = num%3;
			int[] coord = {col, row};
			
			switch(col)	{
			case(0):
				coord[0] = 2;
			break;
			case(1):
				coord[0] = 0;
			break;
			case(2):
				coord[0] = 1;
			break;
			}
			
			return coord;
	}
	
	int[] getOpCoord(String s)	{
		int[] coord = new int[2];
		switch(s)	{
		case("."):
			coord[0] = 2;
			coord[1] = 6;
			break;
		case("+"):
			coord[0] = 3;
			coord[1] = 4;
			break;
		case("-"):
			coord[0] = 3;
			coord[1] = 3;
			break;
		case("*"):
			coord[0] = 3;
			coord[1] = 2;
			break;
		case("/"):
			coord[0] = 2;
			coord[1] = 2;
			break;
		}
		return coord;
	}
	
	static boolean isOp(char s)	{
		 char[] opList = {'+', '-', '*', '/'};

		 for(char op:opList)	{
			 if(op==s)
				 return true;
			 else
				 continue;
		 }
		 return false;
	}
					
	static boolean isDigit(char s)	{
		 char[] numList = new char[11];
		 for(int i=0;i<=9;i++)	
			 numList[i] = (char) String.valueOf(i).charAt(0);
		 numList[10] = '.';
		 
		 for(char op:numList)	{
			 if(op==s)
				 return true;
			 else
				 continue;
		 }
		 return false;
	}

	
	int convertNum(ArrayList<Integer> input)	{
		int place = input.size()-1, suffix, convertedInt = 0;
		for(int i=0;i<input.size();i++)	{
			suffix = (int) Math.pow(10, place);
			convertedInt += input.get(i) * suffix;
			place--;
		}
		return convertedInt;
	}

	double compute(String exp)	{
			ArrayList<Double> numList = new ArrayList<>();
			ArrayList<String> opList = new ArrayList<>();
			String num = "0";
				
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

			numList.add(Double.valueOf(num));
			
			boolean hasNoOp = opList.size()==0;		
			double in1 = numList.get(0), in2 = 0;
			if(numList.size()>=2)	{
				in2 = numList.get(1);
			}
			
			if(hasNoOp)	{
				return in1;
			}	
			else	{
				String op = opList.get(0);
			
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
		}
	
	double trimZeros(double d)	{
		String s = String.valueOf(d);
		while(s.startsWith("0"))
			s.replaceFirst("0*", "");
		double dTrimmed = Double.parseDouble(s);
		return dTrimmed;
	}
	
	static boolean isInt(double d)	{
		String s = String.valueOf(d);
		return s.endsWith(".0");
	}
	
}