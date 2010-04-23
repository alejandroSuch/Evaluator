package such.alejandro.evaluator;

import java.util.EmptyStackException;
import java.util.Stack;

public class Operation {
	public static String execute(Stack<Token>valores, OperationType tipo) throws Exception{
		try{
			if(OperationType.ADD.equals(tipo)){
				double secondValue = Double.parseDouble(valores.pop().getValue());
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(firstValue+secondValue);
			}
			
			if(OperationType.SUB.equals(tipo)){
				double secondValue = Double.parseDouble(valores.pop().getValue());
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(firstValue-secondValue);
			}
			
			if(OperationType.MULT.equals(tipo)){
				double secondValue = Double.parseDouble(valores.pop().getValue());
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(firstValue*secondValue);
			}
			
			if(OperationType.DIV.equals(tipo)){
				double secondValue = Double.parseDouble(valores.pop().getValue());
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(firstValue/secondValue);
			}
			
			if(OperationType.SQRT.equals(tipo)){
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(Math.sqrt(firstValue));
			}
			
			if(OperationType.POW.equals(tipo)){
				double secondValue = Double.parseDouble(valores.pop().getValue());
				double firstValue = Double.parseDouble(valores.pop().getValue());
				return Double.toString(Math.pow(firstValue, secondValue));
			}
			
			return null;
		} catch (EmptyStackException e) {
			throw new Exception("Error. Missing arguments on the operation");
		}
	}
}
