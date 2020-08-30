package application;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> numList = new ArrayList<>();
		ArrayList<String> ops = new ArrayList<>();
		String num = "";
		
		String exp = "124.3+1";
		if(exp.charAt(0)=='+' || exp.charAt(0)=='-' || isDigit(exp.charAt(0)))
			num += exp.charAt(0);
		for(int i=1;i<exp.length();i++)	{
			if(isDigit(exp.charAt(i)))
				num += exp.charAt(i);
			else if(isOp(exp.charAt(i)))	{
				numList.add(Integer.valueOf(num));
				num = "";
			}

	}

}
}