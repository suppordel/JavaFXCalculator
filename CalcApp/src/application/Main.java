package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Calculator");
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.TOP_CENTER);
			gp.setHgap(10);
			gp.setVgap(10);
			gp.setPadding(new Insets(25,25,25,25));
			//gp.getColumnConstraints().add(new ColumnConstraints(100));
		
			Scene scene = new Scene(gp,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Methods m = new Methods();
			ArrayList<String> input = new ArrayList<>();
			
				//title
			Text txt = new Text("Simple Calculator");
			txt.setFont(Font.font(STYLESHEET_MODENA, 20));
			gp.add(txt, 0, 0, 6, 2);
			GridPane.setHalignment(txt, HPos.CENTER);
			GridPane.setHgrow(txt, Priority.ALWAYS);
			
				//indices 0 to 9: numbers 0 to 9; indices 10 to 14: period, arithmetic symbols
			String[] numAndOps = new String[15];
			for(int i=0;i<=9;i++)	{
				numAndOps[i] = String.valueOf(i);
			}
			String[] opList = {".", "+", "-", "*", "/"};
			int numAndOpsIdx = 10;
			for(String op : opList)	{
				numAndOps[numAndOpsIdx] = op;
				numAndOpsIdx++;
			}
					
				//number buttons
			Button zeroBtn = m.createButton("0", input, txt);
			zeroBtn.setMinSize(60+gp.getHgap(), 30);
			gp.add(zeroBtn,0,6,2,1);
			for(int i=1;i<=9;i++)	{
				String numStr = numAndOps[i];
				Button numBtn = m.createButton(numStr, input, txt);
				int[] coord = m.getNumpadCoord(i);
				gp.add(numBtn,coord[0],coord[1]);
			}
				
				//operation buttons
			for(int i=10;i<15;i++)	{
				String opStr = numAndOps[i];
				Button opBtn = m.createOpButton(opStr, input, txt);
				int[] coord = m.getOpCoord(opStr);
				gp.add(opBtn,coord[0], coord[1]);
			}
	
					//equal button
			Button equalBtn = new Button("=");
			equalBtn.setMinSize(30, 60+gp.getVgap());
	        equalBtn.setOnAction(new EventHandler<ActionEvent>() {	 
	            @Override
	            public void handle(ActionEvent event) {
	            	String expression = "";
			       	for(String s:input)
			       		expression += s;
			       	
	            	double result = m.compute(expression);
	            	String resultStr = String.valueOf(result);
	            	if(resultStr.endsWith(".0"))	{
	            		resultStr = resultStr.substring(0, resultStr.lastIndexOf('.'));
	            	}
	            	txt.setText(resultStr);
	            	
	            	input.clear();
	            	char[] expArray = resultStr.toCharArray();
	            	for(char ch:expArray)
	            		input.add(String.valueOf(ch));
	            }
	        });
			gp.add(equalBtn, 3,5,1,2);
			
					//clear button
			Button clearBtn = new Button("C");
			clearBtn.setMinSize(30, 30);
	        clearBtn.setOnAction(new EventHandler<ActionEvent>() {	 
	            @Override
	            public void handle(ActionEvent event) {
	            	input.clear();
	            	txt.setText("");
	            }
	        });
			gp.add(clearBtn, 0, 2);
			
					//backspace button
			Button bkspBtn = new Button("BS");
			bkspBtn.setMaxSize(30, 30);
			bkspBtn.setMinSize(30, 30);
		    bkspBtn.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		        	input.remove(input.size()-1);
		        	
			        String exp = "";
			       	for(String s:input)
			       		exp += s;
			       	
					txt.setText(exp);
		        }
		    });
			gp.add(bkspBtn, 1, 2);
			
		
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
